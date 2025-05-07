package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class BookOutOrder extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookOrderItemId;//会员卡号ID;
    private  Integer bookcaseId;//出库位置;
    private  String bookOutOrderStatusCode;//找书出库状态编码;
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
    public  void setBookOutOrderStatusCode(String bookOutOrderStatusCode)    
    {
        this.bookOutOrderStatusCode = bookOutOrderStatusCode;
    }
    public  String getBookOutOrderStatusCode()    
    {
        return bookOutOrderStatusCode;
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
    public  BookOutOrder copy()    
    {
        BookOutOrder entity = null;
        try        
        {
            entity = (BookOutOrder) super.clone();
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