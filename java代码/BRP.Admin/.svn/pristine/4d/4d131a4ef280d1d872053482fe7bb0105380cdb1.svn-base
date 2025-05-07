package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class CompanyProductStocks extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer productId;//商品ID;
    private  Integer companyId;//门店ID;
    private  Integer quantity;//数量;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
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
    public  CompanyProductStocks copy()    
    {
        CompanyProductStocks entity = null;
        try        
        {
            entity = (CompanyProductStocks) super.clone();
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