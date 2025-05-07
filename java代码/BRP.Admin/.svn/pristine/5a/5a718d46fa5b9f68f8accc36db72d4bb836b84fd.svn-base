package BRP.service;

import BRP.data.Book;
import BRP.data.BookOrderItem;
import BRP.model.BookManager;
import BRP.model.BookOrderItemManager;
import BRP.model.BookOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 一键配送
 */
public class UpdateOneClickDeliverServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        // 配送单id
        Integer id = JsonHelper.getInt(jData, "id");
        // 获取要配送的会员id
        Integer memberId = BookOrderManager.getInstance().getEntity(id).getMemberId();
        List<BookOrderItem> bookOrderItemList = BookOrderItemManager.getInstance().getList("book_order_id = " + id);
        for(BookOrderItem bookOrderItem : bookOrderItemList){
            if(StringHelper.isNullOrEmpty(bookOrderItem.getDeliverStatusCode())){
                continue;
            }
            if(bookOrderItem.getDeliverStatusCode().equals("Delivering")){
                bookOrderItem.setDeliverStatusCode("Delivered");
                BookOrderItemManager.getInstance().update(bookOrderItem);
            }
            Book book = BookManager.getInstance().getEntity(bookOrderItem.getBookId());
            // 借的书给书籍绑定会员id，取的书给书籍取消会员id
            if(bookOrderItem.getBookOrderTypeCode().equals("Borrow")){
                book.setMemberId(memberId);
            } else if (bookOrderItem.getBookOrderTypeCode().equals("Return")) {
                book.setMemberId(null);
            }
            BookManager.getInstance().update(book);
        }
        this.writeSuccessResponse(response);
    }
}
