package BRP.model;
import BRP.data.ActivityRecommendMemberMaster;
public class ActivityRecommendMemberManager extends ActivityRecommendMemberMaster 
{
    private static ActivityRecommendMemberManager _instance;
    private ActivityRecommendMemberManager()    
    {
        this.setTableName("activity_recommend_member");
        this.setTypeName("ActivityRecommendMember");
    }
    public  static ActivityRecommendMemberManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ActivityRecommendMemberManager();
        }
        return _instance;
    }
}