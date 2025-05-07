package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class MemberCardBatch extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String name;//批次名称;
    private  String batchNo;//批次编码;
    private  String color;//颜色;
    private  Integer borrowQuantity;//借阅次数;
    private  Boolean isOnlineReading;//是否在线阅读（1可以，0不可以）;
    private  Integer memberCardTypeId;//会员卡分类;
    private  Boolean isUnlimited;//是否无限制;
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
    public  void setBatchNo(String batchNo)    
    {
        this.batchNo = batchNo;
    }
    public  String getBatchNo()    
    {
        return batchNo;
    }
    public  void setColor(String color)    
    {
        this.color = color;
    }
    public  String getColor()    
    {
        return color;
    }
    public  void setBorrowQuantity(Integer borrowQuantity)    
    {
        this.borrowQuantity = borrowQuantity;
    }
    public  Integer getBorrowQuantity()    
    {
        return borrowQuantity;
    }
    public  void setIsOnlineReading(Boolean isOnlineReading)    
    {
        this.isOnlineReading = isOnlineReading;
    }
    public  Boolean getIsOnlineReading()    
    {
        return isOnlineReading;
    }
    public  void setMemberCardTypeId(Integer memberCardTypeId)    
    {
        this.memberCardTypeId = memberCardTypeId;
    }
    public  Integer getMemberCardTypeId()    
    {
        return memberCardTypeId;
    }
    public  void setIsUnlimited(Boolean isUnlimited)    
    {
        this.isUnlimited = isUnlimited;
    }
    public  Boolean getIsUnlimited()    
    {
        return isUnlimited;
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
    public  MemberCardBatch copy()    
    {
        MemberCardBatch entity = null;
        try        
        {
            entity = (MemberCardBatch) super.clone();
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