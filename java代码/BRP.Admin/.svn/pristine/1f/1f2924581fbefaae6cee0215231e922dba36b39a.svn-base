package strosoft.wx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import strosoft.app.service.ServiceHandler;
import strosoft.app.util.AESHelper;
import strosoft.app.util.JsonHelper;

/**
 * 解密微信数据
 * 
 * @author dl
 * 
 */
public class DecryptWxDataServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		// 加密数据
		String encryptedData = JsonHelper.getString(jData, "encryptedData");
		// 会话密钥
		String sessionKey = JsonHelper.getString(jData, "sessionKey");
		// 初始向量
		String iv = JsonHelper.getString(jData, "iv");
		String decryptedData = AESHelper.decryptWXData(encryptedData, sessionKey, iv);
		JSONObject jDecryptedData = JSONObject.fromObject(decryptedData);
		this.writeSuccessResponse(response, jDecryptedData);
	}
}
