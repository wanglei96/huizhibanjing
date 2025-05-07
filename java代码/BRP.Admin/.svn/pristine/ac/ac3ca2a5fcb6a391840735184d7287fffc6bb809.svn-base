package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewMemberCardDisableLog extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  Integer memberCardId;//会员卡ID;
    private  Timestamp stopDate;//停卡日期;
    private  Integer days;//停卡时长;
    private  Timestamp stopEndDate;//停卡结束日期;
    private  String remark;//备注;
    private  Timestamp applyTime;//申请时间;
    private  Boolean isRelieve;//是否解除;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String source;//来源;
    private  String status;//状态;
    private  String type;//类型;
    private  Integer stopCardNum;//停卡次数;
    private  Integer companyId;//门店ID;
    private  String memberCardNo;//会员卡号;
    private  String memberName;//名称;
    private  String typeName;//;
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
    public  void setMemberCardId(Integer memberCardId)    
    {
        this.memberCardId = memberCardId;
    }
    public  Integer getMemberCardId()    
    {
        return memberCardId;
    }
    public  void setStopDate(Timestamp stopDate)    
    {
        this.stopDate = stopDate;
    }
    public  Timestamp getStopDate()    
    {
        return stopDate;
    }
    public  void setDays(Integer days)    
    {
        this.days = days;
    }
    public  Integer getDays()    
    {
        return days;
    }
    public  void setStopEndDate(Timestamp stopEndDate)    
    {
        this.stopEndDate = stopEndDate;
    }
    public  Timestamp getStopEndDate()    
    {
        return stopEndDate;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setApplyTime(Timestamp applyTime)    
    {
        this.applyTime = applyTime;
    }
    public  Timestamp getApplyTime()    
    {
        return applyTime;
    }
    public  void setIsRelieve(Boolean isRelieve)    
    {
        this.isRelieve = isRelieve;
    }
    public  Boolean getIsRelieve()    
    {
        return isRelieve;
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
    public  void setSource(String source)    
    {
        this.source = source;
    }
    public  String getSource()    
    {
        return source;
    }
    public  void setStatus(String status)    
    {
        this.status = status;
    }
    public  String getStatus()    
    {
        return status;
    }
    public  void setType(String type)    
    {
        this.type = type;
    }
    public  String getType()    
    {
        return type;
    }
    public  void setStopCardNum(Integer stopCardNum)    
    {
        this.stopCardNum = stopCardNum;
    }
    public  Integer getStopCardNum()    
    {
        return stopCardNum;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setMemberCardNo(String memberCardNo)    
    {
        this.memberCardNo = memberCardNo;
    }
    public  String getMemberCardNo()    
    {
        return memberCardNo;
    }
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  void setTypeName(String typeName)    
    {
        this.typeName = typeName;
    }
    public  String getTypeName()    
    {
        return typeName;
    }
    public  ViewMemberCardDisableLog copy()    
    {
        ViewMemberCardDisableLog entity = null;
        try        
        {
            entity = (ViewMemberCardDisableLog) super.clone();
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