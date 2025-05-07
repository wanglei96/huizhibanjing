package BRP.model;
import BRP.data.CompanyStatusMaster;
public class CompanyStatusManager extends CompanyStatusMaster 
{
    private static CompanyStatusManager _instance;
    private CompanyStatusManager()    
    {
        this.setTableName("company_status");
        this.setTypeName("CompanyStatus");
    }
    public  static CompanyStatusManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanyStatusManager();
        }
        return _instance;
    }
}