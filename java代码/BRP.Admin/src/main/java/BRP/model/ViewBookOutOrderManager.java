package BRP.model;
import BRP.data.ViewBookOutOrderMaster;
public class ViewBookOutOrderManager extends ViewBookOutOrderMaster 
{
    private static ViewBookOutOrderManager _instance;
    public  static ViewBookOutOrderManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewBookOutOrderManager();
        }
        return _instance;
    }
    private   ViewBookOutOrderManager()    
    {
        this.setTypeName("ViewBookOutOrder");
    }
}