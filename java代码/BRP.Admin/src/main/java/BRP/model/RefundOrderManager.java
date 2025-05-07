package BRP.model;
import BRP.data.RefundOrderMaster;
public class RefundOrderManager extends RefundOrderMaster 
{
    private static RefundOrderManager _instance;
    private RefundOrderManager()    
    {
        this.setTableName("refund_order");
        this.setTypeName("RefundOrder");
    }
    public  static RefundOrderManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new RefundOrderManager();
        }
        return _instance;
    }
}