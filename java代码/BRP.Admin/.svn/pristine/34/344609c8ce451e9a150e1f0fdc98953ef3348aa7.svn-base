package BRP.service;

import BRP.model.BookManager;
import BRP.model.SettlementOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 结算零售单
 */
public class SettlementOrderSettlingServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer settlementOrderId = JsonHelper.getInt(jData, "id");
        String strRetailOrderIds = JsonHelper.getString(jData, "retailOrderIds");
        SettlementOrderManager.getInstance().settlementOrderSettling(settlementOrderId, strRetailOrderIds);
        this.writeSuccessResponse(response);
    }
}
