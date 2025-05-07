package strosoft.sys.model;

import strosoft.sys.data.SysLoginLogMaster;

public class SysLoginLogManager extends SysLoginLogMaster
{
    private static SysLoginLogManager _instance;
    private SysLoginLogManager()    
    {
        this.setTableName("sys_login_log");
        this.setTypeName("SysLoginLog");
    }
    public  static SysLoginLogManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysLoginLogManager();
        }
        return _instance;
    }
}