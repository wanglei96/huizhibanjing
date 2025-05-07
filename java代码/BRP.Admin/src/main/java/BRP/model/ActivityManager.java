package BRP.model;
import BRP.data.ActivityMaster;
public class ActivityManager extends ActivityMaster 
{
    private static ActivityManager _instance;
    private ActivityManager()    
    {
        this.setTableName("activity");
        this.setTypeName("Activity");
    }
    public  static ActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ActivityManager();
        }
        return _instance;
    }
}