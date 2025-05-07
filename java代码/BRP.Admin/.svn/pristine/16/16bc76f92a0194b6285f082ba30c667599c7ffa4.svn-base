package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
public class SysUserRole extends DataEntity implements Cloneable
{
    private  Integer sysUserId;//;
    private  Integer sysRoleId;//;
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
    public  SysUserRole copy()    
    {
        SysUserRole entity = null;
        try        
        {
            entity = (SysUserRole) super.clone();
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