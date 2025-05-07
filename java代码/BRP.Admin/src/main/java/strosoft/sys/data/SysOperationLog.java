package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class SysOperationLog extends DataEntity implements Cloneable
{
    private  Integer id;//id;
    private  String operationTargetCode;//操作的表名;
    private  String operationTypeCode;//操作类型;
    private  String content;//修改内容;
    private  Integer operatorId;//操作人Id;
    private  Timestamp operateTime;//操作时间;
    private  Integer primaryKey;//操作数据id;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setOperationTargetCode(String operationTargetCode)    
    {
        this.operationTargetCode = operationTargetCode;
    }
    public  String getOperationTargetCode()    
    {
        return operationTargetCode;
    }
    public  void setOperationTypeCode(String operationTypeCode)    
    {
        this.operationTypeCode = operationTypeCode;
    }
    public  String getOperationTypeCode()    
    {
        return operationTypeCode;
    }
    public  void setContent(String content)    
    {
        this.content = content;
    }
    public  String getContent()    
    {
        return content;
    }
    public  void setOperatorId(Integer operatorId)    
    {
        this.operatorId = operatorId;
    }
    public  Integer getOperatorId()    
    {
        return operatorId;
    }
    public  void setOperateTime(Timestamp operateTime)    
    {
        this.operateTime = operateTime;
    }
    public  Timestamp getOperateTime()    
    {
        return operateTime;
    }
    public  void setPrimaryKey(Integer primaryKey)    
    {
        this.primaryKey = primaryKey;
    }
    public  Integer getPrimaryKey()    
    {
        return primaryKey;
    }
    public  SysOperationLog copy()    
    {
        SysOperationLog entity = null;
        try        
        {
            entity = (SysOperationLog) super.clone();
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