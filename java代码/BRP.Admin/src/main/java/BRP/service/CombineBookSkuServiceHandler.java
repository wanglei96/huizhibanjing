package BRP.service;

import BRP.data.BookSku;
import BRP.model.BookOrderItemManager;
import BRP.model.BookSkuManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 合并SKU
 */
public class CombineBookSkuServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer keepSkuId = JsonHelper.getInt(jData, "keepSkuId");
        List<Integer> deleteSkuIds =JsonHelper.getIntArray(jData, "deleteSkuIds");
        BookSkuManager.getInstance().combineBookSku(keepSkuId, deleteSkuIds);
        this.writeSuccessResponse(response);
    }
}
