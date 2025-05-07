package BRP.model;
import BRP.data.ClassesMaster;
public class ClassesManager extends ClassesMaster 
{
    private static ClassesManager _instance;
    private ClassesManager()    
    {
        this.setTableName("classes");
        this.setTypeName("Classes");
    }
    public  static ClassesManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ClassesManager();
        }
        return _instance;
    }
}