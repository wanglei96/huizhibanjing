package BRP.model;
import BRP.data.MemberCommunicaLogMaster;
public class MemberCommunicaLogManager extends MemberCommunicaLogMaster 
{
    private static MemberCommunicaLogManager _instance;
    private MemberCommunicaLogManager()    
    {
        this.setTableName("member_communica_log");
        this.setTypeName("MemberCommunicaLog");
    }
    public  static MemberCommunicaLogManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCommunicaLogManager();
        }
        return _instance;
    }
}