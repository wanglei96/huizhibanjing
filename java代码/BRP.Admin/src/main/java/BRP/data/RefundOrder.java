package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.math.BigInteger;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class RefundOrder extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String refundTypeCode;//退款类型;
    private  Integer payOrderId;//要退款的账单ID;
    private  Integer memberId;//退款人ID;
    private  Integer bookId;//图书ID;
    private  BigInteger refundAmount;//退款金额;
    private  Integer operatorId;//操作人;
    private  BigInteger memberFee;//会员费;
    private  BigInteger bookcaseFee;//书箱费;
    private  BigInteger depositFee;//押金费;
    private  BigInteger damageFee;//折损费;
    private  Integer companyId;//门店ID;
    private  String remark;//备注;
    private  Boolean isCancelMember;
    private  Boolean isCancelBookbox;
    private  Boolean isRecoverBook;
    private  Integer refunderId;
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
    public  void setRefundTypeCode(String refundTypeCode)    
    {
        this.refundTypeCode = refundTypeCode;
    }
    public  String getRefundTypeCode()    
    {
        return refundTypeCode;
    }
    public  void setPayOrderId(Integer payOrderId)    
    {
        this.payOrderId = payOrderId;
    }
    public  Integer getPayOrderId()    
    {
        return payOrderId;
    }
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setBookId(Integer bookId)    
    {
        this.bookId = bookId;
    }
    public  Integer getBookId()    
    {
        return bookId;
    }
    public  void setRefundAmount(BigInteger refundAmount)    
    {
        this.refundAmount = refundAmount;
    }
    public  BigInteger getRefundAmount()    
    {
        return refundAmount;
    }
    public  void setOperatorId(Integer operatorId)    
    {
        this.operatorId = operatorId;
    }
    public  Integer getOperatorId()    
    {
        return operatorId;
    }
    public  void setMemberFee(BigInteger memberFee)    
    {
        this.memberFee = memberFee;
    }
    public  BigInteger getMemberFee()    
    {
        return memberFee;
    }
    public  void setBookcaseFee(BigInteger bookcaseFee)    
    {
        this.bookcaseFee = bookcaseFee;
    }
    public  BigInteger getBookcaseFee()    
    {
        return bookcaseFee;
    }
    public  void setDepositFee(BigInteger depositFee)    
    {
        this.depositFee = depositFee;
    }
    public  BigInteger getDepositFee()    
    {
        return depositFee;
    }
    public  void setDamageFee(BigInteger damageFee)    
    {
        this.damageFee = damageFee;
    }
    public  BigInteger getDamageFee()    
    {
        return damageFee;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setIsCancelMember(Boolean isCancelMember)    
    {
        this.isCancelMember = isCancelMember;
    }
    public  Boolean getIsCancelMember()    
    {
        return isCancelMember;
    }
    public  void setIsCancelBookbox(Boolean isCancelBookbox)    
    {
        this.isCancelBookbox = isCancelBookbox;
    }
    public  Boolean getIsCancelBookbox()    
    {
        return isCancelBookbox;
    }
    public  void setIsRecoverBook(Boolean isRecoverBook)    
    {
        this.isRecoverBook = isRecoverBook;
    }
    public  Boolean getIsRecoverBook()    
    {
        return isRecoverBook;
    }
    public  void setRefunderId(Integer refunderId)    
    {
        this.refunderId = refunderId;
    }
    public  Integer getRefunderId()    
    {
        return refunderId;
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
    public  RefundOrder copy()    
    {
        RefundOrder entity = null;
        try        
        {
            entity = (RefundOrder) super.clone();
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