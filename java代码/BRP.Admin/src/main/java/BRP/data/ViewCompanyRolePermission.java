package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class ViewCompanyRolePermission extends DataEntity implements Cloneable
{
    private  Integer companyRoleId;//角色ID;
    private  String permission;//权限编码;
    public  void setCompanyRoleId(Integer companyRoleId)    
    {
        this.companyRoleId = companyRoleId;
    }
    public  Integer getCompanyRoleId()    
    {
        return companyRoleId;
    }
    public  void setPermission(String permission)    
    {
        this.permission = permission;
    }
    public  String getPermission()    
    {
        return permission;
    }
    public  ViewCompanyRolePermission copy()    
    {
        ViewCompanyRolePermission entity = null;
        try        
        {
            entity = (ViewCompanyRolePermission) super.clone();
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