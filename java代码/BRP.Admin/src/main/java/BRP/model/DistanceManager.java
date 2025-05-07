package BRP.model;
import BRP.data.DistanceMaster;
public class DistanceManager extends DistanceMaster 
{
    private static DistanceManager _instance;
    private DistanceManager()    
    {
        this.setTableName("distance");
        this.setTypeName("Distance");
    }
    public  static DistanceManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new DistanceManager();
        }
        return _instance;
    }
}