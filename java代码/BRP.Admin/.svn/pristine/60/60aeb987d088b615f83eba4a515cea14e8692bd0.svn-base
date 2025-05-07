package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class SysUploadFile extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String name;//;
    private  String path;//;
    private  String smallImageFilePath;//小图片路径;
    private  String timeImageFilePath;//带时间图片路径;
    private  String videoImageFilePath;//视频图片文件路径;
    private  Timestamp createTime;//;
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
    public  void setPath(String path)    
    {
        this.path = path;
    }
    public  String getPath()    
    {
        return path;
    }
    public  void setSmallImageFilePath(String smallImageFilePath)    
    {
        this.smallImageFilePath = smallImageFilePath;
    }
    public  String getSmallImageFilePath()    
    {
        return smallImageFilePath;
    }
    public  void setTimeImageFilePath(String timeImageFilePath)    
    {
        this.timeImageFilePath = timeImageFilePath;
    }
    public  String getTimeImageFilePath()    
    {
        return timeImageFilePath;
    }
    public  void setVideoImageFilePath(String videoImageFilePath)    
    {
        this.videoImageFilePath = videoImageFilePath;
    }
    public  String getVideoImageFilePath()    
    {
        return videoImageFilePath;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  SysUploadFile copy()    
    {
        SysUploadFile entity = null;
        try        
        {
            entity = (SysUploadFile) super.clone();
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