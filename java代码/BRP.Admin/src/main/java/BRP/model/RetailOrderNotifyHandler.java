package BRP.model;

import net.sf.json.JSONObject;
import strosoft.app.util.Tracer;
import strosoft.wx.pay.WxPayNoticeContext;
import strosoft.wx.pay.WxPayNotifyHandler;

public class RetailOrderNotifyHandler extends WxPayNotifyHandler {

    @Override
    public void process(WxPayNoticeContext context) throws Exception {
        Tracer.writeLine("RetailOrderNotifyHandler开始...");
        JSONObject jMessage = context.getNoticeMessage();
        // 订单编号
        String outTradeNo = jMessage.getString("out_trade_no");
        // 微信支付交易号
        String wxPayTransactionId = jMessage.getString("transaction_id");
        RetailOrderManager.getInstance().payRetailOrderNotify(outTradeNo, wxPayTransactionId);
        Tracer.writeLine("RetailOrderNotifyHandler结束");
    }

}
