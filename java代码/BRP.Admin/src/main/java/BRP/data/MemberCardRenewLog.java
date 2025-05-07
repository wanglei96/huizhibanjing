package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class MemberCardRenewLog extends DataEntity implements Cloneable
{
    private  Integer id;//id;
    private  Integer memberId;//会员id;
    private  Integer memberCardId;//会员卡id;
    private  Integer activityId;//活动id;
    private  BigDecimal oldAmount;//原续费价格;
    private  BigDecimal amount;//续费价格;
    private  BigDecimal newAmount;
    private  String paymentMethodCode;//支付方式;
    private  Integer employeeId;//办理人;
    private  String remark;//备注;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//修改时间;
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
    public  void setMemberCardId(Integer memberCardId)    
    {
        this.memberCardId = memberCardId;
    }
    public  Integer getMemberCardId()    
    {
        return memberCardId;
    }
    public  void setActivityId(Integer activityId)    
    {
        this.activityId = activityId;
    }
    public  Integer getActivityId()    
    {
        return activityId;
    }
    public  void setOldAmount(BigDecimal oldAmount)    
    {
        this.oldAmount = oldAmount;
    }
    public  BigDecimal getOldAmount()    
    {
        return oldAmount;
    }
    public  void setAmount(BigDecimal amount)    
    {
        this.amount = amount;
    }
    public  BigDecimal getAmount()    
    {
        return amount;
    }
    public  void setNewAmount(BigDecimal newAmount)    
    {
        this.newAmount = newAmount;
    }
    public  BigDecimal getNewAmount()    
    {
        return newAmount;
    }
    public  void setPaymentMethodCode(String paymentMethodCode)    
    {
        this.paymentMethodCode = paymentMethodCode;
    }
    public  String getPaymentMethodCode()    
    {
        return paymentMethodCode;
    }
    public  void setEmployeeId(Integer employeeId)    
    {
        this.employeeId = employeeId;
    }
    public  Integer getEmployeeId()    
    {
        return employeeId;
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
    public  MemberCardRenewLog copy()    
    {
        MemberCardRenewLog entity = null;
        try        
        {
            entity = (MemberCardRenewLog) super.clone();
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