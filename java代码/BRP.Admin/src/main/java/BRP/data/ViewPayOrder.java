package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class ViewPayOrder extends DataEntity implements Cloneable
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
    private  Integer companyId;//门店ID;
    private  Integer bookId;//图书ID;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String memberNo;//会员编号;
    private  String memberName;//会员名称;
    private  String memberMobilePhone;//联系电话;
    private  Boolean memberGender;//性别（1男，0女）;
    private  String memberCardNo;//会员卡号;
    private  String paymentMethodName;//支付方式;
    private  String paymentItemName;//付款项目;
    private  String payOrderTypeName;//账单类型;
    private  String operatorName;//公司名称;
    private  String companyName;//门店名称;
    private  String companyNo;//门店编号;
    private  Integer bookSkuId;//skuID;
    private  String bookSn;//条形码;
    private  String bookName;//书名;
    private  String memberCardTypeName;
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
    public  void setMemberNo(String memberNo)    
    {
        this.memberNo = memberNo;
    }
    public  String getMemberNo()    
    {
        return memberNo;
    }
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  void setMemberMobilePhone(String memberMobilePhone)    
    {
        this.memberMobilePhone = memberMobilePhone;
    }
    public  String getMemberMobilePhone()    
    {
        return memberMobilePhone;
    }
    public  void setMemberGender(Boolean memberGender)    
    {
        this.memberGender = memberGender;
    }
    public  Boolean getMemberGender()    
    {
        return memberGender;
    }
    public  void setMemberCardNo(String memberCardNo)    
    {
        this.memberCardNo = memberCardNo;
    }
    public  String getMemberCardNo()    
    {
        return memberCardNo;
    }
    public  void setPaymentMethodName(String paymentMethodName)    
    {
        this.paymentMethodName = paymentMethodName;
    }
    public  String getPaymentMethodName()    
    {
        return paymentMethodName;
    }
    public  void setPaymentItemName(String paymentItemName)    
    {
        this.paymentItemName = paymentItemName;
    }
    public  String getPaymentItemName()    
    {
        return paymentItemName;
    }
    public  void setPayOrderTypeName(String payOrderTypeName)    
    {
        this.payOrderTypeName = payOrderTypeName;
    }
    public  String getPayOrderTypeName()    
    {
        return payOrderTypeName;
    }
    public  void setOperatorName(String operatorName)    
    {
        this.operatorName = operatorName;
    }
    public  String getOperatorName()    
    {
        return operatorName;
    }
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setCompanyNo(String companyNo)    
    {
        this.companyNo = companyNo;
    }
    public  String getCompanyNo()    
    {
        return companyNo;
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
    public  void setMemberCardTypeName(String memberCardTypeName)    
    {
        this.memberCardTypeName = memberCardTypeName;
    }
    public  String getMemberCardTypeName()    
    {
        return memberCardTypeName;
    }
    public  ViewPayOrder copy()    
    {
        ViewPayOrder entity = null;
        try        
        {
            entity = (ViewPayOrder) super.clone();
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