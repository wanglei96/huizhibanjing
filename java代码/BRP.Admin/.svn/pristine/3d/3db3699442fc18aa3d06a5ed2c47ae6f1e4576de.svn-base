package BRP.model;
import BRP.data.ActivityRecommendErrorMaster;
public class ActivityRecommendErrorManager extends ActivityRecommendErrorMaster 
{
    private static ActivityRecommendErrorManager _instance;
    private ActivityRecommendErrorManager()    
    {
        this.setTableName("activity_recommend_error");
        this.setTypeName("ActivityRecommendError");
    }
    public  static ActivityRecommendErrorManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ActivityRecommendErrorManager();
        }
        return _instance;
    }
}