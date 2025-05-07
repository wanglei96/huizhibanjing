package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class BookDamage extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookId;//图书ID;
    private  Integer memberId;//会员ID;
    private  BigDecimal amount;//赔付价格;
    private  String bookDamageTypeCode;//损坏类型;
    private  Timestamp damageTime;//损坏时间;
    private  Boolean isRemind;//是否提醒;
    private  Integer companyId;//门店ID;
    private  Integer companyUserId;//办理人;
    private  Timestamp paymentTime;//支付时间;
    private  String paymentMethodCode;//支付方式;
    private  String reason;//赔付原因;
    private  String remark;//备注;
    private  Boolean isCompensation;//是否赔付;
    private  Boolean isOff;//是否下架;
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
    public  void setBookDamageTypeCode(String bookDamageTypeCode)    
    {
        this.bookDamageTypeCode = bookDamageTypeCode;
    }
    public  String getBookDamageTypeCode()    
    {
        return bookDamageTypeCode;
    }
    public  void setDamageTime(Timestamp damageTime)    
    {
        this.damageTime = damageTime;
    }
    public  Timestamp getDamageTime()    
    {
        return damageTime;
    }
    public  void setIsRemind(Boolean isRemind)    
    {
        this.isRemind = isRemind;
    }
    public  Boolean getIsRemind()    
    {
        return isRemind;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setCompanyUserId(Integer companyUserId)    
    {
        this.companyUserId = companyUserId;
    }
    public  Integer getCompanyUserId()    
    {
        return companyUserId;
    }
    public  void setPaymentTime(Timestamp paymentTime)    
    {
        this.paymentTime = paymentTime;
    }
    public  Timestamp getPaymentTime()    
    {
        return paymentTime;
    }
    public  void setPaymentMethodCode(String paymentMethodCode)    
    {
        this.paymentMethodCode = paymentMethodCode;
    }
    public  String getPaymentMethodCode()    
    {
        return paymentMethodCode;
    }
    public  void setReason(String reason)    
    {
        this.reason = reason;
    }
    public  String getReason()    
    {
        return reason;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setIsCompensation(Boolean isCompensation)    
    {
        this.isCompensation = isCompensation;
    }
    public  Boolean getIsCompensation()    
    {
        return isCompensation;
    }
    public  void setIsOff(Boolean isOff)    
    {
        this.isOff = isOff;
    }
    public  Boolean getIsOff()    
    {
        return isOff;
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
    public  BookDamage copy()    
    {
        BookDamage entity = null;
        try        
        {
            entity = (BookDamage) super.clone();
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