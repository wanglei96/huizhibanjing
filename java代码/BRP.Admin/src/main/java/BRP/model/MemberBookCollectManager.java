package BRP.model;
import BRP.data.MemberBookCollectMaster;
public class MemberBookCollectManager extends MemberBookCollectMaster 
{
    private static MemberBookCollectManager _instance;
    private MemberBookCollectManager()    
    {
        this.setTableName("member_book_collect");
        this.setTypeName("MemberBookCollect");
    }
    public  static MemberBookCollectManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberBookCollectManager();
        }
        return _instance;
    }
}