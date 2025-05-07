package BRP.model;
import BRP.data.BookcaseOrderMaster;
public class BookcaseOrderManager extends BookcaseOrderMaster 
{
    private static BookcaseOrderManager _instance;
    private BookcaseOrderManager()    
    {
        this.setTableName("bookcase_order");
        this.setTypeName("BookcaseOrder");
    }
    public  static BookcaseOrderManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new BookcaseOrderManager();
        }
        return _instance;
    }
}