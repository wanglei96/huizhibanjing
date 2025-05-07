package BRP.model;
import BRP.data.ViewActivityServiceTimeMaster;
public class ViewActivityServiceTimeManager extends ViewActivityServiceTimeMaster 
{
    private static ViewActivityServiceTimeManager _instance;
    public  static ViewActivityServiceTimeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewActivityServiceTimeManager();
        }
        return _instance;
    }
    private   ViewActivityServiceTimeManager()    
    {
        this.setTypeName("ViewActivityServiceTime");
    }
}