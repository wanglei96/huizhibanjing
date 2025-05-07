package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.math.BigInteger;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class ViewAgeGroupSkuTag extends DataEntity implements Cloneable
{
    private  String ageGroupName;//年龄段分组;
    private  Integer ageGroupId;//年龄段ID;
    private  String languageCode;//语言;
    private  String languageName;//语言;
    private  String bookTagNames;//图书标签;
    private  BigInteger skuCount;//SKU数量;
    private  BigInteger bookCount;//图书数量;
    public  void setAgeGroupName(String ageGroupName)    
    {
        this.ageGroupName = ageGroupName;
    }
    public  String getAgeGroupName()    
    {
        return ageGroupName;
    }
    public  void setAgeGroupId(Integer ageGroupId)    
    {
        this.ageGroupId = ageGroupId;
    }
    public  Integer getAgeGroupId()    
    {
        return ageGroupId;
    }
    public  void setLanguageCode(String languageCode)    
    {
        this.languageCode = languageCode;
    }
    public  String getLanguageCode()    
    {
        return languageCode;
    }
    public  void setLanguageName(String languageName)    
    {
        this.languageName = languageName;
    }
    public  String getLanguageName()    
    {
        return languageName;
    }
    public  void setBookTagNames(String bookTagNames)    
    {
        this.bookTagNames = bookTagNames;
    }
    public  String getBookTagNames()    
    {
        return bookTagNames;
    }
    public  void setSkuCount(BigInteger skuCount)    
    {
        this.skuCount = skuCount;
    }
    public  BigInteger getSkuCount()    
    {
        return skuCount;
    }
    public  void setBookCount(BigInteger bookCount)    
    {
        this.bookCount = bookCount;
    }
    public  BigInteger getBookCount()    
    {
        return bookCount;
    }
    public  ViewAgeGroupSkuTag copy()    
    {
        ViewAgeGroupSkuTag entity = null;
        try        
        {
            entity = (ViewAgeGroupSkuTag) super.clone();
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