package strosoft.wx.service;

import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.wx.common.*;

/**
 * 发送模板消息
 * @author Administrator
 *
 */
public class SendWxTemplateMessageServiceHandler extends ServiceHandler{
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String openId = JsonHelper.getString(jData, "openID");

		WxClient theWXClient = new WxClient(WxConfig.getWxaAppId(), WxConfig.getWxaAppSecret());
		WxAccessToken theWXAccessToken = theWXClient.GetAccessToken();
		WxaSubscribeMessage newWxSubscribeMessage = new WxaSubscribeMessage();
		newWxSubscribeMessage.setToUser(openId);
		newWxSubscribeMessage.setTemplateId("i2jBP4bt8e7vkWbJJNIlbcl7hxUecYVAQovmDpNYcGo");
		newWxSubscribeMessage.setPage("/pages/books/index");

		HashMap<String, JSONObject> hmRequestData = new HashMap<>();

		JSONObject jFirst1 = new JSONObject();
		jFirst1.put("value", "测试图书送达通知");
		hmRequestData.put("thing1", jFirst1);

		// 假设你有一个Date对象
		String time1 = DateHelper.timestampToString(DateHelper.getCurrentTimestamp(), "yyyy年M月d日 HH:mm");

		JSONObject jFirst2 = new JSONObject();
		jFirst2.put("value", time1);
		hmRequestData.put("time2", jFirst2);

		JSONObject jFirst3 = new JSONObject();
		jFirst3.put("value", "快去书箱领取新书吧！");
		hmRequestData.put("thing3", jFirst3);

		newWxSubscribeMessage.setData(hmRequestData);

		JSONObject jResult = theWXClient.SendWxaTempateMessage(theWXAccessToken.getAccessToken(), newWxSubscribeMessage);

        this.writeSuccessResponse(response, jResult);
       
	}

	
}
