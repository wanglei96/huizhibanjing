package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CmsSite extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String name;//站点名称;
    private  String siteTypeCode;//站点类型;
    private  String description;//描述;
    private  String path;//;
    private  Integer displayOrder;//;
    private  Integer parentId;//;
    private  Integer isCmsLanguage;//是否开启CMS多语言;
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
    public  void setParentId(Integer parentId)    
    {
        this.parentId = parentId;
    }
    public  Integer getParentId()    
    {
        return parentId;
    }
    public  void setIsCmsLanguage(Integer isCmsLanguage)    
    {
        this.isCmsLanguage = isCmsLanguage;
    }
    public  Integer getIsCmsLanguage()    
    {
        return isCmsLanguage;
    }
    public  CmsSite copy()    
    {
        CmsSite entity = null;
        try        
        {
            entity = (CmsSite) super.clone();
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