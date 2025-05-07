package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewBookSkuBookTag extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookSkuId;//图书skuID;
    private  Integer bookTagId;//标签ID;
    private  Timestamp createTime;//创建时间;
    private  String languageCode;//语言;
    private  String bookTagName;//标签名称;
    private  Boolean isVisible;//是否会员可见;
    private  String frontName;//前台显示名称;
    private  Integer displayOrder;//排序;
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
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
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
    public  void setIsVisible(Boolean isVisible)    
    {
        this.isVisible = isVisible;
    }
    public  Boolean getIsVisible()    
    {
        return isVisible;
    }
    public  void setFrontName(String frontName)    
    {
        this.frontName = frontName;
    }
    public  String getFrontName()    
    {
        return frontName;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
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
    public  ViewBookSkuBookTag copy()    
    {
        ViewBookSkuBookTag entity = null;
        try        
        {
            entity = (ViewBookSkuBookTag) super.clone();
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