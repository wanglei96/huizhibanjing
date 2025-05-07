package strosoft.wx.pay;

/**
 * 微信退款通知消息处理类
 * 
 * @author Admin
 *
 */
public abstract class WxPayRefundNotifyHandler {
	public abstract void process(WxPayNoticeContext context) throws Exception;
}
