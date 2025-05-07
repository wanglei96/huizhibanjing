package BRP.service;

import BRP.data.PayOrder;
import BRP.model.PayOrderManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SplitPayOrderServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            //根据门店获取属下书箱费+会员费账单
            String condition = String.format("company_id = %d and payment_item_code='MemberBookcaseFee'", companyId);
            List<PayOrder> lstPayOrder = PayOrderManager.getInstance().getList(sqlSession, condition);
            List<PayOrder> newBookcaseFeePayOrderList = new ArrayList<>();
            for (PayOrder payOrder : lstPayOrder){
                payOrder.setAmount(new BigDecimal(299));
                payOrder.setPaymentItemCode("MembershipFee");
                PayOrderManager.getInstance().update(sqlSession, payOrder);
                payOrder.setId(null);
                payOrder.setAmount(new BigDecimal(68));
                payOrder.setPaymentItemCode("BookcaseFee");
                newBookcaseFeePayOrderList.add(payOrder);
            }
            PayOrderManager.getInstance().addList(sqlSession, newBookcaseFeePayOrderList);
            writeSuccessResponse(response);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new Exception(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }
}
