package strosoft.sys.model;
import strosoft.sys.data.SysBasicTableFieldTypeMaster;
public class SysBasicTableFieldTypeManager extends SysBasicTableFieldTypeMaster 
{
    private static SysBasicTableFieldTypeManager _instance;
    private SysBasicTableFieldTypeManager()    
    {
        this.setTableName("sys_basic_table_field_type");
        this.setTypeName("SysBasicTableFieldType");
    }
    public  static SysBasicTableFieldTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysBasicTableFieldTypeManager();
        }
        return _instance;
    }
}