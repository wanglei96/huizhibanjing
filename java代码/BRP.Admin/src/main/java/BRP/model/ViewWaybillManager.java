package BRP.model;
import BRP.data.ViewWaybillMaster;
public class ViewWaybillManager extends ViewWaybillMaster 
{
    private static ViewWaybillManager _instance;
    public  static ViewWaybillManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewWaybillManager();
        }
        return _instance;
    }
    private   ViewWaybillManager()    
    {
        this.setTypeName("ViewWaybill");
    }
}