package BRP.service;

import BRP.model.MemberScoreManager;
import BRP.model.RequiredBookManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BatchAddRequiredBookServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        List<Integer> bookSkuIds = JsonHelper.getIntArray(jData, "bookSkuIds");
        Integer requiredBookCategoryId = JsonHelper.getInt(jData, "requiredBookCategoryId");
        RequiredBookManager.getInstance().batchAddRequiredBook(bookSkuIds, requiredBookCategoryId);
        this.writeSuccessResponse(response);
    }
}
