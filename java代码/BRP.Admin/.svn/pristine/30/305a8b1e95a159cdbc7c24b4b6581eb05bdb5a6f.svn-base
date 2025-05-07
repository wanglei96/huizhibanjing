package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewSysLoginLog extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer accountId;//账户ID;
    private  String sysLoginTypeCode;//登录类型;
    private  String ip;//登录用户IP;
    private  String errorMessage;//登录失败原因;
    private  Timestamp createTime;//创建时间;
    private  String name;//;
    private  String loginName;//;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setAccountId(Integer accountId)    
    {
        this.accountId = accountId;
    }
    public  Integer getAccountId()    
    {
        return accountId;
    }
    public  void setSysLoginTypeCode(String sysLoginTypeCode)    
    {
        this.sysLoginTypeCode = sysLoginTypeCode;
    }
    public  String getSysLoginTypeCode()    
    {
        return sysLoginTypeCode;
    }
    public  void setIp(String ip)    
    {
        this.ip = ip;
    }
    public  String getIp()    
    {
        return ip;
    }
    public  void setErrorMessage(String errorMessage)    
    {
        this.errorMessage = errorMessage;
    }
    public  String getErrorMessage()    
    {
        return errorMessage;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setLoginName(String loginName)    
    {
        this.loginName = loginName;
    }
    public  String getLoginName()    
    {
        return loginName;
    }
    public  ViewSysLoginLog copy()    
    {
        ViewSysLoginLog entity = null;
        try        
        {
            entity = (ViewSysLoginLog) super.clone();
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