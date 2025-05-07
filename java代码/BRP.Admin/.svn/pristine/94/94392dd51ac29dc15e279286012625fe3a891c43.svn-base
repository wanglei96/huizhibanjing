package BRP.model;
import BRP.data.MemberBookBorrowMaster;
public class MemberBookBorrowManager extends MemberBookBorrowMaster 
{
    private static MemberBookBorrowManager _instance;
    private MemberBookBorrowManager()    
    {
        this.setTableName("member_book_borrow");
        this.setTypeName("MemberBookBorrow");
    }
    public  static MemberBookBorrowManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberBookBorrowManager();
        }
        return _instance;
    }
}