package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class ViewCmsTemplate extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String name;//;
    private  String relatedFileName;//模板名称;
    private  String createdFileName;//创建的文件名称;
    private  String createdFileExtName;//生成的文件名称后缀;
    private  Integer parentId;//;
    private  String cmsTemplateTypeCode;//;
    private  String description;//;
    private  Integer displayOrder;//;
    private  Integer cmsSiteId;//;
    private  String encode;//编码;
    private  String path;//;
    private  String tName;//;
    private  String url;//;
    private  Integer isSystemDefaultChannelTemplate;//;
    private  Integer isSystemDefaultContentTemplate;//;
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
    public  void setRelatedFileName(String relatedFileName)    
    {
        this.relatedFileName = relatedFileName;
    }
    public  String getRelatedFileName()    
    {
        return relatedFileName;
    }
    public  void setCreatedFileName(String createdFileName)    
    {
        this.createdFileName = createdFileName;
    }
    public  String getCreatedFileName()    
    {
        return createdFileName;
    }
    public  void setCreatedFileExtName(String createdFileExtName)    
    {
        this.createdFileExtName = createdFileExtName;
    }
    public  String getCreatedFileExtName()    
    {
        return createdFileExtName;
    }
    public  void setParentId(Integer parentId)    
    {
        this.parentId = parentId;
    }
    public  Integer getParentId()    
    {
        return parentId;
    }
    public  void setCmsTemplateTypeCode(String cmsTemplateTypeCode)    
    {
        this.cmsTemplateTypeCode = cmsTemplateTypeCode;
    }
    public  String getCmsTemplateTypeCode()    
    {
        return cmsTemplateTypeCode;
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
    public  void setCmsSiteId(Integer cmsSiteId)    
    {
        this.cmsSiteId = cmsSiteId;
    }
    public  Integer getCmsSiteId()    
    {
        return cmsSiteId;
    }
    public  void setEncode(String encode)    
    {
        this.encode = encode;
    }
    public  String getEncode()    
    {
        return encode;
    }
    public  void setPath(String path)    
    {
        this.path = path;
    }
    public  String getPath()    
    {
        return path;
    }
    public  void setTName(String tName)    
    {
        this.tName = tName;
    }
    public  String getTName()    
    {
        return tName;
    }
    public  void setUrl(String url)    
    {
        this.url = url;
    }
    public  String getUrl()    
    {
        return url;
    }
    public  void setIsSystemDefaultChannelTemplate(Integer isSystemDefaultChannelTemplate)    
    {
        this.isSystemDefaultChannelTemplate = isSystemDefaultChannelTemplate;
    }
    public  Integer getIsSystemDefaultChannelTemplate()    
    {
        return isSystemDefaultChannelTemplate;
    }
    public  void setIsSystemDefaultContentTemplate(Integer isSystemDefaultContentTemplate)    
    {
        this.isSystemDefaultContentTemplate = isSystemDefaultContentTemplate;
    }
    public  Integer getIsSystemDefaultContentTemplate()    
    {
        return isSystemDefaultContentTemplate;
    }
    public  ViewCmsTemplate copy()    
    {
        ViewCmsTemplate entity = null;
        try        
        {
            entity = (ViewCmsTemplate) super.clone();
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