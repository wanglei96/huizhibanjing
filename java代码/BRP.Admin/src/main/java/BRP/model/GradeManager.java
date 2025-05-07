package BRP.model;
import BRP.data.GradeMaster;
public class GradeManager extends GradeMaster 
{
    private static GradeManager _instance;
    private GradeManager()    
    {
        this.setTableName("grade");
        this.setTypeName("Grade");
    }
    public  static GradeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new GradeManager();
        }
        return _instance;
    }
}