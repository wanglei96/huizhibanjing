package BRP.model;
import BRP.data.MemberCardServiceTimeGiftTypeMaster;
public class MemberCardServiceTimeGiftTypeManager extends MemberCardServiceTimeGiftTypeMaster 
{
    private static MemberCardServiceTimeGiftTypeManager _instance;
    private MemberCardServiceTimeGiftTypeManager()    
    {
        this.setTableName("member_card_service_time_gift_type");
        this.setTypeName("MemberCardServiceTimeGiftType");
    }
    public  static MemberCardServiceTimeGiftTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCardServiceTimeGiftTypeManager();
        }
        return _instance;
    }
}