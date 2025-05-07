package BRP.model;
import BRP.data.MemberCardServiceTimeTypeMaster;
public class MemberCardServiceTimeTypeManager extends MemberCardServiceTimeTypeMaster 
{
    private static MemberCardServiceTimeTypeManager _instance;
    private MemberCardServiceTimeTypeManager()    
    {
        this.setTableName("member_card_service_time_type");
        this.setTypeName("MemberCardServiceTimeType");
    }
    public  static MemberCardServiceTimeTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCardServiceTimeTypeManager();
        }
        return _instance;
    }
}