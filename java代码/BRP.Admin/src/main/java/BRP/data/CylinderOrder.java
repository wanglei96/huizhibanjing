package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class CylinderOrder extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String cylinderOrderNo;//订单编号;
    private  BigDecimal amount;//支付金额;
    private  String paymentMethodCode;//付款方式;
    private  String paymentItemCode;//付款项目;
    private  Boolean isPaid;//是否支付成功;
    private  Integer companyUserId;//操作人;
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
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setCylinderOrderNo(String cylinderOrderNo)    
    {
        this.cylinderOrderNo = cylinderOrderNo;
    }
    public  String getCylinderOrderNo()    
    {
        return cylinderOrderNo;
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
    public  void setCompanyUserId(Integer companyUserId)    
    {
        this.companyUserId = companyUserId;
    }
    public  Integer getCompanyUserId()    
    {
        return companyUserId;
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
    public  CylinderOrder copy()    
    {
        CylinderOrder entity = null;
        try        
        {
            entity = (CylinderOrder) super.clone();
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