package BRP.service;

import BRP.model.BookSkuManager;
import BRP.model.BookTagManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 合并标签
 */
public class CombineBookTagServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer keepTagId = JsonHelper.getInt(jData, "keepTagId");
        List<Integer> deleteTagIds =JsonHelper.getIntArray(jData, "deleteTagIds");
        BookTagManager.getInstance().combineBookTag(keepTagId, deleteTagIds);
        this.writeSuccessResponse(response);
    }
}
