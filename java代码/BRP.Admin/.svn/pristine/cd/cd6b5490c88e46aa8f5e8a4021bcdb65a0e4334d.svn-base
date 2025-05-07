package BRP.model;
import BRP.data.BookSkuReviseMaster;
public class BookSkuReviseManager extends BookSkuReviseMaster 
{
    private static BookSkuReviseManager _instance;
    private BookSkuReviseManager()    
    {
        this.setTableName("book_sku_revise");
        this.setTypeName("BookSkuRevise");
    }
    public  static BookSkuReviseManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new BookSkuReviseManager();
        }
        return _instance;
    }
}