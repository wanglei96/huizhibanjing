package BRP.model;
import BRP.data.ViewAgeGroupSkuTagMaster;
public class ViewAgeGroupSkuTagManager extends ViewAgeGroupSkuTagMaster 
{
    private static ViewAgeGroupSkuTagManager _instance;
    public  static ViewAgeGroupSkuTagManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewAgeGroupSkuTagManager();
        }
        return _instance;
    }
    private   ViewAgeGroupSkuTagManager()    
    {
        this.setTypeName("ViewAgeGroupSkuTag");
    }
}