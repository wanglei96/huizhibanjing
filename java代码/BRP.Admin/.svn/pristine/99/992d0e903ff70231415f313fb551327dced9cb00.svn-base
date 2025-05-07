package BRP.model;
import BRP.data.ActivityRecommendMaster;
public class ActivityRecommendManager extends ActivityRecommendMaster 
{
    private static ActivityRecommendManager _instance;
    private ActivityRecommendManager()    
    {
        this.setTableName("activity_recommend");
        this.setTypeName("ActivityRecommend");
    }
    public  static ActivityRecommendManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ActivityRecommendManager();
        }
        return _instance;
    }
}