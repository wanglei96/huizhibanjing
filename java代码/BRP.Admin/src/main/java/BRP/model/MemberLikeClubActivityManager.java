package BRP.model;
import BRP.data.MemberLikeClubActivityMaster;
public class MemberLikeClubActivityManager extends MemberLikeClubActivityMaster 
{
    private static MemberLikeClubActivityManager _instance;
    private MemberLikeClubActivityManager()    
    {
        this.setTableName("member_like_club_activity");
        this.setTypeName("MemberLikeClubActivity");
    }
    public  static MemberLikeClubActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberLikeClubActivityManager();
        }
        return _instance;
    }
}