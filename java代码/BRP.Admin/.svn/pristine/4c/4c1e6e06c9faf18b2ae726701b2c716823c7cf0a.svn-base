package strosoft.wx.service;

import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.wx.common.WxClient;
import strosoft.wx.common.WxConfig;
import strosoft.wx.common.WxJsCode2SessionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 取得微信用户信息
 * 
 * @author Admin
 * 
 */
public class GetWxaSessionServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String code = JsonHelper.getString(jData, "code");
		String wxaAppId = WxConfig.getWxaAppId();
		String requestWXAppID = JsonHelper.getString(jData, "WxaAppId");
		if (!StringHelper.isNullOrEmpty(requestWXAppID)) {
			wxaAppId = requestWXAppID;
		}
		String wxaAppsecret = WxConfig.getWxaAppSecret();
		String requestWxaAppSecret = JsonHelper.getString(jData, "WxaAppSecret");
		if (!StringHelper.isNullOrEmpty(requestWxaAppSecret)) {
			wxaAppsecret = requestWxaAppSecret;
		}
		WxClient newWXClient = new WxClient(wxaAppId, wxaAppsecret);
		WxJsCode2SessionResponse theWXJsCode2SessionResponse = newWXClient.JsCode2Session(code);
		this.writeSuccessResponse(response, theWXJsCode2SessionResponse);
	}
}
