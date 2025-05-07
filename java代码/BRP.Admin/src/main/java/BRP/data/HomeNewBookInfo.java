package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class HomeNewBookInfo extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String name;//前台显示书名;
    private  Integer bookSkuId;//ID;
    private  Integer displayOrder;//排序;
    private  Boolean enabled;//是否启用;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String companyName;//门店名称;
    private  String bookSkuBookName;//书名;
    private  String bookSkuISBN;//ISBN;
    private  String imageFilePath;//图片路径;
    private  Integer bookTagId;
    private  String bookTagName;//标签名称;
    private  Integer bookSeriesId;//系列ID;
    private  String bookSeriesName;//系列名称;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setEnabled(Boolean enabled)    
    {
        this.enabled = enabled;
    }
    public  Boolean getEnabled()    
    {
        return enabled;
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
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setBookSkuBookName(String bookSkuBookName)    
    {
        this.bookSkuBookName = bookSkuBookName;
    }
    public  String getBookSkuBookName()    
    {
        return bookSkuBookName;
    }
    public  void setBookSkuISBN(String bookSkuISBN)    
    {
        this.bookSkuISBN = bookSkuISBN;
    }
    public  String getBookSkuISBN()    
    {
        return bookSkuISBN;
    }
    public  void setImageFilePath(String imageFilePath)    
    {
        this.imageFilePath = imageFilePath;
    }
    public  String getImageFilePath()    
    {
        return imageFilePath;
    }
    public  void setBookTagId(Integer bookTagId)    
    {
        this.bookTagId = bookTagId;
    }
    public  Integer getBookTagId()    
    {
        return bookTagId;
    }
    public  void setBookTagName(String bookTagName)    
    {
        this.bookTagName = bookTagName;
    }
    public  String getBookTagName()    
    {
        return bookTagName;
    }
    public  void setBookSeriesId(Integer bookSeriesId)    
    {
        this.bookSeriesId = bookSeriesId;
    }
    public  Integer getBookSeriesId()    
    {
        return bookSeriesId;
    }
    public  void setBookSeriesName(String bookSeriesName)    
    {
        this.bookSeriesName = bookSeriesName;
    }
    public  String getBookSeriesName()    
    {
        return bookSeriesName;
    }
    public  HomeNewBookInfo copy()    
    {
        HomeNewBookInfo entity = null;
        try        
        {
            entity = (HomeNewBookInfo) super.clone();
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