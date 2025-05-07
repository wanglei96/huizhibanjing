package BRP.model;
import BRP.data.CompanyContentMaster;
public class CompanyContentManager extends CompanyContentMaster 
{
    private static CompanyContentManager _instance;
    private CompanyContentManager()    
    {
        this.setTableName("company_content");
        this.setTypeName("CompanyContent");
    }
    public  static CompanyContentManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanyContentManager();
        }
        return _instance;
    }
}