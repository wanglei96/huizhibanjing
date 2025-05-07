package BRP.model;
import BRP.data.HomeNavigationMaster;
public class HomeNavigationManager extends HomeNavigationMaster 
{
    private static HomeNavigationManager _instance;
    private HomeNavigationManager()    
    {
        this.setTableName("home_navigation");
        this.setTypeName("HomeNavigation");
    }
    public  static HomeNavigationManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new HomeNavigationManager();
        }
        return _instance;
    }
}