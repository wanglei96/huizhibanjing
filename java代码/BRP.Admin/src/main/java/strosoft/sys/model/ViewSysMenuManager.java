package strosoft.sys.model;
import strosoft.sys.data.ViewSysMenuMaster;
public class ViewSysMenuManager extends ViewSysMenuMaster 
{
    private static ViewSysMenuManager _instance;
    public  static ViewSysMenuManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewSysMenuManager();
        }
        return _instance;
    }
    private   ViewSysMenuManager()    
    {
        this.setTypeName("ViewSysMenu");
    }
}