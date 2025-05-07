package BRP.model;
import BRP.data.PaymentMethodMaster;
public class PaymentMethodManager extends PaymentMethodMaster 
{
    private static PaymentMethodManager _instance;
    private PaymentMethodManager()    
    {
        this.setTableName("payment_method");
        this.setTypeName("PaymentMethod");
    }
    public  static PaymentMethodManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new PaymentMethodManager();
        }
        return _instance;
    }
}