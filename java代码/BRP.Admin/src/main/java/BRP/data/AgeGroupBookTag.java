package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class AgeGroupBookTag extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer ageGroupId;//年龄段ID;
    private  Integer bookTagId;//书签ID;
    private  Timestamp createTime;//创建时间;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setAgeGroupId(Integer ageGroupId)    
    {
        this.ageGroupId = ageGroupId;
    }
    public  Integer getAgeGroupId()    
    {
        return ageGroupId;
    }
    public  void setBookTagId(Integer bookTagId)    
    {
        this.bookTagId = bookTagId;
    }
    public  Integer getBookTagId()    
    {
        return bookTagId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  AgeGroupBookTag copy()    
    {
        AgeGroupBookTag entity = null;
        try        
        {
            entity = (AgeGroupBookTag) super.clone();
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