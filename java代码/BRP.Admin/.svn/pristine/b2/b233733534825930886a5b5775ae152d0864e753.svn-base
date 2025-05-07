package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class ViewCmsComment extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String content;//;
    private  String createTime;//;
    private  String cmsCommentStatusCode;//;
    private  Integer cmsChannelId;//;
    private  Integer cmsContentId;//;
    private  Integer cmsSiteId;//;
    private  Integer displayOrder;//;
    private  String name;//标题;
    private  String title;//;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setContent(String content)    
    {
        this.content = content;
    }
    public  String getContent()    
    {
        return content;
    }
    public  void setCreateTime(String createTime)    
    {
        this.createTime = createTime;
    }
    public  String getCreateTime()    
    {
        return createTime;
    }
    public  void setCmsCommentStatusCode(String cmsCommentStatusCode)    
    {
        this.cmsCommentStatusCode = cmsCommentStatusCode;
    }
    public  String getCmsCommentStatusCode()    
    {
        return cmsCommentStatusCode;
    }
    public  void setCmsChannelId(Integer cmsChannelId)    
    {
        this.cmsChannelId = cmsChannelId;
    }
    public  Integer getCmsChannelId()    
    {
        return cmsChannelId;
    }
    public  void setCmsContentId(Integer cmsContentId)    
    {
        this.cmsContentId = cmsContentId;
    }
    public  Integer getCmsContentId()    
    {
        return cmsContentId;
    }
    public  void setCmsSiteId(Integer cmsSiteId)    
    {
        this.cmsSiteId = cmsSiteId;
    }
    public  Integer getCmsSiteId()    
    {
        return cmsSiteId;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
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
    public  ViewCmsComment copy()    
    {
        ViewCmsComment entity = null;
        try        
        {
            entity = (ViewCmsComment) super.clone();
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