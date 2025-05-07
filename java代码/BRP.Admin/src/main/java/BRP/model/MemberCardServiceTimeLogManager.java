package BRP.model;
import BRP.data.MemberCardServiceTimeLogMaster;
public class MemberCardServiceTimeLogManager extends MemberCardServiceTimeLogMaster 
{
    private static MemberCardServiceTimeLogManager _instance;
    private MemberCardServiceTimeLogManager()    
    {
        this.setTableName("member_card_service_time_log");
        this.setTypeName("MemberCardServiceTimeLog");
    }
    public  static MemberCardServiceTimeLogManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCardServiceTimeLogManager();
        }
        return _instance;
    }
}