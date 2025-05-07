package strosoft.cms.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CmsContentTransferType extends DataEntity implements Cloneable
{
    private  String code;//;
    private  String name;//;
    private  String desription;//;
    private  Integer displayOrder;//;
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
    public  void setDesription(String desription)    
    {
        this.desription = desription;
    }
    public  String getDesription()    
    {
        return desription;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  CmsContentTransferType copy()    
    {
        CmsContentTransferType entity = null;
        try        
        {
            entity = (CmsContentTransferType) super.clone();
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