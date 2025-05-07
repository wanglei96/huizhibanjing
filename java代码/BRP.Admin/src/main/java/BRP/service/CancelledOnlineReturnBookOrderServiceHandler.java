package BRP.service;

import BRP.model.BookOrderItemManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 取消线上借书
 */
public class CancelledOnlineReturnBookOrderServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer bookOrderItemId = JsonHelper.getInt(jData, "bookOrderItemId");
        BookOrderItemManager.getInstance().cancelledOnlineReturnBookOrder(bookOrderItemId);
        this.writeSuccessResponse(response);
    }
}
