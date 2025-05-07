package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CmsChannelLanguage extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String name;//栏目名称;
    private  String title;//标题;
    private  String keyword;//关键字;
    private  String url;//外部链接;
    private  String text;//内容;
    private  String description;//描述;
    private  Integer imageFileId;//图片ID;
    private  String cmsLanguageCode;//语言编码;
    private  Integer cmsChannelId;//栏目ID;
    private  String channelFilePathRule;//栏目页面生成地址;
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
    public  void setCmsLanguageCode(String cmsLanguageCode)    
    {
        this.cmsLanguageCode = cmsLanguageCode;
    }
    public  String getCmsLanguageCode()    
    {
        return cmsLanguageCode;
    }
    public  void setCmsChannelId(Integer cmsChannelId)    
    {
        this.cmsChannelId = cmsChannelId;
    }
    public  Integer getCmsChannelId()    
    {
        return cmsChannelId;
    }
    public  void setChannelFilePathRule(String channelFilePathRule)    
    {
        this.channelFilePathRule = channelFilePathRule;
    }
    public  String getChannelFilePathRule()    
    {
        return channelFilePathRule;
    }
    public  CmsChannelLanguage copy()    
    {
        CmsChannelLanguage entity = null;
        try        
        {
            entity = (CmsChannelLanguage) super.clone();
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