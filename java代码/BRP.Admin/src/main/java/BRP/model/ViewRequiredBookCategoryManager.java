package BRP.model;
import BRP.data.ViewRequiredBookCategoryMaster;
public class ViewRequiredBookCategoryManager extends ViewRequiredBookCategoryMaster 
{
    private static ViewRequiredBookCategoryManager _instance;
    public  static ViewRequiredBookCategoryManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewRequiredBookCategoryManager();
        }
        return _instance;
    }
    private   ViewRequiredBookCategoryManager()    
    {
        this.setTypeName("ViewRequiredBookCategory");
    }
}