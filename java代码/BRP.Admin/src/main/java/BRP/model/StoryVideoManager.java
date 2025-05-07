package BRP.model;
import BRP.data.StoryVideoMaster;
public class StoryVideoManager extends StoryVideoMaster 
{
    private static StoryVideoManager _instance;
    private StoryVideoManager()    
    {
        this.setTableName("story_video");
        this.setTypeName("StoryVideo");
    }
    public  static StoryVideoManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new StoryVideoManager();
        }
        return _instance;
    }
}