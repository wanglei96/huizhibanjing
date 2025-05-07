package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
import java.math.BigDecimal;
public class ViewBookOrderItem extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookOrderId;//借书单ID;
    private  Integer bookId;//图书ID;
    private  Boolean isDamage;//是否损坏;
    private  String bookOrderTypeCode;//书单类型编码;
    private  String deliverStatusCode;//配送状态编码;
    private  Boolean isReturned;//是否已还;
    private  Timestamp bookReturnTime;//还书时间;
    private  Timestamp bookInStocksTime;//入库时间;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String bookOrderBorrowStatusCode;//借阅状态编码;
    private  String bookOrderReturnStatusCode;//还书状态编码;
    private  String bookOrderNo;//单据编号;
    private  Timestamp businessDate;//单据时间;
    private  Integer companyId;//门店ID;
    private  Integer memberId;//会员ID;
    private  Boolean isOffline;//是否线下;
    private  Integer delivererId;//配送员ID;
    private  Integer bookpickerId;//拣书员ID;
    private  Timestamp deliverTime;//配送时间;
    private  Integer displayOrder;//配送单排序（配送员小程序）;
    private  Timestamp deliveredTime;
    private  String errorRemark;
    private  Integer deliverAreaNumber;
    private  String bookSn;//条形码;
    private  Integer bookSkuId;//skuID;
    private  String borrowStatusCode;//借阅状态;
    private  Integer bookcaseId;//书柜ID;
    private  String bookName;//书名;
    private  Integer bookSeriesId;//系列ID;
    private  String bindingTypeCode;//装订方式;
    private  BigDecimal bookSkuPrice;//定价;
    private  String bookcaseName;//书柜名称;
    private  String bookSeriesName;//系列名称;
    private  String bindingTypeName;//装订名称;
    private  String bookOrderTypeName;//书单类型名称;
    private  String memberName;//名称;
    private  String memberNo;//会员编号;
    private  String memberMobilePhone;//联系电话;
    private  Timestamp memberBirthday;//出生日期;
    private  Integer deliverAreaId;//配送大区ID;
    private  Integer deliverLineId;//配送线路ID;
    private  Integer deliverPlaceId;//配送点ID;
    private  String address;//地址;
    private  Boolean memberEnabled;//是否启用;
    private  Boolean memberBookboxIsActivated;//是否激活;
    private  String memberEntranceGuard;
    private  String bookboxPlace;
    private  String borrowStatusName;//图书借阅庄涛;
    private  String bookOrderBorrowStatusName;//借阅状态;
    private  String bookOrderReturnStatusName;//还书状态;
    private  Integer memberCardId;//ID;
    private  String memberCardNo;//会员卡号;
    private  Timestamp expireDate;//失效日期;
    private  String memberCardStatusCode;//会员卡状态;
    private  String deliverStatusName;//配送状态名称;
    private  String deliverAreaName;//配送大区;
    private  String deliverLineName;//名称;
    private  String deliverPlaceName;//名称;
    private  Integer deliverPlaceDisplayOrder;
    private  String delivererName;//姓名;
    private  String bookpickerName;//姓名;
    private  String imageFilePath;//路径;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setBookOrderId(Integer bookOrderId)    
    {
        this.bookOrderId = bookOrderId;
    }
    public  Integer getBookOrderId()    
    {
        return bookOrderId;
    }
    public  void setBookId(Integer bookId)    
    {
        this.bookId = bookId;
    }
    public  Integer getBookId()    
    {
        return bookId;
    }
    public  void setIsDamage(Boolean isDamage)    
    {
        this.isDamage = isDamage;
    }
    public  Boolean getIsDamage()    
    {
        return isDamage;
    }
    public  void setBookOrderTypeCode(String bookOrderTypeCode)    
    {
        this.bookOrderTypeCode = bookOrderTypeCode;
    }
    public  String getBookOrderTypeCode()    
    {
        return bookOrderTypeCode;
    }
    public  void setDeliverStatusCode(String deliverStatusCode)    
    {
        this.deliverStatusCode = deliverStatusCode;
    }
    public  String getDeliverStatusCode()    
    {
        return deliverStatusCode;
    }
    public  void setIsReturned(Boolean isReturned)    
    {
        this.isReturned = isReturned;
    }
    public  Boolean getIsReturned()    
    {
        return isReturned;
    }
    public  void setBookReturnTime(Timestamp bookReturnTime)    
    {
        this.bookReturnTime = bookReturnTime;
    }
    public  Timestamp getBookReturnTime()    
    {
        return bookReturnTime;
    }
    public  void setBookInStocksTime(Timestamp bookInStocksTime)    
    {
        this.bookInStocksTime = bookInStocksTime;
    }
    public  Timestamp getBookInStocksTime()    
    {
        return bookInStocksTime;
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
    public  void setBookOrderNo(String bookOrderNo)    
    {
        this.bookOrderNo = bookOrderNo;
    }
    public  String getBookOrderNo()    
    {
        return bookOrderNo;
    }
    public  void setBusinessDate(Timestamp businessDate)    
    {
        this.businessDate = businessDate;
    }
    public  Timestamp getBusinessDate()    
    {
        return businessDate;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setIsOffline(Boolean isOffline)    
    {
        this.isOffline = isOffline;
    }
    public  Boolean getIsOffline()    
    {
        return isOffline;
    }
    public  void setDelivererId(Integer delivererId)    
    {
        this.delivererId = delivererId;
    }
    public  Integer getDelivererId()    
    {
        return delivererId;
    }
    public  void setBookpickerId(Integer bookpickerId)    
    {
        this.bookpickerId = bookpickerId;
    }
    public  Integer getBookpickerId()    
    {
        return bookpickerId;
    }
    public  void setDeliverTime(Timestamp deliverTime)    
    {
        this.deliverTime = deliverTime;
    }
    public  Timestamp getDeliverTime()    
    {
        return deliverTime;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setDeliveredTime(Timestamp deliveredTime)    
    {
        this.deliveredTime = deliveredTime;
    }
    public  Timestamp getDeliveredTime()    
    {
        return deliveredTime;
    }
    public  void setErrorRemark(String errorRemark)    
    {
        this.errorRemark = errorRemark;
    }
    public  String getErrorRemark()    
    {
        return errorRemark;
    }
    public  void setDeliverAreaNumber(Integer deliverAreaNumber)    
    {
        this.deliverAreaNumber = deliverAreaNumber;
    }
    public  Integer getDeliverAreaNumber()    
    {
        return deliverAreaNumber;
    }
    public  void setBookSn(String bookSn)    
    {
        this.bookSn = bookSn;
    }
    public  String getBookSn()    
    {
        return bookSn;
    }
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
    }
    public  void setBorrowStatusCode(String borrowStatusCode)    
    {
        this.borrowStatusCode = borrowStatusCode;
    }
    public  String getBorrowStatusCode()    
    {
        return borrowStatusCode;
    }
    public  void setBookcaseId(Integer bookcaseId)    
    {
        this.bookcaseId = bookcaseId;
    }
    public  Integer getBookcaseId()    
    {
        return bookcaseId;
    }
    public  void setBookName(String bookName)    
    {
        this.bookName = bookName;
    }
    public  String getBookName()    
    {
        return bookName;
    }
    public  void setBookSeriesId(Integer bookSeriesId)    
    {
        this.bookSeriesId = bookSeriesId;
    }
    public  Integer getBookSeriesId()    
    {
        return bookSeriesId;
    }
    public  void setBindingTypeCode(String bindingTypeCode)    
    {
        this.bindingTypeCode = bindingTypeCode;
    }
    public  String getBindingTypeCode()    
    {
        return bindingTypeCode;
    }
    public  void setBookSkuPrice(BigDecimal bookSkuPrice)    
    {
        this.bookSkuPrice = bookSkuPrice;
    }
    public  BigDecimal getBookSkuPrice()    
    {
        return bookSkuPrice;
    }
    public  void setBookcaseName(String bookcaseName)    
    {
        this.bookcaseName = bookcaseName;
    }
    public  String getBookcaseName()    
    {
        return bookcaseName;
    }
    public  void setBookSeriesName(String bookSeriesName)    
    {
        this.bookSeriesName = bookSeriesName;
    }
    public  String getBookSeriesName()    
    {
        return bookSeriesName;
    }
    public  void setBindingTypeName(String bindingTypeName)    
    {
        this.bindingTypeName = bindingTypeName;
    }
    public  String getBindingTypeName()    
    {
        return bindingTypeName;
    }
    public  void setBookOrderTypeName(String bookOrderTypeName)    
    {
        this.bookOrderTypeName = bookOrderTypeName;
    }
    public  String getBookOrderTypeName()    
    {
        return bookOrderTypeName;
    }
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  void setMemberNo(String memberNo)    
    {
        this.memberNo = memberNo;
    }
    public  String getMemberNo()    
    {
        return memberNo;
    }
    public  void setMemberMobilePhone(String memberMobilePhone)    
    {
        this.memberMobilePhone = memberMobilePhone;
    }
    public  String getMemberMobilePhone()    
    {
        return memberMobilePhone;
    }
    public  void setMemberBirthday(Timestamp memberBirthday)    
    {
        this.memberBirthday = memberBirthday;
    }
    public  Timestamp getMemberBirthday()    
    {
        return memberBirthday;
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
    public  void setMemberEnabled(Boolean memberEnabled)    
    {
        this.memberEnabled = memberEnabled;
    }
    public  Boolean getMemberEnabled()    
    {
        return memberEnabled;
    }
    public  void setMemberBookboxIsActivated(Boolean memberBookboxIsActivated)    
    {
        this.memberBookboxIsActivated = memberBookboxIsActivated;
    }
    public  Boolean getMemberBookboxIsActivated()    
    {
        return memberBookboxIsActivated;
    }
    public  void setMemberEntranceGuard(String memberEntranceGuard)    
    {
        this.memberEntranceGuard = memberEntranceGuard;
    }
    public  String getMemberEntranceGuard()    
    {
        return memberEntranceGuard;
    }
    public  void setBookboxPlace(String bookboxPlace)    
    {
        this.bookboxPlace = bookboxPlace;
    }
    public  String getBookboxPlace()    
    {
        return bookboxPlace;
    }
    public  void setBorrowStatusName(String borrowStatusName)    
    {
        this.borrowStatusName = borrowStatusName;
    }
    public  String getBorrowStatusName()    
    {
        return borrowStatusName;
    }
    public  void setBookOrderBorrowStatusName(String bookOrderBorrowStatusName)    
    {
        this.bookOrderBorrowStatusName = bookOrderBorrowStatusName;
    }
    public  String getBookOrderBorrowStatusName()    
    {
        return bookOrderBorrowStatusName;
    }
    public  void setBookOrderReturnStatusName(String bookOrderReturnStatusName)    
    {
        this.bookOrderReturnStatusName = bookOrderReturnStatusName;
    }
    public  String getBookOrderReturnStatusName()    
    {
        return bookOrderReturnStatusName;
    }
    public  void setMemberCardId(Integer memberCardId)    
    {
        this.memberCardId = memberCardId;
    }
    public  Integer getMemberCardId()    
    {
        return memberCardId;
    }
    public  void setMemberCardNo(String memberCardNo)    
    {
        this.memberCardNo = memberCardNo;
    }
    public  String getMemberCardNo()    
    {
        return memberCardNo;
    }
    public  void setExpireDate(Timestamp expireDate)    
    {
        this.expireDate = expireDate;
    }
    public  Timestamp getExpireDate()    
    {
        return expireDate;
    }
    public  void setMemberCardStatusCode(String memberCardStatusCode)    
    {
        this.memberCardStatusCode = memberCardStatusCode;
    }
    public  String getMemberCardStatusCode()    
    {
        return memberCardStatusCode;
    }
    public  void setDeliverStatusName(String deliverStatusName)    
    {
        this.deliverStatusName = deliverStatusName;
    }
    public  String getDeliverStatusName()    
    {
        return deliverStatusName;
    }
    public  void setDeliverAreaName(String deliverAreaName)    
    {
        this.deliverAreaName = deliverAreaName;
    }
    public  String getDeliverAreaName()    
    {
        return deliverAreaName;
    }
    public  void setDeliverLineName(String deliverLineName)    
    {
        this.deliverLineName = deliverLineName;
    }
    public  String getDeliverLineName()    
    {
        return deliverLineName;
    }
    public  void setDeliverPlaceName(String deliverPlaceName)    
    {
        this.deliverPlaceName = deliverPlaceName;
    }
    public  String getDeliverPlaceName()    
    {
        return deliverPlaceName;
    }
    public  void setDeliverPlaceDisplayOrder(Integer deliverPlaceDisplayOrder)    
    {
        this.deliverPlaceDisplayOrder = deliverPlaceDisplayOrder;
    }
    public  Integer getDeliverPlaceDisplayOrder()    
    {
        return deliverPlaceDisplayOrder;
    }
    public  void setDelivererName(String delivererName)    
    {
        this.delivererName = delivererName;
    }
    public  String getDelivererName()    
    {
        return delivererName;
    }
    public  void setBookpickerName(String bookpickerName)    
    {
        this.bookpickerName = bookpickerName;
    }
    public  String getBookpickerName()    
    {
        return bookpickerName;
    }
    public  void setImageFilePath(String imageFilePath)    
    {
        this.imageFilePath = imageFilePath;
    }
    public  String getImageFilePath()    
    {
        return imageFilePath;
    }
    public  ViewBookOrderItem copy()    
    {
        ViewBookOrderItem entity = null;
        try        
        {
            entity = (ViewBookOrderItem) super.clone();
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