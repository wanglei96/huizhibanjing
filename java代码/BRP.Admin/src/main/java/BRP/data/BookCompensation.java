package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class BookCompensation extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookId;//图书ID;
    private  Integer memberId;//会员ID;
    private  BigDecimal amount;//赔付金额;
    private  String reason;//赔付原因;
    private  String paymentMethodCode;//支付方式;
    private  Integer companyUserId;//办理人;
    private  String remark;//备注;
    private  Timestamp paymentTime;//支付时间;
    private  Integer bookDamageId;//图书损坏记录ID;
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
    public  void setBookId(Integer bookId)    
    {
        this.bookId = bookId;
    }
    public  Integer getBookId()    
    {
        return bookId;
    }
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setAmount(BigDecimal amount)    
    {
        this.amount = amount;
    }
    public  BigDecimal getAmount()    
    {
        return amount;
    }
    public  void setReason(String reason)    
    {
        this.reason = reason;
    }
    public  String getReason()    
    {
        return reason;
    }
    public  void setPaymentMethodCode(String paymentMethodCode)    
    {
        this.paymentMethodCode = paymentMethodCode;
    }
    public  String getPaymentMethodCode()    
    {
        return paymentMethodCode;
    }
    public  void setCompanyUserId(Integer companyUserId)    
    {
        this.companyUserId = companyUserId;
    }
    public  Integer getCompanyUserId()    
    {
        return companyUserId;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setPaymentTime(Timestamp paymentTime)    
    {
        this.paymentTime = paymentTime;
    }
    public  Timestamp getPaymentTime()    
    {
        return paymentTime;
    }
    public  void setBookDamageId(Integer bookDamageId)    
    {
        this.bookDamageId = bookDamageId;
    }
    public  Integer getBookDamageId()    
    {
        return bookDamageId;
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
    public  BookCompensation copy()    
    {
        BookCompensation entity = null;
        try        
        {
            entity = (BookCompensation) super.clone();
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