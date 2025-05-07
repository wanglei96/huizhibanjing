package BRP.model;
import BRP.data.MemberBookboxMaster;
public class MemberBookboxManager extends MemberBookboxMaster 
{
    private static MemberBookboxManager _instance;
    private MemberBookboxManager()    
    {
        this.setTableName("member_bookbox");
        this.setTypeName("MemberBookbox");
    }
    public  static MemberBookboxManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberBookboxManager();
        }
        return _instance;
    }
}