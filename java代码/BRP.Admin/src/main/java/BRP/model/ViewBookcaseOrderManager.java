package BRP.model;
import BRP.data.ViewBookcaseOrderMaster;
public class ViewBookcaseOrderManager extends ViewBookcaseOrderMaster 
{
    private static ViewBookcaseOrderManager _instance;
    public  static ViewBookcaseOrderManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewBookcaseOrderManager();
        }
        return _instance;
    }
    private   ViewBookcaseOrderManager()    
    {
        this.setTypeName("ViewBookcaseOrder");
    }
}