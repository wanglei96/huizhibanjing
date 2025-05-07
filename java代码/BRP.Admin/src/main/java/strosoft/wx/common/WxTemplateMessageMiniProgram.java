package strosoft.wx.common;

/**
 * 微信模板消息小程序信息
 * 
 * @author dl
 * 
 */
public class WxTemplateMessageMiniProgram {

	/**
	 * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
	 */
	public String AppID;

	public String getAppID() {
		return AppID;
	}

	public void setAppID(String appID) {
		AppID = appID;
	}

	public String getPagePath() {
		return PagePath;
	}

	public void setPagePath(String pagePath) {
		PagePath = pagePath;
	}

	/**
	 * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
	 */
	public String PagePath;
}
