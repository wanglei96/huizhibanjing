package strosoft.wx.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.util.Logger;
import strosoft.app.util.StreamHelper;
import strosoft.app.util.StringHelper;
import strosoft.app.util.Tracer;
import strosoft.wx.pay.WxPayClient;
import strosoft.wx.pay.WxPayConfig;
import strosoft.wx.pay.WxPayHelper;
import strosoft.wx.pay.WxPayNoticeContext;
import strosoft.wx.pay.WxPayRefundNotifyHandler;

public class WxPayRefundNotify extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WxPayRefundNotify() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 处理请求
	 */
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		try {
			String timeStamp = request.getHeader("Wechatpay-Timestamp");
			String nonce = request.getHeader("Wechatpay-Nonce");
			String body = StreamHelper.readAsString(request.getInputStream());
			String signature = request.getHeader("Wechatpay-Signature");
			Tracer.writeLine("timeStamp:" + timeStamp);
			Tracer.writeLine("nonce:" + nonce);
			Tracer.writeLine("body:" + body);
			Tracer.writeLine("signature:" + signature);
			// timeStamp = "1651053896";
			// nonce = "YPtlC24o3Iym9FmqUpIe8vTAtqdWV9Cm";
			// body =
			// "{\"id\":\"bcd0cb2b-54a8-585c-aaec-2495025774cb\",\"create_time\":\"2022-04-27T18:04:56+08:00\",\"resource_type\":\"encrypt-resource\",\"event_type\":\"TRANSACTION.SUCCESS\",\"summary\":\"支付成功\",\"resource\":{\"original_type\":\"transaction\",\"algorithm\":\"AEAD_AES_256_GCM\",\"ciphertext\":\"DKX5SkzeuGnTLoaVQC2SHMivGrxwoY2PMlUVRZO3J1R/lA2x9W5s3eKOSewzogRWYwXU6Ky3KLhARU5YezTKAWghNq3A4sCsWSbztpuCoIsaFDhP7YA3WOkc+pEc8bWGoSzywBWhZMYvK2MGWKGR27PTmVOxkzE2EfPxWGmsS6Xhla/5uAaJMxU5Omu5adb+AOhLpZmisLXFVdggk/sn5S+N4W8Ym5GVW6rLZLvXkjawhzoBLkgglu0ClK4l0QU2nsszxjRrXfBWg/1iDlTMToZfs+p4Z9Irw3AIVIBkgeQkCjzD7lXV1jmfR6gP5j/ldcaUcHG8QMY+PTIZv42KAQjTdaGYSd7aoAf49ozx/OXxNPJ3dY+c3CtG+0mkF8RikFpqxonYTkXlpsxCcPElmf5Px0u7zhOpxnjYix8u81+tXPuKWcdmKB8fX9XDkbgOgn08wKmIgaLI+DfEiNvLayD7rhORLBdQJ8TOrxnojIMo8GhzvaU57SIEeLLtG1KMHY8daBlNQsisBuU9aMdwJVRBvuE6widSsMEUnoZjUoBxS5bDE5Tmn/zwUQ==\",\"associated_data\":\"transaction\",\"nonce\":\"SFCQ3kO4Z1dr\"}}";
			// signature =
			// "N8KaHfVlvuKLYbDxcQl6KKO65/y5+x6rnNhC8VG+kwLJ8k3+J9covWUHqdRPSCF22rP8qsV7lqUsOvBmnw/7BgrXBuG6PxUYp9Jt0FC/hBgjUfGE4OdzhuadfRjsfb8CJV2k74lDP1oOeQ1r7PoulJLvV9GwwmRmZdiQSc4Tul0OT4sqdSRnFKSZdcyepn9TTQ9aX8aV7AhTXcuCMjQIwYLfFCJJYFcjYQWtKvfOEWBgbaEtkZC5EwFbXmBalySDujZHYackwioH4v7FkxOe0o9+wLLrE4PuTZ1RDPPYSg28HYiRePE22Esqa4wzKspBkVKb45kpHe8hkkwo0CEiLg==";
			// 验证签名
			Boolean isValid = this.checkSignature(timeStamp, nonce, body, signature);
			if (!isValid) {
				Tracer.writeLine("签名验证失败！");
				response.getWriter().write("签名验证失败！");
				return;
			}
			// 检查数据体
			if (StringHelper.isNullOrEmpty(body)) {
				response.getWriter().write("请求数据为空！");
				return;
			}
			JSONObject jBody = JSONObject.fromObject(body);
			/*
			 * { "id":"EV-2018022511223320873", "create_time":"2018-06-08T10:34:56+08:00",
			 * "resource_type":"encrypt-resource", "event_type":"REFUND.SUCCESS",
			 * "summary":"退款成功", "resource" : { "original_type": "refund",
			 * "algorithm":"AEAD_AES_256_GCM", "ciphertext": "...", "associated_data": "",
			 * "nonce": "..." } }
			 * 
			 * { "mchid": "1900000100", "transaction_id": "1008450740201411110005820873",
			 * "out_trade_no": "20150806125346", "refund_id":
			 * "50200207182018070300011301001", "out_refund_no":
			 * "7752501201407033233368018", "refund_status": "SUCCESS", "success_time":
			 * "2018-06-08T10:34:56+08:00", "user_received_account": "招商银行信用卡0403", "amount"
			 * : { "total": 999, "refund": 999, "payer_total": 999, "payer_refund": 999 } }
			 */
			JSONObject jResouce = jBody.getJSONObject("resource");
			String associatedData = jResouce.getString("associated_data");
			String _nonce = jResouce.getString("nonce");
			String ciphertext = jResouce.getString("ciphertext");
			String message = WxPayHelper.decryptToString(WxPayConfig.getWxApiV3Key(), associatedData, _nonce,
					ciphertext);
			Tracer.writeLine("notice message:" + message);
			JSONObject jMessage = JSONObject.fromObject(message);
			String refundStatus = jMessage.getString("refund_status");
			if (!refundStatus.equals("SUCCESS")) {
				response.getWriter().write(refundStatus);
				return;
			}
			WxPayNoticeContext context = new WxPayNoticeContext();
			context.setNoticeMessage(jMessage);
			try {
				String handlerTypeName = WxPayConfig.getWxRefundNotifyHandlerTypeName();
				Class serviceHandlerClass = Class.forName(handlerTypeName);
				WxPayRefundNotifyHandler serviceHandler = (WxPayRefundNotifyHandler) serviceHandlerClass.newInstance();
				serviceHandler.process(context);
				response.getWriter().write("success");
			} catch (Exception ex) {
				ex.printStackTrace();
				response.getWriter().write(ex.getMessage());
			}
		} catch (Exception ex) {
			JSONArray jarrStackTrace = JSONArray.fromObject(ex.getStackTrace());
			Logger.writeLine(ex.getMessage());
			Logger.writeLine(jarrStackTrace.toString());
			response.getWriter().write(jarrStackTrace.toString());
		}
	}

	/**
	 * 验证签名
	 * 
	 * @param timeStamp
	 * @param nonce
	 * @param body
	 * @param signature
	 * @return
	 * @throws Exception
	 */
	private Boolean checkSignature(String timeStamp, String nonce, String body, String signature) throws Exception {
		String text = String.format("%s\n%s\n%s\n", timeStamp, nonce, body);
		WxPayClient theWXPayClient = new WxPayClient();
		Boolean isValid = theWXPayClient.verify(text, signature);
		return isValid;
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}
}
