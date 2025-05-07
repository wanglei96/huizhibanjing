package strosoft.sys.model;
import strosoft.sys.data.SysAlignMaster;
public class SysAlignManager extends SysAlignMaster 
{
    private static SysAlignManager _instance;
    private SysAlignManager()    
    {
        this.setTableName("sys_align");
        this.setTypeName("SysAlign");
    }
    public  static SysAlignManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysAlignManager();
        }
        return _instance;
    }
}