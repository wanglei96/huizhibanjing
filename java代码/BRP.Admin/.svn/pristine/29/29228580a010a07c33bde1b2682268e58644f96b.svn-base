package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewActivityRecommend extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  Integer referrerCardId;//推荐人ID;
    private  Integer newMemberCardId;//新会员卡ID;
    private  Integer days;//天数;
    private  Integer activityId;//活动ID;
    private  Timestamp openCardTime;//开卡时间;
    private  Timestamp createTime;//创建时间;
    private  String companyName;//门店名称;
    private  String referrerCardCardNo;//会员卡号;
    private  Timestamp referrerCardEffectiveDate;//生效日期;
    private  Timestamp referrerCardExpireDate;//失效日期;
    private  String referrerName;//名称;
    private  String newMemberCardCardNo;//会员卡号;
    private  String newMemberName;//名称;
    private  String activityName;//活动名称;
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
    public  void setReferrerCardId(Integer referrerCardId)    
    {
        this.referrerCardId = referrerCardId;
    }
    public  Integer getReferrerCardId()    
    {
        return referrerCardId;
    }
    public  void setNewMemberCardId(Integer newMemberCardId)    
    {
        this.newMemberCardId = newMemberCardId;
    }
    public  Integer getNewMemberCardId()    
    {
        return newMemberCardId;
    }
    public  void setDays(Integer days)    
    {
        this.days = days;
    }
    public  Integer getDays()    
    {
        return days;
    }
    public  void setActivityId(Integer activityId)    
    {
        this.activityId = activityId;
    }
    public  Integer getActivityId()    
    {
        return activityId;
    }
    public  void setOpenCardTime(Timestamp openCardTime)    
    {
        this.openCardTime = openCardTime;
    }
    public  Timestamp getOpenCardTime()    
    {
        return openCardTime;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setReferrerCardCardNo(String referrerCardCardNo)    
    {
        this.referrerCardCardNo = referrerCardCardNo;
    }
    public  String getReferrerCardCardNo()    
    {
        return referrerCardCardNo;
    }
    public  void setReferrerCardEffectiveDate(Timestamp referrerCardEffectiveDate)    
    {
        this.referrerCardEffectiveDate = referrerCardEffectiveDate;
    }
    public  Timestamp getReferrerCardEffectiveDate()    
    {
        return referrerCardEffectiveDate;
    }
    public  void setReferrerCardExpireDate(Timestamp referrerCardExpireDate)    
    {
        this.referrerCardExpireDate = referrerCardExpireDate;
    }
    public  Timestamp getReferrerCardExpireDate()    
    {
        return referrerCardExpireDate;
    }
    public  void setReferrerName(String referrerName)    
    {
        this.referrerName = referrerName;
    }
    public  String getReferrerName()    
    {
        return referrerName;
    }
    public  void setNewMemberCardCardNo(String newMemberCardCardNo)    
    {
        this.newMemberCardCardNo = newMemberCardCardNo;
    }
    public  String getNewMemberCardCardNo()    
    {
        return newMemberCardCardNo;
    }
    public  void setNewMemberName(String newMemberName)    
    {
        this.newMemberName = newMemberName;
    }
    public  String getNewMemberName()    
    {
        return newMemberName;
    }
    public  void setActivityName(String activityName)    
    {
        this.activityName = activityName;
    }
    public  String getActivityName()    
    {
        return activityName;
    }
    public  ViewActivityRecommend copy()    
    {
        ViewActivityRecommend entity = null;
        try        
        {
            entity = (ViewActivityRecommend) super.clone();
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