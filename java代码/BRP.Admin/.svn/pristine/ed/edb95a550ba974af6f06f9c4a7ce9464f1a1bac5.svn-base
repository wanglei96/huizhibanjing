package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class CompanyContent extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String title;//标题;
    private  String url;//外部链接;
    private  String text;//内容;
    private  Integer companyChannelId;//所属栏目;
    private  Integer displayOrder;//排序;
    private  Integer imageFileId;//图片文件ID;
    private  Integer attachmentFileId;//附件的文件ID;
    private  Integer companyUserId;//商户用户ID;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
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
    public  void setCompanyChannelId(Integer companyChannelId)    
    {
        this.companyChannelId = companyChannelId;
    }
    public  Integer getCompanyChannelId()    
    {
        return companyChannelId;
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
    public  void setCompanyUserId(Integer companyUserId)    
    {
        this.companyUserId = companyUserId;
    }
    public  Integer getCompanyUserId()    
    {
        return companyUserId;
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
    public  CompanyContent copy()    
    {
        CompanyContent entity = null;
        try        
        {
            entity = (CompanyContent) super.clone();
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