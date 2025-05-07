package strosoft.cms.data;
import net.sf.json.JSONObject;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CmsSiteType extends DataEntity implements Cloneable
{
    private  String code;//;
    private  String name;//;
    private  String description;//;
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
    public  CmsSiteType copy()    
    {
        CmsSiteType entity = null;
        try        
        {
            entity = (CmsSiteType) super.clone();
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