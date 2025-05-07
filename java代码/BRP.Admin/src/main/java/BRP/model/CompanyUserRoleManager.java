package BRP.model;

import BRP.data.CompanyRole;
import BRP.data.CompanyUserRole;
import BRP.data.CompanyUserRoleMaster;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.util.JsonHelper;

import java.util.List;

public class CompanyUserRoleManager extends CompanyUserRoleMaster {
    private static CompanyUserRoleManager _instance;

    private CompanyUserRoleManager() {
        this.setTableName("company_user_role");
        this.setTypeName("CompanyUserRole");
    }

    public static CompanyUserRoleManager getInstance() {
        if (_instance == null) {
            _instance = new CompanyUserRoleManager();
        }
        return _instance;
    }

    public JSONObject getJSONArrayByCompanyUserId(int companyUserId, int companyId) throws Exception {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            // 获取操作人员的角色
            List<CompanyUserRole> lstCompanyUserRole = CompanyUserRoleManager.getInstance().getList("company_user_id=" + companyUserId);
            //查询当前商户下的角色并排除超级管理员角色
            String condition = "company_id=" + companyId + " and name<>'超级管理员'";
            // 获取全部角色
            List<CompanyRole> lstCompanyRoles = CompanyRoleManager.getInstance().getList(condition);
            JSONArray jarrCompanyRole = JsonHelper.toJsonArray(lstCompanyRoles);
            // 设置该角色拥有的权限
            jarrCompanyRole = this.setJSONArrayUserRole(lstCompanyUserRole, jarrCompanyRole);
            JSONObject jResult = new JSONObject();
            jResult.put("dataList", jarrCompanyRole);
            return jResult;
        } catch (Exception e) {
            e.printStackTrace();
            if (null != sqlSession) {
                sqlSession.rollback();
            }
            throw e;
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }
    }

    public JSONArray setJSONArrayUserRole(List<CompanyUserRole> lstCompanyUserRole, JSONArray jarrCompanyRole) {
        for (int i = 0; i < jarrCompanyRole.size(); i++) {
            Boolean flag = hasPermission(lstCompanyUserRole, jarrCompanyRole.getJSONObject(i).getString("id"));
            jarrCompanyRole.getJSONObject(i).put("checked", flag);
        }
        return jarrCompanyRole;
    }

    /**
     * 该项角色是否在角色列表中
     *
     * @param lstCompanyUserRole
     * @param permission
     * @return
     */
    public Boolean hasPermission(List<CompanyUserRole> lstCompanyUserRole, String companyRoleId) {
        if (null == lstCompanyUserRole) {
            return false;
        }
        for (int i = 0; i < lstCompanyUserRole.size(); i++) {
            Integer currentPermission = lstCompanyUserRole.get(i).getCompanyRoleId();
            if (currentPermission.toString().equals(companyRoleId)) {
                return true;
            }
        }
        return false;
    }

    /*
     * 多项添加
     */
    public int add(int companyUserId, List<Integer> lstValues) throws Exception {
        String[] sqls = new String[lstValues.size() + 1];
        sqls[0] = String.format("delete from company_user_role where company_user_id = %s " +
                        "and company_role_id not in (SELECT id from company_role where name='超级管理员' and company_id=(SELECT company_id from company_user WHERE id = %s))"
                , companyUserId, companyUserId);
        for (int i = 0; i < lstValues.size(); i++) {
            Integer value = lstValues.get(i);
            sqls[i + 1] = String.format("insert into company_user_role (company_user_id,company_role_id) values (%s, %s)",
                    companyUserId, value);
        }
        int result = MyBatisManager.getInstance().executeUpdate(sqls);
        return result;
    }
}