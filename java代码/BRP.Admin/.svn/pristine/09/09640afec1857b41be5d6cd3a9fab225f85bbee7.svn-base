package strosoft.sys.model;
import strosoft.sys.data.SysFieldDataTypeMaster;
public class SysFieldDataTypeManager extends SysFieldDataTypeMaster 
{
    private static SysFieldDataTypeManager _instance;
    private SysFieldDataTypeManager()    
    {
        this.setTableName("sys_field_data_type");
        this.setTypeName("SysFieldDataType");
    }
    public  static SysFieldDataTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysFieldDataTypeManager();
        }
        return _instance;
    }
}