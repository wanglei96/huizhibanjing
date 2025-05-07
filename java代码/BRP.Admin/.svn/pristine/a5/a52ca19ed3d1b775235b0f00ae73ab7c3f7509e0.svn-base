package BRP.model;
import BRP.data.BookCloudStockMaster;
public class BookCloudStockManager extends BookCloudStockMaster 
{
    private static BookCloudStockManager _instance;
    private BookCloudStockManager()    
    {
        this.setTableName("book_cloud_stock");
        this.setTypeName("BookCloudStock");
    }
    public  static BookCloudStockManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new BookCloudStockManager();
        }
        return _instance;
    }
}