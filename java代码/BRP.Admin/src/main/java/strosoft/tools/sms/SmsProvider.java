package strosoft.tools.sms;

import org.w3c.dom.Node;

public class SmsProvider {

	private String providerName;
	private Node configNode; 
	private String account;
	private String password;
	/*
	 * 发送短信
	 */
	public void sendSms(String phone,
			String content) throws Exception {

	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String serviceName) {
		this.providerName = serviceName;
	}
	public Node getConfigNode() {
		return configNode;
	}
	public void setConfigNode(Node configNode) {
		this.configNode = configNode;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
