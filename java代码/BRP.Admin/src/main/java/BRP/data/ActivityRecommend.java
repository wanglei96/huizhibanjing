package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ActivityRecommend extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  Integer referrerCardId;//推荐人ID;
    private  Integer newMemberCardId;//新会员卡ID;
    private  Integer days;//天数;
    private  Integer activityId;//活动ID;
    private  Timestamp openCardTime;//开卡时间;
    private  Timestamp createTime;//创建时间;
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
    public  ActivityRecommend copy()    
    {
        ActivityRecommend entity = null;
        try        
        {
            entity = (ActivityRecommend) super.clone();
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