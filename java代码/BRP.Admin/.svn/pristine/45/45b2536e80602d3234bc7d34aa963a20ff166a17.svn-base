package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class RequiredBook extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookSkuId;//图书ID;
    private  Integer requiredBookCategoryId;//分类ID;
    private  Timestamp createTime;//创建时间;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
    }
    public  void setRequiredBookCategoryId(Integer requiredBookCategoryId)    
    {
        this.requiredBookCategoryId = requiredBookCategoryId;
    }
    public  Integer getRequiredBookCategoryId()    
    {
        return requiredBookCategoryId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  RequiredBook copy()    
    {
        RequiredBook entity = null;
        try        
        {
            entity = (RequiredBook) super.clone();
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