package BRP.model;
import BRP.data.HomeRecommendMaster;
public class HomeRecommendManager extends HomeRecommendMaster 
{
    private static HomeRecommendManager _instance;
    private HomeRecommendManager()    
    {
        this.setTableName("home_recommend");
        this.setTypeName("HomeRecommend");
    }
    public  static HomeRecommendManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new HomeRecommendManager();
        }
        return _instance;
    }
}