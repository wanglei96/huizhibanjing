package strosoft.wx.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.HttpHelper;
import strosoft.app.util.JsonHelper;
import strosoft.wx.common.WxAccessToken;
import strosoft.wx.common.WxClient;
import strosoft.wx.common.WxConfig;

/**
 * 获取小程序二维码
 * 
 * @author Admin
 * 
 */
public class GetWxaQRCodeServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jData = this.getRequestData(request);
		WxClient newWXClient = new WxClient();
		String path = JsonHelper.getString(jData, "path");
		String width = JsonHelper.getString(jData, "width");
		WxAccessToken theWXAccessToken = newWXClient.GetAccessToken(WxConfig.getWxaAppId(), WxConfig.getWxaAppSecret());
		String url = String.format("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=%s",
				theWXAccessToken.getAccessToken());
		JSONObject jDataParameter = new JSONObject();
		jDataParameter.put("path", path);
		jDataParameter.put("width", width);
		String relativePath = SysConfig.getUploadDirectory() + "/" + UUID.randomUUID().toString() + ".jpg";
		String saveFilePath = SysConfig.getRootPath() + "/"  + relativePath; 
		String parameters = jDataParameter.toString();
		HttpHelper.downloadFilePost(url, saveFilePath, parameters);
		this.writeSuccessResponse(response, relativePath);
	}
}
