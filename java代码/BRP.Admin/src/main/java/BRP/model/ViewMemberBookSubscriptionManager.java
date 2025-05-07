package BRP.model;
import BRP.data.ViewMemberBookSubscriptionMaster;
public class ViewMemberBookSubscriptionManager extends ViewMemberBookSubscriptionMaster 
{
    private static ViewMemberBookSubscriptionManager _instance;
    public  static ViewMemberBookSubscriptionManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewMemberBookSubscriptionManager();
        }
        return _instance;
    }
    private   ViewMemberBookSubscriptionManager()    
    {
        this.setTypeName("ViewMemberBookSubscription");
    }
}