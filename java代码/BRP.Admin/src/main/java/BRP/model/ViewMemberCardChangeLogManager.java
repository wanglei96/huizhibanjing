package BRP.model;
import BRP.data.ViewMemberCardChangeLogMaster;
public class ViewMemberCardChangeLogManager extends ViewMemberCardChangeLogMaster 
{
    private static ViewMemberCardChangeLogManager _instance;
    public  static ViewMemberCardChangeLogManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewMemberCardChangeLogManager();
        }
        return _instance;
    }
    private   ViewMemberCardChangeLogManager()    
    {
        this.setTypeName("ViewMemberCardChangeLog");
    }
}