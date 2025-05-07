package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ConfirmInStocksServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);

        Integer bookOrderItemId = JsonHelper.getInt(jData, "bookOrderItemId");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            //修改配送单详情
            BookOrderItem theBookOrderItem = BookOrderItemManager.getInstance().getEntity(sqlSession, bookOrderItemId);
            theBookOrderItem.setDeliverStatusCode(DeliverStatusCodes.Storageed);
            theBookOrderItem.setIsReturned(true);
            BookOrderItemManager.getInstance().update(sqlSession, theBookOrderItem);

            //修改图书信息
            Book theBook = BookManager.getInstance().getEntity(sqlSession, theBookOrderItem.getBookId());
            theBook.setBorrowStatusCode(BorrowStatusCodes.In);
            theBook.setIsAvailable(true);
            theBook.setMemberId(null);
            BookManager.getInstance().update(sqlSession, theBook);

            //修改单据还书状态为完成
            String condition = String.format("book_order_id = %d and book_order_type_code = '%s'",
                    theBookOrderItem.getBookOrderId(), BookOrderTypeCodes.Return);
            List<BookOrderItem> lstBookOrderItem = BookOrderItemManager.getInstance().getList(sqlSession, condition);
            boolean allDelivered = lstBookOrderItem.stream()
                    .allMatch(item -> item.getDeliverStatusCode().equals(DeliverStatusCodes.Storageed));
            BookOrder theBookOrder = BookOrderManager.getInstance().getEntity(sqlSession, theBookOrderItem.getBookOrderId());
            if (allDelivered) {
                theBookOrder.setBookOrderReturnStatusCode(BookOrderReturnStatusCodes.Finished);
                BookOrderManager.getInstance().update(sqlSession, theBookOrder);
            }

            //添加入库单
            BookInOrder newBookInOrder = new BookInOrder();
            newBookInOrder.setBookOrderItemId(bookOrderItemId);
            newBookInOrder.setBookInOrderStatusCode("WaitCloudStockIn");
            newBookInOrder.setIsEnabled(true);
            newBookInOrder.setBookcaseId(theBook.getBookcaseId());
            BookInOrderManager.getInstance().add(sqlSession, newBookInOrder);

            //获取这本图书的借阅item
            String borrowedCondition = String.format(
                    "book_id= %d and member_id=%d and book_order_type_code='%s' and is_returned is true",
                    theBook.getId(), theBookOrder.getMemberId(), BookOrderTypeCodes.Borrow);
            List<ViewBookOrderItem> lstBorrowedViewBookOrderItem = ViewBookOrderItemManager.getInstance().
                    getList(sqlSession, borrowedCondition, "create_time desc");
            if (lstBorrowedViewBookOrderItem.size() > 0) {
                String strInStocksTime = DateHelper.getDateTimeString();
                String sql = String.format("update book_order_item set " +
                        "book_in_stocks_time = '%s' \n" +
                        "where id = %d", strInStocksTime, lstBorrowedViewBookOrderItem.get(0).getId());
                MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
            }
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
