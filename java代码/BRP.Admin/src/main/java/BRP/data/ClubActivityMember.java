package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ClubActivityMember extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  Timestamp joinDate;//加入时间;
    private  Integer clubActivityId;//俱乐部活动ID;
    private  Integer surplusQuantity;//剩余数量;
    private  Integer retailOrderId;
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
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setJoinDate(Timestamp joinDate)    
    {
        this.joinDate = joinDate;
    }
    public  Timestamp getJoinDate()    
    {
        return joinDate;
    }
    public  void setClubActivityId(Integer clubActivityId)    
    {
        this.clubActivityId = clubActivityId;
    }
    public  Integer getClubActivityId()    
    {
        return clubActivityId;
    }
    public  void setSurplusQuantity(Integer surplusQuantity)    
    {
        this.surplusQuantity = surplusQuantity;
    }
    public  Integer getSurplusQuantity()    
    {
        return surplusQuantity;
    }
    public  void setRetailOrderId(Integer retailOrderId)    
    {
        this.retailOrderId = retailOrderId;
    }
    public  Integer getRetailOrderId()    
    {
        return retailOrderId;
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
    public  ClubActivityMember copy()    
    {
        ClubActivityMember entity = null;
        try        
        {
            entity = (ClubActivityMember) super.clone();
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