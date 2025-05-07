package BRP.service;

import BRP.data.MemberCard;
import BRP.data.MemberCardDisableLogMaster;
import BRP.model.MemberCardDisableLogManager;
import BRP.model.MemberCardManager;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import BRP.data.MemberCardDisableLog;

public class AddMemberCardDisableLogServiceHandler extends AddServiceHandler {
    @Override
    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity) throws Exception {
        MemberCardDisableLog newMemberCardDisableLog = (MemberCardDisableLog) newDataEntity;
        Integer memberCardId = newMemberCardDisableLog.getMemberCardId();
        if (memberCardId != null) {
            MemberCard memberCard = MemberCardManager.getInstance().getEntity(newAddServiceContext.getSession(), memberCardId);
            if (memberCard.getDisabled()) {
                throw new Exception("该会员卡不可用");
            }
            Integer stoppableCardQuantity = memberCard.getStoppableCardQuantity() - 1;
            if (stoppableCardQuantity.compareTo(0) < 0) {
                throw new Exception("已无可停卡次数");
            } else {
                memberCard.setStoppableCardQuantity(stoppableCardQuantity);
                MemberCardManager.getInstance().update(newAddServiceContext.getSession(), memberCard);
            }
        }
        return newMemberCardDisableLog;
    }
}

