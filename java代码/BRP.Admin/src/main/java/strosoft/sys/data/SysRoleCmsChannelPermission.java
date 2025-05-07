package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
public class SysRoleCmsChannelPermission extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  Integer sysRoleId;//角色ID;
    private  Integer cmsChannelId;//栏目ID;
    private  Integer cmsSiteId;//网站ID;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setSysRoleId(Integer sysRoleId)    
    {
        this.sysRoleId = sysRoleId;
    }
    public  Integer getSysRoleId()    
    {
        return sysRoleId;
    }
    public  void setCmsChannelId(Integer cmsChannelId)    
    {
        this.cmsChannelId = cmsChannelId;
    }
    public  Integer getCmsChannelId()    
    {
        return cmsChannelId;
    }
    public  void setCmsSiteId(Integer cmsSiteId)    
    {
        this.cmsSiteId = cmsSiteId;
    }
    public  Integer getCmsSiteId()    
    {
        return cmsSiteId;
    }
    public  SysRoleCmsChannelPermission copy()    
    {
        SysRoleCmsChannelPermission entity = null;
        try        
        {
            entity = (SysRoleCmsChannelPermission) super.clone();
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