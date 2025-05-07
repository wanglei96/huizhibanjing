package BRP.model;
import BRP.data.BookSkuBookTagMaster;
public class BookSkuBookTagManager extends BookSkuBookTagMaster 
{
    private static BookSkuBookTagManager _instance;
    private BookSkuBookTagManager()    
    {
        this.setTableName("book_sku_book_tag");
        this.setTypeName("BookSkuBookTag");
    }
    public  static BookSkuBookTagManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new BookSkuBookTagManager();
        }
        return _instance;
    }
}