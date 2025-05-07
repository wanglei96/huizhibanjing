package BRP.model;
import BRP.data.ViewCompanyMenuMaster;
public class ViewCompanyMenuManager extends ViewCompanyMenuMaster 
{
    private static ViewCompanyMenuManager _instance;
    public  static ViewCompanyMenuManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCompanyMenuManager();
        }
        return _instance;
    }
    private   ViewCompanyMenuManager()    
    {
        this.setTypeName("ViewCompanyMenu");
    }
}