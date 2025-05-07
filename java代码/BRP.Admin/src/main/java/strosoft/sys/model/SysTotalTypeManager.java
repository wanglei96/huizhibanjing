package strosoft.sys.model;
import strosoft.sys.data.SysTotalTypeMaster;
public class SysTotalTypeManager extends SysTotalTypeMaster 
{
    private static SysTotalTypeManager _instance;
    private SysTotalTypeManager()    
    {
        this.setTableName("sys_total_type");
        this.setTypeName("SysTotalType");
    }
    public  static SysTotalTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysTotalTypeManager();
        }
        return _instance;
    }
}