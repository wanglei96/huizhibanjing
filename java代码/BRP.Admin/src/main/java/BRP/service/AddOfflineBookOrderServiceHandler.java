package BRP.service;

import BRP.data.ReturnBookOrderInfo;
import BRP.model.BookOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddOfflineBookOrderServiceHandler extends ServiceHandler {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        String borrowBookSn = JsonHelper.getString(jData, "borrowBookSn");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Integer memberId = JsonHelper.getInt(jData, "memberId");
        BookOrderManager.getInstance().addOfflineBookOrder(companyId, borrowBookSn,memberId);
        this.writeSuccessResponse(response);
    }
}
