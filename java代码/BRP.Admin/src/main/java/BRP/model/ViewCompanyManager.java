package BRP.model;
import BRP.data.ViewCompanyMaster;
public class ViewCompanyManager extends ViewCompanyMaster 
{
    private static ViewCompanyManager _instance;
    public  static ViewCompanyManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCompanyManager();
        }
        return _instance;
    }
    private   ViewCompanyManager()    
    {
        this.setTypeName("ViewCompany");
    }
}