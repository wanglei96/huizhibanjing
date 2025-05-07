package BRP.model;
import BRP.data.HomeNewBookMaster;
public class HomeNewBookManager extends HomeNewBookMaster 
{
    private static HomeNewBookManager _instance;
    private HomeNewBookManager()    
    {
        this.setTableName("home_new_book");
        this.setTypeName("HomeNewBook");
    }
    public  static HomeNewBookManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new HomeNewBookManager();
        }
        return _instance;
    }
}