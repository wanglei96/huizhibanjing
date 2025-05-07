package BRP.model;
import BRP.data.MemberCollectStoryVideoMaster;
public class MemberCollectStoryVideoManager extends MemberCollectStoryVideoMaster 
{
    private static MemberCollectStoryVideoManager _instance;
    private MemberCollectStoryVideoManager()    
    {
        this.setTableName("member_collect_story_video");
        this.setTypeName("MemberCollectStoryVideo");
    }
    public  static MemberCollectStoryVideoManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCollectStoryVideoManager();
        }
        return _instance;
    }
}