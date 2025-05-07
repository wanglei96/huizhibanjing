package BRP.model;
import BRP.data.CompanyRolePermissionMaster;
import strosoft.app.common.MyBatisManager;

import java.util.List;

public class CompanyRolePermissionManager extends CompanyRolePermissionMaster
{
    private static CompanyRolePermissionManager _instance;
    private CompanyRolePermissionManager()    
    {
        this.setTableName("company_role_permission");
        this.setTypeName("CompanyRolePermission");
    }
    public  static CompanyRolePermissionManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanyRolePermissionManager();
        }
        return _instance;
    }

    /*
     * 多项添加
     */
    public int add(int companyRoleId, List<String> lstValues) throws Exception {
        String[] sqls = new String[lstValues.size() + 1];
        sqls[0] = String.format("delete from company_role_permission where company_role_id = %s", companyRoleId);
        for (int i = 0; i < lstValues.size(); i++) {
            String value = lstValues.get(i);
            sqls[i + 1] = String.format("insert into company_role_permission (company_role_id,permission) values (%s, '%s')",
                    companyRoleId, value);
        }
        int result = MyBatisManager.getInstance().executeUpdate(sqls);
        return result;
    }
}