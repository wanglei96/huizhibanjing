package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class ViewMemberBookbox extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String memberName;//名称;
    private  String memberCardNo;//会员卡号;
    private  BigDecimal bookboxAmount;//书箱费;
    private  Timestamp payTime;//缴费时间;
    private  String paymentMethodName;//支付方式;
    private  Boolean isValid;//是否有效;
    private  Boolean isCancel;//是否注销;
    private  Timestamp cancelTime;//注销时间;
    private  String remark;//备注;
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
    public  void setPaymentMethodName(String paymentMethodName)    
    {
        this.paymentMethodName = paymentMethodName;
    }
    public  String getPaymentMethodName()    
    {
        return paymentMethodName;
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
    public  ViewMemberBookbox copy()    
    {
        ViewMemberBookbox entity = null;
        try        
        {
            entity = (ViewMemberBookbox) super.clone();
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