package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewBookOrderItemManager extends ViewBookOrderItemMaster {
    private static ViewBookOrderItemManager _instance;

    public static ViewBookOrderItemManager getInstance() {
        if (_instance == null) {
            _instance = new ViewBookOrderItemManager();
        }
        return _instance;
    }

    private ViewBookOrderItemManager() {
        this.setTypeName("ViewBookOrderItem");
    }

    /**
     * 根据会员ID获取已借图书列表
     *
     * @param memberId
     * @return
     */
    public List<ViewBookOrderItem> getListByMemberId(Integer companyId, Integer memberId) throws Exception {
        String condition = String.format("book_order_type_code = '%s' " +
                        "and member_id=%s  and company_id=%s",
                BookOrderTypeCodes.Borrow, memberId, companyId);
        return this.getList(condition, "create_time desc");
    }

    public ViewBookOrderItem getEntityById(SqlSession session, int newBookOrderId) {
        return session.selectOne("selectViewBookOrderItemById", newBookOrderId);
    }

    /**
     * 根据图书编号和图书状态获取已借图书单
     *
     * @param session
     * @param bookSn
     * @return
     */
    public ViewBookOrderItem getListByBookSn(SqlSession session, String bookSn) {
        String condition = String.format("book_sn = '%s' and book_order_borrow_status_code = '%s'",
                bookSn, BookOrderBorrowStatusCodes.Received);
        List<ViewBookOrderItem> lstViewBookOrderItem = this.getList(session, condition);
        if (lstViewBookOrderItem != null && lstViewBookOrderItem.size() > 0) {
            return lstViewBookOrderItem.get(0);
        }
        return null;
    }

    /**
     * 获取会员当日图书借书详情
     *
     * @param session
     * @param companyId
     * @param memberId
     * @param bookId
     * @return
     */
    public ViewBookOrderItem getEntityByBookIdAndNowDate(
            SqlSession session, Integer companyId, Integer memberId, Integer bookId, String bookOrderTypeCode) {
        String condition = String.format(
                "member_id=%d and book_id=%d and company_id=%d and book_order_type_code='%s' and DATE(create_time) = CURDATE()",
                memberId, bookId, companyId, bookOrderTypeCode);
        List<ViewBookOrderItem> lstViewBookOrderItem = this.getList(session, condition);
        if (lstViewBookOrderItem != null && lstViewBookOrderItem.size() > 0) {
            return lstViewBookOrderItem.get(0);
        }
        return null;
    }

    public Integer getBorrowedQuantity(Integer memberId) throws Exception {
        String sql = String.format("SELECT \n" +
                "    SUM(CASE WHEN book_order_type_code = 'Borrow' THEN 1 ELSE 0 END) - \n" +
                "    SUM(CASE WHEN book_order_type_code = 'Return' THEN 1 ELSE 0 END) AS borrowed_quantity\n" +
                "FROM \n" +
                "    view_book_order_item \n" +
                "WHERE \n" +
                "    member_id = %d", memberId);
        Integer count = MyBatisManager.getInstance().executeInt(sql);
        return count;
    }

    public Integer getBorrowedQuantity(SqlSession sqlSession, Integer memberId) throws Exception {
        String sql = String.format("SELECT \n" +
                "    SUM(CASE WHEN book_order_type_code = 'Borrow' THEN 1 ELSE 0 END) - \n" +
                "    SUM(CASE WHEN book_order_type_code = 'Return' THEN 1 ELSE 0 END) AS borrowed_quantity\n" +
                "FROM \n" +
                "    view_book_order_item \n" +
                "WHERE \n" +
                "    member_id = %d", memberId);
        Integer count = MyBatisManager.getInstance().executeInt(sql);
        return count;
    }
}