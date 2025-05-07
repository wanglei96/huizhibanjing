package BRP.service;

import BRP.model.BookOrderItemManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LateReturnBookRecoveryServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String bookSn = JsonHelper.getString(jData, "bookSn");
        String memberCardNo = JsonHelper.getString(jData, "memberCardNo");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BookOrderItemManager.getInstance().lateReturnBookRecovery(companyId, bookSn,memberCardNo);
        this.writeSuccessResponse(response);
    }
}
