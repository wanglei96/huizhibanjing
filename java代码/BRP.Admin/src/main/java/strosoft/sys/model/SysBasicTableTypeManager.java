package strosoft.sys.model;
import strosoft.sys.data.SysBasicTableTypeMaster;
public class SysBasicTableTypeManager extends SysBasicTableTypeMaster 
{
    private static SysBasicTableTypeManager _instance;
    private SysBasicTableTypeManager()    
    {
        this.setTableName("sys_basic_table_type");
        this.setTypeName("SysBasicTableType");
    }
    public  static SysBasicTableTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysBasicTableTypeManager();
        }
        return _instance;
    }
}