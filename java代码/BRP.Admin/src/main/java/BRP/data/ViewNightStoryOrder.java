package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewNightStoryOrder extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  Timestamp subscribeTime;//订阅时间;
    private  String subscribeTypeCode;//订阅类型编码;
    private  Timestamp expireTime;//到期时间;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String memberName;//名称;
    private  Integer companyId;//ID;
    private  String companyName;//门店名称;
    private  String subscribeTypeName;//订阅类型;
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
    public  void setSubscribeTime(Timestamp subscribeTime)    
    {
        this.subscribeTime = subscribeTime;
    }
    public  Timestamp getSubscribeTime()    
    {
        return subscribeTime;
    }
    public  void setSubscribeTypeCode(String subscribeTypeCode)    
    {
        this.subscribeTypeCode = subscribeTypeCode;
    }
    public  String getSubscribeTypeCode()    
    {
        return subscribeTypeCode;
    }
    public  void setExpireTime(Timestamp expireTime)    
    {
        this.expireTime = expireTime;
    }
    public  Timestamp getExpireTime()    
    {
        return expireTime;
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
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  void setSubscribeTypeName(String subscribeTypeName)    
    {
        this.subscribeTypeName = subscribeTypeName;
    }
    public  String getSubscribeTypeName()    
    {
        return subscribeTypeName;
    }
    public  ViewNightStoryOrder copy()    
    {
        ViewNightStoryOrder entity = null;
        try        
        {
            entity = (ViewNightStoryOrder) super.clone();
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