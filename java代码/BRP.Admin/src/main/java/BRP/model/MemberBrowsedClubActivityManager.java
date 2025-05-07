package BRP.model;
import BRP.data.MemberBrowsedClubActivityMaster;
public class MemberBrowsedClubActivityManager extends MemberBrowsedClubActivityMaster 
{
    private static MemberBrowsedClubActivityManager _instance;
    private MemberBrowsedClubActivityManager()    
    {
        this.setTableName("member_browsed_club_activity");
        this.setTypeName("MemberBrowsedClubActivity");
    }
    public  static MemberBrowsedClubActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberBrowsedClubActivityManager();
        }
        return _instance;
    }
}