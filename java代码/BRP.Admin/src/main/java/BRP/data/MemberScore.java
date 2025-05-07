package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class MemberScore extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  String memberScoreTypeCode;//积分类型编码;
    private  Integer score;//积分;
    private  Timestamp scoreTime;//积分时间;
    private  Timestamp createTime;//创建时间;
    private  String operator;
    private  String type;
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
    public  MemberScore copy()    
    {
        MemberScore entity = null;
        try        
        {
            entity = (MemberScore) super.clone();
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