package strosoft.wx.pay;

/**
 * 微信支付配置
 * 
 * @author Admin
 *
 */
public class WxPayConfig {
	private static String WxMchId;// 商户号
	private static String WxApiV3Key;// API V3密钥
	private static String WxMchSerialNo;// 商户证书序列号
	private static String WxPrivateKey;// 商户私钥
	private static String WxNotifyHandlerTypeName;// 微信支付通知处理类名称
	private static String WxRefundNotifyHandlerTypeName;// 微信退款通知处理类名称
	private static String WxRefundNotifyUrl;// 通知地址
	private static String PCWxNotifyHandlerTypeName;// PC通知地址
	private static String WxPrivateKeyFilePath;// 商户私钥路径

	public static String getWxMchId() {
		return WxMchId;
	}

	public static void setWxMchId(String WXMchId) {
		WxPayConfig.WxMchId = WXMchId;
	}

	public static String getWxApiV3Key() {
		return WxApiV3Key;
	}

	public static void setWxApiV3Key(String wxApiV3Key) {
		WxPayConfig.WxApiV3Key = wxApiV3Key;
	}

	public static String getWxMchSerialNo() {
		return WxMchSerialNo;
	}

	public static void setWxMchSerialNo(String wxMchSerialNo) {
		WxPayConfig.WxMchSerialNo = wxMchSerialNo;
	}

	public static String getWxPrivateKey() {
		return WxPrivateKey;
	}

	public static void setWxPrivateKey(String wxPrivateKey) {
		WxPayConfig.WxPrivateKey = wxPrivateKey;
	}

	public static String getWxNotifyHandlerTypeName() {
		return WxNotifyHandlerTypeName;
	}

	public static void setWxNotifyHandlerTypeName(String wXNotifyHandlerTypeName) {
		WxNotifyHandlerTypeName = wXNotifyHandlerTypeName;
	}

	public static String getWxRefundNotifyHandlerTypeName() {
		return WxRefundNotifyHandlerTypeName;
	}

	public static void setWxRefundNotifyHandlerTypeName(String wXRefundNotifyHandlerTypeName) {
		WxRefundNotifyHandlerTypeName = wXRefundNotifyHandlerTypeName;
	}

	public static String getWxRefundNotifyUrl() {
		return WxRefundNotifyUrl;
	}

	public static void setWxRefundNotifyUrl(String wXRefundNotifyUrl) {
		WxRefundNotifyUrl = wXRefundNotifyUrl;
	}

	public static String getPCWxNotifyHandlerTypeName() {
		return PCWxNotifyHandlerTypeName;
	}

	public static void setPCWxNotifyHandlerTypeName(String PCWxNotifyHandlerTypeName) {
		WxPayConfig.PCWxNotifyHandlerTypeName = PCWxNotifyHandlerTypeName;
	}

	public static String getWxPrivateKeyFilePath() {
		return WxPrivateKeyFilePath;
	}

	public static void setWxPrivateKeyFilePath(String wxPrivateKeyFilePath) {
		WxPrivateKeyFilePath = wxPrivateKeyFilePath;
	}
}
