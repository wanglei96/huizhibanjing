package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CmsTemplateTag extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String name;//标签名称;
    private  String elementName;//html元素名称;
    private  String handlerName;//处理类名称;
    private  String description;//;
    private  Integer displayOrder;//;
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
    public  void setElementName(String elementName)    
    {
        this.elementName = elementName;
    }
    public  String getElementName()    
    {
        return elementName;
    }
    public  void setHandlerName(String handlerName)    
    {
        this.handlerName = handlerName;
    }
    public  String getHandlerName()    
    {
        return handlerName;
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
    public  CmsTemplateTag copy()    
    {
        CmsTemplateTag entity = null;
        try        
        {
            entity = (CmsTemplateTag) super.clone();
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