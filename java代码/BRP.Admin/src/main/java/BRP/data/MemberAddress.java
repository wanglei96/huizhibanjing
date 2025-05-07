package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.Double;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class MemberAddress extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  String entranceGuard;//门禁;
    private  Integer deliverAreaId;//配送大区管理;
    private  Integer deliverLineId;//路线（书箱位置）;
    private  Integer deliverPlaceId;
    private  String address;//地址;
    private  Integer delivererId;//配送员ID;
    private  Double longitude;//经度;
    private  Double latitude;//纬度;
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
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setEntranceGuard(String entranceGuard)    
    {
        this.entranceGuard = entranceGuard;
    }
    public  String getEntranceGuard()    
    {
        return entranceGuard;
    }
    public  void setDeliverAreaId(Integer deliverAreaId)    
    {
        this.deliverAreaId = deliverAreaId;
    }
    public  Integer getDeliverAreaId()    
    {
        return deliverAreaId;
    }
    public  void setDeliverLineId(Integer deliverLineId)    
    {
        this.deliverLineId = deliverLineId;
    }
    public  Integer getDeliverLineId()    
    {
        return deliverLineId;
    }
    public  void setDeliverPlaceId(Integer deliverPlaceId)    
    {
        this.deliverPlaceId = deliverPlaceId;
    }
    public  Integer getDeliverPlaceId()    
    {
        return deliverPlaceId;
    }
    public  void setAddress(String address)    
    {
        this.address = address;
    }
    public  String getAddress()    
    {
        return address;
    }
    public  void setDelivererId(Integer delivererId)    
    {
        this.delivererId = delivererId;
    }
    public  Integer getDelivererId()    
    {
        return delivererId;
    }
    public  void setLongitude(Double longitude)    
    {
        this.longitude = longitude;
    }
    public  Double getLongitude()    
    {
        return longitude;
    }
    public  void setLatitude(Double latitude)    
    {
        this.latitude = latitude;
    }
    public  Double getLatitude()    
    {
        return latitude;
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
    public  MemberAddress copy()    
    {
        MemberAddress entity = null;
        try        
        {
            entity = (MemberAddress) super.clone();
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