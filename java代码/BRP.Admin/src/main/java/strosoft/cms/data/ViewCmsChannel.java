package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewCmsChannel extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String code;//;
    private  String name;//标题;
    private  String title;//标题;
    private  String keyword;//;
    private  Timestamp createTime;//时间;
    private  String url;//;
    private  String text;//;
    private  Integer parentId;//上级栏目ID;
    private  Integer cmsChannelTemplateId;//栏目模板类型;
    private  Integer cmsContentTemplateId;//内容模板类型;
    private  String cmsChannelTemplateName;//;
    private  String cmsContentTemplateName;//;
    private  String description;//;
    private  Integer imageFileId;//栏目图片文件ID;
    private  String imageFilePath;//;
    private  String imageFileName;//;
    private  Integer displayOrder;//排序;
    private  Integer cmsSiteId;//所属站点ID;
    private  String contentType;//内容类型;
    private  String cmsTemplateUrl;//;
    private  String cmsChannelTemplatePath;//;
    private  String createFilePath;//;
    private  String parentName;//标题;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
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
    public  void setTitle(String title)    
    {
        this.title = title;
    }
    public  String getTitle()    
    {
        return title;
    }
    public  void setKeyword(String keyword)    
    {
        this.keyword = keyword;
    }
    public  String getKeyword()    
    {
        return keyword;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setUrl(String url)    
    {
        this.url = url;
    }
    public  String getUrl()    
    {
        return url;
    }
    public  void setText(String text)    
    {
        this.text = text;
    }
    public  String getText()    
    {
        return text;
    }
    public  void setParentId(Integer parentId)    
    {
        this.parentId = parentId;
    }
    public  Integer getParentId()    
    {
        return parentId;
    }
    public  void setCmsChannelTemplateId(Integer cmsChannelTemplateId)    
    {
        this.cmsChannelTemplateId = cmsChannelTemplateId;
    }
    public  Integer getCmsChannelTemplateId()    
    {
        return cmsChannelTemplateId;
    }
    public  void setCmsContentTemplateId(Integer cmsContentTemplateId)    
    {
        this.cmsContentTemplateId = cmsContentTemplateId;
    }
    public  Integer getCmsContentTemplateId()    
    {
        return cmsContentTemplateId;
    }
    public  void setCmsChannelTemplateName(String cmsChannelTemplateName)    
    {
        this.cmsChannelTemplateName = cmsChannelTemplateName;
    }
    public  String getCmsChannelTemplateName()    
    {
        return cmsChannelTemplateName;
    }
    public  void setCmsContentTemplateName(String cmsContentTemplateName)    
    {
        this.cmsContentTemplateName = cmsContentTemplateName;
    }
    public  String getCmsContentTemplateName()    
    {
        return cmsContentTemplateName;
    }
    public  void setDescription(String description)    
    {
        this.description = description;
    }
    public  String getDescription()    
    {
        return description;
    }
    public  void setImageFileId(Integer imageFileId)    
    {
        this.imageFileId = imageFileId;
    }
    public  Integer getImageFileId()    
    {
        return imageFileId;
    }
    public  void setImageFilePath(String imageFilePath)    
    {
        this.imageFilePath = imageFilePath;
    }
    public  String getImageFilePath()    
    {
        return imageFilePath;
    }
    public  void setImageFileName(String imageFileName)    
    {
        this.imageFileName = imageFileName;
    }
    public  String getImageFileName()    
    {
        return imageFileName;
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
    public  void setContentType(String contentType)    
    {
        this.contentType = contentType;
    }
    public  String getContentType()    
    {
        return contentType;
    }
    public  void setCmsTemplateUrl(String cmsTemplateUrl)    
    {
        this.cmsTemplateUrl = cmsTemplateUrl;
    }
    public  String getCmsTemplateUrl()    
    {
        return cmsTemplateUrl;
    }
    public  void setCmsChannelTemplatePath(String cmsChannelTemplatePath)    
    {
        this.cmsChannelTemplatePath = cmsChannelTemplatePath;
    }
    public  String getCmsChannelTemplatePath()    
    {
        return cmsChannelTemplatePath;
    }
    public  void setCreateFilePath(String createFilePath)    
    {
        this.createFilePath = createFilePath;
    }
    public  String getCreateFilePath()    
    {
        return createFilePath;
    }
    public  void setParentName(String parentName)    
    {
        this.parentName = parentName;
    }
    public  String getParentName()    
    {
        return parentName;
    }
    public  ViewCmsChannel copy()    
    {
        ViewCmsChannel entity = null;
        try        
        {
            entity = (ViewCmsChannel) super.clone();
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