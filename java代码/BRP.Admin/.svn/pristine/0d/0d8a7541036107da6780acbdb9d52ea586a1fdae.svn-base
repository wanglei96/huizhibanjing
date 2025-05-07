package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewAgeGroupBookTag extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer ageGroupId;//年龄段ID;
    private  Integer bookTagId;//书签ID;
    private  Timestamp createTime;//创建时间;
    private  String languageCode;//语言;
    private  String bookTagName;//标签名称;
    private  Integer bookTagDisplayOrder;//排序;
    private  Boolean isVisible;//是否会员可见;
    private  String ageGroupName;//年龄段分组;
    private  String languageName;//语言;
    private  Integer companyId;//门店ID;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setAgeGroupId(Integer ageGroupId)    
    {
        this.ageGroupId = ageGroupId;
    }
    public  Integer getAgeGroupId()    
    {
        return ageGroupId;
    }
    public  void setBookTagId(Integer bookTagId)    
    {
        this.bookTagId = bookTagId;
    }
    public  Integer getBookTagId()    
    {
        return bookTagId;
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
    public  void setBookTagName(String bookTagName)    
    {
        this.bookTagName = bookTagName;
    }
    public  String getBookTagName()    
    {
        return bookTagName;
    }
    public  void setBookTagDisplayOrder(Integer bookTagDisplayOrder)    
    {
        this.bookTagDisplayOrder = bookTagDisplayOrder;
    }
    public  Integer getBookTagDisplayOrder()    
    {
        return bookTagDisplayOrder;
    }
    public  void setIsVisible(Boolean isVisible)    
    {
        this.isVisible = isVisible;
    }
    public  Boolean getIsVisible()    
    {
        return isVisible;
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
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  ViewAgeGroupBookTag copy()    
    {
        ViewAgeGroupBookTag entity = null;
        try        
        {
            entity = (ViewAgeGroupBookTag) super.clone();
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