package BRP.model;
import BRP.data.DeliverAreaMaster;
public class DeliverAreaManager extends DeliverAreaMaster 
{
    private static DeliverAreaManager _instance;
    private DeliverAreaManager()    
    {
        this.setTableName("deliver_area");
        this.setTypeName("DeliverArea");
    }
    public  static DeliverAreaManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new DeliverAreaManager();
        }
        return _instance;
    }
}