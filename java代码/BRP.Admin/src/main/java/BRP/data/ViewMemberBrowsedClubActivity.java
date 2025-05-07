package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewMemberBrowsedClubActivity extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  Integer clubActivityId;//俱乐部活动ID;
    private  Timestamp createTime;//创建时间;
    private  String clubActivityName;//活动名称;
    private  Timestamp clubActivityCreateTime;//俱乐部活动创建时间;
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
    public  void setClubActivityId(Integer clubActivityId)    
    {
        this.clubActivityId = clubActivityId;
    }
    public  Integer getClubActivityId()    
    {
        return clubActivityId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setClubActivityName(String clubActivityName)    
    {
        this.clubActivityName = clubActivityName;
    }
    public  String getClubActivityName()    
    {
        return clubActivityName;
    }
    public  void setClubActivityCreateTime(Timestamp clubActivityCreateTime)    
    {
        this.clubActivityCreateTime = clubActivityCreateTime;
    }
    public  Timestamp getClubActivityCreateTime()    
    {
        return clubActivityCreateTime;
    }
    public  ViewMemberBrowsedClubActivity copy()    
    {
        ViewMemberBrowsedClubActivity entity = null;
        try        
        {
            entity = (ViewMemberBrowsedClubActivity) super.clone();
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