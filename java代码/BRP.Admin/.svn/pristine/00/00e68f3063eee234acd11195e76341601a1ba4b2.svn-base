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
import strosoft.wx.pay.WxPayNotifyHandler;

public class WxPayNotify extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WxPayNotify() {
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
			// 解密消息
			if (StringHelper.isNullOrEmpty(body)) {
				response.getWriter().write("请求数据为空！");
				return;
			}
			// body =
			// "{\"id\":\"00a7f748-1d52-592d-8d9b-0dc033317f9a\",\"create_time\":\"2022-04-27T15:40:56+08:00\",\"resource_type\":\"encrypt-resource\",\"event_type\":\"TRANSACTION.SUCCESS\",\"summary\":\"支付成功\",\"resource\":{\"original_type\":\"transaction\",\"algorithm\":\"AEAD_AES_256_GCM\",\"ciphertext\":\"Nrq82cSE+ZMVhnE+ZJrn7kpfzCe4osx2LTUrSif4mL+/KMPvkNAvSe+5ISF/P0Ye62IDEa4r/Z1CZTVDnodCKjzpbo2/S9lFVFCcqPKb1qcgSYzxz0ogBH+3yFsrZynVcpDrrFjoRJ2vrt5fFRSWsfp7GUaX/1scvfE1voyBSo5Mf7mRwe2PESc/ziliLRjYtxj+I/TUyQjWCrptS1WtY7ocsmPQftj6Ndohpiww122lE65NJc4vuHL5+kpN+tQMZ1grUun09Shk7MbkIHzk0k9qxeod2SXAbFGujtleXaY5FjyqDS07e5tNnKsnL64NHfSwMZ+4qOTXyMMonqf+J/cy0CYyF+vkAB75Xfd/rO4PKFqFbuCKfz2bi5xKhCg4euBO9m5mtFvANWnfLqWTrczUXrHc8fXPn+zw1Btsxu8KNfAPHLdhIAdr+XFlDGhoG8GfQMhbhd5Qj5sbeB1llSrzG/PKgclfMLQCEsnt5ntjMOnTY596BNz4pkGelv+/E2gJvLzoDsgHO6WSUqb+ogn8fcKuUEma+KgZJeA74QlzyMGXgxeP/3XB9Q==\",\"associated_data\":\"transaction\",\"nonce\":\"fELNiDqBpqA9\"}}";
			JSONObject jBody = JSONObject.fromObject(body);
			/*
			 * {"id":"00a7f748-1d52-592d-8d9b-0dc033317f9a","create_time":
			 * "2022-04-27T15:40:56+08:00","resource_type":"encrypt-resource","event_type":
			 * "TRANSACTION.SUCCESS","summary":"支付成功","resource":{"original_type":
			 * "transaction","algorithm":"AEAD_AES_256_GCM","ciphertext":
			 * "Nrq82cSE+ZMVhnE+ZJrn7kpfzCe4osx2LTUrSif4mL+/KMPvkNAvSe+5ISF/P0Ye62IDEa4r/Z1CZTVDnodCKjzpbo2/S9lFVFCcqPKb1qcgSYzxz0ogBH+3yFsrZynVcpDrrFjoRJ2vrt5fFRSWsfp7GUaX/1scvfE1voyBSo5Mf7mRwe2PESc/ziliLRjYtxj+I/TUyQjWCrptS1WtY7ocsmPQftj6Ndohpiww122lE65NJc4vuHL5+kpN+tQMZ1grUun09Shk7MbkIHzk0k9qxeod2SXAbFGujtleXaY5FjyqDS07e5tNnKsnL64NHfSwMZ+4qOTXyMMonqf+J/cy0CYyF+vkAB75Xfd/rO4PKFqFbuCKfz2bi5xKhCg4euBO9m5mtFvANWnfLqWTrczUXrHc8fXPn+zw1Btsxu8KNfAPHLdhIAdr+XFlDGhoG8GfQMhbhd5Qj5sbeB1llSrzG/PKgclfMLQCEsnt5ntjMOnTY596BNz4pkGelv+/E2gJvLzoDsgHO6WSUqb+ogn8fcKuUEma+KgZJeA74QlzyMGXgxeP/3XB9Q=="
			 * ,"associated_data":"transaction","nonce":"fELNiDqBpqA9"}}
			 */
			JSONObject jResouce = jBody.getJSONObject("resource");

			String associatedData = jResouce.getString("associated_data");
			String _nonce = jResouce.getString("nonce");
			String ciphertext = jResouce.getString("ciphertext");
			String message = WxPayHelper.decryptToString(WxPayConfig.getWxApiV3Key(), associatedData, _nonce,
					ciphertext);
			Tracer.writeLine("notice message:" + message);
			JSONObject jMessage = JSONObject.fromObject(message);
			String tradeState = jMessage.getString("trade_state");
			if (!tradeState.equals("SUCCESS")) {
				response.getWriter().write(tradeState);
				return;
			}
//		{
//		    "transaction_id":"1217752501201407033233368018",
//		    "amount":{
//		        "payer_total":100,
//		        "total":100,
//		        "currency":"CNY",
//		        "payer_currency":"CNY"
//		    },
//		    "mchid":"1230000109",
//		    "trade_state":"SUCCESS",
//		    "bank_type":"CMC",
//		    "promotion_detail":[
//		        {
//		            "amount":100,
//		            "wechatpay_contribute":0,
//		            "coupon_id":"109519",
//		            "scope":"GLOBAL",
//		            "merchant_contribute":0,
//		            "name":"单品惠-6",
//		            "other_contribute":0,
//		            "currency":"CNY",
//		            "stock_id":"931386",
//		            "goods_detail":[
//		                {
//		                    "goods_remark":"商品备注信息",
//		                    "quantity":1,
//		                    "discount_amount":1,
//		                    "goods_id":"M1006",
//		                    "unit_price":100
//		                },
//		                {
//		                    "goods_remark":"商品备注信息",
//		                    "quantity":1,
//		                    "discount_amount":1,
//		                    "goods_id":"M1006",
//		                    "unit_price":100
//		                }
//		            ]
//		        },
//		        {
//		            "amount":100,
//		            "wechatpay_contribute":0,
//		            "coupon_id":"109519",
//		            "scope":"GLOBAL",
//		            "merchant_contribute":0,
//		            "name":"单品惠-6",
//		            "other_contribute":0,
//		            "currency":"CNY",
//		            "stock_id":"931386",
//		            "goods_detail":[
//		                {
//		                    "goods_remark":"商品备注信息",
//		                    "quantity":1,
//		                    "discount_amount":1,
//		                    "goods_id":"M1006",
//		                    "unit_price":100
//		                },
//		                {
//		                    "goods_remark":"商品备注信息",
//		                    "quantity":1,
//		                    "discount_amount":1,
//		                    "goods_id":"M1006",
//		                    "unit_price":100
//		                }
//		            ]
//		        }
//		    ],
//		    "success_time":"2018-06-08T10:34:56+08:00",
//		    "payer":{
//		        "openid":"oUpF8uMuAJO_M2pxb1Q9zNjWeS6o"
//		    },
//		    "out_trade_no":"1217752501201407033233368018",
//		    "appid":"wxd678efh567hg6787",
//		    "trade_state_desc":"支付成功",
//		    "trade_type":"MICROPAY",
//		    "attach":"自定义数据",
//		    "scene_info":{
//		        "device_id":"013467007045764"
//		    }
//		}

			WxPayNoticeContext context = new WxPayNoticeContext();
			context.setNoticeMessage(jMessage);
			try {
				String handlerTypeName = WxPayConfig.getWxNotifyHandlerTypeName();
				Class serviceHandlerClass = Class.forName(handlerTypeName);
				WxPayNotifyHandler serviceHandler = (WxPayNotifyHandler) serviceHandlerClass.newInstance();
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
