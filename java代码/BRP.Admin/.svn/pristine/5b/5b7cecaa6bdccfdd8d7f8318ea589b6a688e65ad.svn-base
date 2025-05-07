package strosoft.sys.model;
import strosoft.sys.data.SysBuildProjectMaster;
public class SysBuildProjectManager extends SysBuildProjectMaster 
{
    private static SysBuildProjectManager _instance;
    private SysBuildProjectManager()    
    {
        this.setTableName("sys_build_project");
        this.setTypeName("SysBuildProject");
    }
    public  static SysBuildProjectManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysBuildProjectManager();
        }
        return _instance;
    }
}