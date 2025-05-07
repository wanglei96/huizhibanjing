package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class ViewSalesOrder extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String salesOrderNo;//订单编号;
    private  BigDecimal amount;//支付金额;
    private  String paymentMethodCode;//付款方式编码;
    private  String salesOrderTypeCode;//订单类型编码;
    private  Boolean isPaid;//是否支付成功;
    private  String salesOrderStatusCode;//书箱订单状态编码;
    private  Integer companyUserId;//操作人;
    private  String receiverName;//收货人;
    private  String receiverPhone;//收货人电话;
    private  String receiverAddress;//收货地址;
    private  Timestamp payTime;//付款时间;
    private  String logisticsInfo;//物流信息 ;
    private  Timestamp deliveredTime;//发货时间;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//修改时间;
    private  String isPaidName;//是否支付名称;
    private  String companyNo;//门店编号;
    private  String companyName;//门店名称;
    private  String paymentMethodName;//支付方式;
    private  String salesOrderTypeName;//订单类型;
    private  String salesOrderStatusName;//订单状态;
    private  String companyUserName;//公司名称;
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
    public  void setSalesOrderNo(String salesOrderNo)    
    {
        this.salesOrderNo = salesOrderNo;
    }
    public  String getSalesOrderNo()    
    {
        return salesOrderNo;
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
    public  void setSalesOrderTypeCode(String salesOrderTypeCode)    
    {
        this.salesOrderTypeCode = salesOrderTypeCode;
    }
    public  String getSalesOrderTypeCode()    
    {
        return salesOrderTypeCode;
    }
    public  void setIsPaid(Boolean isPaid)    
    {
        this.isPaid = isPaid;
    }
    public  Boolean getIsPaid()    
    {
        return isPaid;
    }
    public  void setSalesOrderStatusCode(String salesOrderStatusCode)    
    {
        this.salesOrderStatusCode = salesOrderStatusCode;
    }
    public  String getSalesOrderStatusCode()    
    {
        return salesOrderStatusCode;
    }
    public  void setCompanyUserId(Integer companyUserId)    
    {
        this.companyUserId = companyUserId;
    }
    public  Integer getCompanyUserId()    
    {
        return companyUserId;
    }
    public  void setReceiverName(String receiverName)    
    {
        this.receiverName = receiverName;
    }
    public  String getReceiverName()    
    {
        return receiverName;
    }
    public  void setReceiverPhone(String receiverPhone)    
    {
        this.receiverPhone = receiverPhone;
    }
    public  String getReceiverPhone()    
    {
        return receiverPhone;
    }
    public  void setReceiverAddress(String receiverAddress)    
    {
        this.receiverAddress = receiverAddress;
    }
    public  String getReceiverAddress()    
    {
        return receiverAddress;
    }
    public  void setPayTime(Timestamp payTime)    
    {
        this.payTime = payTime;
    }
    public  Timestamp getPayTime()    
    {
        return payTime;
    }
    public  void setLogisticsInfo(String logisticsInfo)    
    {
        this.logisticsInfo = logisticsInfo;
    }
    public  String getLogisticsInfo()    
    {
        return logisticsInfo;
    }
    public  void setDeliveredTime(Timestamp deliveredTime)    
    {
        this.deliveredTime = deliveredTime;
    }
    public  Timestamp getDeliveredTime()    
    {
        return deliveredTime;
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
    public  void setIsPaidName(String isPaidName)    
    {
        this.isPaidName = isPaidName;
    }
    public  String getIsPaidName()    
    {
        return isPaidName;
    }
    public  void setCompanyNo(String companyNo)    
    {
        this.companyNo = companyNo;
    }
    public  String getCompanyNo()    
    {
        return companyNo;
    }
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setPaymentMethodName(String paymentMethodName)    
    {
        this.paymentMethodName = paymentMethodName;
    }
    public  String getPaymentMethodName()    
    {
        return paymentMethodName;
    }
    public  void setSalesOrderTypeName(String salesOrderTypeName)    
    {
        this.salesOrderTypeName = salesOrderTypeName;
    }
    public  String getSalesOrderTypeName()    
    {
        return salesOrderTypeName;
    }
    public  void setSalesOrderStatusName(String salesOrderStatusName)    
    {
        this.salesOrderStatusName = salesOrderStatusName;
    }
    public  String getSalesOrderStatusName()    
    {
        return salesOrderStatusName;
    }
    public  void setCompanyUserName(String companyUserName)    
    {
        this.companyUserName = companyUserName;
    }
    public  String getCompanyUserName()    
    {
        return companyUserName;
    }
    public  ViewSalesOrder copy()    
    {
        ViewSalesOrder entity = null;
        try        
        {
            entity = (ViewSalesOrder) super.clone();
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