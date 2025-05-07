package BRP.model;
import BRP.data.MemberBookSubscriptionMaster;
public class MemberBookSubscriptionManager extends MemberBookSubscriptionMaster 
{
    private static MemberBookSubscriptionManager _instance;
    private MemberBookSubscriptionManager()    
    {
        this.setTableName("member_book_subscription");
        this.setTypeName("MemberBookSubscription");
    }
    public  static MemberBookSubscriptionManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberBookSubscriptionManager();
        }
        return _instance;
    }
}