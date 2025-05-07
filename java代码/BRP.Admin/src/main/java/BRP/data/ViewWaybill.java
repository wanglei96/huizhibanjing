package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.math.BigInteger;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewWaybill extends DataEntity implements Cloneable
{
    private  String name;//名称;
    private  String memberNo;//会员编号;
    private  String mobilePhone;//联系电话;
    private  Integer deliverAreaId;//ID;
    private  String deliverAreaName;//配送大区;
    private  BigInteger jieNum;//借阅数量;
    private  BigInteger huanNum;//还书数量;
    private  Boolean enabled;//是否启用;
    private  String address;//地址;
    private  String entranceGuard;//门禁;
    private  String distanceCode;//距离远近编码;
    private  String bookboxNo;//书箱号;
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  String bookOrderNo;//单据编号;
    private  Integer delivererId;//配送员ID;
    private  Timestamp businessDate;//单据时间;
    private  String bookOrderBorrowStatusCode;//借阅状态编码;
    private  String bookOrderReturnStatusCode;//还书状态编码;
    private  Integer companyId;//门店ID;
    private  Boolean isOffline;//是否线下;
    private  String errorRemark;//异常情况;
    private  Integer displayOrder;//配送单排序（配送员小程序）;
    private  Timestamp deliverTime;//配送时间;
    private  String rejectedReason;//审核失败原因;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  Integer displayOrderBorrowStatusDisplayOrder;//借阅状态顺序;
    private  Integer deliverPlaceId;
    private  String deliverPlaceName;//配送点名称;
    private  String deliverLineName;
    private  Integer isHandle;//未处理订单;
    private  Integer isTime;//未到配送时间订单;
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setMemberNo(String memberNo)    
    {
        this.memberNo = memberNo;
    }
    public  String getMemberNo()    
    {
        return memberNo;
    }
    public  void setMobilePhone(String mobilePhone)    
    {
        this.mobilePhone = mobilePhone;
    }
    public  String getMobilePhone()    
    {
        return mobilePhone;
    }
    public  void setDeliverAreaId(Integer deliverAreaId)    
    {
        this.deliverAreaId = deliverAreaId;
    }
    public  Integer getDeliverAreaId()    
    {
        return deliverAreaId;
    }
    public  void setDeliverAreaName(String deliverAreaName)    
    {
        this.deliverAreaName = deliverAreaName;
    }
    public  String getDeliverAreaName()    
    {
        return deliverAreaName;
    }
    public  void setJieNum(BigInteger jieNum)    
    {
        this.jieNum = jieNum;
    }
    public  BigInteger getJieNum()    
    {
        return jieNum;
    }
    public  void setHuanNum(BigInteger huanNum)    
    {
        this.huanNum = huanNum;
    }
    public  BigInteger getHuanNum()    
    {
        return huanNum;
    }
    public  void setEnabled(Boolean enabled)    
    {
        this.enabled = enabled;
    }
    public  Boolean getEnabled()    
    {
        return enabled;
    }
    public  void setAddress(String address)    
    {
        this.address = address;
    }
    public  String getAddress()    
    {
        return address;
    }
    public  void setEntranceGuard(String entranceGuard)    
    {
        this.entranceGuard = entranceGuard;
    }
    public  String getEntranceGuard()    
    {
        return entranceGuard;
    }
    public  void setDistanceCode(String distanceCode)    
    {
        this.distanceCode = distanceCode;
    }
    public  String getDistanceCode()    
    {
        return distanceCode;
    }
    public  void setBookboxNo(String bookboxNo)    
    {
        this.bookboxNo = bookboxNo;
    }
    public  String getBookboxNo()    
    {
        return bookboxNo;
    }
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
    public  void setBookOrderNo(String bookOrderNo)    
    {
        this.bookOrderNo = bookOrderNo;
    }
    public  String getBookOrderNo()    
    {
        return bookOrderNo;
    }
    public  void setDelivererId(Integer delivererId)    
    {
        this.delivererId = delivererId;
    }
    public  Integer getDelivererId()    
    {
        return delivererId;
    }
    public  void setBusinessDate(Timestamp businessDate)    
    {
        this.businessDate = businessDate;
    }
    public  Timestamp getBusinessDate()    
    {
        return businessDate;
    }
    public  void setBookOrderBorrowStatusCode(String bookOrderBorrowStatusCode)    
    {
        this.bookOrderBorrowStatusCode = bookOrderBorrowStatusCode;
    }
    public  String getBookOrderBorrowStatusCode()    
    {
        return bookOrderBorrowStatusCode;
    }
    public  void setBookOrderReturnStatusCode(String bookOrderReturnStatusCode)    
    {
        this.bookOrderReturnStatusCode = bookOrderReturnStatusCode;
    }
    public  String getBookOrderReturnStatusCode()    
    {
        return bookOrderReturnStatusCode;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setIsOffline(Boolean isOffline)    
    {
        this.isOffline = isOffline;
    }
    public  Boolean getIsOffline()    
    {
        return isOffline;
    }
    public  void setErrorRemark(String errorRemark)    
    {
        this.errorRemark = errorRemark;
    }
    public  String getErrorRemark()    
    {
        return errorRemark;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setDeliverTime(Timestamp deliverTime)    
    {
        this.deliverTime = deliverTime;
    }
    public  Timestamp getDeliverTime()    
    {
        return deliverTime;
    }
    public  void setRejectedReason(String rejectedReason)    
    {
        this.rejectedReason = rejectedReason;
    }
    public  String getRejectedReason()    
    {
        return rejectedReason;
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
    public  void setDisplayOrderBorrowStatusDisplayOrder(Integer displayOrderBorrowStatusDisplayOrder)    
    {
        this.displayOrderBorrowStatusDisplayOrder = displayOrderBorrowStatusDisplayOrder;
    }
    public  Integer getDisplayOrderBorrowStatusDisplayOrder()    
    {
        return displayOrderBorrowStatusDisplayOrder;
    }
    public  void setDeliverPlaceId(Integer deliverPlaceId)    
    {
        this.deliverPlaceId = deliverPlaceId;
    }
    public  Integer getDeliverPlaceId()    
    {
        return deliverPlaceId;
    }
    public  void setDeliverPlaceName(String deliverPlaceName)    
    {
        this.deliverPlaceName = deliverPlaceName;
    }
    public  String getDeliverPlaceName()    
    {
        return deliverPlaceName;
    }
    public  void setDeliverLineName(String deliverLineName)    
    {
        this.deliverLineName = deliverLineName;
    }
    public  String getDeliverLineName()    
    {
        return deliverLineName;
    }
    public  void setIsHandle(Integer isHandle)    
    {
        this.isHandle = isHandle;
    }
    public  Integer getIsHandle()    
    {
        return isHandle;
    }
    public  void setIsTime(Integer isTime)    
    {
        this.isTime = isTime;
    }
    public  Integer getIsTime()    
    {
        return isTime;
    }
    public  ViewWaybill copy()    
    {
        ViewWaybill entity = null;
        try        
        {
            entity = (ViewWaybill) super.clone();
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