package BRP.model;

import BRP.data.BookSkuBookTag;
import BRP.data.MemberBorrowQuantityLog;
import BRP.data.MemberBorrowQuantityLogMaster;
import BRP.data.ViewBookSkuAgeGroup;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberBorrowQuantityLogManager extends MemberBorrowQuantityLogMaster {
    private static MemberBorrowQuantityLogManager _instance;

    private MemberBorrowQuantityLogManager() {
        this.setTableName("member_borrow_quantity_log");
        this.setTypeName("MemberBorrowQuantityLog");
    }

    public static MemberBorrowQuantityLogManager getInstance() {
        if (_instance == null) {
            _instance = new MemberBorrowQuantityLogManager();
        }
        return _instance;
    }

    /**
     * 查询到期扩展数量
     */
    public void updateMemberBorrowableQuantity() throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            //获取到期添加扩展数量
            List<MemberBorrowQuantityLog> lstMemberBorrowQuantityLog = this.getListExpiryTime(session);
            if (lstMemberBorrowQuantityLog != null && lstMemberBorrowQuantityLog.size() > 0) {
                for (MemberBorrowQuantityLog memberBorrowQuantityLog : lstMemberBorrowQuantityLog) {
                    Integer memberId = memberBorrowQuantityLog.getMemberId();
                    Integer quantity = memberBorrowQuantityLog.getQuantity();
                    String sql = String.format("update member_card " +
                            "set max_borrowable_quantity=coalesce(max_borrowable_quantity, 0)-%s," +
                            " borrowable_quantity=coalesce(borrowable_quantity, 0)-%s " +
                            "where member_id =%d and disabled is not true", quantity, quantity, memberId);
                    MyBatisManager.getInstance().executeUpdate(session, sql);
                    memberBorrowQuantityLog.setIsReduced(true);
                    this.update(session, memberBorrowQuantityLog);
                }
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
     * 获取过期没有减的记录
     *
     * @return
     */
    private List<MemberBorrowQuantityLog> getListExpiryTime(SqlSession session) {
        String condition = "expiry_time < now() and is_reduced is not true";
        return this.getList(session, condition);
    }
}