package BRP.model;
import BRP.data.CompanyChannelMaster;
public class CompanyChannelManager extends CompanyChannelMaster 
{
    private static CompanyChannelManager _instance;
    private CompanyChannelManager()    
    {
        this.setTableName("company_channel");
        this.setTypeName("CompanyChannel");
    }
    public  static CompanyChannelManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanyChannelManager();
        }
        return _instance;
    }
}