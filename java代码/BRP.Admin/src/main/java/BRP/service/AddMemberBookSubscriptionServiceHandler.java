package BRP.service;

import BRP.data.MemberBookBorrow;
import BRP.data.MemberBookSubscription;
import BRP.model.MemberBookBorrowManager;
import BRP.model.MemberBookSubscriptionManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;

import java.util.List;

public class AddMemberBookSubscriptionServiceHandler extends AddServiceHandler {
    @Override
    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity) throws Exception {
        MemberBookSubscription newMemberBookSubscription = (MemberBookSubscription) newDataEntity;
        MySqlConditionBuilder cBuilder = new MySqlConditionBuilder();
        cBuilder.addEqualCondition("member_id", newMemberBookSubscription.getMemberId());
        cBuilder.addEqualCondition("book_sku_id", newMemberBookSubscription.getBookSkuId());
        List<MemberBookSubscription> memberBookBorrowList =
                MemberBookSubscriptionManager.getInstance().getList(newAddServiceContext.getSession(), cBuilder.toString());
        if (!memberBookBorrowList.isEmpty()) {
            throw new Exception("已添加过该图书借阅通知");
        }
        return newDataEntity;
    }
}
