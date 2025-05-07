package BRP.model;
import BRP.data.ViewCompanyRolePermissionMaster;
public class ViewCompanyRolePermissionManager extends ViewCompanyRolePermissionMaster 
{
    private static ViewCompanyRolePermissionManager _instance;
    public  static ViewCompanyRolePermissionManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCompanyRolePermissionManager();
        }
        return _instance;
    }
    private   ViewCompanyRolePermissionManager()    
    {
        this.setTypeName("ViewCompanyRolePermission");
    }
}