package BRP.model;
import BRP.data.VeiwReadActivityMaster;
public class VeiwReadActivityManager extends VeiwReadActivityMaster 
{
    private static VeiwReadActivityManager _instance;
    public  static VeiwReadActivityManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new VeiwReadActivityManager();
        }
        return _instance;
    }
    private   VeiwReadActivityManager()    
    {
        this.setTypeName("VeiwReadActivity");
    }
}