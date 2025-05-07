package BRP.model;
import BRP.data.BookStocksLogMaster;
public class BookStocksLogManager extends BookStocksLogMaster 
{
    private static BookStocksLogManager _instance;
    private BookStocksLogManager()    
    {
        this.setTableName("book_stocks_log");
        this.setTypeName("BookStocksLog");
    }
    public  static BookStocksLogManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new BookStocksLogManager();
        }
        return _instance;
    }
}