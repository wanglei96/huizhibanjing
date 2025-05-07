package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ActivityServiceTime extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  Integer activityId;//活动ID;
    private  String activityFreeTypeCode;//赠送类型编码;
    private  Integer memberCardId;//会员卡ID;
    private  Integer days;//天数;
    private  String remark;//备注;
    private  Timestamp expireDate;
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
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setActivityId(Integer activityId)    
    {
        this.activityId = activityId;
    }
    public  Integer getActivityId()    
    {
        return activityId;
    }
    public  void setActivityFreeTypeCode(String activityFreeTypeCode)    
    {
        this.activityFreeTypeCode = activityFreeTypeCode;
    }
    public  String getActivityFreeTypeCode()    
    {
        return activityFreeTypeCode;
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
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setExpireDate(Timestamp expireDate)    
    {
        this.expireDate = expireDate;
    }
    public  Timestamp getExpireDate()    
    {
        return expireDate;
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
    public  ActivityServiceTime copy()    
    {
        ActivityServiceTime entity = null;
        try        
        {
            entity = (ActivityServiceTime) super.clone();
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