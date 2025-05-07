package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewBookSkuAgeGroup extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookSkuId;//图书skuID;
    private  Integer ageGroupId;//年龄段编码;
    private  Timestamp createTime;//创建时间;
    private  String languageCode;//语言;
    private  Integer companyId;//门店ID;
    private  String ageGroupName;//年龄段分组;
    private  String languageName;//语言;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
    }
    public  void setAgeGroupId(Integer ageGroupId)    
    {
        this.ageGroupId = ageGroupId;
    }
    public  Integer getAgeGroupId()    
    {
        return ageGroupId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setLanguageCode(String languageCode)    
    {
        this.languageCode = languageCode;
    }
    public  String getLanguageCode()    
    {
        return languageCode;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setAgeGroupName(String ageGroupName)    
    {
        this.ageGroupName = ageGroupName;
    }
    public  String getAgeGroupName()    
    {
        return ageGroupName;
    }
    public  void setLanguageName(String languageName)    
    {
        this.languageName = languageName;
    }
    public  String getLanguageName()    
    {
        return languageName;
    }
    public  ViewBookSkuAgeGroup copy()    
    {
        ViewBookSkuAgeGroup entity = null;
        try        
        {
            entity = (ViewBookSkuAgeGroup) super.clone();
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