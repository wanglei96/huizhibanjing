package BRP.model;
import BRP.data.ViewMessageMaster;
public class ViewMessageManager extends ViewMessageMaster 
{
    private static ViewMessageManager _instance;
    public  static ViewMessageManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewMessageManager();
        }
        return _instance;
    }
    private   ViewMessageManager()    
    {
        this.setTypeName("ViewMessage");
    }
}