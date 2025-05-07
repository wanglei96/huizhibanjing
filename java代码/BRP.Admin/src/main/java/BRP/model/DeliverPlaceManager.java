package BRP.model;
import BRP.data.DeliverPlaceMaster;
public class DeliverPlaceManager extends DeliverPlaceMaster 
{
    private static DeliverPlaceManager _instance;
    private DeliverPlaceManager()    
    {
        this.setTableName("deliver_place");
        this.setTypeName("DeliverPlace");
    }
    public  static DeliverPlaceManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new DeliverPlaceManager();
        }
        return _instance;
    }
}