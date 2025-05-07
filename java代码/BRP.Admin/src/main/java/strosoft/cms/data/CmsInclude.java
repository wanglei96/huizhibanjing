package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CmsInclude extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String name;//;
    private  String description;//;
    private  String path;//;
    private  String displayOrder;//;
    private  String encode;//;
    private  String cmsIncludeContent;//;
    private  Integer cmsSiteId;//;
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
    public  void setDisplayOrder(String displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  String getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setEncode(String encode)    
    {
        this.encode = encode;
    }
    public  String getEncode()    
    {
        return encode;
    }
    public  void setCmsIncludeContent(String cmsIncludeContent)    
    {
        this.cmsIncludeContent = cmsIncludeContent;
    }
    public  String getCmsIncludeContent()    
    {
        return cmsIncludeContent;
    }
    public  void setCmsSiteId(Integer cmsSiteId)    
    {
        this.cmsSiteId = cmsSiteId;
    }
    public  Integer getCmsSiteId()    
    {
        return cmsSiteId;
    }
    public  CmsInclude copy()    
    {
        CmsInclude entity = null;
        try        
        {
            entity = (CmsInclude) super.clone();
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