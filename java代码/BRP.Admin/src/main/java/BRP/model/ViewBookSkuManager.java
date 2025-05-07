package BRP.model;
import BRP.data.ViewBookSkuMaster;
public class ViewBookSkuManager extends ViewBookSkuMaster 
{
    private static ViewBookSkuManager _instance;
    public  static ViewBookSkuManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewBookSkuManager();
        }
        return _instance;
    }
    private   ViewBookSkuManager()    
    {
        this.setTypeName("ViewBookSku");
    }
}