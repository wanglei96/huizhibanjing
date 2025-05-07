package BRP.service;

import BRP.model.BookOrderItemManager;
import BRP.model.BookOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 取消线上借书
 */
public class CancelledOnlineBorrowBookOrderServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer bookOrderItemId = JsonHelper.getInt(jData, "bookOrderItemId");
        BookOrderItemManager.getInstance().cancelledOnlineBorrowBookOrder(bookOrderItemId);
        this.writeSuccessResponse(response);
    }
}
