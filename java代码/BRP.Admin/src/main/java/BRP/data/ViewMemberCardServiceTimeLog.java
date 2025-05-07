package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
import java.math.BigDecimal;
public class ViewMemberCardServiceTimeLog extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String memberCardNo;//会员卡号;
    private  Timestamp memberCardExpireDate;//失效日期;
    private  Integer memberId;//会员ID;
    private  String memberName;//名称;
    private  Integer days;//增加/减少时长;
    private  Integer giftDays;//赠送时长;
    private  String memberCardServiceTimeTypeName;//类型;
    private  String memberCardServiceTimeTypeCode;//名称;
    private  BigDecimal cost;//费用;
    private  Timestamp effectiveTime;//生效时间;
    private  Timestamp agoExpiryTime;//添加前到期时间;
    private  Timestamp createTime;//创建时间;
    private  String paymentTypeName;//支付方式类型;
    private  String remark;//备注;
    private  Timestamp addTime;//添加时间;
    private  Timestamp expiryTime;//到期时间;
    private  Boolean isDeleted;//是否删除;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setMemberCardNo(String memberCardNo)    
    {
        this.memberCardNo = memberCardNo;
    }
    public  String getMemberCardNo()    
    {
        return memberCardNo;
    }
    public  void setMemberCardExpireDate(Timestamp memberCardExpireDate)    
    {
        this.memberCardExpireDate = memberCardExpireDate;
    }
    public  Timestamp getMemberCardExpireDate()    
    {
        return memberCardExpireDate;
    }
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  void setDays(Integer days)    
    {
        this.days = days;
    }
    public  Integer getDays()    
    {
        return days;
    }
    public  void setGiftDays(Integer giftDays)    
    {
        this.giftDays = giftDays;
    }
    public  Integer getGiftDays()    
    {
        return giftDays;
    }
    public  void setMemberCardServiceTimeTypeName(String memberCardServiceTimeTypeName)    
    {
        this.memberCardServiceTimeTypeName = memberCardServiceTimeTypeName;
    }
    public  String getMemberCardServiceTimeTypeName()    
    {
        return memberCardServiceTimeTypeName;
    }
    public  void setMemberCardServiceTimeTypeCode(String memberCardServiceTimeTypeCode)    
    {
        this.memberCardServiceTimeTypeCode = memberCardServiceTimeTypeCode;
    }
    public  String getMemberCardServiceTimeTypeCode()    
    {
        return memberCardServiceTimeTypeCode;
    }
    public  void setCost(BigDecimal cost)    
    {
        this.cost = cost;
    }
    public  BigDecimal getCost()    
    {
        return cost;
    }
    public  void setEffectiveTime(Timestamp effectiveTime)    
    {
        this.effectiveTime = effectiveTime;
    }
    public  Timestamp getEffectiveTime()    
    {
        return effectiveTime;
    }
    public  void setAgoExpiryTime(Timestamp agoExpiryTime)    
    {
        this.agoExpiryTime = agoExpiryTime;
    }
    public  Timestamp getAgoExpiryTime()    
    {
        return agoExpiryTime;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setPaymentTypeName(String paymentTypeName)    
    {
        this.paymentTypeName = paymentTypeName;
    }
    public  String getPaymentTypeName()    
    {
        return paymentTypeName;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setAddTime(Timestamp addTime)    
    {
        this.addTime = addTime;
    }
    public  Timestamp getAddTime()    
    {
        return addTime;
    }
    public  void setExpiryTime(Timestamp expiryTime)    
    {
        this.expiryTime = expiryTime;
    }
    public  Timestamp getExpiryTime()    
    {
        return expiryTime;
    }
    public  void setIsDeleted(Boolean isDeleted)    
    {
        this.isDeleted = isDeleted;
    }
    public  Boolean getIsDeleted()    
    {
        return isDeleted;
    }
    public  ViewMemberCardServiceTimeLog copy()    
    {
        ViewMemberCardServiceTimeLog entity = null;
        try        
        {
            entity = (ViewMemberCardServiceTimeLog) super.clone();
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