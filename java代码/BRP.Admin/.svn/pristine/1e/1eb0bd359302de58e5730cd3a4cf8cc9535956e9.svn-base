package BRP.model;
import BRP.data.EmployeeMaster;
public class EmployeeManager extends EmployeeMaster 
{
    private static EmployeeManager _instance;
    private EmployeeManager()    
    {
        this.setTableName("employee");
        this.setTypeName("Employee");
    }
    public  static EmployeeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new EmployeeManager();
        }
        return _instance;
    }
}