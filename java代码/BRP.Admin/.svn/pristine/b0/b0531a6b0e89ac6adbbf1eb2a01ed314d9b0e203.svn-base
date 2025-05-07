package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class ViewSysUserRole extends DataEntity implements Cloneable
{
    private  Integer sysUserId;//;
    private  Integer sysRoleId;//;
    private  String sysUserName;//;
    private  String sysRoleName;//;
    public  void setSysUserId(Integer sysUserId)    
    {
        this.sysUserId = sysUserId;
    }
    public  Integer getSysUserId()    
    {
        return sysUserId;
    }
    public  void setSysRoleId(Integer sysRoleId)    
    {
        this.sysRoleId = sysRoleId;
    }
    public  Integer getSysRoleId()    
    {
        return sysRoleId;
    }
    public  void setSysUserName(String sysUserName)    
    {
        this.sysUserName = sysUserName;
    }
    public  String getSysUserName()    
    {
        return sysUserName;
    }
    public  void setSysRoleName(String sysRoleName)    
    {
        this.sysRoleName = sysRoleName;
    }
    public  String getSysRoleName()    
    {
        return sysRoleName;
    }
    public  ViewSysUserRole copy()    
    {
        ViewSysUserRole entity = null;
        try        
        {
            entity = (ViewSysUserRole) super.clone();
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