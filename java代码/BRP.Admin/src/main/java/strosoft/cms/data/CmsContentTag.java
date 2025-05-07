package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CmsContentTag extends DataEntity implements Cloneable
{
    private  Integer cmsContentId;//内容id;
    private  String tag;//标签;
    public  void setCmsContentId(Integer cmsContentId)    
    {
        this.cmsContentId = cmsContentId;
    }
    public  Integer getCmsContentId()    
    {
        return cmsContentId;
    }
    public  void setTag(String tag)    
    {
        this.tag = tag;
    }
    public  String getTag()    
    {
        return tag;
    }
    public  CmsContentTag copy()    
    {
        CmsContentTag entity = null;
        try        
        {
            entity = (CmsContentTag) super.clone();
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