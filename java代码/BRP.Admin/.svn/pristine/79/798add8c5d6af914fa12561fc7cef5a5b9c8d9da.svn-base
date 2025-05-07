package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ClubActivityCategory extends DataEntity implements Cloneable
{
    private  String code;//;
    private  String name;//;
    private  String description;//;
    private  Integer displayOrder;//;
    private  Boolean enabled;//;
    private  Timestamp createTime;//;
    private  Timestamp updateTime;//;
    private  Boolean isDeleted;
    public  void setCode(String code)    
    {
        this.code = code;
    }
    public  String getCode()    
    {
        return code;
    }
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setDescription(String description)    
    {
        this.description = description;
    }
    public  String getDescription()    
    {
        return description;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setEnabled(Boolean enabled)    
    {
        this.enabled = enabled;
    }
    public  Boolean getEnabled()    
    {
        return enabled;
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
    public  ClubActivityCategory copy()    
    {
        ClubActivityCategory entity = null;
        try        
        {
            entity = (ClubActivityCategory) super.clone();
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