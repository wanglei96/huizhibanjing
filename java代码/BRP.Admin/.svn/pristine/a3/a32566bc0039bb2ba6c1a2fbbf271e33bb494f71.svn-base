package BRP.model;
import BRP.data.MemberBookmark;
import BRP.data.MemberBookmarkMaster;
import strosoft.app.common.MySqlConditionBuilder;

import java.util.List;

public class MemberBookmarkManager extends MemberBookmarkMaster
{
    private static MemberBookmarkManager _instance;
    private MemberBookmarkManager()    
    {
        this.setTableName("member_bookmark");
        this.setTypeName("MemberBookmark");
    }
    public  static MemberBookmarkManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MemberBookmarkManager();
        }
        return _instance;
    }

    /**
     * 根据会员ID和图书SKUId取得实体
     * @param memberId
     * @param bookSkuId
     * @return
     * @throws Exception
     */
    public MemberBookmark getEntityByMemberIdAndBookSkuId(int memberId, int bookSkuId) throws Exception {
        MySqlConditionBuilder mscb = new MySqlConditionBuilder();
        mscb.addEqualCondition("book_sku_id", bookSkuId);
        mscb.addEqualCondition("member_id", memberId);
        String condition = mscb.toString();
        List<MemberBookmark> lstMemberBookmark = this.getList(condition);
        if(lstMemberBookmark.size() == 0){
            return null;
        }
        return lstMemberBookmark.get(0);
    }
}