package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class CmsChannel extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String code;//;
    private  String name;//标题;
    private  String title;//标题;
    private  String keyword;//关键字;
    private  Timestamp createTime;//时间;
    private  String url;//导航地址;
    private  String text;//正文;
    private  Integer parentId;//上级栏目ID;
    private  String contentType;//内容类型;
    private  Integer cmsChannelTemplateId;//栏目模板类型;
    private  Integer cmsContentTemplateId;//内容模板类型;
    private  String description;//页面描述;
    private  Integer displayOrder;//排序;
    private  Integer cmsSiteId;//所属站点ID;
    private  String cmsLinkTypeCode;//链接类型编码;
    private  String channelFilePathRule;//;
    private  String contentFilePathRule;//;
    private  Integer imageFileId;//栏目图片文件ID;
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
    public  void setContentType(String contentType)    
    {
        this.contentType = contentType;
    }
    public  String getContentType()    
    {
        return contentType;
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
    public  void setCmsLinkTypeCode(String cmsLinkTypeCode)    
    {
        this.cmsLinkTypeCode = cmsLinkTypeCode;
    }
    public  String getCmsLinkTypeCode()    
    {
        return cmsLinkTypeCode;
    }
    public  void setChannelFilePathRule(String channelFilePathRule)    
    {
        this.channelFilePathRule = channelFilePathRule;
    }
    public  String getChannelFilePathRule()    
    {
        return channelFilePathRule;
    }
    public  void setContentFilePathRule(String contentFilePathRule)    
    {
        this.contentFilePathRule = contentFilePathRule;
    }
    public  String getContentFilePathRule()    
    {
        return contentFilePathRule;
    }
    public  void setImageFileId(Integer imageFileId)    
    {
        this.imageFileId = imageFileId;
    }
    public  Integer getImageFileId()    
    {
        return imageFileId;
    }
    public  CmsChannel copy()    
    {
        CmsChannel entity = null;
        try        
        {
            entity = (CmsChannel) super.clone();
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