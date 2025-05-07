package BRP.model;
import BRP.data.PayOrderTypeMaster;
public class PayOrderTypeManager extends PayOrderTypeMaster 
{
    private static PayOrderTypeManager _instance;
    private PayOrderTypeManager()    
    {
        this.setTableName("pay_order_type");
        this.setTypeName("PayOrderType");
    }
    public  static PayOrderTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new PayOrderTypeManager();
        }
        return _instance;
    }
}