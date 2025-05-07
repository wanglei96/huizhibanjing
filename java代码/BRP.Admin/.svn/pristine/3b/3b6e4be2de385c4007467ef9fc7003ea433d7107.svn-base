package BRP.service;

import BRP.model.BookSkuManager;
import BRP.model.BookcaseManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 合并书柜
 */
public class CombineBookcaseServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer keepBookcaseId = JsonHelper.getInt(jData, "keepBookcaseId");
        List<Integer> deleteBookcaseIds =JsonHelper.getIntArray(jData, "deleteBookcaseIds");
        BookcaseManager.getInstance().combineBookcase(keepBookcaseId, deleteBookcaseIds);
        this.writeSuccessResponse(response);
    }
}
