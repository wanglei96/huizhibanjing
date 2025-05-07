package BRP.model;
import BRP.data.MemberAddressMaster;
public class MemberAddressManager extends MemberAddressMaster 
{
    private static MemberAddressManager _instance;
    private MemberAddressManager()    
    {
        this.setTableName("member_address");
        this.setTypeName("MemberAddress");
    }
    public  static MemberAddressManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberAddressManager();
        }
        return _instance;
    }
}