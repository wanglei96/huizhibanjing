package strosoft.wx.pay;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.util.ResourceUtils;
import strosoft.app.common.SysConfig;
import strosoft.app.util.FileHelper;

@WebListener
public class WxPayStartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 设置配置信息
		this.setConfig();
	}

	/*
	 * 设置配置信息
	 */
	private void setConfig() {
		Properties theProperties = new Properties();
		FileInputStream theFileInputStream;
		try {
			String rootPath = ResourceUtils.getURL("classpath:").getPath();
			SysConfig.setRootPath(rootPath);
			theFileInputStream = new FileInputStream(rootPath + "/config/config.properties");
			theProperties.load(theFileInputStream);
			// 商户号
			String wxMchId = theProperties.getProperty("WxMchId");
			WxPayConfig.setWxMchId(wxMchId);
			// API V3密钥
			String wxApiV3Key = theProperties.getProperty("WxApiV3Key");
			WxPayConfig.setWxApiV3Key(wxApiV3Key);
			// 商户证书序列号
			String wxMchSerialNo = theProperties.getProperty("WxMchSerialNo");
			WxPayConfig.setWxMchSerialNo(wxMchSerialNo);
			// 商户私钥
			String wxPrivateKeyFilePath = SysConfig.getRootPath() + "/" + theProperties.getProperty("WxPrivateKeyFile");
			WxPayConfig.setWxPrivateKeyFilePath(wxPrivateKeyFilePath);
			String privateKey = FileHelper.readAllText(wxPrivateKeyFilePath);
			WxPayConfig.setWxPrivateKey(privateKey);
			// 微信支付通知处理类名称
			String wxNotifyHandlerTypeName = theProperties.getProperty("WxNotifyHandlerTypeName");
			WxPayConfig.setWxNotifyHandlerTypeName(wxNotifyHandlerTypeName);
			// PC微信支付通知处理类名称
			String pcWxNotifyHandlerTypeName = theProperties.getProperty("PCWxNotifyHandlerTypeName");
			WxPayConfig.setPCWxNotifyHandlerTypeName(pcWxNotifyHandlerTypeName);
			// 微信退款通知处理类名称
			String wxRefundNotifyHandlerTypeName = theProperties.getProperty("WxRefundNotifyHandlerTypeName");
			WxPayConfig.setWxRefundNotifyHandlerTypeName(wxRefundNotifyHandlerTypeName);
			// 微信退款通知地址
			String wxRefundNotifyUrl = theProperties.getProperty("WxRefundNotifyUrl");
			WxPayConfig.setWxRefundNotifyUrl(wxRefundNotifyUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
