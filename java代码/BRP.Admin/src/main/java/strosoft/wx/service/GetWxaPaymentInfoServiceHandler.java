package strosoft.wx.service;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.app.util.Tracer;
import strosoft.wx.common.WxConfig;
import strosoft.wx.pay.WxPayClient;
import strosoft.wx.pay.WxPayConfig;

/**
 * 取得微信小程序支付信息
 * 
 * @author Admin
 * 
 */
public class GetWxaPaymentInfoServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JSONObject jData = this.getRequestData(request);
		Tracer.writeLine("jData:" + jData);
		Tracer.writeLine("mchid1:" +  WxPayConfig.getWxMchId());
		WxPayClient theWxPayClient = new WxPayClient();
		// 取得prepayId
		JSONObject jParameter = new JSONObject();

		jParameter.put("mchid", WxPayConfig.getWxMchId());
		Tracer.writeLine("mchid2:" +  WxPayConfig.getWxMchId());
		String outTradeNo = JsonHelper.getString(jData, "outTradeNo");
		jParameter.put("out_trade_no", outTradeNo);
		Tracer.writeLine("outTradeNo:" + outTradeNo);
		jParameter.put("appid", WxConfig.getWxaAppId());
		String description = JsonHelper.getString(jData, "description");
		if (StringHelper.isNullOrEmpty(description)) {
			description = "订单" + outTradeNo;
		}
		jParameter.put("description", description);
		String notifyUrl = JsonHelper.getString(jData, "notifyUrl");
		jParameter.put("notify_url", notifyUrl);
		JSONObject jAmount = new JSONObject();
		/*String strAmount = JsonHelper.getString(jData, "amount");
		BigDecimal bigAmount = new BigDecimal(strAmount);
		Tracer.writeLine("bigAmount:" + bigAmount);
		BigDecimal bigOneAmount = bigAmount.multiply(new BigDecimal(100));
		Tracer.writeLine("bigOneAmount:" + bigOneAmount);
		int totalAmount = bigOneAmount.intValue();
		Tracer.writeLine("totalAmount:" + totalAmount);*/
		// 金额
		int totalAmount = JsonHelper.getDecimal(jData, "amount").multiply(new BigDecimal(100)).intValue();
		Tracer.writeLine("totalAmount:" + totalAmount);
		jAmount.put("total", totalAmount);
		jAmount.put("currency", "CNY");
		jParameter.put("amount", jAmount);
		// 支付人
		JSONObject jPayer = new JSONObject();
		String openId = JsonHelper.getString(jData, "openId");
		jPayer.put("openid", openId);
		jParameter.put("payer", jPayer);

		String prepayId = theWxPayClient.getPrepayId(jParameter);
		Tracer.writeLine("prepayId:" + prepayId);

		// 获取支付信息
		JSONObject jPaymentInfo = theWxPayClient.getPaymentInfo(WxConfig.getWxaAppId(), prepayId);
		Tracer.writeLine("jPaymentInfo:" + jPaymentInfo);
		this.writeSuccessResponse(response, jPaymentInfo);
	}
}
