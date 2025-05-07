package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.DateHelper;

import java.util.List;

public class BookOrderItemManager extends BookOrderItemMaster {
    private static BookOrderItemManager _instance;

    private BookOrderItemManager() {
        this.setTableName("book_order_item");
        this.setTypeName("BookOrderItem");
    }

    public static BookOrderItemManager getInstance() {
        if (_instance == null) {
            _instance = new BookOrderItemManager();
        }
        return _instance;
    }

    /**
     * 更改用户所借书籍
     *
     * @param memberCardNo
     * @param oldBookSn
     * @param newBookSn
     */
    public void changeMemberBorrowBook(Integer companyId, String memberCardNo, String oldBookSn, String newBookSn) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            MemberCard theMemberCard = MemberCardManager.getInstance().getEntityByMemberCardNo(session, companyId, memberCardNo);
            if (theMemberCard == null) {
                throw new Exception("请输入正确卡号！会员卡号不存在");
            }
            Book theOldBook = BookManager.getInstance().getEntityBySn(session, oldBookSn, companyId);
            if (theOldBook == null) {
                throw new Exception("请输入正确书号！当前书号不存在");
            }
            Book theNewBook = BookManager.getInstance().getEntityBySn(session, newBookSn, companyId);
            if (theNewBook == null) {
                throw new Exception("请输入正确书号！替换的书号不存在");
            }
            if (theNewBook.getIsAvailable() == null || !theNewBook.getIsAvailable()) {
                throw new Exception("要替换的新书，当前状态不可借！");
            }
            if (theNewBook.getBookStatusCode() == null ||
                    theNewBook.getBookStatusCode().equals(BookStatusCodes.Off)) {
                throw new Exception("要替换的新书，未上架！");
            }
            //判断是否加入还书单
            Boolean isAReturned = this.checkReturned(session, theOldBook.getId(), theMemberCard.getMemberId());
            if (isAReturned) {
                throw new Exception("书号《" + oldBookSn + "》已加入还书单，暂不能换书");
            }

            String condition = String.format("member_id = %d and book_id = %d and is_offline is not true\n" +
                            " and deliver_status_code in ('%s','%s','%s') and is_returned is not true\n" +
                            " and book_order_type_code='%s'",
                    theMemberCard.getMemberId(), theOldBook.getId(),
                    DeliverStatusCodes.Delivering, DeliverStatusCodes.Outing, DeliverStatusCodes.Delivered,
                    BookOrderTypeCodes.Borrow);
            List<ViewBookOrderItem> lstViewBookOrderItem = ViewBookOrderItemManager.
                    getInstance().getList(session, condition, "create_time desc");
            if (lstViewBookOrderItem == null || lstViewBookOrderItem.size() == 0) {
                throw new Exception("当前会员没有借阅该书，请核对所借书号");
            }
            String sql = String.format("update book_order_item set book_id = %d where id = %d",
                    theNewBook.getId(), lstViewBookOrderItem.get(0).getId());
            MyBatisManager.getInstance().executeUpdate(session, sql);
            theNewBook.setBorrowStatusCode(theOldBook.getBorrowStatusCode());
            if (lstViewBookOrderItem.get(0).getDeliverStatusCode().equals(DeliverStatusCodes.Delivered)) {
                theNewBook.setMemberId(theMemberCard.getMemberId());
            }
            theNewBook.setIsAvailable(false);

            theOldBook.setBorrowStatusCode(BorrowStatusCodes.In);
            theOldBook.setMemberId(null);
            theOldBook.setIsAvailable(true);
            BookManager.getInstance().update(session, theNewBook);
            BookManager.getInstance().update(session, theOldBook);
            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 用户误点还书恢复
     *
     * @param companyId
     * @param bookSn
     * @throws Exception
     */
    public void lateReturnBookRecovery(Integer companyId, String bookSn, String memberCardNo) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            Book theBook = BookManager.getInstance().getEntityBySn(session, bookSn, companyId);
            if (theBook == null) {
                throw new Exception("请输入正确书号！需要恢复的书号不存在");
            }
            ViewMemberCard theMemberCard = ViewMemberCardManager.getInstance().getEntityByMemberCardNo(session, companyId, memberCardNo);
            if (theMemberCard == null) {
                throw new Exception("请输入正确卡号！会员卡号不存在");
            }
            if (theMemberCard.getDisabled() != null && theMemberCard.getDisabled()) {
                throw new Exception("请输入正确卡号！会员卡已失效");
            }
            //获取会员卡已借阅数量
            Integer borrowedQuantity = ViewBookOrderItemManager.getInstance().getBorrowedQuantity(session, theMemberCard.getMemberId());
            //获取会员卡总借阅数量
            Integer allQuantity = theMemberCard.getMaxBorrowableQuantity().intValue() + theMemberCard.getExtendQuantity().intValue();

            if (borrowedQuantity >= allQuantity) {
                throw new Exception("会员已借书数量超过上限！不能取消还书");
            }
            //获取误还订单
            String condition = String.format("member_id = %d and book_id = %d and is_offline is not true\n" +
                            " and deliver_status_code ='%s'\n" +
                            " and book_order_type_code='%s'",
                    theMemberCard.getMemberId(), theBook.getId(),
                    DeliverStatusCodes.Delivering, BookOrderTypeCodes.Return);
            List<ViewBookOrderItem> lstViewBookOrderItem = ViewBookOrderItemManager.
                    getInstance().getList(session, condition, "create_time desc");
            if (lstViewBookOrderItem == null || lstViewBookOrderItem.size() == 0) {
                throw new Exception("当前会员没有查询到误还订单，请核对书号");
            }
            ViewBookOrderItem theBookOrderItem = lstViewBookOrderItem.get(0);
            //获取订单item数量
            Integer itemCount = this.getCount(session, "book_order_id=" + theBookOrderItem.getBookOrderId());
            if (itemCount > 1) {
                this.delete(session, theBookOrderItem.getId());
            } else {
                this.delete(session, theBookOrderItem.getId());
                BookOrderManager.getInstance().delete(session, theBookOrderItem.getBookOrderId());
            }
            theBook.setBorrowStatusCode(BorrowStatusCodes.Out);
            theBook.setUpdateTime(DateHelper.getCurrentTimestamp());
            BookManager.getInstance().update(session, theBook);
            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 取消所有已借书籍
     *
     * @param companyId
     * @param memberCardNo
     * @param bookSn
     */
    public void cancelledMemberBorrowBook(Integer companyId, String memberCardNo, String bookSn) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            Book theBook = BookManager.getInstance().getEntityBySn(session, bookSn, companyId);
            if (theBook == null) {
                throw new Exception("请输入正确书号！需要恢复的书号不存在");
            }
            MemberCard theMemberCard = MemberCardManager.getInstance().getEntityByMemberCardNo(session, companyId, memberCardNo);
            if (theMemberCard == null) {
                throw new Exception("请输入正确卡号！会员卡号不存在");
            }
            //判断是否加入还书单
            Boolean isAReturned = this.checkReturned(session, theBook.getId(), theMemberCard.getMemberId());
            if (isAReturned) {
                throw new Exception("书号《" + bookSn + "》已加入还书单，暂不能取消借书");
            }
            theBook.setMemberId(null);
            theBook.setIsAvailable(true);
            theBook.setBorrowStatusCode(BorrowStatusCodes.In);
            BookManager.getInstance().update(session, theBook);
            //获取会员要取消的借书单
            String condition = String.format("member_id = %d and book_id = %d and is_offline is not true\n" +
                            " and deliver_status_code in ('%s','%s')\n" +
                            " and book_order_type_code='%s'",
                    theMemberCard.getMemberId(), theBook.getId(),
                    DeliverStatusCodes.Delivering, DeliverStatusCodes.Outing, BookOrderTypeCodes.Borrow);
            List<ViewBookOrderItem> lstViewBookOrderItem = ViewBookOrderItemManager.
                    getInstance().getList(session, condition, "create_time desc");
            if (lstViewBookOrderItem == null || lstViewBookOrderItem.size() == 0) {
                throw new Exception("当前会员没有查询到借阅订单，请核对书号");
            }
            ViewBookOrderItem theBookOrderItem = lstViewBookOrderItem.get(0);

            //获取订单item数量
            Integer itemCount = this.getCount(session, "book_order_id=" + theBookOrderItem.getBookOrderId());
            if (itemCount > 1) {
                this.delete(session, theBookOrderItem.getId());
                if (theBookOrderItem.getBookOrderBorrowStatusCode().equals(BookOrderBorrowStatusCodes.Submitting) ||
                        theBookOrderItem.getBookOrderBorrowStatusCode().equals(BookOrderBorrowStatusCodes.StockOuting)) {
                    //判断订单的找书单是否全部找到
                    Boolean isAllFound = this.checkAllFound(session, theBookOrderItem.getBookOrderId());
                    if (isAllFound) {
                        //修改订单状态为带配送
                        String updateSql = String.format("update book_order set book_order_borrow_status_code='%s' where id=%d", BookOrderBorrowStatusCodes.Delivering, theBookOrderItem.getBookOrderId());
                        MyBatisManager.getInstance().executeUpdate(session, updateSql);
                    }
                }

            } else {
                this.delete(session, theBookOrderItem.getId());
                BookOrderManager.getInstance().delete(session, theBookOrderItem.getBookOrderId());
            }
            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 判断订单下的找书单是否全部找到
     *
     * @param session
     * @param bookOrderId
     * @return
     */
    private Boolean checkAllFound(SqlSession session, Integer bookOrderId) {
        String condition = String.format("book_order_id = %d and book_out_order_status_code='NotFound'", bookOrderId);
        Integer count = ViewBookOutOrderManager.getInstance().getCount(session, condition);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     * 两个会员换书
     *
     * @param companyId
     * @param memberACardNo
     * @param memberABookSn
     * @param memberBCardNo
     * @param memberBBookSn
     */
    public void swopMemberBorrowBook(Integer companyId, String memberACardNo, String memberABookSn, String memberBCardNo, String memberBBookSn) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            Book theMemberABook = BookManager.getInstance().getEntityBySn(session, memberABookSn, companyId);
            if (theMemberABook == null) {
                throw new Exception("请输入正确书号！会员A的书号不存在");
            }
            String bookBorrowStatusCodeA = theMemberABook.getBorrowStatusCode();
            Integer memberAId = theMemberABook.getMemberId();
            Book theMemberBBook = BookManager.getInstance().getEntityBySn(session, memberBBookSn, companyId);
            if (theMemberBBook == null) {
                throw new Exception("请输入正确书号！会员B的书号不存在");
            }
            String bookBorrowStatusCodeB = theMemberBBook.getBorrowStatusCode();
            Integer memberBId = theMemberBBook.getMemberId();
            MemberCard theMemberACard = MemberCardManager.getInstance().getEntityByMemberCardNo(session, companyId, memberACardNo);
            if (theMemberACard == null) {
                throw new Exception("请输入正确卡号！会员A卡号不存在");
            }
            MemberCard theMemberBCard = MemberCardManager.getInstance().getEntityByMemberCardNo(session, companyId, memberBCardNo);
            if (theMemberACard == null) {
                throw new Exception("请输入正确卡号！会员A卡号不存在");
            }
            //判断是否加入还书单
            Boolean isAReturned = this.checkReturned(session, theMemberABook.getId(), theMemberBCard.getMemberId());
            if (isAReturned) {
                throw new Exception("会员A借的书已加入还书单，暂不能换书");
            }
            Boolean isBReturned = this.checkReturned(session, theMemberBBook.getId(), theMemberBCard.getMemberId());
            if (isBReturned) {
                throw new Exception("会员B借的书已加入还书单，暂不能换书");
            }
            //查询A的借书单
            String conditionA = String.format("member_id = %d and book_id = %d and is_offline is not true\n" +
                            " and deliver_status_code in ('%s','%s','%s') and is_returned is not true\n" +
                            " and book_order_type_code='%s'",
                    theMemberACard.getMemberId(), theMemberABook.getId(),
                    DeliverStatusCodes.Delivering, DeliverStatusCodes.Outing, DeliverStatusCodes.Delivered,
                    BookOrderTypeCodes.Borrow);
            List<ViewBookOrderItem> lstViewBookOrderItem = ViewBookOrderItemManager.
                    getInstance().getList(session, conditionA, "create_time desc");
            if (lstViewBookOrderItem == null || lstViewBookOrderItem.size() == 0) {
                throw new Exception("会员A没有查询到借阅订单，请核对书号");
            }
            //修改A的借书单
            String sql = String.format("update book_order_item set book_id = %d where id = %d",
                    theMemberBBook.getId(), lstViewBookOrderItem.get(0).getId());
            MyBatisManager.getInstance().executeUpdate(session, sql);

            //查询B的借书单
            String conditionB = String.format("member_id = %d and book_id = %d and is_offline is not true\n" +
                            " and deliver_status_code in ('%s','%s','%s') and is_returned is not true\n" +
                            " and book_order_type_code='%s'",
                    theMemberBCard.getMemberId(), theMemberBBook.getId(),
                    DeliverStatusCodes.Delivering, DeliverStatusCodes.Outing, DeliverStatusCodes.Delivered,
                    BookOrderTypeCodes.Borrow);
            List<ViewBookOrderItem> lstViewBookOrderItemB = ViewBookOrderItemManager.
                    getInstance().getList(session, conditionB, "create_time desc");
            if (lstViewBookOrderItemB == null || lstViewBookOrderItemB.size() == 0) {
                throw new Exception("会员B没有查询到借阅订单，请核对书号");
            }
            //修改B的借书单
            String sqlB = String.format("update book_order_item set book_id = %d where id = %d",
                    theMemberABook.getId(), lstViewBookOrderItemB.get(0).getId());
            MyBatisManager.getInstance().executeUpdate(session, sqlB);
            //修改A的图书
            theMemberABook.setBorrowStatusCode(bookBorrowStatusCodeB);
            theMemberABook.setMemberId(memberBId);
            //修改B的图书
            theMemberBBook.setBorrowStatusCode(bookBorrowStatusCodeA);
            theMemberBBook.setMemberId(memberAId);
            BookManager.getInstance().update(session, theMemberBBook);
            BookManager.getInstance().update(session, theMemberABook);
            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 判断是否加入还书单
     *
     * @param session
     * @param bookId
     * @param memberId
     * @return
     */
    private Boolean checkReturned(SqlSession session, Integer bookId, Integer memberId) {
        String condition = String.format("member_id = %d and book_id = %d\n" +
                        " and deliver_status_code in('%s','%s') \n" +
                        " and book_order_type_code='%s'",
                memberId, bookId, DeliverStatusCodes.Delivering, DeliverStatusCodes.Storageing, BookOrderTypeCodes.Return);
        Integer count = ViewBookOrderItemManager.getInstance().getCount(session, condition);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 取消用户借书
     */
    public void cancelledOnlineBorrowBookOrder(Integer bookOrderItemId) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            BookOrderItem theBookOrderItem = this.getEntity(session, bookOrderItemId);
            BookOrder theBookOrder = BookOrderManager.getInstance().getEntity(session, theBookOrderItem.getBookOrderId());

            Book theBook = BookManager.getInstance().getEntity(session, theBookOrderItem.getBookId());
            theBook.setMemberId(null);
            theBook.setIsAvailable(true);
            theBook.setBorrowStatusCode(BorrowStatusCodes.In);
            BookManager.getInstance().update(session, theBook);

            //获取借阅的数量
            Integer borrowCount = this.getCount(session, "book_order_id=" + theBookOrderItem.getBookOrderId() + " and book_order_type_code = 'Borrow'");
            if (borrowCount == 1) {
                theBookOrder.setBookOrderBorrowStatusCode(null);
                BookOrderManager.getInstance().update(session, theBookOrder);
            }
            //获取订单item数量
            Integer itemCount = this.getCount(session, "book_order_id=" + theBookOrderItem.getBookOrderId());
            if (itemCount > 1) {
                this.delete(session, bookOrderItemId);
            } else {
                this.delete(session, bookOrderItemId);
                BookOrderManager.getInstance().delete(session, theBookOrderItem.getBookOrderId());
            }

            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 取消用户还书订单
     */
    public void cancelledOnlineReturnBookOrder(Integer bookOrderItemId) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            BookOrderItem theBookOrderItem = this.getEntity(session, bookOrderItemId);
            BookOrder theBookOrder = BookOrderManager.getInstance().getEntity(session, theBookOrderItem.getBookOrderId());
            //获取会员卡
            /*MemberCard theMemberCard = MemberCardManager.getInstance().getEntityByMemberId(session, theBookOrder.getMemberId());
            if (theMemberCard == null) {
                throw new Exception("会员卡不存在！");
            }
            if (theMemberCard.getBorrowableQuantity() <= 0) {
                throw new Exception("会员已借书数量超过上限！不能取消还书");
            }
             //修改会员卡可借数量
            theMemberCard.setBorrowableQuantity(theMemberCard.getBorrowableQuantity() - 1);
            MemberCardManager.getInstance().update(session, theMemberCard);
            */
            //获取会员卡
            ViewMemberCard theViewMemberCard = ViewMemberCardManager.getInstance().getEntityByMemberId(session, theBookOrder.getMemberId());
            //获取会员卡已借阅数量
            Integer borrowedQuantity = ViewBookOrderItemManager.getInstance().getBorrowedQuantity(session, theBookOrder.getMemberId());
            //获取会员卡总借阅数量
            Integer allQuantity = theViewMemberCard.getMaxBorrowableQuantity().intValue() + theViewMemberCard.getExtendQuantity().intValue();

            if (borrowedQuantity >= allQuantity) {
                throw new Exception("会员已借书数量超过上限！不能取消还书");
            }


            Book theBook = BookManager.getInstance().getEntity(session, theBookOrderItem.getBookId());
            theBook.setMemberId(theBookOrder.getMemberId());
            theBook.setBorrowStatusCode(BorrowStatusCodes.Out);
            BookManager.getInstance().update(session, theBook);

            //获取借阅的数量
            Integer borrowCount = this.getCount(session, "book_order_id=" + theBookOrderItem.getBookOrderId() + " and book_order_type_code = 'Return'");
            if (borrowCount == 1) {
                theBookOrder.setBookOrderReturnStatusCode(null);
                BookOrderManager.getInstance().update(session, theBookOrder);
            }

            //获取订单item数量
            Integer itemCount = this.getCount(session, "book_order_id=" + theBookOrderItem.getBookOrderId());
            if (itemCount > 1) {
                this.delete(session, bookOrderItemId);
            } else {
                this.delete(session, bookOrderItemId);
                BookOrderManager.getInstance().delete(session, theBookOrderItem.getBookOrderId());
            }

            //修改这本书的借书单明细
            String condition = String.format(
                    "book_id = %d and member_id = %d and is_returned is true and book_order_type_code='%s'",
                    theBookOrderItem.getBookId(), theBookOrder.getMemberId(), BookOrderTypeCodes.Borrow);
            List<ViewBookOrderItem> borrowBookOrderItemList = ViewBookOrderItemManager.
                    getInstance().getList(session, condition, "create_time desc");
            if (borrowBookOrderItemList.size() > 0) {
                String sql = String.format("update book_order_item set is_returned = 0,  " +
                        "book_return_time = null \n" +
                        "where id = %d", borrowBookOrderItemList.get(0).getId());
                MyBatisManager.getInstance().executeUpdate(session, sql);
            }

            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 一件帮助会员还书
     *
     * @param companyId
     * @param memberCardNo
     */
    public void oneClickMemberReturnBook(Integer companyId, String memberCardNo) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            MemberCard theMemberCard = MemberCardManager.getInstance().getEntityByMemberCardNo(session, companyId, memberCardNo);
            if (theMemberCard == null) {
                throw new Exception("请输入正确卡号！会员卡不存在");
            }
            //获取会员正在借阅中的书籍

            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }
}