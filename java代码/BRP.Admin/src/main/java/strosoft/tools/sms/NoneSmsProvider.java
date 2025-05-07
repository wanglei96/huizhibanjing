package strosoft.tools.sms;

import strosoft.app.util.HttpHelper;

public class NoneSmsProvider extends SmsProvider {

	private String serverAddress = "http://sh2.cshxsp.com";

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public NoneSmsProvider() {

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
	}
}
