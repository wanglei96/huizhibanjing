package BRP.model;
import BRP.data.SalesOrderItemMaster;
public class SalesOrderItemManager extends SalesOrderItemMaster 
{
    private static SalesOrderItemManager _instance;
    private SalesOrderItemManager()    
    {
        this.setTableName("sales_order_item");
        this.setTypeName("SalesOrderItem");
    }
    public  static SalesOrderItemManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SalesOrderItemManager();
        }
        return _instance;
    }
}