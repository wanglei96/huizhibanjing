package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CmsContentLanguage extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String title;//标题;
    private  String subTitle;//副标题;
    private  String url;//外部链接;
    private  String text;//内容;
    private  String summary;//摘要;
    private  String author;//作者;
    private  String source;//来源;
    private  String remark;//备注;
    private  String description;//描述;
    private  String keywords;//关键字;
    private  Integer pageView;//浏览量;
    private  Integer thumbFileId;//缩略图ID;
    private  Integer attachmentFileId;//附件ID;
    private  Integer videoFileId;//视频文件ID;
    private  Integer cmsContentId;//内容ID;
    private  String cmsLanguageCode;//语言编码;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setTitle(String title)    
    {
        this.title = title;
    }
    public  String getTitle()    
    {
        return title;
    }
    public  void setSubTitle(String subTitle)    
    {
        this.subTitle = subTitle;
    }
    public  String getSubTitle()    
    {
        return subTitle;
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
    public  void setSummary(String summary)    
    {
        this.summary = summary;
    }
    public  String getSummary()    
    {
        return summary;
    }
    public  void setAuthor(String author)    
    {
        this.author = author;
    }
    public  String getAuthor()    
    {
        return author;
    }
    public  void setSource(String source)    
    {
        this.source = source;
    }
    public  String getSource()    
    {
        return source;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setDescription(String description)    
    {
        this.description = description;
    }
    public  String getDescription()    
    {
        return description;
    }
    public  void setKeywords(String keywords)    
    {
        this.keywords = keywords;
    }
    public  String getKeywords()    
    {
        return keywords;
    }
    public  void setPageView(Integer pageView)    
    {
        this.pageView = pageView;
    }
    public  Integer getPageView()    
    {
        return pageView;
    }
    public  void setThumbFileId(Integer thumbFileId)    
    {
        this.thumbFileId = thumbFileId;
    }
    public  Integer getThumbFileId()    
    {
        return thumbFileId;
    }
    public  void setAttachmentFileId(Integer attachmentFileId)    
    {
        this.attachmentFileId = attachmentFileId;
    }
    public  Integer getAttachmentFileId()    
    {
        return attachmentFileId;
    }
    public  void setVideoFileId(Integer videoFileId)    
    {
        this.videoFileId = videoFileId;
    }
    public  Integer getVideoFileId()    
    {
        return videoFileId;
    }
    public  void setCmsContentId(Integer cmsContentId)    
    {
        this.cmsContentId = cmsContentId;
    }
    public  Integer getCmsContentId()    
    {
        return cmsContentId;
    }
    public  void setCmsLanguageCode(String cmsLanguageCode)    
    {
        this.cmsLanguageCode = cmsLanguageCode;
    }
    public  String getCmsLanguageCode()    
    {
        return cmsLanguageCode;
    }
    public  CmsContentLanguage copy()    
    {
        CmsContentLanguage entity = null;
        try        
        {
            entity = (CmsContentLanguage) super.clone();
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