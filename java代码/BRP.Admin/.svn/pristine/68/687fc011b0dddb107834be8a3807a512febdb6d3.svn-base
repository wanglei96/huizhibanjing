package BRP.service;

import BRP.data.BookOrder;
import BRP.data.BookOrderItem;
import BRP.data.BookOutOrder;
import BRP.model.BookOrderItemManager;
import BRP.model.BookOrderManager;
import BRP.model.BookOutOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配送员小程序 找到点我
 */
public class DeliverBookOrderFindMeServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);

        Integer id = JsonHelper.getInt(jData, "id");

        BookOutOrder bookOutOrder = BookOutOrderManager.getInstance().getEntity(id);
        bookOutOrder.setBookOutOrderStatusCode("Found");
        BookOutOrderManager.getInstance().update(bookOutOrder);


        BookOrderItem bookOrderItem = BookOrderItemManager.getInstance().getEntity(bookOutOrder.getBookOrderItemId());

        // 修改配送单书本状态编码为 等待配送
        bookOrderItem.setDeliverStatusCode("Delivering");
        BookOrderItemManager.getInstance().update(bookOrderItem);

        Integer totalCount = BookOrderItemManager.getInstance().getCount("book_order_id = " + bookOrderItem.getBookOrderId());
        Integer DeliveringCount = BookOrderItemManager.getInstance().getCount("book_order_id = " + bookOrderItem.getBookOrderId() + " and deliver_status_code = 'Delivering'");
        if(totalCount == DeliveringCount){
            BookOrder bookOrder = BookOrderManager.getInstance().getEntity(bookOrderItem.getBookOrderId());
            bookOrder.setBookOrderBorrowStatusCode("Delivering");
            BookOrderManager.getInstance().update(bookOrder);
        }
        this.writeSuccessResponse(response, "该书籍已修改为待配送状态");
    }
}
