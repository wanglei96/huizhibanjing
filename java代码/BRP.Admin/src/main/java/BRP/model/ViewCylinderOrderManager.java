package BRP.model;
import BRP.data.ViewCylinderOrderMaster;
public class ViewCylinderOrderManager extends ViewCylinderOrderMaster 
{
    private static ViewCylinderOrderManager _instance;
    public  static ViewCylinderOrderManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCylinderOrderManager();
        }
        return _instance;
    }
    private   ViewCylinderOrderManager()    
    {
        this.setTypeName("ViewCylinderOrder");
    }
}