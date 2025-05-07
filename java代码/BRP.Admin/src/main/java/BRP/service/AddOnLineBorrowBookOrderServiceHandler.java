package BRP.service;

import BRP.data.BookOrder;
import BRP.model.BookOrderItemManager;
import BRP.model.BookOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 添加线上借书订单
 */
public class AddOnLineBorrowBookOrderServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer memberId = JsonHelper.getInt(jData, "memberId");
        Integer bookSkuId = JsonHelper.getInt(jData, "bookSkuId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String deliverTime = BookOrderManager.getInstance().addOnLineBorrowBookOrder(companyId, memberId, bookSkuId);
        this.writeSuccessResponse(response, deliverTime);
    }
}
