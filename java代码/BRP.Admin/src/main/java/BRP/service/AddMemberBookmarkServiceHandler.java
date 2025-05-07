package BRP.service;

import BRP.data.MemberBookCollect;
import BRP.data.MemberBookCollectMaster;
import BRP.data.MemberBookmark;
import BRP.model.MemberBookCollectManager;
import BRP.model.MemberBookmarkManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;

import java.util.List;

public class AddMemberBookmarkServiceHandler extends AddServiceHandler {
    @Override
    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity) throws Exception {
        MemberBookmark newMemberBookmark = (MemberBookmark) newDataEntity;
        MySqlConditionBuilder cBuilder = new MySqlConditionBuilder();
        cBuilder.addEqualCondition("member_id", newMemberBookmark.getMemberId());
        cBuilder.addEqualCondition("book_sku_id", newMemberBookmark.getBookSkuId());
        List<MemberBookmark> memberBookCollectList =
                MemberBookmarkManager.getInstance().getList(newAddServiceContext.getSession(), cBuilder.toString());
        if (!memberBookCollectList.isEmpty()) {
            throw new Exception("已收藏过该图书");
        }
        return newDataEntity;
    }
}
