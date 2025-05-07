package BRP.model;
import BRP.data.ViewCompanyContentMaster;
public class ViewCompanyContentManager extends ViewCompanyContentMaster 
{
    private static ViewCompanyContentManager _instance;
    public  static ViewCompanyContentManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCompanyContentManager();
        }
        return _instance;
    }
    private   ViewCompanyContentManager()    
    {
        this.setTypeName("ViewCompanyContent");
    }
}