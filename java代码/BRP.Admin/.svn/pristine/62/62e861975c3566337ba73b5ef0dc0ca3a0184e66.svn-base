package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.math.BigInteger;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewDeliverLine extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String name;//配送员名称;
    private  String description;//描述;
    private  Integer deliverAreaId;//配送大区;
    private  Integer delivererId;//配送员ID;
    private  Integer bookpickerId;//拣书员ID;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String deliverAreaName;//配送大区;
    private  Integer companyId;//门店ID;
    private  String delivererName;//配送员名称;
    private  String bookpickerName;//拣书员名称;
    private  BigInteger deliverPlaceNum;//配送点总数;
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
    public  void setDescription(String description)    
    {
        this.description = description;
    }
    public  String getDescription()    
    {
        return description;
    }
    public  void setDeliverAreaId(Integer deliverAreaId)    
    {
        this.deliverAreaId = deliverAreaId;
    }
    public  Integer getDeliverAreaId()    
    {
        return deliverAreaId;
    }
    public  void setDelivererId(Integer delivererId)    
    {
        this.delivererId = delivererId;
    }
    public  Integer getDelivererId()    
    {
        return delivererId;
    }
    public  void setBookpickerId(Integer bookpickerId)    
    {
        this.bookpickerId = bookpickerId;
    }
    public  Integer getBookpickerId()    
    {
        return bookpickerId;
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
    public  void setDelivererName(String delivererName)    
    {
        this.delivererName = delivererName;
    }
    public  String getDelivererName()    
    {
        return delivererName;
    }
    public  void setBookpickerName(String bookpickerName)    
    {
        this.bookpickerName = bookpickerName;
    }
    public  String getBookpickerName()    
    {
        return bookpickerName;
    }
    public  void setDeliverPlaceNum(BigInteger deliverPlaceNum)    
    {
        this.deliverPlaceNum = deliverPlaceNum;
    }
    public  BigInteger getDeliverPlaceNum()    
    {
        return deliverPlaceNum;
    }
    public  ViewDeliverLine copy()    
    {
        ViewDeliverLine entity = null;
        try        
        {
            entity = (ViewDeliverLine) super.clone();
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