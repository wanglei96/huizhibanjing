package BRP.model;
import BRP.data.ViewCompanyOrganizationMaster;
public class ViewCompanyOrganizationManager extends ViewCompanyOrganizationMaster 
{
    private static ViewCompanyOrganizationManager _instance;
    public  static ViewCompanyOrganizationManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCompanyOrganizationManager();
        }
        return _instance;
    }
    private   ViewCompanyOrganizationManager()    
    {
        this.setTypeName("ViewCompanyOrganization");
    }
}