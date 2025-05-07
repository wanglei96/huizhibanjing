package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewCompanyOrganization extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String name;//名称;
    private  Integer displayOrder;//排序;
    private  String description;//描述;
    private  Timestamp createTime;//创建时间;
    private  Integer companyId;//公司ID;
    private  Integer parentId;//父级ID;
    private  Boolean isDeleted;//是否删除;
    private  String parentName;//名称;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
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
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setParentId(Integer parentId)    
    {
        this.parentId = parentId;
    }
    public  Integer getParentId()    
    {
        return parentId;
    }
    public  void setIsDeleted(Boolean isDeleted)    
    {
        this.isDeleted = isDeleted;
    }
    public  Boolean getIsDeleted()    
    {
        return isDeleted;
    }
    public  void setParentName(String parentName)    
    {
        this.parentName = parentName;
    }
    public  String getParentName()    
    {
        return parentName;
    }
    public  ViewCompanyOrganization copy()    
    {
        ViewCompanyOrganization entity = null;
        try        
        {
            entity = (ViewCompanyOrganization) super.clone();
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