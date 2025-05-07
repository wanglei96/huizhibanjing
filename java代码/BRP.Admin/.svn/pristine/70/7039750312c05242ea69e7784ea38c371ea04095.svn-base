package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class BookOrderItem extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookOrderId;//借书单ID;
    private  Integer bookId;//图书ID;
    private  Boolean isDamage;//是否损坏;
    private  String bookOrderTypeCode;//书单类型编码;
    private  String deliverStatusCode;//配送状态;
    private  Boolean isReturned;
    private  Boolean isAbnormal;
    private  String abnormalContnet;
    private  Timestamp bookReturnTime;
    private  Timestamp bookInStocksTime;
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
    public  void setBookOrderId(Integer bookOrderId)    
    {
        this.bookOrderId = bookOrderId;
    }
    public  Integer getBookOrderId()    
    {
        return bookOrderId;
    }
    public  void setBookId(Integer bookId)    
    {
        this.bookId = bookId;
    }
    public  Integer getBookId()    
    {
        return bookId;
    }
    public  void setIsDamage(Boolean isDamage)    
    {
        this.isDamage = isDamage;
    }
    public  Boolean getIsDamage()    
    {
        return isDamage;
    }
    public  void setBookOrderTypeCode(String bookOrderTypeCode)    
    {
        this.bookOrderTypeCode = bookOrderTypeCode;
    }
    public  String getBookOrderTypeCode()    
    {
        return bookOrderTypeCode;
    }
    public  void setDeliverStatusCode(String deliverStatusCode)    
    {
        this.deliverStatusCode = deliverStatusCode;
    }
    public  String getDeliverStatusCode()    
    {
        return deliverStatusCode;
    }
    public  void setIsReturned(Boolean isReturned)    
    {
        this.isReturned = isReturned;
    }
    public  Boolean getIsReturned()    
    {
        return isReturned;
    }
    public  void setIsAbnormal(Boolean isAbnormal)    
    {
        this.isAbnormal = isAbnormal;
    }
    public  Boolean getIsAbnormal()    
    {
        return isAbnormal;
    }
    public  void setAbnormalContnet(String abnormalContnet)    
    {
        this.abnormalContnet = abnormalContnet;
    }
    public  String getAbnormalContnet()    
    {
        return abnormalContnet;
    }
    public  void setBookReturnTime(Timestamp bookReturnTime)    
    {
        this.bookReturnTime = bookReturnTime;
    }
    public  Timestamp getBookReturnTime()    
    {
        return bookReturnTime;
    }
    public  void setBookInStocksTime(Timestamp bookInStocksTime)    
    {
        this.bookInStocksTime = bookInStocksTime;
    }
    public  Timestamp getBookInStocksTime()    
    {
        return bookInStocksTime;
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
    public  BookOrderItem copy()    
    {
        BookOrderItem entity = null;
        try        
        {
            entity = (BookOrderItem) super.clone();
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