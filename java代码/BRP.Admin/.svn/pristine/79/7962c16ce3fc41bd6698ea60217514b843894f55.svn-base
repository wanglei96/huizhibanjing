package BRP.service;

import BRP.model.BookOrderManager;
import BRP.model.MemberScoreManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CustomerProcessingBookOrderServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer bookOrderId = JsonHelper.getInt(jData, "id");
        Integer borrowCount = JsonHelper.getInt(jData, "borrowCount");
        Integer returnCount = JsonHelper.getInt(jData, "returnCount");
        //BookOrderManager.getInstance().customerProcessingBookOrder(bookOrderId, borrowCount, returnCount);
        this.writeSuccessResponse(response);
    }
}
