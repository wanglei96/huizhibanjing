package BRP.model;
import BRP.data.ViewCompanySettingMaster;
public class ViewCompanySettingManager extends ViewCompanySettingMaster 
{
    private static ViewCompanySettingManager _instance;
    public  static ViewCompanySettingManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCompanySettingManager();
        }
        return _instance;
    }
    private   ViewCompanySettingManager()    
    {
        this.setTypeName("ViewCompanySetting");
    }
}