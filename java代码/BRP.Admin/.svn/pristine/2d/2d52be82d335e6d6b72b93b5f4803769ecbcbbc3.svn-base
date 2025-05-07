package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class SysBasicTable extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String tableName;//;
    private  String title;//;
    private  String sysBasicTableTypeCode;//;
    private  String description;//;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setTableName(String tableName)    
    {
        this.tableName = tableName;
    }
    public  String getTableName()    
    {
        return tableName;
    }
    public  void setTitle(String title)    
    {
        this.title = title;
    }
    public  String getTitle()    
    {
        return title;
    }
    public  void setSysBasicTableTypeCode(String sysBasicTableTypeCode)    
    {
        this.sysBasicTableTypeCode = sysBasicTableTypeCode;
    }
    public  String getSysBasicTableTypeCode()    
    {
        return sysBasicTableTypeCode;
    }
    public  void setDescription(String description)    
    {
        this.description = description;
    }
    public  String getDescription()    
    {
        return description;
    }
    public  SysBasicTable copy()    
    {
        SysBasicTable entity = null;
        try        
        {
            entity = (SysBasicTable) super.clone();
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