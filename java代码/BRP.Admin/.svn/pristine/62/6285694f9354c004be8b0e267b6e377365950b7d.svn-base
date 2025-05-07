package BRP.model;
import BRP.data.ClubActivityCategoryMaster;
public class ClubActivityCategoryManager extends ClubActivityCategoryMaster 
{
    private static ClubActivityCategoryManager _instance;
    private ClubActivityCategoryManager()    
    {
        this.setTableName("club_activity_category");
        this.setTypeName("ClubActivityCategory");
    }
    public  static ClubActivityCategoryManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ClubActivityCategoryManager();
        }
        return _instance;
    }
}