package BRP.model;
import BRP.data.ViewClubActivityMaster;
public class ViewClubActivityManager extends ViewClubActivityMaster 
{
    private static ViewClubActivityManager _instance;
    public  static ViewClubActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewClubActivityManager();
        }
        return _instance;
    }
    private   ViewClubActivityManager()    
    {
        this.setTypeName("ViewClubActivity");
    }
}