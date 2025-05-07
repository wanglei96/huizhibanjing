package BRP.model;
import BRP.data.ViewMemberBrowsedClubActivityMaster;
public class ViewMemberBrowsedClubActivityManager extends ViewMemberBrowsedClubActivityMaster 
{
    private static ViewMemberBrowsedClubActivityManager _instance;
    public  static ViewMemberBrowsedClubActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewMemberBrowsedClubActivityManager();
        }
        return _instance;
    }
    private   ViewMemberBrowsedClubActivityManager()    
    {
        this.setTypeName("ViewMemberBrowsedClubActivity");
    }
}