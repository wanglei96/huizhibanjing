package BRP.model;
import BRP.data.ViewMemberBrowsedStoryVideoMaster;
public class ViewMemberBrowsedStoryVideoManager extends ViewMemberBrowsedStoryVideoMaster 
{
    private static ViewMemberBrowsedStoryVideoManager _instance;
    public  static ViewMemberBrowsedStoryVideoManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewMemberBrowsedStoryVideoManager();
        }
        return _instance;
    }
    private   ViewMemberBrowsedStoryVideoManager()    
    {
        this.setTypeName("ViewMemberBrowsedStoryVideo");
    }
}