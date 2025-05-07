package strosoft.sys.model;
import strosoft.sys.data.SysOperationTypeMaster;
public class SysOperationTypeManager extends SysOperationTypeMaster 
{
    private static SysOperationTypeManager _instance;
    private SysOperationTypeManager()    
    {
        this.setTableName("sys_operation_type");
        this.setTypeName("SysOperationType");
    }
    public  static SysOperationTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysOperationTypeManager();
        }
        return _instance;
    }
}