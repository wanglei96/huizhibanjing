package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class CmsContent extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String title;//;
    private  String subTitle;//;
    private  String cmsContentFilePath;//生成内容文件的地址;
    private  String url;//外部链接;
    private  String text;//内容;
    private  String summary;//内容摘要;
    private  String author;//作者;
    private  String source;//来源;
    private  Boolean isRecommend;//是否推荐;
    private  Boolean isHot;//是否热点;
    private  Boolean isTop;//是否置顶;
    private  Boolean isHighlight;//是否醒目;
    private  Timestamp publishDate;//添加日期;
    private  Integer cmsChannelId;//所属栏目;
    private  String cmsContentStatusCode;//内容状态编码;
    private  String remark;//备注;
    private  Integer displayOrder;//;
    private  Integer imageFileId;//图片文件ID;
    private  Integer attachmentFileId;//附件的文件ID;
    private  Integer videoFileId;//内容视频的文件ID;
    private  Integer thumbFileId;//缩略图文件ID;
    private  String description;//描述;
    private  String keywords;//关键字;
    private  Integer hits;//浏览量;
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
    public  void setCmsContentFilePath(String cmsContentFilePath)    
    {
        this.cmsContentFilePath = cmsContentFilePath;
    }
    public  String getCmsContentFilePath()    
    {
        return cmsContentFilePath;
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
    public  void setIsRecommend(Boolean isRecommend)    
    {
        this.isRecommend = isRecommend;
    }
    public  Boolean getIsRecommend()    
    {
        return isRecommend;
    }
    public  void setIsHot(Boolean isHot)    
    {
        this.isHot = isHot;
    }
    public  Boolean getIsHot()    
    {
        return isHot;
    }
    public  void setIsTop(Boolean isTop)    
    {
        this.isTop = isTop;
    }
    public  Boolean getIsTop()    
    {
        return isTop;
    }
    public  void setIsHighlight(Boolean isHighlight)    
    {
        this.isHighlight = isHighlight;
    }
    public  Boolean getIsHighlight()    
    {
        return isHighlight;
    }
    public  void setPublishDate(Timestamp publishDate)    
    {
        this.publishDate = publishDate;
    }
    public  Timestamp getPublishDate()    
    {
        return publishDate;
    }
    public  void setCmsChannelId(Integer cmsChannelId)    
    {
        this.cmsChannelId = cmsChannelId;
    }
    public  Integer getCmsChannelId()    
    {
        return cmsChannelId;
    }
    public  void setCmsContentStatusCode(String cmsContentStatusCode)    
    {
        this.cmsContentStatusCode = cmsContentStatusCode;
    }
    public  String getCmsContentStatusCode()    
    {
        return cmsContentStatusCode;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setImageFileId(Integer imageFileId)    
    {
        this.imageFileId = imageFileId;
    }
    public  Integer getImageFileId()    
    {
        return imageFileId;
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
    public  void setThumbFileId(Integer thumbFileId)    
    {
        this.thumbFileId = thumbFileId;
    }
    public  Integer getThumbFileId()    
    {
        return thumbFileId;
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
    public  void setHits(Integer hits)    
    {
        this.hits = hits;
    }
    public  Integer getHits()    
    {
        return hits;
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
    public  CmsContent copy()    
    {
        CmsContent entity = null;
        try        
        {
            entity = (CmsContent) super.clone();
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