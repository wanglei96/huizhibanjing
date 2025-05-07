package BRP.model;
import BRP.data.MemberBrowsedStoryVideoMaster;
public class MemberBrowsedStoryVideoManager extends MemberBrowsedStoryVideoMaster 
{
    private static MemberBrowsedStoryVideoManager _instance;
    private MemberBrowsedStoryVideoManager()    
    {
        this.setTableName("member_browsed_story_video");
        this.setTypeName("MemberBrowsedStoryVideo");
    }
    public  static MemberBrowsedStoryVideoManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberBrowsedStoryVideoManager();
        }
        return _instance;
    }
}