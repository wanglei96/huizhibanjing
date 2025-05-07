package strosoft.sso.model;

import java.util.Hashtable;
import java.util.List;
/**
 * 要验证的系统类别
 * @author administrator
 *
 */
public class Application {
	private String applicationId;
	private Hashtable<String, List<ActiveUserSession>> activeUsers = new Hashtable<String, List<ActiveUserSession>>();
	private Integer userSessionOverTime;
	private Boolean duplicateLogin;

	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public Hashtable<String, List<ActiveUserSession>> getActiveUsers() {
		return activeUsers;
	}
	public void setActiveUsers(Hashtable<String, List<ActiveUserSession>> activeUsers) {
		this.activeUsers = activeUsers;
	}
	public Integer getUserSessionOverTime() {
		return userSessionOverTime;
	}
	public void setUserSessionOverTime(Integer userSessionOverTime) {
		this.userSessionOverTime = userSessionOverTime;
	}
	public Boolean getDuplicateLogin() {
		return duplicateLogin;
	}
	public void setDuplicateLogin(Boolean duplicateLogin) {
		this.duplicateLogin = duplicateLogin;
	}
}