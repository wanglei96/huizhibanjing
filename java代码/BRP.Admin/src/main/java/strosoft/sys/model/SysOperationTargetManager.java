package strosoft.sys.model;
import strosoft.sys.data.SysOperationTargetMaster;
public class SysOperationTargetManager extends SysOperationTargetMaster 
{
    private static SysOperationTargetManager _instance;
    private SysOperationTargetManager()    
    {
        this.setTableName("sys_operation_target");
        this.setTypeName("SysOperationTarget");
    }
    public  static SysOperationTargetManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysOperationTargetManager();
        }
        return _instance;
    }
}