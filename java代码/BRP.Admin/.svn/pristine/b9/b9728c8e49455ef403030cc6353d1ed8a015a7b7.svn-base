package BRP.model;
import BRP.data.DeliverMaster;
public class DeliverManager extends DeliverMaster 
{
    private static DeliverManager _instance;
    private DeliverManager()    
    {
        this.setTableName("deliver");
        this.setTypeName("Deliver");
    }
    public  static DeliverManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new DeliverManager();
        }
        return _instance;
    }
}