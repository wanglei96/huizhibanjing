package BRP.model;
import BRP.data.ViewActivityMaster;
public class ViewActivityManager extends ViewActivityMaster 
{
    private static ViewActivityManager _instance;
    public  static ViewActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewActivityManager();
        }
        return _instance;
    }
    private   ViewActivityManager()    
    {
        this.setTypeName("ViewActivity");
    }
}