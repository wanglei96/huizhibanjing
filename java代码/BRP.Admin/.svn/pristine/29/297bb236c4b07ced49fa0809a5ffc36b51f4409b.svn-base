package strosoft.sys.model;
import strosoft.sys.data.SysLoginTypeMaster;
public class SysLoginTypeManager extends SysLoginTypeMaster 
{
    private static SysLoginTypeManager _instance;
    private SysLoginTypeManager()    
    {
        this.setTableName("sys_login_type");
        this.setTypeName("SysLoginType");
    }
    public  static SysLoginTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysLoginTypeManager();
        }
        return _instance;
    }
}