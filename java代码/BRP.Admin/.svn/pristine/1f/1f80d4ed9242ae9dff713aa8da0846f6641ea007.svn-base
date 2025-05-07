package BRP.model;
import BRP.data.MemberCardStatusMaster;
public class MemberCardStatusManager extends MemberCardStatusMaster 
{
    private static MemberCardStatusManager _instance;
    private MemberCardStatusManager()    
    {
        this.setTableName("member_card_status");
        this.setTypeName("MemberCardStatus");
    }
    public  static MemberCardStatusManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCardStatusManager();
        }
        return _instance;
    }
}