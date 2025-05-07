package strosoft.sys.data;
import net.sf.json.JSONObject;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class SysBasicTableRelation extends DataEntity implements Cloneable
{
    private  String relatedTableName;//;
    private  String relatedColumnName;//;
    private  String basicTableName;//;
    public  void setRelatedTableName(String relatedTableName)    
    {
        this.relatedTableName = relatedTableName;
    }
    public  String getRelatedTableName()    
    {
        return relatedTableName;
    }
    public  void setRelatedColumnName(String relatedColumnName)    
    {
        this.relatedColumnName = relatedColumnName;
    }
    public  String getRelatedColumnName()    
    {
        return relatedColumnName;
    }
    public  void setBasicTableName(String basicTableName)    
    {
        this.basicTableName = basicTableName;
    }
    public  String getBasicTableName()    
    {
        return basicTableName;
    }
    public  SysBasicTableRelation copy()    
    {
        SysBasicTableRelation entity = null;
        try        
        {
            entity = (SysBasicTableRelation) super.clone();
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