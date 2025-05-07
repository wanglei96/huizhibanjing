package BRP.model;
import BRP.data.ViewSalesOrderItemMaster;
public class ViewSalesOrderItemManager extends ViewSalesOrderItemMaster 
{
    private static ViewSalesOrderItemManager _instance;
    public  static ViewSalesOrderItemManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewSalesOrderItemManager();
        }
        return _instance;
    }
    private   ViewSalesOrderItemManager()    
    {
        this.setTypeName("ViewSalesOrderItem");
    }
}