package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class ViewBookCompensation extends DataEntity implements Cloneable
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
    private  Integer bookSkuId;//skuID;
    private  String bookSn;//条形码;
    private  String bookName;//书名;
    private  String memberName;//名称;
    private  String companyUserName;//姓名;
    private  String paymentMethodName;//支付方式;
    private  Boolean isRemind;//是否提醒;
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
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
    }
    public  void setBookSn(String bookSn)    
    {
        this.bookSn = bookSn;
    }
    public  String getBookSn()    
    {
        return bookSn;
    }
    public  void setBookName(String bookName)    
    {
        this.bookName = bookName;
    }
    public  String getBookName()    
    {
        return bookName;
    }
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  void setCompanyUserName(String companyUserName)    
    {
        this.companyUserName = companyUserName;
    }
    public  String getCompanyUserName()    
    {
        return companyUserName;
    }
    public  void setPaymentMethodName(String paymentMethodName)    
    {
        this.paymentMethodName = paymentMethodName;
    }
    public  String getPaymentMethodName()    
    {
        return paymentMethodName;
    }
    public  void setIsRemind(Boolean isRemind)    
    {
        this.isRemind = isRemind;
    }
    public  Boolean getIsRemind()    
    {
        return isRemind;
    }
    public  ViewBookCompensation copy()    
    {
        ViewBookCompensation entity = null;
        try        
        {
            entity = (ViewBookCompensation) super.clone();
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