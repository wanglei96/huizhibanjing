package BRP.model;
import BRP.data.MemberCardBatchMaster;
public class MemberCardBatchManager extends MemberCardBatchMaster 
{
    private static MemberCardBatchManager _instance;
    private MemberCardBatchManager()    
    {
        this.setTableName("member_card_batch");
        this.setTypeName("MemberCardBatch");
    }
    public  static MemberCardBatchManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberCardBatchManager();
        }
        return _instance;
    }
}