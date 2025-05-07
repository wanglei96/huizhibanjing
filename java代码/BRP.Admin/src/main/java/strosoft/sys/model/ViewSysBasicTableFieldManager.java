package strosoft.sys.model;
import strosoft.sys.data.ViewSysBasicTableFieldMaster;
public class ViewSysBasicTableFieldManager extends ViewSysBasicTableFieldMaster 
{
    private static ViewSysBasicTableFieldManager _instance;
    public  static ViewSysBasicTableFieldManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewSysBasicTableFieldManager();
        }
        return _instance;
    }
    private   ViewSysBasicTableFieldManager()    
    {
        this.setTypeName("ViewSysBasicTableField");
    }
}