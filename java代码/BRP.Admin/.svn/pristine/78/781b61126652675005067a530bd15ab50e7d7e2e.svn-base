package BRP.model;
import BRP.data.CompanyUserSysRoleMaster;
public class CompanyUserSysRoleManager extends CompanyUserSysRoleMaster 
{
    private static CompanyUserSysRoleManager _instance;
    private CompanyUserSysRoleManager()    
    {
        this.setTableName("company_user_sys_role");
        this.setTypeName("CompanyUserSysRole");
    }
    public  static CompanyUserSysRoleManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanyUserSysRoleManager();
        }
        return _instance;
    }
}