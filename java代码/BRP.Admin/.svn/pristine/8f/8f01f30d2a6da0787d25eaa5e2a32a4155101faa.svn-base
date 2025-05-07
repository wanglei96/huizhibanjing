package BRP.service;

import BRP.model.BookSkuManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 审核门店SKU 添加到总部SKU
 */
public class ApprovedBookSkuServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer bookSkuId = JsonHelper.getInt(jData, "bookSkuId");
        Integer approverId = JsonHelper.getInt(jData, "approverId");
        String approveComment = JsonHelper.getString(jData, "approveComment");
        BookSkuManager.getInstance().ApprovedBookSku(bookSkuId,approverId,approveComment);
        this.writeSuccessResponse(response);
    }
}
