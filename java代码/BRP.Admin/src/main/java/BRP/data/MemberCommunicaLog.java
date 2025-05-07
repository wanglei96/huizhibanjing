package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class MemberCommunicaLog extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  String memberCommunicaLogTypeCode;//沟通类别;
    private  String content;//沟通内容;
    private  Integer companyUserId;//沟通人员(默认当前登录人);
    private  String memberCommunicaLogMethodCode;//沟通方式;
    private  Timestamp communicaTime;//沟通时间;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//修改时间;
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
    public  void setMemberCommunicaLogTypeCode(String memberCommunicaLogTypeCode)    
    {
        this.memberCommunicaLogTypeCode = memberCommunicaLogTypeCode;
    }
    public  String getMemberCommunicaLogTypeCode()    
    {
        return memberCommunicaLogTypeCode;
    }
    public  void setContent(String content)    
    {
        this.content = content;
    }
    public  String getContent()    
    {
        return content;
    }
    public  void setCompanyUserId(Integer companyUserId)    
    {
        this.companyUserId = companyUserId;
    }
    public  Integer getCompanyUserId()    
    {
        return companyUserId;
    }
    public  void setMemberCommunicaLogMethodCode(String memberCommunicaLogMethodCode)    
    {
        this.memberCommunicaLogMethodCode = memberCommunicaLogMethodCode;
    }
    public  String getMemberCommunicaLogMethodCode()    
    {
        return memberCommunicaLogMethodCode;
    }
    public  void setCommunicaTime(Timestamp communicaTime)    
    {
        this.communicaTime = communicaTime;
    }
    public  Timestamp getCommunicaTime()    
    {
        return communicaTime;
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
    public  MemberCommunicaLog copy()    
    {
        MemberCommunicaLog entity = null;
        try        
        {
            entity = (MemberCommunicaLog) super.clone();
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