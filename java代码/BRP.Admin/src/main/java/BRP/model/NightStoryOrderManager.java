package BRP.model;
import BRP.data.NightStoryOrderMaster;
public class NightStoryOrderManager extends NightStoryOrderMaster 
{
    private static NightStoryOrderManager _instance;
    private NightStoryOrderManager()    
    {
        this.setTableName("night_story_order");
        this.setTypeName("NightStoryOrder");
    }
    public  static NightStoryOrderManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new NightStoryOrderManager();
        }
        return _instance;
    }
}