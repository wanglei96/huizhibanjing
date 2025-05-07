package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class ViewCmsSite extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String name;//站点名称;
    private  String siteTypeCode;//站点类型;
    private  String description;//描述;
    private  String path;//;
    private  Integer displayOrder;//;
    private  String typeName;//;
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
    public  void setSiteTypeCode(String siteTypeCode)    
    {
        this.siteTypeCode = siteTypeCode;
    }
    public  String getSiteTypeCode()    
    {
        return siteTypeCode;
    }
    public  void setDescription(String description)    
    {
        this.description = description;
    }
    public  String getDescription()    
    {
        return description;
    }
    public  void setPath(String path)    
    {
        this.path = path;
    }
    public  String getPath()    
    {
        return path;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setTypeName(String typeName)    
    {
        this.typeName = typeName;
    }
    public  String getTypeName()    
    {
        return typeName;
    }
    public  ViewCmsSite copy()    
    {
        ViewCmsSite entity = null;
        try        
        {
            entity = (ViewCmsSite) super.clone();
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