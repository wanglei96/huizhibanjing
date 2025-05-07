package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.math.BigInteger;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewBookTag extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String name;//标签名称;
    private  String languageCode;//语言;
    private  Boolean isVisible;//是否会员可见;
    private  String frontName;//前台显示名称;
    private  Integer displayOrder;//排序;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String companyName;//门店名称;
    private  String languageName;//语言名称;
    private  BigInteger skuCount;//SKU数量;
    private  BigInteger bookCount;//图书数量;
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
    public  void setLanguageCode(String languageCode)    
    {
        this.languageCode = languageCode;
    }
    public  String getLanguageCode()    
    {
        return languageCode;
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
    public  void setLanguageName(String languageName)    
    {
        this.languageName = languageName;
    }
    public  String getLanguageName()    
    {
        return languageName;
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
    public  ViewBookTag copy()    
    {
        ViewBookTag entity = null;
        try        
        {
            entity = (ViewBookTag) super.clone();
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