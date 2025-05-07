package BRP.model;
import BRP.data.ViewReadActivityMaster;
public class ViewReadActivityManager extends ViewReadActivityMaster 
{
    private static ViewReadActivityManager _instance;
    public  static ViewReadActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewReadActivityManager();
        }
        return _instance;
    }
    private   ViewReadActivityManager()    
    {
        this.setTypeName("ViewReadActivity");
    }
}