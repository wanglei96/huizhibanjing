package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewMemberScore extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  String memberScoreTypeCode;//积分类型编码;
    private  Integer score;//积分;
    private  Timestamp scoreTime;//积分时间;
    private  Timestamp createTime;//创建时间;
    private  String operator;//操作人;
    private  String type;//加减类型;
    private  Integer companyId;//门店ID;
    private  String memberName;//名称;
    private  String memberCardNo;//会员卡号;
    private  String memberScoreTypeName;//会员积分类型;
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
    public  void setMemberScoreTypeCode(String memberScoreTypeCode)    
    {
        this.memberScoreTypeCode = memberScoreTypeCode;
    }
    public  String getMemberScoreTypeCode()    
    {
        return memberScoreTypeCode;
    }
    public  void setScore(Integer score)    
    {
        this.score = score;
    }
    public  Integer getScore()    
    {
        return score;
    }
    public  void setScoreTime(Timestamp scoreTime)    
    {
        this.scoreTime = scoreTime;
    }
    public  Timestamp getScoreTime()    
    {
        return scoreTime;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setOperator(String operator)    
    {
        this.operator = operator;
    }
    public  String getOperator()    
    {
        return operator;
    }
    public  void setType(String type)    
    {
        this.type = type;
    }
    public  String getType()    
    {
        return type;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  void setMemberCardNo(String memberCardNo)    
    {
        this.memberCardNo = memberCardNo;
    }
    public  String getMemberCardNo()    
    {
        return memberCardNo;
    }
    public  void setMemberScoreTypeName(String memberScoreTypeName)    
    {
        this.memberScoreTypeName = memberScoreTypeName;
    }
    public  String getMemberScoreTypeName()    
    {
        return memberScoreTypeName;
    }
    public  ViewMemberScore copy()    
    {
        ViewMemberScore entity = null;
        try        
        {
            entity = (ViewMemberScore) super.clone();
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