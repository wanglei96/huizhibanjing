package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class BookOrder extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  String bookOrderNo;//单据编号;
    private  Integer delivererId;//配送员ID;
    private  Timestamp businessDate;//单据时间;
    private  String bookOrderBorrowStatusCode;//借阅状态编码;
    private  String bookOrderReturnStatusCode;//还书状态编码;
    private  Integer companyId;//门店ID;
    private  Boolean isOffline;//是否线下;
    private  String errorRemark;//异常情况;
    private  Integer displayOrder;//配送单排序（配送员小程序）;
    private  Timestamp deliverTime;//配送时间;
    private  String rejectedReason;
    private  Timestamp deliveredTime;
    private  Integer bookpickerId;
    private  Integer deliverAreaNumber;
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
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setBookOrderNo(String bookOrderNo)    
    {
        this.bookOrderNo = bookOrderNo;
    }
    public  String getBookOrderNo()    
    {
        return bookOrderNo;
    }
    public  void setDelivererId(Integer delivererId)    
    {
        this.delivererId = delivererId;
    }
    public  Integer getDelivererId()    
    {
        return delivererId;
    }
    public  void setBusinessDate(Timestamp businessDate)    
    {
        this.businessDate = businessDate;
    }
    public  Timestamp getBusinessDate()    
    {
        return businessDate;
    }
    public  void setBookOrderBorrowStatusCode(String bookOrderBorrowStatusCode)    
    {
        this.bookOrderBorrowStatusCode = bookOrderBorrowStatusCode;
    }
    public  String getBookOrderBorrowStatusCode()    
    {
        return bookOrderBorrowStatusCode;
    }
    public  void setBookOrderReturnStatusCode(String bookOrderReturnStatusCode)    
    {
        this.bookOrderReturnStatusCode = bookOrderReturnStatusCode;
    }
    public  String getBookOrderReturnStatusCode()    
    {
        return bookOrderReturnStatusCode;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setIsOffline(Boolean isOffline)    
    {
        this.isOffline = isOffline;
    }
    public  Boolean getIsOffline()    
    {
        return isOffline;
    }
    public  void setErrorRemark(String errorRemark)    
    {
        this.errorRemark = errorRemark;
    }
    public  String getErrorRemark()    
    {
        return errorRemark;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setDeliverTime(Timestamp deliverTime)    
    {
        this.deliverTime = deliverTime;
    }
    public  Timestamp getDeliverTime()    
    {
        return deliverTime;
    }
    public  void setRejectedReason(String rejectedReason)    
    {
        this.rejectedReason = rejectedReason;
    }
    public  String getRejectedReason()    
    {
        return rejectedReason;
    }
    public  void setDeliveredTime(Timestamp deliveredTime)    
    {
        this.deliveredTime = deliveredTime;
    }
    public  Timestamp getDeliveredTime()    
    {
        return deliveredTime;
    }
    public  void setBookpickerId(Integer bookpickerId)    
    {
        this.bookpickerId = bookpickerId;
    }
    public  Integer getBookpickerId()    
    {
        return bookpickerId;
    }
    public  void setDeliverAreaNumber(Integer deliverAreaNumber)    
    {
        this.deliverAreaNumber = deliverAreaNumber;
    }
    public  Integer getDeliverAreaNumber()    
    {
        return deliverAreaNumber;
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
    public  BookOrder copy()    
    {
        BookOrder entity = null;
        try        
        {
            entity = (BookOrder) super.clone();
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