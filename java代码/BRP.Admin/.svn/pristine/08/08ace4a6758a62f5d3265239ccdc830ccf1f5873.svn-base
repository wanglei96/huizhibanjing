package BRP.model;
import BRP.data.ProductMaster;
public class ProductManager extends ProductMaster 
{
    private static ProductManager _instance;
    private ProductManager()    
    {
        this.setTableName("product");
        this.setTypeName("Product");
    }
    public  static ProductManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ProductManager();
        }
        return _instance;
    }
}