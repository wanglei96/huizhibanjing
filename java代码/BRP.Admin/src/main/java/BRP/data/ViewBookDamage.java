package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class ViewBookDamage extends DataEntity implements Cloneable
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
    private  Integer bookSkuId;//skuID;
    private  String bookSn;//条形码;
    private  String memberName;//名称;
    private  String memberCardNo;//会员卡号;
    private  String bookName;//书名;
    private  String bindingTypeCode;//装订方式;
    private  Integer bookSeriesId;//系列ID;
    private  String bindingTypeName;//装订方式;
    private  String bookSeriesName;//系列名称;
    private  String bookDamageTypeName;//孙华类型;
    private  String companyUserName;//姓名;
    private  String paymentMethodName;//支付方式;
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
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  void setMemberCardNo(String memberCardNo)    
    {
        this.memberCardNo = memberCardNo;
    }
    public  String getMemberCardNo()    
    {
        return memberCardNo;
    }
    public  void setBookName(String bookName)    
    {
        this.bookName = bookName;
    }
    public  String getBookName()    
    {
        return bookName;
    }
    public  void setBindingTypeCode(String bindingTypeCode)    
    {
        this.bindingTypeCode = bindingTypeCode;
    }
    public  String getBindingTypeCode()    
    {
        return bindingTypeCode;
    }
    public  void setBookSeriesId(Integer bookSeriesId)    
    {
        this.bookSeriesId = bookSeriesId;
    }
    public  Integer getBookSeriesId()    
    {
        return bookSeriesId;
    }
    public  void setBindingTypeName(String bindingTypeName)    
    {
        this.bindingTypeName = bindingTypeName;
    }
    public  String getBindingTypeName()    
    {
        return bindingTypeName;
    }
    public  void setBookSeriesName(String bookSeriesName)    
    {
        this.bookSeriesName = bookSeriesName;
    }
    public  String getBookSeriesName()    
    {
        return bookSeriesName;
    }
    public  void setBookDamageTypeName(String bookDamageTypeName)    
    {
        this.bookDamageTypeName = bookDamageTypeName;
    }
    public  String getBookDamageTypeName()    
    {
        return bookDamageTypeName;
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
    public  ViewBookDamage copy()    
    {
        ViewBookDamage entity = null;
        try        
        {
            entity = (ViewBookDamage) super.clone();
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