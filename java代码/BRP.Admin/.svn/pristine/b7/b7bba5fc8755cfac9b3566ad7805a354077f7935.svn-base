package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CmsChannelChannelGroup extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  Integer cmsChannelId;//栏目ID;
    private  String cmsChannelGroupCode;//栏目分类编码;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setCmsChannelId(Integer cmsChannelId)    
    {
        this.cmsChannelId = cmsChannelId;
    }
    public  Integer getCmsChannelId()    
    {
        return cmsChannelId;
    }
    public  void setCmsChannelGroupCode(String cmsChannelGroupCode)    
    {
        this.cmsChannelGroupCode = cmsChannelGroupCode;
    }
    public  String getCmsChannelGroupCode()    
    {
        return cmsChannelGroupCode;
    }
    public  CmsChannelChannelGroup copy()    
    {
        CmsChannelChannelGroup entity = null;
        try        
        {
            entity = (CmsChannelChannelGroup) super.clone();
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