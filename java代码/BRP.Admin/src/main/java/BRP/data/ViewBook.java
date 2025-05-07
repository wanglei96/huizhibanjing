package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
import java.math.BigDecimal;
public class ViewBook extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookSkuId;//skuID;
    private  String sn;//条形码;
    private  String borrowStatusCode;//借阅状态;
    private  String bookStatusCode;//图书状态编码;
    private  Boolean isAvailable;//是否可借;
    private  Integer companyId;//门店ID;
    private  Integer memberId;//会员ID;
    private  Integer bookcaseId;//书柜ID;
    private  String remark;//备注;
    private  Timestamp offlineTime;//下架时间;
    private  String offlineReason;//下架原因;
    private  Integer offlineApproverId;//下架处理人ID;
    private  Boolean isDamaged;//是否有损;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String bookName;//书名;
    private  Integer imageFileId;//封面ID;
    private  String author;//作者;
    private  Integer bookSeriesId;//系列ID;
    private  String press;//出版社;
    private  BigDecimal price;//定价;
    private  String ISBN;//ISBN;
    private  String languageCode;//语言;
    private  String bindingTypeCode;//装订方式;
    private  String dangdangLink;//当当网链接;
    private  String introduction;//简介;
    private  Boolean isOnline;//是否线上可见;
    private  String imageFilePath;//封面路径;
    private  String imageFileName;//封面名称;
    private  String smallImageFilePath;//小图片路径;
    private  String companyName;//门店名称;
    private  String bookSeriesName;//系列;
    private  String languageName;//语言;
    private  String bindingTypeName;//装订方式名称;
    private  String bookcaseName;//书柜名称;
    private  String bookStatusName;//图书状态名称;
    private  String borrowStatusName;//借阅状态;
    private  String offlineApproverName;//公司名称;
    private  String memberName;//名称;
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
    public  void setSn(String sn)    
    {
        this.sn = sn;
    }
    public  String getSn()    
    {
        return sn;
    }
    public  void setBorrowStatusCode(String borrowStatusCode)    
    {
        this.borrowStatusCode = borrowStatusCode;
    }
    public  String getBorrowStatusCode()    
    {
        return borrowStatusCode;
    }
    public  void setBookStatusCode(String bookStatusCode)    
    {
        this.bookStatusCode = bookStatusCode;
    }
    public  String getBookStatusCode()    
    {
        return bookStatusCode;
    }
    public  void setIsAvailable(Boolean isAvailable)    
    {
        this.isAvailable = isAvailable;
    }
    public  Boolean getIsAvailable()    
    {
        return isAvailable;
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
    public  void setBookcaseId(Integer bookcaseId)    
    {
        this.bookcaseId = bookcaseId;
    }
    public  Integer getBookcaseId()    
    {
        return bookcaseId;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setOfflineTime(Timestamp offlineTime)    
    {
        this.offlineTime = offlineTime;
    }
    public  Timestamp getOfflineTime()    
    {
        return offlineTime;
    }
    public  void setOfflineReason(String offlineReason)    
    {
        this.offlineReason = offlineReason;
    }
    public  String getOfflineReason()    
    {
        return offlineReason;
    }
    public  void setOfflineApproverId(Integer offlineApproverId)    
    {
        this.offlineApproverId = offlineApproverId;
    }
    public  Integer getOfflineApproverId()    
    {
        return offlineApproverId;
    }
    public  void setIsDamaged(Boolean isDamaged)    
    {
        this.isDamaged = isDamaged;
    }
    public  Boolean getIsDamaged()    
    {
        return isDamaged;
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
    public  void setBookName(String bookName)    
    {
        this.bookName = bookName;
    }
    public  String getBookName()    
    {
        return bookName;
    }
    public  void setImageFileId(Integer imageFileId)    
    {
        this.imageFileId = imageFileId;
    }
    public  Integer getImageFileId()    
    {
        return imageFileId;
    }
    public  void setAuthor(String author)    
    {
        this.author = author;
    }
    public  String getAuthor()    
    {
        return author;
    }
    public  void setBookSeriesId(Integer bookSeriesId)    
    {
        this.bookSeriesId = bookSeriesId;
    }
    public  Integer getBookSeriesId()    
    {
        return bookSeriesId;
    }
    public  void setPress(String press)    
    {
        this.press = press;
    }
    public  String getPress()    
    {
        return press;
    }
    public  void setPrice(BigDecimal price)    
    {
        this.price = price;
    }
    public  BigDecimal getPrice()    
    {
        return price;
    }
    public  void setISBN(String ISBN)    
    {
        this.ISBN = ISBN;
    }
    public  String getISBN()    
    {
        return ISBN;
    }
    public  void setLanguageCode(String languageCode)    
    {
        this.languageCode = languageCode;
    }
    public  String getLanguageCode()    
    {
        return languageCode;
    }
    public  void setBindingTypeCode(String bindingTypeCode)    
    {
        this.bindingTypeCode = bindingTypeCode;
    }
    public  String getBindingTypeCode()    
    {
        return bindingTypeCode;
    }
    public  void setDangdangLink(String dangdangLink)    
    {
        this.dangdangLink = dangdangLink;
    }
    public  String getDangdangLink()    
    {
        return dangdangLink;
    }
    public  void setIntroduction(String introduction)    
    {
        this.introduction = introduction;
    }
    public  String getIntroduction()    
    {
        return introduction;
    }
    public  void setIsOnline(Boolean isOnline)    
    {
        this.isOnline = isOnline;
    }
    public  Boolean getIsOnline()    
    {
        return isOnline;
    }
    public  void setImageFilePath(String imageFilePath)    
    {
        this.imageFilePath = imageFilePath;
    }
    public  String getImageFilePath()    
    {
        return imageFilePath;
    }
    public  void setImageFileName(String imageFileName)    
    {
        this.imageFileName = imageFileName;
    }
    public  String getImageFileName()    
    {
        return imageFileName;
    }
    public  void setSmallImageFilePath(String smallImageFilePath)    
    {
        this.smallImageFilePath = smallImageFilePath;
    }
    public  String getSmallImageFilePath()    
    {
        return smallImageFilePath;
    }
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setBookSeriesName(String bookSeriesName)    
    {
        this.bookSeriesName = bookSeriesName;
    }
    public  String getBookSeriesName()    
    {
        return bookSeriesName;
    }
    public  void setLanguageName(String languageName)    
    {
        this.languageName = languageName;
    }
    public  String getLanguageName()    
    {
        return languageName;
    }
    public  void setBindingTypeName(String bindingTypeName)    
    {
        this.bindingTypeName = bindingTypeName;
    }
    public  String getBindingTypeName()    
    {
        return bindingTypeName;
    }
    public  void setBookcaseName(String bookcaseName)    
    {
        this.bookcaseName = bookcaseName;
    }
    public  String getBookcaseName()    
    {
        return bookcaseName;
    }
    public  void setBookStatusName(String bookStatusName)    
    {
        this.bookStatusName = bookStatusName;
    }
    public  String getBookStatusName()    
    {
        return bookStatusName;
    }
    public  void setBorrowStatusName(String borrowStatusName)    
    {
        this.borrowStatusName = borrowStatusName;
    }
    public  String getBorrowStatusName()    
    {
        return borrowStatusName;
    }
    public  void setOfflineApproverName(String offlineApproverName)    
    {
        this.offlineApproverName = offlineApproverName;
    }
    public  String getOfflineApproverName()    
    {
        return offlineApproverName;
    }
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  ViewBook copy()    
    {
        ViewBook entity = null;
        try        
        {
            entity = (ViewBook) super.clone();
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