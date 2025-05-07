package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.ScheduledTask;
import strosoft.main.boot.JAppApplication;

import java.util.List;
import java.util.stream.Collectors;

public class FinishedSalesOrderScheduledTask extends ScheduledTask {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);

    /**
     * 处理定时任务
     */
    @Override
    protected void process() {
        try {
            this.updateSalesOrderFinished();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSalesOrderFinished() throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            String condition = "sales_order_status_code = 'Delivered' \n" +
                    "AND DATE(delivered_time) <= DATE_SUB(CURDATE(), INTERVAL 10 DAY)\n";
            List<SalesOrder> lstSalesOrder = SalesOrderManager.getInstance().getList(session, condition);
            if (lstSalesOrder.size() > 0) {
                String idString = lstSalesOrder.stream()
                        .map(SalesOrder::getId)
                        .map(String::valueOf)
                        .collect(Collectors.joining(","));
                String updateSalesSql = "UPDATE sales_order SET sales_order_status_code = 'Finished' WHERE id IN (" + idString + ")";
                MyBatisManager.getInstance().executeUpdate(session, updateSalesSql);
                String conditionItem = "sales_order_id IN (" + idString + ")";
                List<ViewSalesOrderItem> lstSalesOrderItem = ViewSalesOrderItemManager.
                        getInstance().getList(session, conditionItem);
                for (ViewSalesOrderItem salesOrderItem : lstSalesOrderItem) {
                    //获取产品库存
                    CompanyProductStocks theCompanyProductStocks = CompanyProductStocksManager.getInstance().
                            getEntityByProductIdAndCompanyId(session, salesOrderItem.getProductId(), salesOrderItem.getCompanyId());
                    if (theCompanyProductStocks != null) {
                        if (theCompanyProductStocks.getQuantity() == null) {
                            theCompanyProductStocks.setQuantity(salesOrderItem.getQuantity().intValue());
                        } else {
                            theCompanyProductStocks.setQuantity(theCompanyProductStocks.getQuantity() + salesOrderItem.getQuantity().intValue());
                        }
                        CompanyProductStocksManager.getInstance().update(session, theCompanyProductStocks);
                    } else {
                        CompanyProductStocks newCompanyProductStocks = new CompanyProductStocks();
                        newCompanyProductStocks.setCompanyId(salesOrderItem.getCompanyId());
                        newCompanyProductStocks.setProductId(salesOrderItem.getProductId());
                        newCompanyProductStocks.setQuantity(salesOrderItem.getQuantity().intValue());
                        CompanyProductStocksManager.getInstance().add(session, newCompanyProductStocks);
                    }
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
}
