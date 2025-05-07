package BRP.model;
import BRP.data.ClubActivityMemberMaster;
public class ClubActivityMemberManager extends ClubActivityMemberMaster 
{
    private static ClubActivityMemberManager _instance;
    private ClubActivityMemberManager()    
    {
        this.setTableName("club_activity_member");
        this.setTypeName("ClubActivityMember");
    }
    public  static ClubActivityMemberManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ClubActivityMemberManager();
        }
        return _instance;
    }
}