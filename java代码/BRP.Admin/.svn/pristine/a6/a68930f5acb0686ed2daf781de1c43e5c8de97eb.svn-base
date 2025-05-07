package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class Company extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String companyName;//门店名称;
    private  String loginName;//门店管理员账号;
    private  String password;//门店管理员密码;
    private  Integer licenseImageFileId;//证照照片ID;
    private  String companyNo;//门店编号;
    private  String contact;//联系人;
    private  String mobilePhone;//联系电话;
    private  String address;//门店地址;
    private  String description;//简介;
    private  String remark;//备注;
    private  Boolean enabled;//是否启用;
    private  Boolean deleted;//是否删除;
    private  Boolean isDefault;//是否默认门店;
    private  String servicePhone;//客服电话;
    private  Integer customerServiceImageFileId;//客服二维码图片;
    private  Integer promotionCopywritingImageFileId;//宣传文案二维码;
    private  Integer bookboxInventoryQuantity;//书箱库存数量;
    private  Integer imageFileId;//门店照片ID;
    private  String bankName;//开户银行;
    private  String accountName;//户名;
    private  String bankCardNo;//银行卡号;
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
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setLoginName(String loginName)    
    {
        this.loginName = loginName;
    }
    public  String getLoginName()    
    {
        return loginName;
    }
    public  void setPassword(String password)    
    {
        this.password = password;
    }
    public  String getPassword()    
    {
        return password;
    }
    public  void setLicenseImageFileId(Integer licenseImageFileId)    
    {
        this.licenseImageFileId = licenseImageFileId;
    }
    public  Integer getLicenseImageFileId()    
    {
        return licenseImageFileId;
    }
    public  void setCompanyNo(String companyNo)    
    {
        this.companyNo = companyNo;
    }
    public  String getCompanyNo()    
    {
        return companyNo;
    }
    public  void setContact(String contact)    
    {
        this.contact = contact;
    }
    public  String getContact()    
    {
        return contact;
    }
    public  void setMobilePhone(String mobilePhone)    
    {
        this.mobilePhone = mobilePhone;
    }
    public  String getMobilePhone()    
    {
        return mobilePhone;
    }
    public  void setAddress(String address)    
    {
        this.address = address;
    }
    public  String getAddress()    
    {
        return address;
    }
    public  void setDescription(String description)    
    {
        this.description = description;
    }
    public  String getDescription()    
    {
        return description;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setEnabled(Boolean enabled)    
    {
        this.enabled = enabled;
    }
    public  Boolean getEnabled()    
    {
        return enabled;
    }
    public  void setDeleted(Boolean deleted)    
    {
        this.deleted = deleted;
    }
    public  Boolean getDeleted()    
    {
        return deleted;
    }
    public  void setIsDefault(Boolean isDefault)    
    {
        this.isDefault = isDefault;
    }
    public  Boolean getIsDefault()    
    {
        return isDefault;
    }
    public  void setServicePhone(String servicePhone)    
    {
        this.servicePhone = servicePhone;
    }
    public  String getServicePhone()    
    {
        return servicePhone;
    }
    public  void setCustomerServiceImageFileId(Integer customerServiceImageFileId)    
    {
        this.customerServiceImageFileId = customerServiceImageFileId;
    }
    public  Integer getCustomerServiceImageFileId()    
    {
        return customerServiceImageFileId;
    }
    public  void setPromotionCopywritingImageFileId(Integer promotionCopywritingImageFileId)    
    {
        this.promotionCopywritingImageFileId = promotionCopywritingImageFileId;
    }
    public  Integer getPromotionCopywritingImageFileId()    
    {
        return promotionCopywritingImageFileId;
    }
    public  void setBookboxInventoryQuantity(Integer bookboxInventoryQuantity)    
    {
        this.bookboxInventoryQuantity = bookboxInventoryQuantity;
    }
    public  Integer getBookboxInventoryQuantity()    
    {
        return bookboxInventoryQuantity;
    }
    public  void setImageFileId(Integer imageFileId)    
    {
        this.imageFileId = imageFileId;
    }
    public  Integer getImageFileId()    
    {
        return imageFileId;
    }
    public  void setBankName(String bankName)    
    {
        this.bankName = bankName;
    }
    public  String getBankName()    
    {
        return bankName;
    }
    public  void setAccountName(String accountName)    
    {
        this.accountName = accountName;
    }
    public  String getAccountName()    
    {
        return accountName;
    }
    public  void setBankCardNo(String bankCardNo)    
    {
        this.bankCardNo = bankCardNo;
    }
    public  String getBankCardNo()    
    {
        return bankCardNo;
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
    public  Company copy()    
    {
        Company entity = null;
        try        
        {
            entity = (Company) super.clone();
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