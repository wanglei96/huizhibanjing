package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class VeiwReadActivity extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String name;//名称;
    private  Integer imageFileId;//图片ID;
    private  BigDecimal price;//价格;
    private  String content;//内容;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String imageFilePath;//图片路径;
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
    public  void setImageFileId(Integer imageFileId)    
    {
        this.imageFileId = imageFileId;
    }
    public  Integer getImageFileId()    
    {
        return imageFileId;
    }
    public  void setPrice(BigDecimal price)    
    {
        this.price = price;
    }
    public  BigDecimal getPrice()    
    {
        return price;
    }
    public  void setContent(String content)    
    {
        this.content = content;
    }
    public  String getContent()    
    {
        return content;
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
    public  void setImageFilePath(String imageFilePath)    
    {
        this.imageFilePath = imageFilePath;
    }
    public  String getImageFilePath()    
    {
        return imageFilePath;
    }
    public  VeiwReadActivity copy()    
    {
        VeiwReadActivity entity = null;
        try        
        {
            entity = (VeiwReadActivity) super.clone();
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