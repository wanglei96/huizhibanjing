package strosoft.wx.pay;

import net.sf.json.JSONObject;

/**
 * 微信支付通知环境
 * 
 * @author Admin
 *
 */
public class WxPayNoticeContext {
	private JSONObject noticeMessage;

	public JSONObject getNoticeMessage() {
		return noticeMessage;
	}

	public void setNoticeMessage(JSONObject noticeMessage) {
		this.noticeMessage = noticeMessage;
	}
}
