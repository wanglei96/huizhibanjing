package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ActivityRecommendLog extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberCardId;//会员卡ID;
    private  Integer days;//天数;
    private  Timestamp createTime;//创建时间;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setMemberCardId(Integer memberCardId)    
    {
        this.memberCardId = memberCardId;
    }
    public  Integer getMemberCardId()    
    {
        return memberCardId;
    }
    public  void setDays(Integer days)    
    {
        this.days = days;
    }
    public  Integer getDays()    
    {
        return days;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  ActivityRecommendLog copy()    
    {
        ActivityRecommendLog entity = null;
        try        
        {
            entity = (ActivityRecommendLog) super.clone();
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