package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 添加线下借书单
 */
public class AddBorrowBookOrderServiceHandler extends AddServiceHandler {
    private SqlSession sqlSession;
    private String borrowBookSn;
    private Book theBook;
    private Integer memberId;

    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity)
            throws Exception {
        HttpServletRequest request = newAddServiceContext.getRequest();
        JSONObject jData = this.getRequestData(request);
        borrowBookSn = JsonHelper.getString(jData, "borrowBookSn");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        sqlSession = newAddServiceContext.getSession();
        BookOrder newBookOrder = (BookOrder) newDataEntity;
        memberId = newBookOrder.getMemberId();
        theBook = BookManager.getInstance().getEntityBySn(sqlSession, borrowBookSn, companyId);
        if (null == theBook) {
            throw new Exception("该图书编号未找到相关图书！");
        }
        if (theBook.getIsAvailable() == null || !theBook.getIsAvailable()) {
            throw new Exception("该图书暂时不可借！");
        }
        if (theBook.getBookStatusCode() == null || theBook.getBookStatusCode().equals(BookStatusCodes.Off)) {
            throw new Exception("该图书暂未上架！");
        }
        //获取会员
        //Member theMember = MemberManager.getInstance().getEntity(sqlSession, memberId);
        /*if (theMember.getEnabled() == null || !theMember.getEnabled()) {
            throw new Exception("会员已锁定，暂时无法借书");
        }*/
        //获取会员卡
        ViewMemberCard theViewMemberCard = ViewMemberCardManager.getInstance().getEntityByMemberId(sqlSession, memberId);
        if (theViewMemberCard == null) {
            throw new Exception("会员卡不存在！请先办理会员卡。");
        }
        //判断会员卡是否停卡
        if (theViewMemberCard.getIsStopCard() != null && theViewMemberCard.getIsStopCard()) {
            throw new Exception("您的会员卡已办理停卡服务！停卡中无法借书。");
        }
        if (theViewMemberCard.getMemberCardStatusCode() != null && theViewMemberCard.getMemberCardStatusCode().equals("Locked")) {
            throw new Exception("您的线上借书功能已被锁定！请把忘记放进书箱的书带到馆里解锁！");
        }
        if (theViewMemberCard.getMemberCardStatusCode() != null && theViewMemberCard.getMemberCardStatusCode().equals("Cancelled")) {
            throw new Exception("会员卡已注销,暂时无法借书");
        }
        if (theViewMemberCard.getExpireDate() != null && theViewMemberCard.getExpireDate().before(DateHelper.getCurrentTimestamp())) {
            throw new Exception("会员已到期，请联系管理员续费激活！");
        }

        //获取会员卡已借阅数量
        Integer borrowedQuantity = ViewBookOrderItemManager.getInstance().getBorrowedQuantity(sqlSession, memberId);
        //获取会员卡总借阅数量
        Integer allQuantity = theViewMemberCard.getMaxBorrowableQuantity().intValue() + theViewMemberCard.getExtendQuantity().intValue();

        if (borrowedQuantity >= allQuantity) {
            throw new Exception("您的书箱已满！最多可以同时借阅" + allQuantity + "本哦~");
        }

        /*if (theMemberCard.getBorrowableQuantity() <= 0) {
            throw new Exception("会员已借书数量超过上限！");
        }*/
        if (theViewMemberCard.getIsUnlimited() == null || !theViewMemberCard.getIsUnlimited()) {
            //获取会员卡已经借阅次数
            String condition = String.format("book_order_type_code='%s' and member_id=%d",
                    BookOrderTypeCodes.Borrow, memberId);
            Integer count = ViewBookOrderItemManager.getInstance().getCount(sqlSession, condition);
            if (theViewMemberCard.getMaxBorrowQuantity() == null || count >= theViewMemberCard.getMaxBorrowQuantity()) {
                throw new Exception("会员暂无借阅次数！");
            }
        }
        //修改会员卡可借数量
        /*theMemberCard.setBorrowableQuantity(theMemberCard.getBorrowableQuantity() - 1);
        MemberCardManager.getInstance().update(sqlSession, theMemberCard);*/

        newBookOrder.setBookOrderBorrowStatusCode(BookOrderBorrowStatusCodes.Received);
        newBookOrder.setBusinessDate(DateHelper.getCurrentTimestamp());
        newBookOrder.setIsOffline(true);
        return newBookOrder;
    }

    /**
     * 增加后处理函数
     *
     * @param newAddServiceContext
     */
    @Override
    protected void postProcess(ServiceContext newAddServiceContext) throws Exception {
        BookOrderItem newBookOrderItem = new BookOrderItem();
        newBookOrderItem.setBookOrderId(newAddServiceContext.getNewId());
        newBookOrderItem.setBookId(theBook.getId());
        newBookOrderItem.setBookOrderTypeCode(BookOrderTypeCodes.Borrow);
        Integer newBookOrderItemId = BookOrderItemManager.getInstance().add(sqlSession, newBookOrderItem);
        theBook.setIsAvailable(false);
        theBook.setBorrowStatusCode(BorrowStatusCodes.Out);
        theBook.setMemberId(memberId);
        BookManager.getInstance().update(sqlSession, theBook);
        //添加出库单
        BookOutOrder newBookOutOrder = new BookOutOrder();
        newBookOutOrder.setBookOrderItemId(newBookOrderItemId);
        newBookOutOrder.setBookOutOrderStatusCode(BookOutOrderStatusCodes.Found);
        BookOutOrderManager.getInstance().add(sqlSession, newBookOutOrder);
        BookOrder theBookOrder = BookOrderManager.getInstance().getEntity(sqlSession, newAddServiceContext.getNewId());
        theBookOrder.setDeliveredTime(DateHelper.getCurrentTimestamp());
        BookOrderManager.getInstance().update(sqlSession, theBookOrder);

        String subscriptionCondition = String.format("book_sku_id = %d and member_id = %d", theBook.getBookSkuId(), memberId);
        Integer subscriptionCount =
                MemberBookSubscriptionManager.getInstance().getCount(sqlSession, subscriptionCondition);
        if (subscriptionCount > 0){
            String deleteSql = String.format("delete from member_book_subscription where book_sku_id = %d and member_id = %d", theBook.getBookSkuId(), memberId);
            MyBatisManager.getInstance().executeDelete(sqlSession, deleteSql);
        }
    }
}
