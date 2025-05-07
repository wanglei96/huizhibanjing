package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class MemberBookbox extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberCardId;//会员卡ID;
    private  BigDecimal bookboxAmount;//书箱费;
    private  Timestamp payTime;//缴费时间;
    private  String paymentMethodCode;//支付方式编码;
    private  Boolean isValid;//是否有效;
    private  Boolean isCancel;//是否注销;
    private  Timestamp cancelTime;//注销时间;
    private  String remark;//备注;
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
    public  void setMemberCardId(Integer memberCardId)    
    {
        this.memberCardId = memberCardId;
    }
    public  Integer getMemberCardId()    
    {
        return memberCardId;
    }
    public  void setBookboxAmount(BigDecimal bookboxAmount)    
    {
        this.bookboxAmount = bookboxAmount;
    }
    public  BigDecimal getBookboxAmount()    
    {
        return bookboxAmount;
    }
    public  void setPayTime(Timestamp payTime)    
    {
        this.payTime = payTime;
    }
    public  Timestamp getPayTime()    
    {
        return payTime;
    }
    public  void setPaymentMethodCode(String paymentMethodCode)    
    {
        this.paymentMethodCode = paymentMethodCode;
    }
    public  String getPaymentMethodCode()    
    {
        return paymentMethodCode;
    }
    public  void setIsValid(Boolean isValid)    
    {
        this.isValid = isValid;
    }
    public  Boolean getIsValid()    
    {
        return isValid;
    }
    public  void setIsCancel(Boolean isCancel)    
    {
        this.isCancel = isCancel;
    }
    public  Boolean getIsCancel()    
    {
        return isCancel;
    }
    public  void setCancelTime(Timestamp cancelTime)    
    {
        this.cancelTime = cancelTime;
    }
    public  Timestamp getCancelTime()    
    {
        return cancelTime;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
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
    public  MemberBookbox copy()    
    {
        MemberBookbox entity = null;
        try        
        {
            entity = (MemberBookbox) super.clone();
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