package BRP.model;
import BRP.data.MemberCollectClubActivityMaster;
public class MemberCollectClubActivityManager extends MemberCollectClubActivityMaster 
{
    private static MemberCollectClubActivityManager _instance;
    private MemberCollectClubActivityManager()    
    {
        this.setTableName("member_collect_club_activity");
        this.setTypeName("MemberCollectClubActivity");
    }
    public  static MemberCollectClubActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCollectClubActivityManager();
        }
        return _instance;
    }
}