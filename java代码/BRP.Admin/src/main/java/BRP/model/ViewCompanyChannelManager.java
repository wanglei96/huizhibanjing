package BRP.model;
import BRP.data.ViewCompanyChannelMaster;
public class ViewCompanyChannelManager extends ViewCompanyChannelMaster 
{
    private static ViewCompanyChannelManager _instance;
    public  static ViewCompanyChannelManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCompanyChannelManager();
        }
        return _instance;
    }
    private   ViewCompanyChannelManager()    
    {
        this.setTypeName("ViewCompanyChannel");
    }
}