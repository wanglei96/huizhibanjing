package BRP.model;
import BRP.data.HolidayMaster;
public class HolidayManager extends HolidayMaster 
{
    private static HolidayManager _instance;
    private HolidayManager()    
    {
        this.setTableName("holiday");
        this.setTypeName("Holiday");
    }
    public  static HolidayManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new HolidayManager();
        }
        return _instance;
    }
}