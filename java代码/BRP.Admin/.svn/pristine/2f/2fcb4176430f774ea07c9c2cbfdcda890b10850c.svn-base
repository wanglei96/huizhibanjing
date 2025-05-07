package BRP.model;
import BRP.data.CylinderOrderMaster;
public class CylinderOrderManager extends CylinderOrderMaster 
{
    private static CylinderOrderManager _instance;
    private CylinderOrderManager()    
    {
        this.setTableName("cylinder_order");
        this.setTypeName("CylinderOrder");
    }
    public  static CylinderOrderManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CylinderOrderManager();
        }
        return _instance;
    }
}