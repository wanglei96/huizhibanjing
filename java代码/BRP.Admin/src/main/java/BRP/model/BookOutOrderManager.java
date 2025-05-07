package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.ClassHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookOutOrderManager extends BookOutOrderMaster {
    private static BookOutOrderManager _instance;

    private BookOutOrderManager() {
        this.setTableName("book_out_order");
        this.setTypeName("BookOutOrder");
    }

    public static BookOutOrderManager getInstance() {
        if (_instance == null) {
            _instance = new BookOutOrderManager();
        }
        return _instance;
    }

    /**
     * 添加找书出库单
     *
     * @param bookOrderId
     */
    public void addBookOutOrder(Integer bookOrderId) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            /*String sql = String.format("update book_order set book_order_borrow_status_code = '%s' where id = %s",
                    BookOrderBorrowStatusCodes.StockOuting, bookOrderId);
            MyBatisManager.getInstance().executeUpdate(session, sql);*/
            String condition = String.format("book_order_id = %s and book_order_type_code = '%s'",
                    bookOrderId, BookOrderTypeCodes.Borrow);
            List<BookOrderItem> lstBookOrderItem = BookOrderItemManager.getInstance().getList(session, condition);
            List<BookOutOrder> lstBookOutOrder = new ArrayList<BookOutOrder>();
            for (BookOrderItem bookOrderItem : lstBookOrderItem) {
                Integer count = this.getCount(session, "book_order_item_id=" + bookOrderItem.getId());
                if (count > 0) {
                    continue;
                }
                BookOutOrder newBookOutOrder = new BookOutOrder();
                newBookOutOrder.setBookOrderItemId(bookOrderItem.getId());
                newBookOutOrder.setBookOutOrderStatusCode(BookOutOrderStatusCodes.NotFound);
                lstBookOutOrder.add(newBookOutOrder);
            }
            this.addList(session, lstBookOutOrder);
            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 出库单出库
     *
     * @param bookOutOrderId
     * @param bookOrderId
     */
    public void bookOutOrderOutStock(Integer bookOutOrderId, Integer bookOrderId, Integer bookId, Integer bookOrderItemId) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            if (bookOrderItemId != null) {
                String updateBookOrderItemSql = String.format(
                        "update book_order_item set deliver_status_code = '%s' where id = %s",
                        "Delivering", bookOrderItemId);
                MyBatisManager.getInstance().executeUpdate(session, updateBookOrderItemSql);
            }
            //判断子表item类型为borrow的配送状态是否全部为Delivering
            String condition = String.format("book_order_id = %s and book_order_type_code = '%s'",
                    bookOrderId, BookOrderTypeCodes.Borrow);
            List<BookOrderItem> lstBookOrderItem = BookOrderItemManager.getInstance().getList(session, condition);
            boolean allDelivering = lstBookOrderItem.stream().allMatch(item -> "Delivering".equals(item.getDeliverStatusCode()));

            if (allDelivering) {
                String updateBookOrderSql = String.format(
                        "update book_order set book_order_borrow_status_code = '%s' where id = %s",
                        BookOrderBorrowStatusCodes.Delivering, bookOrderId);
                MyBatisManager.getInstance().executeUpdate(session, updateBookOrderSql);
            }


            String updateBookOutOrderSql = String.format(
                    "update book_out_order set book_out_order_status_code = '%s' where id = %s",
                    BookOutOrderStatusCodes.Found, bookOutOrderId);
            MyBatisManager.getInstance().executeUpdate(session, updateBookOutOrderSql);

            String updateBookSql = String.format(
                    "update book set borrow_status_code = '%s' where id = %s",
                    BorrowStatusCodes.WaitingDelivery, bookId);
            MyBatisManager.getInstance().executeUpdate(session, updateBookSql);

            //获取订单的所有出库单
            String conditionOut = String.format("book_order_id = %s", bookOrderId);
            List<ViewBookOutOrder> lstBookOutOrder = ViewBookOutOrderManager.getInstance().
                    getList(session, conditionOut);
            boolean allFound = lstBookOutOrder.stream().allMatch(item -> "Found".equals(item.getBookOutOrderStatusCode()));
            if (allFound) {
                String updateBookOrderSql = String.format(
                        "update book_order set book_order_borrow_status_code = '%s' where id = %s",
                        BookOrderBorrowStatusCodes.Delivering, bookOrderId);
                MyBatisManager.getInstance().executeUpdate(session, updateBookOrderSql);
            }

            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }
}