package BRP.service;

import BRP.data.BookOrder;
import BRP.model.BookOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配送员小程序 异常情况
 */
public class DeliverErrorRemarkServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        // 获取配送单id
        Integer bookOrderId = JsonHelper.getInt(jData, "bookOrderId");
        // 获取异常情况
        String errorRemark = JsonHelper.getString(jData, "errorRemark");
        // 查询配送单data
        BookOrder bookOrder = BookOrderManager.getInstance().getEntity(bookOrderId);
        // 修改配送单状态编码为 已送达
        bookOrder.setBookOrderBorrowStatusCode("ErrorProcessing");
        bookOrder.setErrorRemark(errorRemark);
        BookOrderManager.getInstance().update(bookOrder);
        this.writeSuccessResponse(response, "异常情况上报成功");
    }
}
