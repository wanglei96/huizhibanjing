package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class ViewActivity extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String name;//活动名称;
    private  String activityTypeCode;//活动类型;
    private  BigDecimal renewalPrice;//活动收费;
    private  Integer buyDays;//购买天数;
    private  Integer giftDays;//赠送天数;
    private  Integer loanableNum;//可借本数;
    private  Integer giftScore;
    private  Integer giftStopCardQuantity;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  Boolean isDeleted;
    private  String companyName;//门店名称;
    private  String activityTypeName;//活动类型;
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
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setActivityTypeCode(String activityTypeCode)    
    {
        this.activityTypeCode = activityTypeCode;
    }
    public  String getActivityTypeCode()    
    {
        return activityTypeCode;
    }
    public  void setRenewalPrice(BigDecimal renewalPrice)    
    {
        this.renewalPrice = renewalPrice;
    }
    public  BigDecimal getRenewalPrice()    
    {
        return renewalPrice;
    }
    public  void setBuyDays(Integer buyDays)    
    {
        this.buyDays = buyDays;
    }
    public  Integer getBuyDays()    
    {
        return buyDays;
    }
    public  void setGiftDays(Integer giftDays)    
    {
        this.giftDays = giftDays;
    }
    public  Integer getGiftDays()    
    {
        return giftDays;
    }
    public  void setLoanableNum(Integer loanableNum)    
    {
        this.loanableNum = loanableNum;
    }
    public  Integer getLoanableNum()    
    {
        return loanableNum;
    }
    public  void setGiftScore(Integer giftScore)    
    {
        this.giftScore = giftScore;
    }
    public  Integer getGiftScore()    
    {
        return giftScore;
    }
    public  void setGiftStopCardQuantity(Integer giftStopCardQuantity)    
    {
        this.giftStopCardQuantity = giftStopCardQuantity;
    }
    public  Integer getGiftStopCardQuantity()    
    {
        return giftStopCardQuantity;
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
    public  void setIsDeleted(Boolean isDeleted)    
    {
        this.isDeleted = isDeleted;
    }
    public  Boolean getIsDeleted()    
    {
        return isDeleted;
    }
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setActivityTypeName(String activityTypeName)    
    {
        this.activityTypeName = activityTypeName;
    }
    public  String getActivityTypeName()    
    {
        return activityTypeName;
    }
    public  ViewActivity copy()    
    {
        ViewActivity entity = null;
        try        
        {
            entity = (ViewActivity) super.clone();
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