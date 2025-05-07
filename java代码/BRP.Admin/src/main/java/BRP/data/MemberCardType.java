package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class MemberCardType extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String name;//名称;
    private  Integer maxQuantity;//每次最多可借数量;
    private  Boolean isSecurityDeposit;//是否需要借书押金;
    private  BigDecimal securityDepositPrice;
    private  BigDecimal bookboxPrice;
    private  BigDecimal membershipPrice;
    private  Integer days;
    private  String memberCardCategoryCode;//会员卡分类编码;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  Boolean isDeleted;
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
    public  void setMaxQuantity(Integer maxQuantity)    
    {
        this.maxQuantity = maxQuantity;
    }
    public  Integer getMaxQuantity()    
    {
        return maxQuantity;
    }
    public  void setIsSecurityDeposit(Boolean isSecurityDeposit)    
    {
        this.isSecurityDeposit = isSecurityDeposit;
    }
    public  Boolean getIsSecurityDeposit()    
    {
        return isSecurityDeposit;
    }
    public  void setSecurityDepositPrice(BigDecimal securityDepositPrice)    
    {
        this.securityDepositPrice = securityDepositPrice;
    }
    public  BigDecimal getSecurityDepositPrice()    
    {
        return securityDepositPrice;
    }
    public  void setBookboxPrice(BigDecimal bookboxPrice)    
    {
        this.bookboxPrice = bookboxPrice;
    }
    public  BigDecimal getBookboxPrice()    
    {
        return bookboxPrice;
    }
    public  void setMembershipPrice(BigDecimal membershipPrice)    
    {
        this.membershipPrice = membershipPrice;
    }
    public  BigDecimal getMembershipPrice()    
    {
        return membershipPrice;
    }
    public  void setDays(Integer days)    
    {
        this.days = days;
    }
    public  Integer getDays()    
    {
        return days;
    }
    public  void setMemberCardCategoryCode(String memberCardCategoryCode)    
    {
        this.memberCardCategoryCode = memberCardCategoryCode;
    }
    public  String getMemberCardCategoryCode()    
    {
        return memberCardCategoryCode;
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
    public  void setIsDeleted(Boolean isDeleted)    
    {
        this.isDeleted = isDeleted;
    }
    public  Boolean getIsDeleted()    
    {
        return isDeleted;
    }
    public  MemberCardType copy()    
    {
        MemberCardType entity = null;
        try        
        {
            entity = (MemberCardType) super.clone();
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