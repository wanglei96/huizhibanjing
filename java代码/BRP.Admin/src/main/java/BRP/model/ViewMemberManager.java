package BRP.model;
import BRP.data.ViewMemberMaster;
public class ViewMemberManager extends ViewMemberMaster 
{
    private static ViewMemberManager _instance;
    public  static ViewMemberManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewMemberManager();
        }
        return _instance;
    }
    private   ViewMemberManager()    
    {
        this.setTypeName("ViewMember");
    }
}