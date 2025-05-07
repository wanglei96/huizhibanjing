package BRP.model;
import BRP.data.ClubActivityMaster;
public class ClubActivityManager extends ClubActivityMaster 
{
    private static ClubActivityManager _instance;
    private ClubActivityManager()    
    {
        this.setTableName("club_activity");
        this.setTypeName("ClubActivity");
    }
    public  static ClubActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ClubActivityManager();
        }
        return _instance;
    }
}