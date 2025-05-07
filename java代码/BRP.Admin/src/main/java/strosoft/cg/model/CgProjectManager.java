package strosoft.cg.model;
import strosoft.cg.data.CgProjectMaster;
public class CgProjectManager extends CgProjectMaster 
{
    private static CgProjectManager _instance;
    private CgProjectManager()    
    {
        this.setTableName("cg_project");
        this.setTypeName("CgProject");
    }
    public  static CgProjectManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CgProjectManager();
        }
        return _instance;
    }
}