package BRP.model;
import BRP.data.CompanyOrganizationMaster;
public class CompanyOrganizationManager extends CompanyOrganizationMaster 
{
    private static CompanyOrganizationManager _instance;
    private CompanyOrganizationManager()    
    {
        this.setTableName("company_organization");
        this.setTypeName("CompanyOrganization");
    }
    public  static CompanyOrganizationManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanyOrganizationManager();
        }
        return _instance;
    }
}