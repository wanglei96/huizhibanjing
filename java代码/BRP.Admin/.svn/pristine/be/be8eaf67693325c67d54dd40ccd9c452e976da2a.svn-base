package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class BookSkuTagGroupInfo extends DataEntity implements Cloneable
{
    private  Integer bookSkuId;//ID;
    private  String bookName;//书名;
    private  Integer bookTagId;//ID;
    private  String bookTagName;//标签名称;
    private  String imageFilePath;//图片路径;
    private  Integer companyId;//门店ID;
    private  String ISBN;//ISBN;
    private  String bookSeriesName;//系列名;
    private  Integer ageGroupId;//年龄段编码;
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
    }
    public  void setBookName(String bookName)    
    {
        this.bookName = bookName;
    }
    public  String getBookName()    
    {
        return bookName;
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
    public  void setImageFilePath(String imageFilePath)    
    {
        this.imageFilePath = imageFilePath;
    }
    public  String getImageFilePath()    
    {
        return imageFilePath;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setISBN(String ISBN)    
    {
        this.ISBN = ISBN;
    }
    public  String getISBN()    
    {
        return ISBN;
    }
    public  void setBookSeriesName(String bookSeriesName)    
    {
        this.bookSeriesName = bookSeriesName;
    }
    public  String getBookSeriesName()    
    {
        return bookSeriesName;
    }
    public  void setAgeGroupId(Integer ageGroupId)    
    {
        this.ageGroupId = ageGroupId;
    }
    public  Integer getAgeGroupId()    
    {
        return ageGroupId;
    }
    public  BookSkuTagGroupInfo copy()    
    {
        BookSkuTagGroupInfo entity = null;
        try        
        {
            entity = (BookSkuTagGroupInfo) super.clone();
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