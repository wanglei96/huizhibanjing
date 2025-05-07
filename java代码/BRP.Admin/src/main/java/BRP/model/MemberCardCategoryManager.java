package BRP.model;
import BRP.data.MemberCardCategoryMaster;
public class MemberCardCategoryManager extends MemberCardCategoryMaster 
{
    private static MemberCardCategoryManager _instance;
    private MemberCardCategoryManager()    
    {
        this.setTableName("member_card_category");
        this.setTypeName("MemberCardCategory");
    }
    public  static MemberCardCategoryManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCardCategoryManager();
        }
        return _instance;
    }
}