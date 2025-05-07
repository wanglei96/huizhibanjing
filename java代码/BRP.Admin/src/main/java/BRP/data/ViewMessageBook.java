package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewMessageBook extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer messageId;//消息ID;
    private  Integer bookId;//图书ID;
    private  Timestamp createTime;//创建时间;
    private  Integer memberId;
    private  String messageTypeCode;
    private  String bookSn;//条形码;
    private  Integer bookSkuId;//skuID;
    private  String bookName;//书名;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setMessageId(Integer messageId)    
    {
        this.messageId = messageId;
    }
    public  Integer getMessageId()    
    {
        return messageId;
    }
    public  void setBookId(Integer bookId)    
    {
        this.bookId = bookId;
    }
    public  Integer getBookId()    
    {
        return bookId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setMessageTypeCode(String messageTypeCode)    
    {
        this.messageTypeCode = messageTypeCode;
    }
    public  String getMessageTypeCode()    
    {
        return messageTypeCode;
    }
    public  void setBookSn(String bookSn)    
    {
        this.bookSn = bookSn;
    }
    public  String getBookSn()    
    {
        return bookSn;
    }
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
    }
    public  void setBookName(String bookName)    
    {
        this.bookName = bookName;
    }
    public  String getBookName()    
    {
        return bookName;
    }
    public  ViewMessageBook copy()    
    {
        ViewMessageBook entity = null;
        try        
        {
            entity = (ViewMessageBook) super.clone();
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