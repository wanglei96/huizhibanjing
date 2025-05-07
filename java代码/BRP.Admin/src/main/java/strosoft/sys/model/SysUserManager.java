package strosoft.sys.model;

import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.EncryptManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.ArrayListHelper;
import strosoft.sys.data.SysUser;
import strosoft.sys.data.SysUserMaster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * 用户管理类
 */
public class SysUserManager extends SysUserMaster {
    private static SysUserManager _instance;

    public static SysUserManager getInstance() {
        if (_instance == null) {
            _instance = new SysUserManager();
        }
        return _instance;
    }

    private SysUserManager() {
        this.setTableName("sys_user");
        this.setTypeName("SysUser");
    }

    /*
     * 检查登录账号是否存在
     */
    public boolean existsLoginName(String loginName) throws Exception {
        return MyBatisManager.getInstance().exists(this.getTableName(), "login_name", loginName);
    }

    /*
     * 取得实体
     */
    public SysUser getEntity(int sysUserID) throws Exception {
        return (SysUser) super.getDataEntity(sysUserID);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<SysUser> getList() throws IOException {
        List<Object> lstDataEntity = super.getObjectList();
        return super.toList(lstDataEntity);
    }

    /*
     * 根据登陆名称查询信息
     */
    public SysUser getEntityByLoginName(String loginName) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {

            String typeName = this.getTypeName();
            String sqlCommandID = typeName + ".select" + typeName + "ByLoginName";
            SysUser theSysUser = (SysUser) sqlSession.selectOne(sqlCommandID, loginName);
            return theSysUser;
        } finally {
            sqlSession.close();
        }
    }

    /*
     * 修改密码
     */
    public int setSysUserPassword(int sysUserId, String password) throws Exception {
        String sql = String.format("update %s set password = ('%s') where id = (%s)", this.getTableName(),
                EncryptManager.encryptPassword(password), sysUserId);
        int result = MyBatisManager.getInstance().executeUpdate(sql);
        return result;
    }

    /*
     * 设置启用状态
     */
    public int setSysUserEnabled(int sysUserId, Boolean enabled) throws Exception {
        String sql = String.format("update %s set enabled = (%s),wrong_password_count = 0 where id = (%s)", this.getTableName(),
                enabled ? "1" : "0", sysUserId);
        int result = MyBatisManager.getInstance().executeUpdate(sql);
        return result;
    }

    /**
     * 根据组织机构删除成员
     *
     * @param sqlSession
     * @param sysOrganizationIdLists
     * @return
     */
    public int deleteBySysOrganizationIdList(SqlSession sqlSession, List<Integer> sysOrganizationIdLists) {
        if (null == sysOrganizationIdLists || sysOrganizationIdLists.size() == 0) {
            return 0;
        }
        String sysOrganizationIdListStr = ArrayListHelper.intListToString(sysOrganizationIdLists, ",");
        String sql = "delete from sys_user where sys_organization_id in(" + sysOrganizationIdListStr + ")";
        return sqlSession.delete("SysUser.deleteBysql", sql);
    }

    /**
     * 增加错误密码次数
     */
    public void increaseWrongPasswordCount(int sysUserId) throws Exception {
        SysUser theSysUser = this.getEntity(sysUserId);
        int newWrongPasswordCount = 1;
        if (theSysUser.getWrongPasswordCount() != null) {
            newWrongPasswordCount = theSysUser.getWrongPasswordCount() + 1;
        }
        theSysUser.setWrongPasswordCount(newWrongPasswordCount);
        int maxWrongPasswordCount = SysSettingManager.getInstance().getIntSetting("MaxWrongPasswordCount", 5);
        if(newWrongPasswordCount >= maxWrongPasswordCount){
            theSysUser.setEnabled( false);
        }
        this.update(theSysUser);
    }

	/**
	 * 清空错误密码次数
	 * @param sysUserId
	 * @throws Exception
	 */
    public void clearWrongPasswordCount(Integer sysUserId) throws Exception {
        SysUser theSysUser = this.getEntity(sysUserId);
        theSysUser.setWrongPasswordCount(0);
        this.update(theSysUser);
    }
}
