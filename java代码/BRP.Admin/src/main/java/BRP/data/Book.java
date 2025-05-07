package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class Book extends DataEntity implements Cloneable
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
    private  Boolean isDamaged;
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
    public  Book copy()    
    {
        Book entity = null;
        try        
        {
            entity = (Book) super.clone();
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