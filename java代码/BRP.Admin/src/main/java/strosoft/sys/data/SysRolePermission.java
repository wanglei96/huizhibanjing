package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class SysRolePermission extends DataEntity implements Cloneable
{
    private  Integer sysRoleId;//;
    private  String permission;//;
    public  void setSysRoleId(Integer sysRoleId)    
    {
        this.sysRoleId = sysRoleId;
    }
    public  Integer getSysRoleId()    
    {
        return sysRoleId;
    }
    public  void setPermission(String permission)    
    {
        this.permission = permission;
    }
    public  String getPermission()    
    {
        return permission;
    }
    public  SysRolePermission copy()    
    {
        SysRolePermission entity = null;
        try        
        {
            entity = (SysRolePermission) super.clone();
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