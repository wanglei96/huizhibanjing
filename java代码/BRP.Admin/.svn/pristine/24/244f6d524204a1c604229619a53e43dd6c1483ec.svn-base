package BRP.model;
import BRP.data.MemberCardDisableLogTypeMaster;
public class MemberCardDisableLogTypeManager extends MemberCardDisableLogTypeMaster 
{
    private static MemberCardDisableLogTypeManager _instance;
    private MemberCardDisableLogTypeManager()    
    {
        this.setTableName("member_card_disable_log_type");
        this.setTypeName("MemberCardDisableLogType");
    }
    public  static MemberCardDisableLogTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCardDisableLogTypeManager();
        }
        return _instance;
    }
}