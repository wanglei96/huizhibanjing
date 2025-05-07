package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ActivityRecommendError extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String memberCardNo;//会员卡号;
    private  String referrerCardNo;
    private  String errorContext;//错误信息;
    private  Integer activityRecommendId;//老带新ID;
    private  Integer displayOrder;
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
    public  void setMemberCardNo(String memberCardNo)    
    {
        this.memberCardNo = memberCardNo;
    }
    public  String getMemberCardNo()    
    {
        return memberCardNo;
    }
    public  void setReferrerCardNo(String referrerCardNo)    
    {
        this.referrerCardNo = referrerCardNo;
    }
    public  String getReferrerCardNo()    
    {
        return referrerCardNo;
    }
    public  void setErrorContext(String errorContext)    
    {
        this.errorContext = errorContext;
    }
    public  String getErrorContext()    
    {
        return errorContext;
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
    public  ActivityRecommendError copy()    
    {
        ActivityRecommendError entity = null;
        try        
        {
            entity = (ActivityRecommendError) super.clone();
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