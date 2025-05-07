package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONObject;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddClubActivityServiceHandler extends AddServiceHandler {

    @Override
    protected void postProcess(ServiceContext newAddServiceContext) throws Exception {
        HttpServletRequest request = newAddServiceContext.getRequest();
        JSONObject jData = getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Integer quantity = JsonHelper.getInt(jData, "quantity");
        //添加子订单
        List<ClubActivityMember> clubActivityMemberList = JsonHelper.getJavaList(ClubActivityMember.class, jData, "clubActivityMemberList");

        for (ClubActivityMember clubActivityMember : clubActivityMemberList) {
            clubActivityMember.setClubActivityId(newAddServiceContext.newId);
            clubActivityMember.setJoinDate(DateHelper.getCurrentTimestamp());
            clubActivityMember.setSurplusQuantity(quantity);
        }
        ClubActivityMemberManager.getInstance().addList(newAddServiceContext.session, clubActivityMemberList);
    }
}
