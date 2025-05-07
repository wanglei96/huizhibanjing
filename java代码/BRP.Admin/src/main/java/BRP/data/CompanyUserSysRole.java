package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
public class CompanyUserSysRole extends DataEntity implements Cloneable
{
    private  Integer companyUserId;//服务商ID;
    private  Integer sysRoleId;//角色ID;
    public  void setCompanyUserId(Integer companyUserId)    
    {
        this.companyUserId = companyUserId;
    }
    public  Integer getCompanyUserId()    
    {
        return companyUserId;
    }
    public  void setSysRoleId(Integer sysRoleId)    
    {
        this.sysRoleId = sysRoleId;
    }
    public  Integer getSysRoleId()    
    {
        return sysRoleId;
    }
    public  CompanyUserSysRole copy()    
    {
        CompanyUserSysRole entity = null;
        try        
        {
            entity = (CompanyUserSysRole) super.clone();
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