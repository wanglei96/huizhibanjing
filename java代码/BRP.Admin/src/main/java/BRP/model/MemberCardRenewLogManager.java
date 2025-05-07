package BRP.model;
import BRP.data.MemberCardRenewLogMaster;
public class MemberCardRenewLogManager extends MemberCardRenewLogMaster 
{
    private static MemberCardRenewLogManager _instance;
    private MemberCardRenewLogManager()    
    {
        this.setTableName("member_card_renew_log");
        this.setTypeName("MemberCardRenewLog");
    }
    public  static MemberCardRenewLogManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCardRenewLogManager();
        }
        return _instance;
    }
}