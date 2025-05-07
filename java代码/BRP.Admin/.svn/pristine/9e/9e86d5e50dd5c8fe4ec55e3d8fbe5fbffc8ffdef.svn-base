package BRP.model;
import BRP.data.ReadActivityMaster;
public class ReadActivityManager extends ReadActivityMaster 
{
    private static ReadActivityManager _instance;
    private ReadActivityManager()    
    {
        this.setTableName("read_activity");
        this.setTypeName("ReadActivity");
    }
    public  static ReadActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ReadActivityManager();
        }
        return _instance;
    }
}