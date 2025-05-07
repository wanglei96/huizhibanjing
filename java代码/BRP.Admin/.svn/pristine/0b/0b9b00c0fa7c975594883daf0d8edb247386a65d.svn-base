package BRP.service;

import BRP.model.SettlementOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 结算零售单
 */
public class BatchSettlementOrderSettlingServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        List<Integer> settlementOrderids = JsonHelper.getIntArray(jData, "ids");
        SettlementOrderManager.getInstance().batchSettlementOrderSettling(settlementOrderids);
        this.writeSuccessResponse(response);
    }
}
