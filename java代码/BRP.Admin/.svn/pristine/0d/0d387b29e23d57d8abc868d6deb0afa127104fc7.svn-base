package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ActivityRecommendMember extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer referrerCardId;//推荐人会员卡ID;
    private  Integer newMemberCardId;//新会员会员卡ID;
    private  Integer activityRecommendId;//老带新ID;
    private  Integer displayOrder;
    private  Timestamp newExpireDate;
    private  Timestamp createTime;//创建时间;
    private  Timestamp referrerExpireDate;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
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
    public  void setActivityRecommendId(Integer activityRecommendId)    
    {
        this.activityRecommendId = activityRecommendId;
    }
    public  Integer getActivityRecommendId()    
    {
        return activityRecommendId;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setNewExpireDate(Timestamp newExpireDate)    
    {
        this.newExpireDate = newExpireDate;
    }
    public  Timestamp getNewExpireDate()    
    {
        return newExpireDate;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setReferrerExpireDate(Timestamp referrerExpireDate)    
    {
        this.referrerExpireDate = referrerExpireDate;
    }
    public  Timestamp getReferrerExpireDate()    
    {
        return referrerExpireDate;
    }
    public  ActivityRecommendMember copy()    
    {
        ActivityRecommendMember entity = null;
        try        
        {
            entity = (ActivityRecommendMember) super.clone();
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