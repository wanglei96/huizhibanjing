package BRP.model;
import BRP.data.MemberScoreTypeMaster;
public class MemberScoreTypeManager extends MemberScoreTypeMaster 
{
    private static MemberScoreTypeManager _instance;
    private MemberScoreTypeManager()    
    {
        this.setTableName("member_score_type");
        this.setTypeName("MemberScoreType");
    }
    public  static MemberScoreTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberScoreTypeManager();
        }
        return _instance;
    }
}