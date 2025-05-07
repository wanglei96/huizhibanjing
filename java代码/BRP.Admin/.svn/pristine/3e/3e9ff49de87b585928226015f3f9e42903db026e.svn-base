package BRP.service;

import BRP.data.Member;
import BRP.data.PayOrder;
import BRP.model.BookOrderItemManager;
import BRP.model.MemberManager;
import BRP.model.PayOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class CancelBookBoxServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer memberId = JsonHelper.getInt(jData, "memberId");
        String bookboxRemark = JsonHelper.getString(jData, "bookboxRemark");

        Member member = MemberManager.getInstance().getEntity(memberId);
        member.setMemberBookboxStatusCode("Cancel");
        member.setIsActivated(false);
        member.setBookboxAmount(new BigDecimal(0));
        member.setBookboxRemark(bookboxRemark);
        member.setBookboxCancelTime(new Timestamp(System.currentTimeMillis()));
        MemberManager.getInstance().update(member);

        // 删除账单明细
        List<PayOrder> list = PayOrderManager.getInstance().getList("member_id = " + memberId + " and payment_item_code = 'BookcaseFee'");
        list.stream().forEach(payOrder -> {
            try {
                PayOrderManager.getInstance().delete(payOrder.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        this.writeSuccessResponse(response);
    }
}