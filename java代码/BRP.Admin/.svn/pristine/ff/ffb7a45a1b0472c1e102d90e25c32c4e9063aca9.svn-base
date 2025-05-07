package BRP.model;
import BRP.data.RequiredBookCategoryMaster;
public class RequiredBookCategoryManager extends RequiredBookCategoryMaster 
{
    private static RequiredBookCategoryManager _instance;
    private RequiredBookCategoryManager()    
    {
        this.setTableName("required_book_category");
        this.setTypeName("RequiredBookCategory");
    }
    public  static RequiredBookCategoryManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new RequiredBookCategoryManager();
        }
        return _instance;
    }
}