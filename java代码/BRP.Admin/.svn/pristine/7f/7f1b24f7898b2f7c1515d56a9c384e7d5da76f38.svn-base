package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
import java.math.BigDecimal;
public class RetailOrder extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String retailOrderNo;//订单编号;
    private  Integer companyId;//门店ID;
    private  Integer memberId;//会员ID;
    private  Integer clubActivityId;//报名活动ID;
    private  Integer storyVideoId;//晚安故事ID;
    private  Integer nightStoryTypeId;//晚安故事购买类型ID;
    private  Integer nightStoryDays;//晚安故事订阅天数;
    private  Timestamp businessDate;//订单日期;
    private  Timestamp payTime;//支付时间;
    private  String wxPayTransactionId;//微信支付交易号;
    private  BigDecimal amount;//金额;
    private  String retailOrderTypeCode;//零售单类型编码;
    private  String retailOrderStatusCode;//零售单状态编码;
    private  Boolean settled;//是否已结算;
    private  Boolean isStatistics;
    private  String name;
    private  String mobilePhone;
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
    public  void setRetailOrderNo(String retailOrderNo)    
    {
        this.retailOrderNo = retailOrderNo;
    }
    public  String getRetailOrderNo()    
    {
        return retailOrderNo;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setClubActivityId(Integer clubActivityId)    
    {
        this.clubActivityId = clubActivityId;
    }
    public  Integer getClubActivityId()    
    {
        return clubActivityId;
    }
    public  void setStoryVideoId(Integer storyVideoId)    
    {
        this.storyVideoId = storyVideoId;
    }
    public  Integer getStoryVideoId()    
    {
        return storyVideoId;
    }
    public  void setNightStoryTypeId(Integer nightStoryTypeId)    
    {
        this.nightStoryTypeId = nightStoryTypeId;
    }
    public  Integer getNightStoryTypeId()    
    {
        return nightStoryTypeId;
    }
    public  void setNightStoryDays(Integer nightStoryDays)    
    {
        this.nightStoryDays = nightStoryDays;
    }
    public  Integer getNightStoryDays()    
    {
        return nightStoryDays;
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
    public  void setWxPayTransactionId(String wxPayTransactionId)    
    {
        this.wxPayTransactionId = wxPayTransactionId;
    }
    public  String getWxPayTransactionId()    
    {
        return wxPayTransactionId;
    }
    public  void setAmount(BigDecimal amount)    
    {
        this.amount = amount;
    }
    public  BigDecimal getAmount()    
    {
        return amount;
    }
    public  void setRetailOrderTypeCode(String retailOrderTypeCode)    
    {
        this.retailOrderTypeCode = retailOrderTypeCode;
    }
    public  String getRetailOrderTypeCode()    
    {
        return retailOrderTypeCode;
    }
    public  void setRetailOrderStatusCode(String retailOrderStatusCode)    
    {
        this.retailOrderStatusCode = retailOrderStatusCode;
    }
    public  String getRetailOrderStatusCode()    
    {
        return retailOrderStatusCode;
    }
    public  void setSettled(Boolean settled)    
    {
        this.settled = settled;
    }
    public  Boolean getSettled()    
    {
        return settled;
    }
    public  void setIsStatistics(Boolean isStatistics)    
    {
        this.isStatistics = isStatistics;
    }
    public  Boolean getIsStatistics()    
    {
        return isStatistics;
    }
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setMobilePhone(String mobilePhone)    
    {
        this.mobilePhone = mobilePhone;
    }
    public  String getMobilePhone()    
    {
        return mobilePhone;
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
    public  RetailOrder copy()    
    {
        RetailOrder entity = null;
        try        
        {
            entity = (RetailOrder) super.clone();
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