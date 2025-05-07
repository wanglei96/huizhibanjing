package BRP.service;

import BRP.data.*;
import BRP.model.BookManager;
import BRP.model.BookOrderItemManager;
import BRP.model.BookOrderManager;
import BRP.model.ViewBookOrderItemManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ConfirmDeliveryServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);

        Integer bookOrderItemId = JsonHelper.getInt(jData, "bookOrderItemId");
        String bookOrderTypeCode = JsonHelper.getString(jData, "bookOrderTypeCode");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            BookOrderItem theBookOrderItem = BookOrderItemManager.getInstance().getEntity(sqlSession, bookOrderItemId);
            BookOrder theBookOrder = BookOrderManager.getInstance().getEntity(sqlSession, theBookOrderItem.getBookOrderId());
            Book theBook = BookManager.getInstance().getEntity(sqlSession, theBookOrderItem.getBookId());
            if (bookOrderTypeCode.equals(BookOrderTypeCodes.Borrow)) {
                theBookOrderItem.setDeliverStatusCode(DeliverStatusCodes.Delivered);
                BookOrderItemManager.getInstance().update(sqlSession, theBookOrderItem);
                String condition = String.format("book_order_id = %d and book_order_type_code = '%s'",
                        theBookOrderItem.getBookOrderId(), BookOrderTypeCodes.Borrow);
                List<BookOrderItem> lstBookOrderItem = BookOrderItemManager.getInstance().getList(sqlSession, condition);
                boolean allDelivered = lstBookOrderItem.stream()
                        .allMatch(item -> item.getDeliverStatusCode().equals(DeliverStatusCodes.Delivered));
                if (allDelivered) {
                    theBookOrder.setBookOrderBorrowStatusCode(BookOrderBorrowStatusCodes.Received);
                    theBookOrder.setDeliveredTime(DateHelper.getCurrentTimestamp());
                    BookOrderManager.getInstance().update(sqlSession, theBookOrder);
                }
                theBook.setMemberId(theBookOrder.getMemberId());
                theBook.setIsAvailable(false);
                theBook.setBorrowStatusCode(BorrowStatusCodes.Out);
            } else if (bookOrderTypeCode.equals(BookOrderTypeCodes.Return)) {
                theBookOrderItem.setDeliverStatusCode(DeliverStatusCodes.Storageing);
                BookOrderItemManager.getInstance().update(sqlSession, theBookOrderItem);
                String condition = String.format("book_order_id = %d and book_order_type_code = '%s'",
                        theBookOrderItem.getBookOrderId(), BookOrderTypeCodes.Return);
                List<BookOrderItem> lstBookOrderItem = BookOrderItemManager.getInstance().getList(sqlSession, condition);
                boolean allDelivered = lstBookOrderItem.stream()
                        .allMatch(item -> item.getDeliverStatusCode().equals(DeliverStatusCodes.Storageing));
                if (allDelivered) {
                    theBookOrder.setBookOrderReturnStatusCode(BookOrderReturnStatusCodes.Sterilizing);
                    theBookOrder.setBookOrderBorrowStatusCode(BookOrderBorrowStatusCodes.Received);
                    theBookOrder.setDeliveredTime(DateHelper.getCurrentTimestamp());
                    BookOrderManager.getInstance().update(sqlSession, theBookOrder);
                }
                String returnCondition = String.format(
                        "book_id = %d and member_id = %d and is_returned is not true and book_order_type_code='%s'",
                        theBookOrderItem.getBookId(), theBookOrder.getMemberId(), BookOrderTypeCodes.Borrow);
                List<ViewBookOrderItem> borrowBookOrderItemList = ViewBookOrderItemManager.
                        getInstance().getList(sqlSession, returnCondition, "create_time desc");
                if (borrowBookOrderItemList.size() > 0) {
                    String strBookReturnTime = DateHelper.getDateTimeString();
                    String sql = String.format("update book_order_item set is_returned = 1,  " +
                            "book_return_time = '%s' \n" +
                            "where id = %d", strBookReturnTime, borrowBookOrderItemList.get(0).getId());
                    MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
                }
                theBook.setBorrowStatusCode(BorrowStatusCodes.InCloud);
            }
            BookManager.getInstance().update(sqlSession, theBook);
            writeSuccessResponse(response);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }
}
