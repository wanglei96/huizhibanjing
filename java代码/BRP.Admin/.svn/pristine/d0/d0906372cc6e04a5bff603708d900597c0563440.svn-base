package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewSysOperationLog extends DataEntity implements Cloneable
{
    private  Integer id;//id;
    private  String operationTargetCode;//操作的表名;
    private  String operationTypeCode;//操作类型;
    private  String content;//修改内容;
    private  Integer operatorId;//操作人Id;
    private  Timestamp operateTime;//操作时间;
    private  Integer primaryKey;//操作数据id;
    private  String operationTargetName;//;
    private  String operationTypeName;//;
    private  String sysUserTrueName;//;
    private  String memberName;//名称;
    private  String companyUserName;//姓名;
    private  String companyUserLoginName;//登录账号;
    private  String sysUserLoginName;//;
    private  String memberMobilePhone;//联系电话;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setOperationTargetCode(String operationTargetCode)    
    {
        this.operationTargetCode = operationTargetCode;
    }
    public  String getOperationTargetCode()    
    {
        return operationTargetCode;
    }
    public  void setOperationTypeCode(String operationTypeCode)    
    {
        this.operationTypeCode = operationTypeCode;
    }
    public  String getOperationTypeCode()    
    {
        return operationTypeCode;
    }
    public  void setContent(String content)    
    {
        this.content = content;
    }
    public  String getContent()    
    {
        return content;
    }
    public  void setOperatorId(Integer operatorId)    
    {
        this.operatorId = operatorId;
    }
    public  Integer getOperatorId()    
    {
        return operatorId;
    }
    public  void setOperateTime(Timestamp operateTime)    
    {
        this.operateTime = operateTime;
    }
    public  Timestamp getOperateTime()    
    {
        return operateTime;
    }
    public  void setPrimaryKey(Integer primaryKey)    
    {
        this.primaryKey = primaryKey;
    }
    public  Integer getPrimaryKey()    
    {
        return primaryKey;
    }
    public  void setOperationTargetName(String operationTargetName)    
    {
        this.operationTargetName = operationTargetName;
    }
    public  String getOperationTargetName()    
    {
        return operationTargetName;
    }
    public  void setOperationTypeName(String operationTypeName)    
    {
        this.operationTypeName = operationTypeName;
    }
    public  String getOperationTypeName()    
    {
        return operationTypeName;
    }
    public  void setSysUserTrueName(String sysUserTrueName)    
    {
        this.sysUserTrueName = sysUserTrueName;
    }
    public  String getSysUserTrueName()    
    {
        return sysUserTrueName;
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
    public  void setCompanyUserLoginName(String companyUserLoginName)    
    {
        this.companyUserLoginName = companyUserLoginName;
    }
    public  String getCompanyUserLoginName()    
    {
        return companyUserLoginName;
    }
    public  void setSysUserLoginName(String sysUserLoginName)    
    {
        this.sysUserLoginName = sysUserLoginName;
    }
    public  String getSysUserLoginName()    
    {
        return sysUserLoginName;
    }
    public  void setMemberMobilePhone(String memberMobilePhone)    
    {
        this.memberMobilePhone = memberMobilePhone;
    }
    public  String getMemberMobilePhone()    
    {
        return memberMobilePhone;
    }
    public  ViewSysOperationLog copy()    
    {
        ViewSysOperationLog entity = null;
        try        
        {
            entity = (ViewSysOperationLog) super.clone();
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