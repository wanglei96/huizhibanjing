package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
public class CompanyUserRole extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyUserId;//商户用户ID;
    private  Integer companyRoleId;//商户角色ID;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setCompanyUserId(Integer companyUserId)    
    {
        this.companyUserId = companyUserId;
    }
    public  Integer getCompanyUserId()    
    {
        return companyUserId;
    }
    public  void setCompanyRoleId(Integer companyRoleId)    
    {
        this.companyRoleId = companyRoleId;
    }
    public  Integer getCompanyRoleId()    
    {
        return companyRoleId;
    }
    public  CompanyUserRole copy()    
    {
        CompanyUserRole entity = null;
        try        
        {
            entity = (CompanyUserRole) super.clone();
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