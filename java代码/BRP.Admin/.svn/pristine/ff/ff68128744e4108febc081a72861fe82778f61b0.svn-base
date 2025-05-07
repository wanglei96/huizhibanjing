package BRP.model;
import BRP.data.CompanyUserStatusMaster;
public class CompanyUserStatusManager extends CompanyUserStatusMaster 
{
    private static CompanyUserStatusManager _instance;
    private CompanyUserStatusManager()    
    {
        this.setTableName("company_user_status");
        this.setTypeName("CompanyUserStatus");
    }
    public  static CompanyUserStatusManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanyUserStatusManager();
        }
        return _instance;
    }
}