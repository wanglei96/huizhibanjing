package strosoft.wx.common;

import java.util.HashMap;

import net.sf.json.JSONObject;

/**
 * 订阅消息
 * 
 * @author dl
 * 
 */
public class WxSubscribeMessage {
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

	public String getPage() {
		return Page;
	}

	public void setPage(String page) {
		Page = page;
	}

	public HashMap<String, Object> getData() {
		return Data;
	}

	public void setData(HashMap<String, Object> data) {
		Data = data;
	}

	public String getMiniprogramState() {
		return MiniprogramState;
	}

	public void setMiniprogramState(String miniprogramState) {
		MiniprogramState = miniprogramState;
	}

	public String getLang() {
		return Lang;
	}

	public void setLang(String lang) {
		Lang = lang;
	}

	/**
	 * 接收者（用户）的 openid
	 */
	public String ToUser;
	/**
	 * 所需下发的订阅模板id
	 */
	public String TemplateID;
	/**
	 * 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
	 */
	public String Page;
	/**
	 * 模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
	 */
	public HashMap<String, Object> Data;
	/**
	 * 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
	 */
	public String MiniprogramState;
	/**
	 * 进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN
	 */
	public String Lang;

	/**
	 * 转成josn对象
	 * 
	 * @return
	 */
	public JSONObject ToJsonObject() {
		JSONObject jResult = new JSONObject();
		jResult.put("touser", this.ToUser);
		jResult.put("template_id", this.TemplateID);
		jResult.put("page", this.Page);
		jResult.put("miniprogram_state", this.MiniprogramState);
		jResult.put("lang", this.Lang);
		// 数据
		JSONObject jData = new JSONObject();
		for (String key : this.Data.keySet()) {
			JSONObject jValue = new JSONObject();
			String strValue = (String) this.Data.get(key);
			jValue.put("value", strValue);
			jData.put(key, jValue);
		}
		jResult.put("data", jData);
		return jResult;
	}
}
