package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class SalesOrderItem extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer salesOrderId;//书箱订单ID;
    private  Integer productId;//商品ID;
    private  BigDecimal price;//价格;
    private  BigDecimal quantity;//数量;
    private  BigDecimal amount;//金额;
    private  Timestamp updateTime;//更新时间;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setSalesOrderId(Integer salesOrderId)    
    {
        this.salesOrderId = salesOrderId;
    }
    public  Integer getSalesOrderId()    
    {
        return salesOrderId;
    }
    public  void setProductId(Integer productId)    
    {
        this.productId = productId;
    }
    public  Integer getProductId()    
    {
        return productId;
    }
    public  void setPrice(BigDecimal price)    
    {
        this.price = price;
    }
    public  BigDecimal getPrice()    
    {
        return price;
    }
    public  void setQuantity(BigDecimal quantity)    
    {
        this.quantity = quantity;
    }
    public  BigDecimal getQuantity()    
    {
        return quantity;
    }
    public  void setAmount(BigDecimal amount)    
    {
        this.amount = amount;
    }
    public  BigDecimal getAmount()    
    {
        return amount;
    }
    public  void setUpdateTime(Timestamp updateTime)    
    {
        this.updateTime = updateTime;
    }
    public  Timestamp getUpdateTime()    
    {
        return updateTime;
    }
    public  SalesOrderItem copy()    
    {
        SalesOrderItem entity = null;
        try        
        {
            entity = (SalesOrderItem) super.clone();
        }
         catch (CloneNotSupportedException e)         
        {
            e.printStackTrace();
        }
        return entity;
    }
    public  JSONObject toJson()    
    {
        return JSONObject.fromObject(this,JsonHelper.getJsonConfig());
    }
}