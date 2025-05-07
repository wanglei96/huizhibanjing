package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.math.BigInteger;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewDeliverPlace extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String name;//名称;
    private  Integer deliverLineId;//配送线路ID;
    private  String address;//地址;
    private  String description;//描述;
    private  Integer displayOrder;//排序;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String deliverLineName;
    private  Integer delivererId;//配送员ID;
    private  Integer deliverAreaId;//配送大区ID;
    private  String delivererName;//配送员名称;
    private  String deliverAreaName;//配送大区;
    private  Integer companyId;//门店ID;
    private  BigInteger memberNum;//会员数;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setDeliverLineId(Integer deliverLineId)    
    {
        this.deliverLineId = deliverLineId;
    }
    public  Integer getDeliverLineId()    
    {
        return deliverLineId;
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
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
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
    public  void setDeliverLineName(String deliverLineName)    
    {
        this.deliverLineName = deliverLineName;
    }
    public  String getDeliverLineName()    
    {
        return deliverLineName;
    }
    public  void setDelivererId(Integer delivererId)    
    {
        this.delivererId = delivererId;
    }
    public  Integer getDelivererId()    
    {
        return delivererId;
    }
    public  void setDeliverAreaId(Integer deliverAreaId)    
    {
        this.deliverAreaId = deliverAreaId;
    }
    public  Integer getDeliverAreaId()    
    {
        return deliverAreaId;
    }
    public  void setDelivererName(String delivererName)    
    {
        this.delivererName = delivererName;
    }
    public  String getDelivererName()    
    {
        return delivererName;
    }
    public  void setDeliverAreaName(String deliverAreaName)    
    {
        this.deliverAreaName = deliverAreaName;
    }
    public  String getDeliverAreaName()    
    {
        return deliverAreaName;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setMemberNum(BigInteger memberNum)    
    {
        this.memberNum = memberNum;
    }
    public  BigInteger getMemberNum()    
    {
        return memberNum;
    }
    public  ViewDeliverPlace copy()    
    {
        ViewDeliverPlace entity = null;
        try        
        {
            entity = (ViewDeliverPlace) super.clone();
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