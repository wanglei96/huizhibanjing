package BRP.model;
import BRP.data.ViewStoryVideoMaster;
public class ViewStoryVideoManager extends ViewStoryVideoMaster 
{
    private static ViewStoryVideoManager _instance;
    public  static ViewStoryVideoManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewStoryVideoManager();
        }
        return _instance;
    }
    private   ViewStoryVideoManager()    
    {
        this.setTypeName("ViewStoryVideo");
    }
}