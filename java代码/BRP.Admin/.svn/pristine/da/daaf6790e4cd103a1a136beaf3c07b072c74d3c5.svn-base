package BRP.model;
import BRP.data.BookSkuAgeGroupMaster;
public class BookSkuAgeGroupManager extends BookSkuAgeGroupMaster 
{
    private static BookSkuAgeGroupManager _instance;
    private BookSkuAgeGroupManager()    
    {
        this.setTableName("book_sku_age_group");
        this.setTypeName("BookSkuAgeGroup");
    }
    public  static BookSkuAgeGroupManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new BookSkuAgeGroupManager();
        }
        return _instance;
    }
}