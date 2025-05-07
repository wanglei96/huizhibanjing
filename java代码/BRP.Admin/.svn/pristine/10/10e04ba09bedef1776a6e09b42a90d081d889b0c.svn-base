package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewReadActivityMember extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer readActivityId;//阅读活动ID;
    private  Integer memberId;//会员ID;
    private  Timestamp createTime;//创建时间;
    private  String name;//名称;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setReadActivityId(Integer readActivityId)    
    {
        this.readActivityId = readActivityId;
    }
    public  Integer getReadActivityId()    
    {
        return readActivityId;
    }
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  ViewReadActivityMember copy()    
    {
        ViewReadActivityMember entity = null;
        try        
        {
            entity = (ViewReadActivityMember) super.clone();
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