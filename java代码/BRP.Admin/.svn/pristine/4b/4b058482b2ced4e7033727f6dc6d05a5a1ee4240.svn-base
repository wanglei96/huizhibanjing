package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.Double;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
import java.math.BigDecimal;
public class ClubActivity extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String name;//活动名称;
    private  Integer imageFileId;//活动图片ID;
    private  Integer detailImageFileId;//活动详情图片ID;
    private  Integer quantity;//活动次数;
    private  Timestamp expiryDate;//有效期;
    private  String clubActivityCategoryCode;//俱乐部活动分类编码;
    private  BigDecimal cost;//费用;
    private  Double latitude;//经度;
    private  Double longitude;//维度;
    private  String address;//地址;
    private  String content;//内容;
    private  Boolean isHome;
    private  Boolean isTop;
    private  Integer maxApplyQuantity;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
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
    public  void setImageFileId(Integer imageFileId)    
    {
        this.imageFileId = imageFileId;
    }
    public  Integer getImageFileId()    
    {
        return imageFileId;
    }
    public  void setDetailImageFileId(Integer detailImageFileId)    
    {
        this.detailImageFileId = detailImageFileId;
    }
    public  Integer getDetailImageFileId()    
    {
        return detailImageFileId;
    }
    public  void setQuantity(Integer quantity)    
    {
        this.quantity = quantity;
    }
    public  Integer getQuantity()    
    {
        return quantity;
    }
    public  void setExpiryDate(Timestamp expiryDate)    
    {
        this.expiryDate = expiryDate;
    }
    public  Timestamp getExpiryDate()    
    {
        return expiryDate;
    }
    public  void setClubActivityCategoryCode(String clubActivityCategoryCode)    
    {
        this.clubActivityCategoryCode = clubActivityCategoryCode;
    }
    public  String getClubActivityCategoryCode()    
    {
        return clubActivityCategoryCode;
    }
    public  void setCost(BigDecimal cost)    
    {
        this.cost = cost;
    }
    public  BigDecimal getCost()    
    {
        return cost;
    }
    public  void setLatitude(Double latitude)    
    {
        this.latitude = latitude;
    }
    public  Double getLatitude()    
    {
        return latitude;
    }
    public  void setLongitude(Double longitude)    
    {
        this.longitude = longitude;
    }
    public  Double getLongitude()    
    {
        return longitude;
    }
    public  void setAddress(String address)    
    {
        this.address = address;
    }
    public  String getAddress()    
    {
        return address;
    }
    public  void setContent(String content)    
    {
        this.content = content;
    }
    public  String getContent()    
    {
        return content;
    }
    public  void setIsHome(Boolean isHome)    
    {
        this.isHome = isHome;
    }
    public  Boolean getIsHome()    
    {
        return isHome;
    }
    public  void setIsTop(Boolean isTop)    
    {
        this.isTop = isTop;
    }
    public  Boolean getIsTop()    
    {
        return isTop;
    }
    public  void setMaxApplyQuantity(Integer maxApplyQuantity)    
    {
        this.maxApplyQuantity = maxApplyQuantity;
    }
    public  Integer getMaxApplyQuantity()    
    {
        return maxApplyQuantity;
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
    public  ClubActivity copy()    
    {
        ClubActivity entity = null;
        try        
        {
            entity = (ClubActivity) super.clone();
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