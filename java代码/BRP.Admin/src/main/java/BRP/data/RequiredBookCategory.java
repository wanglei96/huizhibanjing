package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class RequiredBookCategory extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;
    private  String name;//分类名称;
    private  Integer parentId;//上级ID;
    private  Integer imageFileId;//图片ID;
    private  Integer displayOrder;//排序;
    private  Boolean enabled;//是否启用;
    private  String description;//描述;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//修改时间;
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
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setParentId(Integer parentId)    
    {
        this.parentId = parentId;
    }
    public  Integer getParentId()    
    {
        return parentId;
    }
    public  void setImageFileId(Integer imageFileId)    
    {
        this.imageFileId = imageFileId;
    }
    public  Integer getImageFileId()    
    {
        return imageFileId;
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
    public  void setDescription(String description)    
    {
        this.description = description;
    }
    public  String getDescription()    
    {
        return description;
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
    public  RequiredBookCategory copy()    
    {
        RequiredBookCategory entity = null;
        try        
        {
            entity = (RequiredBookCategory) super.clone();
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