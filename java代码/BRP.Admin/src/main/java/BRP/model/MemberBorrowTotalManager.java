package BRP.model;
import BRP.data.MemberBorrowTotalMaster;
public class MemberBorrowTotalManager extends MemberBorrowTotalMaster 
{
    private static MemberBorrowTotalManager _instance;
    public  static MemberBorrowTotalManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberBorrowTotalManager();
        }
        return _instance;
    }
    private   MemberBorrowTotalManager()    
    {
        this.setTypeName("MemberBorrowTotal");
    }
}