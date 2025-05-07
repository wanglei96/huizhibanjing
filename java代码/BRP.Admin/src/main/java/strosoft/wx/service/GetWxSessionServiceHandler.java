package strosoft.wx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.app.util.Tracer;
import strosoft.wx.common.WxClient;
import strosoft.wx.common.WxConfig;
import strosoft.wx.common.WxJsCode2SessionResponse;

/**
 * 取得微信用户信息
 * 
 * @author Admin
 * 
 */
public class GetWxSessionServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String code = JsonHelper.getString(jData, "code");
		Tracer.writeLine("code: " + code);
		String wxAppID = WxConfig.getWxAppId();
		Tracer.writeLine("wxAppID: " + wxAppID);
		String requestWXAppID = JsonHelper.getString(jData, "WxAppID");
		if (!StringHelper.isNullOrEmpty(requestWXAppID)) {
			wxAppID = requestWXAppID;
		}
		String wxAppSecret = WxConfig.getWxAppSecret();
		String requestWXAppSecret = JsonHelper.getString(jData, "WxAppSecret");
		if (!StringHelper.isNullOrEmpty(requestWXAppSecret)) {
			wxAppSecret = requestWXAppSecret;
		}
		WxClient newWXClient = new WxClient(wxAppID, wxAppSecret);
		WxJsCode2SessionResponse theWXJsCode2SessionResponse = newWXClient.JsCode2Session(code);
		Tracer.writeLine("UnionID: " + theWXJsCode2SessionResponse.getUnionID());
		Tracer.writeLine("OpenID: " + theWXJsCode2SessionResponse.getOpenID());
		this.writeSuccessResponse(response, theWXJsCode2SessionResponse);
	}
}
