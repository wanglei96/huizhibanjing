package BRP.service;

import BRP.model.BookOrderItemManager;
import BRP.model.MemberCardManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddMemberBorrowTimeServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String newMemberCardNo = JsonHelper.getString(jData, "newMemberCardNo");
        String oldMemberCardNo = JsonHelper.getString(jData, "oldMemberCardNo");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        MemberCardManager.getInstance().addMemberBorrowTime(companyId, newMemberCardNo, oldMemberCardNo);
        this.writeSuccessResponse(response);
    }
}
