package strosoft.cg.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class CgProject extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String name;//名称;
    private  String configuration;//配置;
    private  String databaseAddress;//数据库连接串;
    private  Integer databasePort;//数据库端口;
    private  String databaseName;//数据库名;
    private  String databaseUsername;//数据库用户名;
    private  String databasePassword;//数据库密码;
    private  String outputDirectory;//输出目录;
    private  Timestamp createTime;//;
    private  Timestamp updateTime;//;
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
    public  void setConfiguration(String configuration)    
    {
        this.configuration = configuration;
    }
    public  String getConfiguration()    
    {
        return configuration;
    }
    public  void setDatabaseAddress(String databaseAddress)    
    {
        this.databaseAddress = databaseAddress;
    }
    public  String getDatabaseAddress()    
    {
        return databaseAddress;
    }
    public  void setDatabasePort(Integer databasePort)    
    {
        this.databasePort = databasePort;
    }
    public  Integer getDatabasePort()    
    {
        return databasePort;
    }
    public  void setDatabaseName(String databaseName)    
    {
        this.databaseName = databaseName;
    }
    public  String getDatabaseName()    
    {
        return databaseName;
    }
    public  void setDatabaseUsername(String databaseUsername)    
    {
        this.databaseUsername = databaseUsername;
    }
    public  String getDatabaseUsername()    
    {
        return databaseUsername;
    }
    public  void setDatabasePassword(String databasePassword)    
    {
        this.databasePassword = databasePassword;
    }
    public  String getDatabasePassword()    
    {
        return databasePassword;
    }
    public  void setOutputDirectory(String outputDirectory)    
    {
        this.outputDirectory = outputDirectory;
    }
    public  String getOutputDirectory()    
    {
        return outputDirectory;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setUpdateTime(Timestamp updateTime)    
    {
        this.updateTime = updateTime;
    }
    public  Timestamp getUpdateTime()    
    {
        return updateTime;
    }
    public  CgProject copy()    
    {
        CgProject entity = null;
        try        
        {
            entity = (CgProject) super.clone();
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