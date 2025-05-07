package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewMemberCommunicaLog extends DataEntity implements Cloneable
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
    private  String memberCommunicaLogTypeName;//类型名称;
    private  String memberCommunicaLogMethodName;//沟通方式;
    private  String memberName;//名称;
    private  String companyUserName;//姓名;
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
    public  void setMemberCommunicaLogTypeName(String memberCommunicaLogTypeName)    
    {
        this.memberCommunicaLogTypeName = memberCommunicaLogTypeName;
    }
    public  String getMemberCommunicaLogTypeName()    
    {
        return memberCommunicaLogTypeName;
    }
    public  void setMemberCommunicaLogMethodName(String memberCommunicaLogMethodName)    
    {
        this.memberCommunicaLogMethodName = memberCommunicaLogMethodName;
    }
    public  String getMemberCommunicaLogMethodName()    
    {
        return memberCommunicaLogMethodName;
    }
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  void setCompanyUserName(String companyUserName)    
    {
        this.companyUserName = companyUserName;
    }
    public  String getCompanyUserName()    
    {
        return companyUserName;
    }
    public  ViewMemberCommunicaLog copy()    
    {
        ViewMemberCommunicaLog entity = null;
        try        
        {
            entity = (ViewMemberCommunicaLog) super.clone();
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