package strosoft.sys.model;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.MyBatisManager;

/*
 * 权限管理类
 */
public class PermissionManager {
    private static PermissionManager _instance;

    public static PermissionManager getInstance() {
        if (_instance == null) {
            _instance = new PermissionManager();
        }
        return _instance;
    }

    private PermissionManager() {
    }

    /*
     * 重置超级管理员权限
     */
    public void resetAdminPermissions() throws Exception {
        String[] sqls = new String[]{ // 删除菜单中没有的功能
                "delete from sys_function where resource not in (select value from sys_menu)",
                // 删除菜单和功能中 没有的角色权限
                "delete from sys_role_permission "
                        + "where permission not in (select Value as permission from sys_menu union select code as permission from sys_function)",
                // 添加超级管理员用户
                "insert into sys_user(true_name, login_name, password) "
                        + "select '超级管理员', 'Admin', '21232F297A57A5A743894A0E4A801FC3' " + "from dual "
                        + "where not exists(select ID from sys_user where login_name='Admin')",
                // 添加超级管理员角色
                "insert into sys_role(name) " + "select '超级管理员' " + "from dual "
                        + "where  not exists(select ID from sys_role where name='超级管理员')",
                // 添加超级管理员用户与角色关系
                "insert into sys_user_role (sys_user_id, sys_role_id) " + "select "
                        + "(select ID from sys_user where  login_name = 'Admin') as sys_user_id, "
                        + "(select ID from sys_role where Name='超级管理员') AS sys_role_id " + "from dual "
                        + "where not exists(select * from sys_user_role where sys_user_id =  (select ID from sys_user where  login_name = 'Admin') and sys_role_id = (select ID from sys_role where Name='超级管理员'))",
                // 删除超级管理员角色权限
                "delete from sys_role_permission " + "where sys_role_id = (select ID from sys_role where Name='超级管理员')",
                // 增加超级管理员角色菜单权限
                "INSERT INTO sys_role_permission (sys_role_id,permission) "
                        + "select (select id from sys_role where name='超级管理员') as AdminRoleId, value from sys_menu",
                // 增加超级管理员角色功能权限
                "INSERT INTO sys_role_permission (sys_role_id,permission) "
                        + "select (select id from sys_role where name='超级管理员') as AdminRoleId, code from sys_function"};
        MyBatisManager.getInstance().executeUpdate(sqls);
    }

    /*
     * 重置超级管理员权限
     */
    public void resetAdminPermissions(SqlSession sqlSession) throws Exception {
        String[] sqls = new String[]{ // 删除菜单中没有的功能
                "delete from sys_function where resource not in (select value from sys_menu)",
                // 删除菜单和功能中没有的角色权限
                "delete from sys_role_permission "
                        + "where permission not in (select Value as permission from sys_menu union select code as permission from sys_function)",
                // 添加超级管理员用户
                "insert into sys_user(true_name, login_name, password) "
                        + "select '超级管理员', 'Admin', '21232F297A57A5A743894A0E4A801FC3' " + "from dual "
                        + "where not exists(select ID from sys_user where login_name='Admin')",
                // 添加超级管理员角色
                "insert into sys_role(name) " + "select '超级管理员' " + "from dual "
                        + "where  not exists(select ID from sys_role where name='超级管理员')",
                // 添加超级管理员用户与角色关系
                "insert into sys_user_role (sys_user_id, sys_role_id) " + "select "
                        + "(select ID from sys_user where  login_name = 'Admin') as sys_user_id, "
                        + "(select ID from sys_role where Name='超级管理员') AS sys_role_id " + "from dual "
                        + "where not exists(select * from sys_user_role where sys_user_id =  (select ID from sys_user where  login_name = 'Admin') and sys_role_id = (select ID from sys_role where Name='超级管理员'))",
                // 删除超级管理员角色权限
                "delete from sys_role_permission " + "where sys_role_id = (select ID from sys_role where Name='超级管理员')",
                // 增加超级管理员角色菜单权限
                "INSERT INTO sys_role_permission (sys_role_id,permission) "
                        + "select (select id from sys_role where name='超级管理员') as AdminRoleId, value from sys_menu",
                // 增加超级管理员角色功能权限
                "INSERT INTO sys_role_permission (sys_role_id,permission) "
                        + "select (select id from sys_role where name='超级管理员') as AdminRoleId, code from sys_function"};
        MyBatisManager.getInstance().executeUpdate(sqlSession, sqls);
    }

    /**
     * 重置所有门店超级管理员权限
     *
     * @throws Exception
     */
    public void resetAllCompanyAdminPermissions() throws Exception {
        String[] sqls = new String[]{
                // XXX 目前只能重置菜单权限
                // TODO 删除菜单中没有的功能
                //"delete from sys_function where resource not in (select value from sys_menu)",
                // 删除菜单和功能中 没有的角色权限
                "delete from company_role_permission where permission not in (select value as permission from sys_menu)",
                // 添加超级管理员角色
                "insert into company_role(name,create_time,company_id) " +
                        "SELECT '超级管理员',NOW(),id from company where id not in (SELECT company_id from company_role where name='超级管理员')",
                // 添加超级管理员用户与角色关系
                "insert into company_user_role (company_user_id, company_role_id) \n" +
                        "SELECT id company_user_id ,(select id from company_role where name='超级管理员' and company_id=company_user.company_id) AS company_role_id from company_user\n" +
                        "where company_user.id in (SELECT id from company_user where login_name in (SELECT login_name FROM company)) \n" +
                        "and not EXISTS (SELECT * from company_user_role where company_user_id=company_user.id and company_role_id= (select id from company_role where name='超级管理员' and company_id=company_user.company_id))",
                // 删除超级管理员角色权限
                "DELETE from company_role_permission where company_role_id in (select id from company_role where name='超级管理员')",
                // 增加超级管理员角色菜单权限
                "INSERT INTO company_role_permission (company_role_id,permission) " +
                        "SELECT * from (SELECT company_role.id,sys_menu.`value` from company_role CROSS JOIN sys_menu where company_role.`name`='超级管理员' " +
                        "and sys_menu.sys_menu_type_code='Company') as role_menu order by id"};
        MyBatisManager.getInstance().executeUpdate(sqls);
    }

    /**
     * 重置所有门店超级管理员权限
     *
     * @param sqlSession
     * @throws Exception
     */
    public void resetAllCompanyAdminPermissions(SqlSession sqlSession) throws Exception {
        String[] sqls = new String[]{
                // XXX 目前只能重置菜单权限
                // TODO 删除菜单中没有的功能
                //"delete from sys_function where resource not in (select value from sys_menu)",
                // 删除菜单和功能中 没有的角色权限
                "delete from company_role_permission where permission not in (select value as permission from sys_menu)",
                // 添加超级管理员角色
                "insert into company_role(name,create_time,company_id) " +
                        "SELECT '超级管理员',NOW(),id from company where id not in (SELECT company_id from company_role where name='超级管理员')",
                // 添加超级管理员用户与角色关系
                "insert into company_user_role (company_user_id, company_role_id) \n" +
                        "SELECT id company_user_id ,(select id from company_role where name='超级管理员' and company_id=company_user.company_id) AS company_role_id from company_user\n" +
                        "where company_user.id in (SELECT id from company_user where login_name in (SELECT login_name FROM company)) \n" +
                        "and not EXISTS (SELECT * from company_user_role where company_user_id=company_user.id and company_role_id= (select id from company_role where name='超级管理员' and company_id=company_user.company_id))",
                // 删除超级管理员角色权限
                "DELETE from company_role_permission where company_role_id in (select id from company_role where name='超级管理员')",
                // 增加超级管理员角色菜单权限
                "INSERT INTO company_role_permission (company_role_id,permission) " +
                        "SELECT * from (SELECT company_role.id,sys_menu.`value` from company_role CROSS JOIN sys_menu where company_role.`name`='超级管理员' " +
                        "and sys_menu.sys_menu_type_code='Company') as role_menu order by id"};
        MyBatisManager.getInstance().executeUpdate(sqlSession, sqls);
    }
}
