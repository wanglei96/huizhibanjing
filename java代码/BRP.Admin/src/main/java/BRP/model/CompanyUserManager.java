package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.EncryptManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.util.StringHelper;
import strosoft.sys.data.SysUser;
import strosoft.sys.model.SysSettingManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CompanyUserManager extends CompanyUserMaster {
    private static CompanyUserManager _instance;
    public static Hashtable<Integer, String> storeRandomMap = new Hashtable<>();

    private CompanyUserManager() {
        this.setTableName("company_user");
        this.setTypeName("CompanyUser");
    }

    public static CompanyUserManager getInstance() {
        if (_instance == null) {
            _instance = new CompanyUserManager();
        }
        return _instance;
    }

    /*
     * 设置启用状态
     */
    public int setCompanyUserEnabled(int companyUserId, Boolean enabled) throws Exception {
        String sql = String.format("update %s set enabled = (%s), wrong_password_count=0 where id = (%s)", this.getTableName(),
                enabled ? "1" : "0", companyUserId);
        int result = MyBatisManager.getInstance().executeUpdate(sql);
        return result;
    }

    public CompanyUser getEntityByCompanyIdAndLoginName(SqlSession session, Integer companyId, String loginName) {
        MySqlConditionBuilder msc = new MySqlConditionBuilder();
        msc.addEqualCondition("company_id", companyId);
        msc.addEqualCondition("login_name", loginName);
        String condition = msc.toString();
        List<CompanyUser> companyUserList = CompanyUserManager.getInstance().getList(session, condition);
        if (companyUserList.size() > 0) {
            return companyUserList.get(0);
        }
        return null;
    }

    /**
     * 修改密码
     *
     * @param companyUserId
     * @param newPassword
     */
    public int setCompanyUserPassword(int companyUserId, String newPassword) throws Exception {
        String sql = String.format("update %s set password = ('%s') where id = (%s)", this.getTableName(),
                EncryptManager.encryptPassword(newPassword), companyUserId);
        int result = MyBatisManager.getInstance().executeUpdate(sql);
        return result;
    }

    /**
     * 获取门店超级管理员用户
     *
     * @param session
     * @param companyId
     * @return
     * @throws Exception
     */
    public CompanyUser getCompanyAdminUser(SqlSession session, Integer companyId) throws Exception {
        String sql = "SELECT id from company_user where id = (SELECT company_user_id from company_user_role where company_role_id in (SELECT id from company_role where name='超级管理员' and company_id="
                + companyId + ") LIMIT 1)";
        ArrayList<LinkedHashMap<String, Object>> linkedHashMaps = MyBatisManager.getInstance().executeHashMapList(session, sql);
        if (linkedHashMaps.isEmpty()) {
            throw new Exception("未查询到超级管理员用户");
        }
        CompanyUser theCompanyUser = CompanyUserManager.getInstance().getEntity(session, (Integer) linkedHashMaps.get(0).get("id"));
        return theCompanyUser;
    }

    /**
     * 增加错误密码次数
     */
    public void increaseWrongPasswordCount(int companyUserId) throws Exception {
        CompanyUser theCompanyUser = this.getEntity(companyUserId);
        int newWrongPasswordCount = 1;
        if (theCompanyUser.getWrongPasswordCount() != null) {
            newWrongPasswordCount = theCompanyUser.getWrongPasswordCount() + 1;
        }
        theCompanyUser.setWrongPasswordCount(newWrongPasswordCount);
        //如果密码错误次数大于等于最大密码次数，则锁定用户
        int maxWrongPasswordCount = SysSettingManager.getInstance().getIntSetting("MaxWrongPasswordCount", 5);
        if (newWrongPasswordCount >= maxWrongPasswordCount) {
            theCompanyUser.setEnabled(false);
        }
        this.update(theCompanyUser);
    }

    /**
     * 清空错误密码次数
     *
     * @param companyUserId
     * @throws Exception
     */
    public void clearWrongPasswordCount(Integer companyUserId) throws Exception {
        CompanyUser theCompanyUser = this.getEntity(companyUserId);
        theCompanyUser.setWrongPasswordCount(0);
        this.update(theCompanyUser);
    }

    public ArrayList<LinkedHashMap<String, Object>> getDelivererTotalData(Integer companyId, String deliverLineName, String name, String deliverTimeBegin, String deliverTimeEnd, Integer pageIndex, Integer pageSize, String orderBy) throws Exception {
        String sqlLimit = "";
        if (pageSize > 0) {
            sqlLimit = " limit " + String.valueOf(pageIndex * pageSize) + "," + pageSize;
        }
        String sql = String.format("SELECT cu.id, cu.name, cu.company_id,\n" +
                        "       deliver_lines.deliver_line_names_and_counts,\n" +
                        "       COALESCE(deliver_lines.total_order_count, 0) AS total_order_count\n" +
                        "FROM view_company_user cu\n" +
                        "LEFT JOIN (\n" +
                        "    SELECT deliverer_id,\n" +
                        "           GROUP_CONCAT(CONCAT(deliver_line_name, '（', order_count, '）') SEPARATOR ', ') AS deliver_line_names_and_counts,\n" +
                        "           SUM(order_count) AS total_order_count\n" +
                        "    FROM (\n" +
                        "        SELECT deliverer_id, deliver_line_name, COUNT(*) AS order_count\n" +
                        "        FROM view_book_order\n" +
                        "        WHERE book_order_borrow_status_code IN ('Received', 'ErrorProcessing') %s %s\n" +
                        "        GROUP BY deliverer_id, deliver_line_name\n" +
                        "    ) subquery\n" +
                        "    GROUP BY deliverer_id\n" +
                        ") deliver_lines ON cu.id = deliver_lines.deliverer_id\n" +
                        "WHERE cu.is_deliverer = 1 AND cu.company_id = %d  %s ORDER BY %s  %s;",
                (!StringHelper.isNullOrEmpty(deliverTimeBegin) && !StringHelper.isNullOrEmpty(deliverTimeEnd))
                        ? "AND deliver_time BETWEEN '" + deliverTimeBegin + "' AND '" + deliverTimeEnd + "'"
                        : "",
                (!StringHelper.isNullOrEmpty(deliverLineName)) ? "AND deliver_line_name LIKE '%" + deliverLineName + "%'" : "",
                companyId,
                (!StringHelper.isNullOrEmpty(name)) ? "AND cu.name LIKE '%" + name + "%'" : "",
                orderBy, sqlLimit);
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
        return alData;
    }

    public ArrayList<LinkedHashMap<String, Object>> getBookpickerTotalData(Integer companyId, String deliverLineName, String name, String deliverTimeBegin, String deliverTimeEnd, Integer pageIndex, Integer pageSize, String orderBy) throws Exception {
        String sqlLimit = "";
        if (pageSize > 0) {
            sqlLimit = " limit " + String.valueOf(pageIndex * pageSize) + "," + pageSize;
        }
        String sql = String.format("SELECT cu.id, cu.name, cu.company_id,\n" +
                        "       deliver_lines.deliver_line_names_and_counts,\n" +
                        "       COALESCE(deliver_lines.total_order_count, 0) AS total_order_count\n" +
                        "FROM view_company_user cu\n" +
                        "LEFT JOIN (\n" +
                        "    SELECT bookpicker_id,\n" +
                        "           GROUP_CONCAT(CONCAT(deliver_line_name, '（', order_count, '）') SEPARATOR ', ') AS deliver_line_names_and_counts,\n" +
                        "           SUM(order_count) AS total_order_count\n" +
                        "    FROM (\n" +
                        "        SELECT bookpicker_id, deliver_line_name, COUNT(DISTINCT book_order_id) AS order_count\n" +
                        "        FROM view_book_out_order\n" +
                        "        WHERE book_out_order_status_code = 'Found' and is_offline is not true %s %s\n" +
                        "        GROUP BY bookpicker_id, deliver_line_name\n" +
                        "    ) subquery\n" +
                        "    GROUP BY bookpicker_id\n" +
                        ") deliver_lines ON cu.id = deliver_lines.bookpicker_id\n" +
                        "WHERE cu.is_bookpicker = 1 AND cu.company_id = %d %s ORDER BY %s %s",
                (!StringHelper.isNullOrEmpty(deliverTimeBegin) && !StringHelper.isNullOrEmpty(deliverTimeEnd))
                        ? "AND deliver_time BETWEEN '" + deliverTimeBegin + "' AND '" + deliverTimeEnd + "'"
                        : "",
                (!StringHelper.isNullOrEmpty(deliverLineName)) ? "AND deliver_line_name LIKE '%" + deliverLineName + "%'" : "",
                companyId,
                (!StringHelper.isNullOrEmpty(name)) ? "AND cu.name LIKE '%" + name + "%'" : "",
                orderBy, sqlLimit);
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
        return alData;
    }

    public String getGuid(Integer companyId) {
        String guid = UUID.randomUUID().toString();
        storeRandomMap.put(companyId, guid);
        return guid;
    }

    /**
     * 解锁超级管理员
     * @param companyId
     */
    public void unlockCompanyNumber(Integer companyId) throws Exception {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            CompanyUser companyUser =this.getCompanyAdminUser(sqlSession, companyId);
            companyUser.setEnabled(true);
            companyUser.setWrongPasswordCount(0);
            this.update(sqlSession, companyUser);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new Exception(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }
}