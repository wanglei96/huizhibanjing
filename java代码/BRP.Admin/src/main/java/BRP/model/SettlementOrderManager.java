package BRP.model;

import BRP.data.BookSku;
import BRP.data.BookSkuAgeGroup;
import BRP.data.SettlementOrderMaster;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.DateHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SettlementOrderManager extends SettlementOrderMaster {
    private static SettlementOrderManager _instance;

    private SettlementOrderManager() {
        this.setTableName("settlement_order");
        this.setTypeName("SettlementOrder");
    }

    public static SettlementOrderManager getInstance() {
        if (_instance == null) {
            _instance = new SettlementOrderManager();
        }
        return _instance;
    }

    public void settlementOrderSettling(Integer settlementOrderId, String strRetailOrderIds) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            //修改结算单状态
            String updateSettlementOrderSql = "update settlement_order set settlement_order_status_code = 'Settled' where id = " + settlementOrderId;
            MyBatisManager.getInstance().executeUpdate(sqlSession, updateSettlementOrderSql);
            //修改零售单状态
            String updateRetailOrderSql = "update retail_order set settled = 1 where id in (" + strRetailOrderIds + ")";
            MyBatisManager.getInstance().executeUpdate(sqlSession, updateRetailOrderSql);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }

    public void batchSettlementOrderSettling(List<Integer> settlementOrderids) throws Exception {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            String settlementOrderidsString = settlementOrderids.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            //修改结算单状态
            String updateSettlementOrderSql = "update settlement_order set settlement_order_status_code = 'Settled' where id in (" + settlementOrderidsString + ")";
            MyBatisManager.getInstance().executeUpdate(sqlSession, updateSettlementOrderSql);

            String strRetailOrderIdsSql = "select retail_order_ids from settlement_order where id in (" + settlementOrderidsString + ")";
            List<String> strRetailOrderIds = MyBatisManager.getInstance().getStringList(sqlSession, strRetailOrderIdsSql);
            String combinedString = strRetailOrderIds.stream()
                    .collect(Collectors.joining(","));
            //修改零售单状态
            String updateRetailOrderSql = "update retail_order set settled = 1 where id in (" + combinedString + ")";
            MyBatisManager.getInstance().executeUpdate(sqlSession, updateRetailOrderSql);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new Exception(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }
}