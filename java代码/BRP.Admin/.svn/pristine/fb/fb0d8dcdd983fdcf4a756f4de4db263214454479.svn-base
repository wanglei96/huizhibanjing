package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class ViewSalesOrderItem extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer salesOrderId;//书箱订单ID;
    private  Integer productId;//商品ID;
    private  BigDecimal price;//价格;
    private  BigDecimal quantity;//数量;
    private  BigDecimal amount;//金额;
    private  Timestamp updateTime;//更新时间;
    private  Integer companyId;
    private  String salesOrderNo;//订单编号;
    private  String salesOrderStatusCode;
    private  String productName;//商品名称;
    private  String description;
    private  String companyNo;//门店编号;
    private  String companyName;//门店名称;
    private  String companyUserName;//公司名称;
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
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setSalesOrderNo(String salesOrderNo)    
    {
        this.salesOrderNo = salesOrderNo;
    }
    public  String getSalesOrderNo()    
    {
        return salesOrderNo;
    }
    public  void setSalesOrderStatusCode(String salesOrderStatusCode)    
    {
        this.salesOrderStatusCode = salesOrderStatusCode;
    }
    public  String getSalesOrderStatusCode()    
    {
        return salesOrderStatusCode;
    }
    public  void setProductName(String productName)    
    {
        this.productName = productName;
    }
    public  String getProductName()    
    {
        return productName;
    }
    public  void setDescription(String description)    
    {
        this.description = description;
    }
    public  String getDescription()    
    {
        return description;
    }
    public  void setCompanyNo(String companyNo)    
    {
        this.companyNo = companyNo;
    }
    public  String getCompanyNo()    
    {
        return companyNo;
    }
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setCompanyUserName(String companyUserName)    
    {
        this.companyUserName = companyUserName;
    }
    public  String getCompanyUserName()    
    {
        return companyUserName;
    }
    public  ViewSalesOrderItem copy()    
    {
        ViewSalesOrderItem entity = null;
        try        
        {
            entity = (ViewSalesOrderItem) super.clone();
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