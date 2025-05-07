package strosoft.sys.model;

/**
 * 验证服务配置类
 * @author administrator
 *
 */
public class Authentication {
	private String applicationId;
	private Boolean duplicateLogin;
	private Integer userSessionOverTime;

	public Boolean getDuplicateLogin() {
		return duplicateLogin;
	}
	public void setDuplicateLogin(Boolean duplicateLogin) {
		this.duplicateLogin = duplicateLogin;
	}
	public Integer getUserSessionOverTime() {
		return userSessionOverTime;
	}
	public void setUserSessionOverTime(Integer userSessionOverTime) {
		this.userSessionOverTime = userSessionOverTime;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
}
