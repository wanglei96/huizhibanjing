package strosoft.wx.common;

import java.util.HashMap;
import java.util.Iterator;


import net.sf.json.JSONObject;

/**
 * 模板消息
 * 
 * @author Admin
 * 
 */
public class WxaSubscribeMessage {
	/**
	 * 接收者openid
	 */
	private String toUser;
	/**
	 * 跳转页面
	 */
	private String page;
	/**
	 * 模板ID
	 */
	public String templateId;
	/**
	 * 小程序跳转类型
	 */
	public String miniprogramState;
	/**
	 * 语言类型
	 */
	public String lang;
	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getMiniprogramState() {
		return miniprogramState;
	}

	public void setMiniprogramState(String miniprogramState) {
		this.miniprogramState = miniprogramState;
	}

	/**
	 * 数据
	 */
	public HashMap<String, JSONObject> Data;
	
	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateID) {
		this.templateId = templateID;
	}

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
		jResult.put("touser", this.toUser);
		jResult.put("template_id", this.templateId);
		jResult.put("page", this.page);
		// 数据
		JSONObject jData = new JSONObject();
		for (String key : this.Data.keySet()) {
			JSONObject hmValue = this.Data.get(key);
			jData.put(key, hmValue);
		}
		jResult.put("data", jData);
		return jResult;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
