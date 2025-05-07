package BRP.model;
import BRP.data.MemberCardTypeMaster;
public class MemberCardTypeManager extends MemberCardTypeMaster 
{
    private static MemberCardTypeManager _instance;
    private MemberCardTypeManager()    
    {
        this.setTableName("member_card_type");
        this.setTypeName("MemberCardType");
    }
    public  static MemberCardTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCardTypeManager();
        }
        return _instance;
    }
}