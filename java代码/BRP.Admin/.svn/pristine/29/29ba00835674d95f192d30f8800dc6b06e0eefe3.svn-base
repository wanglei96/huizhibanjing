package BRP.model;
import BRP.data.HomeRandomBookMaster;
public class HomeRandomBookManager extends HomeRandomBookMaster 
{
    private static HomeRandomBookManager _instance;
    private HomeRandomBookManager()    
    {
        this.setTableName("home_random_book");
        this.setTypeName("HomeRandomBook");
    }
    public  static HomeRandomBookManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new HomeRandomBookManager();
        }
        return _instance;
    }
}