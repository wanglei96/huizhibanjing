package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CmsChannelGroup extends DataEntity implements Cloneable
{
    private  String code;//;
    private  String name;//;
    private  String description;//;
    private  Integer displayOrder;//;
    private  Integer cmsSiteId;//;
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
    public  CmsChannelGroup copy()    
    {
        CmsChannelGroup entity = null;
        try        
        {
            entity = (CmsChannelGroup) super.clone();
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