package BRP.model;
import BRP.data.ViewHomeNavigationMaster;
public class ViewHomeNavigationManager extends ViewHomeNavigationMaster 
{
    private static ViewHomeNavigationManager _instance;
    public  static ViewHomeNavigationManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewHomeNavigationManager();
        }
        return _instance;
    }
    private   ViewHomeNavigationManager()    
    {
        this.setTypeName("ViewHomeNavigation");
    }
}