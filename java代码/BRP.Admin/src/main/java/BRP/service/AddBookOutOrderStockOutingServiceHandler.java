package BRP.service;

import BRP.data.BookOrderItem;
import BRP.model.BookOrderItemManager;
import BRP.model.BookOutOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddBookOutOrderStockOutingServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Integer bookOrderId = JsonHelper.getInt(jData, "bookOrderId");
        BookOutOrderManager.getInstance().addBookOutOrder(bookOrderId);
        this.writeSuccessResponse(response);
    }
}
