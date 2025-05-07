package BRP.model;
import BRP.data.BookCompensationMaster;
public class BookCompensationManager extends BookCompensationMaster 
{
    private static BookCompensationManager _instance;
    private BookCompensationManager()    
    {
        this.setTableName("book_compensation");
        this.setTypeName("BookCompensation");
    }
    public  static BookCompensationManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new BookCompensationManager();
        }
        return _instance;
    }
}