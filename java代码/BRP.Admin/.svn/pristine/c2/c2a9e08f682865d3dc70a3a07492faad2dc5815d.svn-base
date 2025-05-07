package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class SysBasicTableType extends DataEntity implements Cloneable
{
    private  String code;//;
    private  String name;//;
    private  String description;//;
    private  Integer displayOrder;//;
    private  Integer userId;//;
    private  Timestamp createTime;//;
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
    public  void setUserId(Integer userId)    
    {
        this.userId = userId;
    }
    public  Integer getUserId()    
    {
        return userId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  SysBasicTableType copy()    
    {
        SysBasicTableType entity = null;
        try        
        {
            entity = (SysBasicTableType) super.clone();
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