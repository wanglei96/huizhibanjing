package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class BookInOrder extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookOrderItemId;//借还书单详情ID;
    private  Integer bookcaseId;//位置;
    private  String bookInOrderStatusCode;//状态编码;
    private  Boolean isEnabled;//是否上架;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//修改时间;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setBookOrderItemId(Integer bookOrderItemId)    
    {
        this.bookOrderItemId = bookOrderItemId;
    }
    public  Integer getBookOrderItemId()    
    {
        return bookOrderItemId;
    }
    public  void setBookcaseId(Integer bookcaseId)    
    {
        this.bookcaseId = bookcaseId;
    }
    public  Integer getBookcaseId()    
    {
        return bookcaseId;
    }
    public  void setBookInOrderStatusCode(String bookInOrderStatusCode)    
    {
        this.bookInOrderStatusCode = bookInOrderStatusCode;
    }
    public  String getBookInOrderStatusCode()    
    {
        return bookInOrderStatusCode;
    }
    public  void setIsEnabled(Boolean isEnabled)    
    {
        this.isEnabled = isEnabled;
    }
    public  Boolean getIsEnabled()    
    {
        return isEnabled;
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
    public  BookInOrder copy()    
    {
        BookInOrder entity = null;
        try        
        {
            entity = (BookInOrder) super.clone();
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