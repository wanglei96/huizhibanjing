package strosoft.tools.sms;

import java.net.URLEncoder;

import strosoft.app.util.HttpHelper;

public class HxSmsServiceProvider extends SmsProvider {

	private String serverAddress = "http://sh2.cshxsp.com";

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public HxSmsServiceProvider() {

	}

	/*
	 * 发送短信
	 */
	public void sendSms(String phone, String content) throws Exception {
		String account = this.getAccount();
		String password = this.getPassword();
		sendSms(account, password, phone, content);
	}

	/*
	 * 发送短信
	 */
	public void sendSms(String account, String password, String phone,
			String content) throws Exception {
		String url = String
				.format("%s/sms.aspx?action=send&account=%s&password=%s&mobile=%s&content=%s&sendTime=&extno=",
						this.getServerAddress(), account, password, phone,
						URLEncoder.encode(content, "utf-8"));
		HttpHelper.get(url);
	}
}
