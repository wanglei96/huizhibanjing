package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.math.BigInteger;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewDeliverer extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyRoleId;//商户角色ID;
    private  Integer companyId;//门店ID;
    private  Integer companyOrganizationId;//商户组织机构ID;
    private  String name;//配送员名称;
    private  String loginName;//登录名;
    private  String password;//密码;
    private  Integer licenseImageFileId;//证照照片ID;
    private  String employeeNo;//员工编号;
    private  String mobilePhone;//联系电话;
    private  String email;//电子邮箱;
    private  String address;//地址;
    private  Boolean isDeleted;//是否删除;
    private  String description;//描述;
    private  Boolean enabled;//是否启用;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  Integer deliverAreaId;//配送大区;
    private  String companyName;//门店名称;
    private  String deliverAreaName;//配送大区;
    private  BigInteger deliverLineNum;//路线数量;
    private  BigInteger deliverPlaceNum;//地址数量;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setCompanyRoleId(Integer companyRoleId)    
    {
        this.companyRoleId = companyRoleId;
    }
    public  Integer getCompanyRoleId()    
    {
        return companyRoleId;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setCompanyOrganizationId(Integer companyOrganizationId)    
    {
        this.companyOrganizationId = companyOrganizationId;
    }
    public  Integer getCompanyOrganizationId()    
    {
        return companyOrganizationId;
    }
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
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
    public  void setEmployeeNo(String employeeNo)    
    {
        this.employeeNo = employeeNo;
    }
    public  String getEmployeeNo()    
    {
        return employeeNo;
    }
    public  void setMobilePhone(String mobilePhone)    
    {
        this.mobilePhone = mobilePhone;
    }
    public  String getMobilePhone()    
    {
        return mobilePhone;
    }
    public  void setEmail(String email)    
    {
        this.email = email;
    }
    public  String getEmail()    
    {
        return email;
    }
    public  void setAddress(String address)    
    {
        this.address = address;
    }
    public  String getAddress()    
    {
        return address;
    }
    public  void setIsDeleted(Boolean isDeleted)    
    {
        this.isDeleted = isDeleted;
    }
    public  Boolean getIsDeleted()    
    {
        return isDeleted;
    }
    public  void setDescription(String description)    
    {
        this.description = description;
    }
    public  String getDescription()    
    {
        return description;
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
    public  void setDeliverAreaId(Integer deliverAreaId)    
    {
        this.deliverAreaId = deliverAreaId;
    }
    public  Integer getDeliverAreaId()    
    {
        return deliverAreaId;
    }
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setDeliverAreaName(String deliverAreaName)    
    {
        this.deliverAreaName = deliverAreaName;
    }
    public  String getDeliverAreaName()    
    {
        return deliverAreaName;
    }
    public  void setDeliverLineNum(BigInteger deliverLineNum)    
    {
        this.deliverLineNum = deliverLineNum;
    }
    public  BigInteger getDeliverLineNum()    
    {
        return deliverLineNum;
    }
    public  void setDeliverPlaceNum(BigInteger deliverPlaceNum)    
    {
        this.deliverPlaceNum = deliverPlaceNum;
    }
    public  BigInteger getDeliverPlaceNum()    
    {
        return deliverPlaceNum;
    }
    public  ViewDeliverer copy()    
    {
        ViewDeliverer entity = null;
        try        
        {
            entity = (ViewDeliverer) super.clone();
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