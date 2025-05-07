package BRP.service;

import BRP.data.ReturnBookOrderInfo;
import BRP.model.BookOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 添加线上还书单
 */
public class AddOnLineReturnBookOrderServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer memberId = JsonHelper.getInt(jData, "memberId");
        Integer bookId = JsonHelper.getInt(jData, "bookId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BookOrderManager.getInstance().addOnLineReturnBookOrder(companyId, memberId, bookId);
        this.writeSuccessResponse(response);
    }
}
