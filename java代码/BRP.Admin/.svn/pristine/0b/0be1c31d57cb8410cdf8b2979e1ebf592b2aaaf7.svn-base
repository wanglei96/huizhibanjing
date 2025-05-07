package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.DateHelper;
import strosoft.app.util.Tracer;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesOrderManager extends SalesOrderMaster {
    private static SalesOrderManager _instance;

    private SalesOrderManager() {
        this.setTableName("sales_order");
        this.setTypeName("SalesOrder");
    }

    public static SalesOrderManager getInstance() {
        if (_instance == null) {
            _instance = new SalesOrderManager();
        }
        return _instance;
    }

    public void paySalesOrderNotify(String outTradeNo, String wxPayTransactionId) throws Exception {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            // 修改订单信息
            SalesOrder theSalesOrder = this.getEntityBySalesOrderNo(sqlSession, outTradeNo);
            if (theSalesOrder == null) {
                Tracer.writeLine(String.format("未找到订单编号为%s的订单！", outTradeNo));
                throw new Exception(String.format("未找到订单编号为%s的订单！", outTradeNo));
            }
            if (!theSalesOrder.getSalesOrderStatusCode().equals(SalesOrderStatusCodes.Paying)) {
                Tracer.writeLine("订单" + outTradeNo + "状态为不对！ ,当前状态编码为：" + theSalesOrder.getSalesOrderStatusCode());
                throw new Exception("订单" + outTradeNo + "状态为不对！ ,当前状态编码为：" + theSalesOrder.getSalesOrderStatusCode());
            }
            theSalesOrder.setSalesOrderStatusCode(SalesOrderStatusCodes.Delivering);
            theSalesOrder.setIsPaid(true);
            // 支付时间
            theSalesOrder.setPayTime(DateHelper.getCurrentTimestamp());
            this.update(sqlSession, theSalesOrder);
            if(theSalesOrder.getSalesOrderTypeCode().equals("Bookbox")){

            }
            /*PayOrder newPayOrder = new PayOrder();
            newPayOrder.setCompanyId(theSalesOrder.getCompanyId());
            newPayOrder.setAmount(theSalesOrder.getAmount());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date now = new Date();
            String payOrderNo = sdf.format(now);
            newPayOrder.setPayOrderNo(payOrderNo);
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newPayOrder.setBusinessDate(currentTimestamp);
            newPayOrder.setPaymentItemCode("CompanyBookboxFee");
            newPayOrder.setPayTime(currentTimestamp);
            newPayOrder.setOperatorId(theSalesOrder.getCompanyUserId());
            newPayOrder.setPaymentMethodCode(theSalesOrder.getPaymentMethodCode());
            newPayOrder.setIsFinished(true);
            newPayOrder.setIsPaid(true);
            PayOrderManager.getInstance().add(sqlSession, newPayOrder);*/
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw e;
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }

}