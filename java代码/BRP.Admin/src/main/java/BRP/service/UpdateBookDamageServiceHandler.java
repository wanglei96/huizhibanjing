package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.UpdateServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpdateBookDamageServiceHandler extends UpdateServiceHandler {

    protected DataEntity processDataEntity(ServiceContext updateServiceContext, DataEntity newDataEntity)
            throws Exception {
        HttpServletRequest request = updateServiceContext.getRequest();
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BookDamage newBookDamage = (BookDamage) newDataEntity;
        Integer memberId = newBookDamage.getMemberId();
        newBookDamage.setDamageTime(DateHelper.getCurrentTimestamp());
        SqlSession session = updateServiceContext.getSession();
        Book theBook = BookManager.getInstance().getEntity(updateServiceContext.session, newBookDamage.getBookId());
        if (newBookDamage.getIsOff() == null || !newBookDamage.getIsOff()) {
            theBook.setBookStatusCode(BookStatusCodes.On);
            theBook.setIsAvailable(true);
        }
        theBook.setIsDamaged(true);

        if (newBookDamage.getIsCompensation() != null && newBookDamage.getIsCompensation()) {
            newBookDamage.setPaymentTime(DateHelper.getCurrentTimestamp());
            //添加pay_order账单
            PayOrder newPayOrder = new PayOrder();
            newPayOrder.setCompanyId(companyId);
            newPayOrder.setAmount(newBookDamage.getAmount());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            newPayOrder.setMemberId(newBookDamage.getMemberId());
            Date now = new Date();
            String payOrderNo = sdf.format(now);
            newPayOrder.setPayOrderNo(payOrderNo);
            newPayOrder.setBusinessDate(DateHelper.getCurrentTimestamp());
            newPayOrder.setPaymentItemCode("BookDamageCompensate");
            newPayOrder.setPayTime(DateHelper.getCurrentTimestamp());
            newPayOrder.setOperatorId(newBookDamage.getCompanyUserId());
            newPayOrder.setBookId(newBookDamage.getBookId());
            newPayOrder.setPaymentMethodCode(newBookDamage.getPaymentMethodCode());
            newPayOrder.setIsFinished(true);
            newPayOrder.setIsPaid(true);
            PayOrderManager.getInstance().add(updateServiceContext.session, newPayOrder);

            //判断类型
            if (newBookDamage.getBookDamageTypeCode().equals("MemberDamage") ||
                    newBookDamage.getBookDamageTypeCode().equals("MemberLost")) {
                if (memberId != null) {
                    //判断会员是否还书
                    String condition = String.format(
                            "book_id = %d and member_id = %d and is_returned is not true and book_order_type_code='%s'",
                            newBookDamage.getBookId(), memberId, BookOrderTypeCodes.Borrow);
                    List<ViewBookOrderItem> borrowBookOrderItemList = ViewBookOrderItemManager.
                            getInstance().getList(session, condition, "create_time desc");
                    if (borrowBookOrderItemList.size() > 0) {
                        String strBookReturnTime = DateHelper.getDateTimeString();
                        String sql = String.format("update book_order_item set is_returned = 1,  " +
                                "book_return_time = '%s',book_in_stocks_time='%s' \n" +
                                "where id = %d", strBookReturnTime, strBookReturnTime, borrowBookOrderItemList.get(0).getId());
                        MyBatisManager.getInstance().executeUpdate(session, sql);
                        //添加还书单
                        BookOrder newBookOrder = new BookOrder();
                        newBookOrder.setBookOrderNo(payOrderNo);
                        newBookOrder.setIsOffline(true);
                        newBookOrder.setBookOrderReturnStatusCode(BookOrderReturnStatusCodes.Finished);
                        newBookOrder.setMemberId(memberId);
                        newBookOrder.setBusinessDate(DateHelper.getCurrentTimestamp());
                        newBookOrder.setCompanyId(companyId);
                        Integer newBookOrderId = BookOrderManager.getInstance().add(session, newBookOrder);

                        //添加书籍订单项
                        BookOrderItem newBookOrderItem = new BookOrderItem();
                        newBookOrderItem.setBookOrderId(newBookOrderId);
                        newBookOrderItem.setBookId(newBookDamage.getBookId());
                        newBookOrderItem.setBookOrderTypeCode(BookOrderTypeCodes.Return);
                        newBookOrderItem.setBookReturnTime(DateHelper.getCurrentTimestamp());
                        newBookOrderItem.setDeliverStatusCode(DeliverStatusCodes.Storageed);
                        newBookOrderItem.setIsReturned(true);
                        int newBookOrderItemId = BookOrderItemManager.getInstance().add(session, newBookOrderItem);
                        //添加入库记录
                        this.addBookInOrder(session, newBookOrderItemId, theBook);
                    } else {
                        //获取这本图书的借阅item
                        String borrowedCondition = String.format(
                                "company_id= %d and  book_id= %d and member_id=%d " +
                                        " and book_order_type_code='%s' and is_returned is true",
                                companyId, theBook.getId(), memberId, BookOrderTypeCodes.Borrow);
                        List<ViewBookOrderItem> lstBorrowedViewBookOrderItem = ViewBookOrderItemManager.getInstance().
                                getList(session, borrowedCondition, "create_time desc");
                        if (lstBorrowedViewBookOrderItem.size() > 0) {
                            String strBookReturnTime = DateHelper.getDateTimeString();
                            String sql = String.format("update book_order_item set " +
                                    "book_in_stocks_time = '%s' \n" +
                                    "where id = %d", strBookReturnTime, lstBorrowedViewBookOrderItem.get(0).getId());
                            MyBatisManager.getInstance().executeUpdate(session, sql);
                        }

                        String conditionStorageing = String.format(
                                "book_id= %d and deliver_status_code='%s' and book_order_type_code='%s'",
                                theBook.getId(), DeliverStatusCodes.Storageing, BookOrderTypeCodes.Return);
                        List<BookOrderItem> lstBookOrderItem = BookOrderItemManager.getInstance().
                                getList(session, conditionStorageing, "create_time desc");
                        if (lstBookOrderItem.size()> 0) {
                            BookOrderItem theBookOrderItem = lstBookOrderItem.get(0);
                            //修改配送单详情
                            theBookOrderItem.setDeliverStatusCode(DeliverStatusCodes.Storageed);
                            theBookOrderItem.setIsReturned(true);
                            BookOrderItemManager.getInstance().update(session, theBookOrderItem);

                            //修改单据还书状态为完成
                            String bookOrderCondition = String.format("book_order_id = %d and book_order_type_code = '%s'",
                                    theBookOrderItem.getBookOrderId(), BookOrderTypeCodes.Return);
                            List<BookOrderItem> bookOrderItemList = BookOrderItemManager.getInstance().getList(session, bookOrderCondition);
                            boolean allDelivered = bookOrderItemList.stream()
                                    .allMatch(item -> item.getDeliverStatusCode().equals(DeliverStatusCodes.Storageed));
                            BookOrder theBookOrder = BookOrderManager.getInstance().getEntity(session, theBookOrderItem.getBookOrderId());
                            if (allDelivered) {
                                theBookOrder.setBookOrderReturnStatusCode(BookOrderReturnStatusCodes.Finished);
                                BookOrderManager.getInstance().update(session, theBookOrder);
                            }
                            //添加入库记录
                            this.addBookInOrder(session, lstBookOrderItem.get(0).getId(), theBook);
                        }
                    }
                    theBook.setMemberId(null);
                    theBook.setBorrowStatusCode(BorrowStatusCodes.In);
                }
            }
        }
        BookManager.getInstance().update(updateServiceContext.session, theBook);
        return newBookDamage;
    }

    private void addBookInOrder(SqlSession session, int newBookOrderItemId, Book book) {
        //添加入库单
        BookInOrder newBookInOrder = new BookInOrder();
        newBookInOrder.setBookOrderItemId(newBookOrderItemId);
        newBookInOrder.setBookInOrderStatusCode("WaitCloudStockIn");
        newBookInOrder.setIsEnabled(false);
        newBookInOrder.setBookcaseId(book.getBookcaseId());
        BookInOrderManager.getInstance().add(session, newBookInOrder);
    }
}
