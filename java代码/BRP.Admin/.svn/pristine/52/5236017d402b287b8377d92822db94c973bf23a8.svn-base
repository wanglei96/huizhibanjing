package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class BookSku extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String bookName;//书名;
    private  Integer imageFileId;//封面ID;
    private  String author;//作者;
    private  String press;//出版社;
    private  BigDecimal price;//定价;
    private  String ISBN;//ISBN;
    private  String languageCode;//语言;
    private  String bindingTypeCode;//装订方式;
    private  String dangdangLink;//担当网链接;
    private  String introduction;//简介;
    private  String remark;//备注;
    private  Integer companyId;//门店ID;
    private  Integer approverId;//处理人ID;
    private  Timestamp approveTime;//处理时间;
    private  String approveComment;//处理内容;
    private  String approveStatusCode;//审批状态编码;
    private  Boolean isOnline;//是否线上可见;
    private  Integer bookSeriesId;//系列ID;
    private  Boolean isOut;
    private  Integer insufficientInventoryCount;
    private  Integer platformBookSkuId;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//修改时间;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
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
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setApproverId(Integer approverId)    
    {
        this.approverId = approverId;
    }
    public  Integer getApproverId()    
    {
        return approverId;
    }
    public  void setApproveTime(Timestamp approveTime)    
    {
        this.approveTime = approveTime;
    }
    public  Timestamp getApproveTime()    
    {
        return approveTime;
    }
    public  void setApproveComment(String approveComment)    
    {
        this.approveComment = approveComment;
    }
    public  String getApproveComment()    
    {
        return approveComment;
    }
    public  void setApproveStatusCode(String approveStatusCode)    
    {
        this.approveStatusCode = approveStatusCode;
    }
    public  String getApproveStatusCode()    
    {
        return approveStatusCode;
    }
    public  void setIsOnline(Boolean isOnline)    
    {
        this.isOnline = isOnline;
    }
    public  Boolean getIsOnline()    
    {
        return isOnline;
    }
    public  void setBookSeriesId(Integer bookSeriesId)    
    {
        this.bookSeriesId = bookSeriesId;
    }
    public  Integer getBookSeriesId()    
    {
        return bookSeriesId;
    }
    public  void setIsOut(Boolean isOut)    
    {
        this.isOut = isOut;
    }
    public  Boolean getIsOut()    
    {
        return isOut;
    }
    public  void setInsufficientInventoryCount(Integer insufficientInventoryCount)    
    {
        this.insufficientInventoryCount = insufficientInventoryCount;
    }
    public  Integer getInsufficientInventoryCount()    
    {
        return insufficientInventoryCount;
    }
    public  void setPlatformBookSkuId(Integer platformBookSkuId)    
    {
        this.platformBookSkuId = platformBookSkuId;
    }
    public  Integer getPlatformBookSkuId()    
    {
        return platformBookSkuId;
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
    public  BookSku copy()    
    {
        BookSku entity = null;
        try        
        {
            entity = (BookSku) super.clone();
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