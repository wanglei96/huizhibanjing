package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
import java.math.BigDecimal;
public class ViewCompanyProductStocks extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer productId;//商品ID;
    private  Integer companyId;//门店ID;
    private  Integer quantity;//数量;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String companyName;//门店名称;
    private  String productName;//商品名称;
    private  String productCode;//编码;
    private  String productDescription;//描述;
    private  BigDecimal productPrice;//价格;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setProductId(Integer productId)    
    {
        this.productId = productId;
    }
    public  Integer getProductId()    
    {
        return productId;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setQuantity(Integer quantity)    
    {
        this.quantity = quantity;
    }
    public  Integer getQuantity()    
    {
        return quantity;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setUpdateTime(Timestamp updateTime)    
    {
        this.updateTime = updateTime;
    }
    public  Timestamp getUpdateTime()    
    {
        return updateTime;
    }
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setProductName(String productName)    
    {
        this.productName = productName;
    }
    public  String getProductName()    
    {
        return productName;
    }
    public  void setProductCode(String productCode)    
    {
        this.productCode = productCode;
    }
    public  String getProductCode()    
    {
        return productCode;
    }
    public  void setProductDescription(String productDescription)    
    {
        this.productDescription = productDescription;
    }
    public  String getProductDescription()    
    {
        return productDescription;
    }
    public  void setProductPrice(BigDecimal productPrice)    
    {
        this.productPrice = productPrice;
    }
    public  BigDecimal getProductPrice()    
    {
        return productPrice;
    }
    public  ViewCompanyProductStocks copy()    
    {
        ViewCompanyProductStocks entity = null;
        try        
        {
            entity = (ViewCompanyProductStocks) super.clone();
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