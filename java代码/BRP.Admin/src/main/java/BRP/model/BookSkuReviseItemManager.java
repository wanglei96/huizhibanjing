package BRP.model;
import BRP.data.BookSkuReviseItemMaster;
public class BookSkuReviseItemManager extends BookSkuReviseItemMaster 
{
    private static BookSkuReviseItemManager _instance;
    private BookSkuReviseItemManager()    
    {
        this.setTableName("book_sku_revise_item");
        this.setTypeName("BookSkuReviseItem");
    }
    public  static BookSkuReviseItemManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new BookSkuReviseItemManager();
        }
        return _instance;
    }
}