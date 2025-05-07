package strosoft.sys.model;

import strosoft.sys.data.ViewSysOperationLogMaster;

public class ViewSysOperationLogManager extends ViewSysOperationLogMaster
{
    private static ViewSysOperationLogManager _instance;
    public  static ViewSysOperationLogManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewSysOperationLogManager();
        }
        return _instance;
    }
    private   ViewSysOperationLogManager()    
    {
        this.setTypeName("ViewSysOperationLog");
    }
}