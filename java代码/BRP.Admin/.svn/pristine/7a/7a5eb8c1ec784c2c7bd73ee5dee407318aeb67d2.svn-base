package BRP.model;
import BRP.data.ReadActivityMemberMaster;
public class ReadActivityMemberManager extends ReadActivityMemberMaster 
{
    private static ReadActivityMemberManager _instance;
    private ReadActivityMemberManager()    
    {
        this.setTableName("read_activity_member");
        this.setTypeName("ReadActivityMember");
    }
    public  static ReadActivityMemberManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ReadActivityMemberManager();
        }
        return _instance;
    }
}