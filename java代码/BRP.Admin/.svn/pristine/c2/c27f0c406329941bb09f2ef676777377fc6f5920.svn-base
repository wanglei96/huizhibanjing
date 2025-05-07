package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewBookSkuReviseItem extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookSkuReviseId;//图书SKU修订ID;
    private  String bookSkuFieldCode;//图书SKU字段编码;
    private  String approveStatusCode;//审核状态编码;
    private  String oldValue;//原值;
    private  String newValue;//新值;
    private  Timestamp createTime;//创建时间;
    private  Integer bookSkuId;//图书SKUID;
    private  String bookSkuFieldName;//字段名称;
    private  String approveStatusName;//审核状态名称;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setBookSkuReviseId(Integer bookSkuReviseId)    
    {
        this.bookSkuReviseId = bookSkuReviseId;
    }
    public  Integer getBookSkuReviseId()    
    {
        return bookSkuReviseId;
    }
    public  void setBookSkuFieldCode(String bookSkuFieldCode)    
    {
        this.bookSkuFieldCode = bookSkuFieldCode;
    }
    public  String getBookSkuFieldCode()    
    {
        return bookSkuFieldCode;
    }
    public  void setApproveStatusCode(String approveStatusCode)    
    {
        this.approveStatusCode = approveStatusCode;
    }
    public  String getApproveStatusCode()    
    {
        return approveStatusCode;
    }
    public  void setOldValue(String oldValue)    
    {
        this.oldValue = oldValue;
    }
    public  String getOldValue()    
    {
        return oldValue;
    }
    public  void setNewValue(String newValue)    
    {
        this.newValue = newValue;
    }
    public  String getNewValue()    
    {
        return newValue;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
    }
    public  void setBookSkuFieldName(String bookSkuFieldName)    
    {
        this.bookSkuFieldName = bookSkuFieldName;
    }
    public  String getBookSkuFieldName()    
    {
        return bookSkuFieldName;
    }
    public  void setApproveStatusName(String approveStatusName)    
    {
        this.approveStatusName = approveStatusName;
    }
    public  String getApproveStatusName()    
    {
        return approveStatusName;
    }
    public  ViewBookSkuReviseItem copy()    
    {
        ViewBookSkuReviseItem entity = null;
        try        
        {
            entity = (ViewBookSkuReviseItem) super.clone();
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