package BRP.model;
import BRP.data.DeliverLineMaster;
public class DeliverLineManager extends DeliverLineMaster 
{
    private static DeliverLineManager _instance;
    private DeliverLineManager()    
    {
        this.setTableName("deliver_line");
        this.setTypeName("DeliverLine");
    }
    public  static DeliverLineManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new DeliverLineManager();
        }
        return _instance;
    }
}