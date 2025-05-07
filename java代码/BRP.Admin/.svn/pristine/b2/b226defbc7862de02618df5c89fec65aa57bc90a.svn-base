package BRP.model;
import BRP.data.CompanySettingValueMaster;
public class CompanySettingValueManager extends CompanySettingValueMaster 
{
    private static CompanySettingValueManager _instance;
    private CompanySettingValueManager()    
    {
        this.setTableName("company_setting_value");
        this.setTypeName("CompanySettingValue");
    }
    public  static CompanySettingValueManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanySettingValueManager();
        }
        return _instance;
    }
}