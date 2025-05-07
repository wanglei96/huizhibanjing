package BRP.model;
import BRP.data.MemberLikeStoryVideoMaster;
public class MemberLikeStoryVideoManager extends MemberLikeStoryVideoMaster 
{
    private static MemberLikeStoryVideoManager _instance;
    private MemberLikeStoryVideoManager()    
    {
        this.setTableName("member_like_story_video");
        this.setTypeName("MemberLikeStoryVideo");
    }
    public  static MemberLikeStoryVideoManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberLikeStoryVideoManager();
        }
        return _instance;
    }
}