package strosoft.sys.model;
import strosoft.sys.data.SysValidationDataTypeMaster;
public class SysValidationDataTypeManager extends SysValidationDataTypeMaster 
{
    private static SysValidationDataTypeManager _instance;
    private SysValidationDataTypeManager()    
    {
        this.setTableName("sys_validation_data_type");
        this.setTypeName("SysValidationDataType");
    }
    public  static SysValidationDataTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysValidationDataTypeManager();
        }
        return _instance;
    }
}