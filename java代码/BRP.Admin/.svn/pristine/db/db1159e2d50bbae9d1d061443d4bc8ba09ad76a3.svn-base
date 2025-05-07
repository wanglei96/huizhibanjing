package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class PayOrder extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  String payOrderNo;//账单编号;
    private  BigDecimal amount;//支付金额;
    private  String paymentMethodCode;//付款方式;
    private  String paymentItemCode;//付款项目;
    private  Boolean isPaid;//是否支付成功;
    private  Timestamp businessDate;//单据时间;
    private  Timestamp payTime;//支付时间;
    private  String payOrderTypeCode;//账单类型编码;
    private  Boolean isFinished;//是否完成;
    private  Integer operatorId;//操作人;
    private  Boolean isRefund;//是否退款;
    private  Integer companyId;
    private  Integer bookId;
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
    public  void setPayOrderNo(String payOrderNo)    
    {
        this.payOrderNo = payOrderNo;
    }
    public  String getPayOrderNo()    
    {
        return payOrderNo;
    }
    public  void setAmount(BigDecimal amount)    
    {
        this.amount = amount;
    }
    public  BigDecimal getAmount()    
    {
        return amount;
    }
    public  void setPaymentMethodCode(String paymentMethodCode)    
    {
        this.paymentMethodCode = paymentMethodCode;
    }
    public  String getPaymentMethodCode()    
    {
        return paymentMethodCode;
    }
    public  void setPaymentItemCode(String paymentItemCode)    
    {
        this.paymentItemCode = paymentItemCode;
    }
    public  String getPaymentItemCode()    
    {
        return paymentItemCode;
    }
    public  void setIsPaid(Boolean isPaid)    
    {
        this.isPaid = isPaid;
    }
    public  Boolean getIsPaid()    
    {
        return isPaid;
    }
    public  void setBusinessDate(Timestamp businessDate)    
    {
        this.businessDate = businessDate;
    }
    public  Timestamp getBusinessDate()    
    {
        return businessDate;
    }
    public  void setPayTime(Timestamp payTime)    
    {
        this.payTime = payTime;
    }
    public  Timestamp getPayTime()    
    {
        return payTime;
    }
    public  void setPayOrderTypeCode(String payOrderTypeCode)    
    {
        this.payOrderTypeCode = payOrderTypeCode;
    }
    public  String getPayOrderTypeCode()    
    {
        return payOrderTypeCode;
    }
    public  void setIsFinished(Boolean isFinished)    
    {
        this.isFinished = isFinished;
    }
    public  Boolean getIsFinished()    
    {
        return isFinished;
    }
    public  void setOperatorId(Integer operatorId)    
    {
        this.operatorId = operatorId;
    }
    public  Integer getOperatorId()    
    {
        return operatorId;
    }
    public  void setIsRefund(Boolean isRefund)    
    {
        this.isRefund = isRefund;
    }
    public  Boolean getIsRefund()    
    {
        return isRefund;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setBookId(Integer bookId)    
    {
        this.bookId = bookId;
    }
    public  Integer getBookId()    
    {
        return bookId;
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
    public  PayOrder copy()    
    {
        PayOrder entity = null;
        try        
        {
            entity = (PayOrder) super.clone();
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