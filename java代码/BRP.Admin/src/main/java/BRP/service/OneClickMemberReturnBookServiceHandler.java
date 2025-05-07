package BRP.service;

import BRP.data.BookOrderReturnStatusCodes;
import BRP.data.Member;
import BRP.model.BookOrderItemManager;
import BRP.model.MemberManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OneClickMemberReturnBookServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        String memberCardNo = JsonHelper.getString(jData, "memberCardNo");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BookOrderItemManager.getInstance().oneClickMemberReturnBook(companyId, memberCardNo);
        this.writeSuccessResponse(response);
    }
}
