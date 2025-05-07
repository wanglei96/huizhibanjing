package BRP.model;
import BRP.data.ActivityRecommendLogMaster;
public class ActivityRecommendLogManager extends ActivityRecommendLogMaster 
{
    private static ActivityRecommendLogManager _instance;
    private ActivityRecommendLogManager()    
    {
        this.setTableName("activity_recommend_log");
        this.setTypeName("ActivityRecommendLog");
    }
    public  static ActivityRecommendLogManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ActivityRecommendLogManager();
        }
        return _instance;
    }
}