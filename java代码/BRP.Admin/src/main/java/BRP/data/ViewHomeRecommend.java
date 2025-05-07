package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewHomeRecommend extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String name;//名称;
    private  Integer companyId;//门店ID;
    private  Integer clubActivityId;//图片ID;
    private  Integer displayOrder;//显示顺序;
    private  Boolean enabled;//是否启用;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String clubActivityName;//活动名称;
    private  String clubActivityAddress;
    private  Timestamp clubActivityExpiryDate;
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
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setClubActivityId(Integer clubActivityId)    
    {
        this.clubActivityId = clubActivityId;
    }
    public  Integer getClubActivityId()    
    {
        return clubActivityId;
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
    public  void setClubActivityName(String clubActivityName)    
    {
        this.clubActivityName = clubActivityName;
    }
    public  String getClubActivityName()    
    {
        return clubActivityName;
    }
    public  void setClubActivityAddress(String clubActivityAddress)    
    {
        this.clubActivityAddress = clubActivityAddress;
    }
    public  String getClubActivityAddress()    
    {
        return clubActivityAddress;
    }
    public  void setClubActivityExpiryDate(Timestamp clubActivityExpiryDate)    
    {
        this.clubActivityExpiryDate = clubActivityExpiryDate;
    }
    public  Timestamp getClubActivityExpiryDate()    
    {
        return clubActivityExpiryDate;
    }
    public  void setImageFilePath(String imageFilePath)    
    {
        this.imageFilePath = imageFilePath;
    }
    public  String getImageFilePath()    
    {
        return imageFilePath;
    }
    public  ViewHomeRecommend copy()    
    {
        ViewHomeRecommend entity = null;
        try        
        {
            entity = (ViewHomeRecommend) super.clone();
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