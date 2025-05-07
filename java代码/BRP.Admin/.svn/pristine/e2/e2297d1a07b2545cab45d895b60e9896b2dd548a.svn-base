package strosoft.wx.common;

import java.util.HashMap;


import net.sf.json.JSONObject;

/**
 * 模板消息
 * 
 * @author Admin
 * 
 */
public class WxTemplateMessage {
	/**
	 * 接收者openid
	 */
	public String ToUser;
	public String getToUser() {
		return ToUser;
	}

	public void setToUser(String toUser) {
		ToUser = toUser;
	}

	public String getTemplateID() {
		return TemplateID;
	}

	public void setTemplateID(String templateID) {
		TemplateID = templateID;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public WxTemplateMessageMiniProgram getMiniProgram() {
		return MiniProgram;
	}

	public void setMiniProgram(WxTemplateMessageMiniProgram miniProgram) {
		MiniProgram = miniProgram;
	}

	

	/**
	 * 模板ID
	 */
	public String TemplateID;
	/**
	 * 模板跳转链接
	 */
	public String Url;
	/**
	 * 跳小程序所需数据，不需跳小程序可不用传该数据
	 */
	public WxTemplateMessageMiniProgram MiniProgram;
	/**
	 * 数据
	 */
	public HashMap<String, JSONObject> Data;

	public HashMap<String, JSONObject> getData() {
		return Data;
	}

	public void setData(HashMap<String, JSONObject> data) {
		Data = data;
	}

	/**
	 * 转为Json对象
	 * 
	 * @return
	 */
	public JSONObject ToJsonObject() {
		JSONObject jResult = new JSONObject();
		jResult.put("touser", this.ToUser);
		jResult.put("template_id", this.TemplateID);
		jResult.put("url", this.Url);
		if (this.MiniProgram != null) {
			JSONObject miniProgram = new JSONObject();
			miniProgram.put("appid", this.MiniProgram.AppID);
			miniProgram.put("pagepath", this.MiniProgram.PagePath);
			jResult.put("miniprogram", miniProgram);
		}
		// 数据
		JSONObject jData = new JSONObject();
		for (String key : this.Data.keySet()) {
			//JSONObject jValue = new JSONObject();
			JSONObject hmValue = this.Data.get(key);
			/* Iterator iterator = hmValue.keys();
			 while(iterator.hasNext()){
			      jValue.put("value", (String) iterator.next());
			      
			      if (hmValue.getString(key) != null) {
						jValue.put("color",hmValue.get(hmValue.getString(key)));
			      }
			 }*/
	
			
			/*for (String key2 : hmValue.keySet()) {
				jValue.put("value", key2);
				
			}*/
			jData.put(key, hmValue);
		}
		jResult.put("data", jData);
		return jResult;
	}
}
