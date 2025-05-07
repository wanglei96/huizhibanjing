package BRP.service;

import BRP.model.BookOrderItemManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SwopMemberBorrowBookServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String memberACardNo = JsonHelper.getString(jData, "memberACardNo");
        String memberABookSn = JsonHelper.getString(jData, "memberABookSn");
        String memberBCardNo = JsonHelper.getString(jData, "memberBCardNo");
        String memberBBookSn = JsonHelper.getString(jData, "memberBBookSn");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BookOrderItemManager.getInstance().swopMemberBorrowBook(
                companyId, memberACardNo, memberABookSn, memberBCardNo, memberBBookSn);
        this.writeSuccessResponse(response);
    }
}
