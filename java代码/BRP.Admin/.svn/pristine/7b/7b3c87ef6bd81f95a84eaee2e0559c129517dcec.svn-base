package BRP.model;
import BRP.data.ActivityTypeMaster;
public class ActivityTypeManager extends ActivityTypeMaster 
{
    private static ActivityTypeManager _instance;
    private ActivityTypeManager()    
    {
        this.setTableName("activity_type");
        this.setTypeName("ActivityType");
    }
    public  static ActivityTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ActivityTypeManager();
        }
        return _instance;
    }
}