package BRP.model;
import BRP.data.PayOrderMaster;
public class PayOrderManager extends PayOrderMaster 
{
    private static PayOrderManager _instance;
    private PayOrderManager()    
    {
        this.setTableName("pay_order");
        this.setTypeName("PayOrder");
    }
    public  static PayOrderManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new PayOrderManager();
        }
        return _instance;
    }
}