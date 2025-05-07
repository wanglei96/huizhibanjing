package BRP.model;
import BRP.data.ViewPayOrderMaster;
public class ViewPayOrderManager extends ViewPayOrderMaster 
{
    private static ViewPayOrderManager _instance;
    public  static ViewPayOrderManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewPayOrderManager();
        }
        return _instance;
    }
    private   ViewPayOrderManager()    
    {
        this.setTypeName("ViewPayOrder");
    }
}