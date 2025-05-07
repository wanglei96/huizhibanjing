package BRP.model;
import BRP.data.NightStoryTypeMaster;
public class NightStoryTypeManager extends NightStoryTypeMaster 
{
    private static NightStoryTypeManager _instance;
    private NightStoryTypeManager()    
    {
        this.setTableName("night_story_type");
        this.setTypeName("NightStoryType");
    }
    public  static NightStoryTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new NightStoryTypeManager();
        }
        return _instance;
    }
}