package BRP.model;
import BRP.data.ViewMemberLikeStoryVideoMaster;
public class ViewMemberLikeStoryVideoManager extends ViewMemberLikeStoryVideoMaster 
{
    private static ViewMemberLikeStoryVideoManager _instance;
    public  static ViewMemberLikeStoryVideoManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewMemberLikeStoryVideoManager();
        }
        return _instance;
    }
    private   ViewMemberLikeStoryVideoManager()    
    {
        this.setTypeName("ViewMemberLikeStoryVideo");
    }
}