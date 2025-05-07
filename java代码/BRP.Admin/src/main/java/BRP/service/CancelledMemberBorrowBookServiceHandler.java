package BRP.service;

import BRP.model.BookOrderItemManager;
import BRP.model.MemberCardManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelledMemberBorrowBookServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String memberCardNo = JsonHelper.getString(jData, "memberCardNo");
        String bookSn = JsonHelper.getString(jData, "bookSn");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BookOrderItemManager.getInstance().cancelledMemberBorrowBook(companyId, memberCardNo, bookSn);
        this.writeSuccessResponse(response);
    }
}
