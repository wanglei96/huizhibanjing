package strosoft.sso.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import strosoft.app.common.SysConfig;
import strosoft.app.util.DateHelper;
/**
 * 登录用户处理类
 * @author administrator
 *
 */
public class ActiveUserManager {
	private static ActiveUserManager _instance;
	private Hashtable<String, Application> applications = new Hashtable<String, Application>();
	private Integer userSessionCheckInertval;
	private LoginStatusMonitoringThread loginStatusMonitoringThread = null;

	public static ActiveUserManager getInstance() {
		if (_instance == null) {
			_instance = new ActiveUserManager();
		}
		return _instance;
	}

	/**
	 * 实例化时，设置参数
	 */
	private ActiveUserManager() {
		userSessionCheckInertval = SysConfig.getUserSessionCheckInertval();
	}

	public Hashtable<String, Application> getApplications() {
		return applications;
	}

	public void setApplications(Hashtable<String, Application> applications) {
		this.applications = applications;
	}

	public Integer getCheckInertval() {
		return userSessionCheckInertval;
	}

	public void setCheckInertval(Integer checkInertval) {
		this.userSessionCheckInertval = checkInertval;
	}
	
	/**
	 * 添加应用，设置应用配置信息
	 * @param applicationId 应用ID
	 * @param duplicateLogin 同一帐号是否可以同时登录
	 * @param userSessionOverTime 非活跃用户失效时间
	 * @throws Exception 添加应用或设置应用配置信息失败
	 */
	public void addApplication(String applicationId,Boolean duplicateLogin,Integer userSessionOverTime) throws Exception{
		// 获取已存储的登录信息
		Application storageApplication = applications.get(applicationId);
		if (null == storageApplication) {
			storageApplication = new Application();
			storageApplication.setApplicationId(applicationId);
			applications.put(applicationId,storageApplication);
		}
		storageApplication.setDuplicateLogin(duplicateLogin);
		storageApplication.setUserSessionOverTime(userSessionOverTime);
		//开启活跃用户判定线程
		if (null == this.loginStatusMonitoringThread) {
			this.loginStatusMonitoringThread = new LoginStatusMonitoringThread();
			Thread thread = new Thread(this.loginStatusMonitoringThread);
			thread.start();
		}
	}

	/**
	 * 登录时，添加一条登录信息，返回生成的唯一标识
	 * 
	 * @param application
	 *            应用信息
	 * @param accountId
	 *            登录ID
	 * @param ipAddress
	 *            登录地址
	 * @return 添加的用户信息
	 * @throws Exception
	 *             添加失败
	 */
	public ActiveUserSession addActiveUser(String applicationId,
			String accountId,String accountName, String ipAddress) throws Exception {
		// 获取已存储的登录信息
		Application storageApplication = applications.get(applicationId);
		if (null == storageApplication) {
			throw new Exception("该应用未添加");
		}
		if (!storageApplication.getDuplicateLogin()) {
			kickOutUser(applicationId, accountId);
		}
		Hashtable<String, List<ActiveUserSession>> storageActiveUsers = storageApplication
				.getActiveUsers();
		ActiveUserSession userSession = new ActiveUserSession();
		Date now = new Date();
		userSession.setAccountName(accountName);
		userSession.setActiveTime(now);
		userSession.setLoginTime(now);
		userSession.setIPAddress(ipAddress);
		userSession.setActiveUserSessionID(UUID.randomUUID().toString());
		// 添加登录信息
		if (storageActiveUsers.containsKey(accountId)) {
			storageActiveUsers.get(accountId).add(userSession);
		} else {
			List<ActiveUserSession> userSessionList = new ArrayList<ActiveUserSession>();
			userSessionList.add(userSession);
			storageActiveUsers.put(accountId, userSessionList);
		}
		return userSession;
	}

	/**
	 * 判断是否为活跃用户
	 * 
	 * @param applicationId
	 *            应用ID
	 * @param accountId
	 *            登录帐号ID
	 * @param activeUserSessionID
	 *            登录唯一标识
	 * @return 是否为活跃用户
	 */
	public Boolean isActiveUser(String applicationId, String accountId,
			String activeUserSessionID) {
		Boolean isActive = false;
		Application storageApplication = applications.get(applicationId);
		if (null == storageApplication) {
			return false;
		}
		List<ActiveUserSession> uerSessions = storageApplication
				.getActiveUsers().get(accountId);
		if (null == uerSessions) {
			return false;
		}
		try {
			for (int i = 0; i < uerSessions.size(); i++) {
				ActiveUserSession currentSession = uerSessions.get(i);
				if (currentSession.getActiveUserSessionID().equals(
						activeUserSessionID)) {
					isActive = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			isActive = false;
		}
		return isActive;

	}

	/**
	 * 修改最后活跃时间
	 * 
	 * @param applicationId
	 *            应用ID
	 * @param accountId
	 *            登录账户ID
	 * @param activeUserSessionID
	 *            登录唯一标识
	 * @throws Exception
	 *             更新失败
	 */
	public void updateActiveUser(String applicationId, String accountId,
			String activeUserSessionID) throws Exception {
		Application storageApplication = applications.get(applicationId);
		if (null == storageApplication) {
			throw new Exception();
		}
		List<ActiveUserSession> uerSessions = storageApplication
				.getActiveUsers().get(accountId);
		if (null == uerSessions) {
			throw new Exception();
		}
		for (int i = 0; i < uerSessions.size(); i++) {
			ActiveUserSession currentSession = uerSessions.get(i);
			if (currentSession.getActiveUserSessionID().equals(
					activeUserSessionID)) {
				currentSession.setActiveTime(new Date());
			}
		}
	}

	/**
	 * 踢出指定系统用户ID的登录状态
	 * 
	 * @param applicationId
	 *            应用ID
	 * @param accountId
	 *            登录帐号ID
	 * @throws Exception
	 *             踢出失败
	 */
	public void kickOutUser(String applicationId, String accountId)
			throws Exception {
		try {  
			 Application application = applications.get(applicationId);
			 application.getActiveUsers().remove(accountId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 根据登录的唯一标识踢出登录用户
	 * 
	 * @param applicationId
	 *            应用ID
	 * @param accountId
	 *            登录帐号ID
	 * @param activeUserSessionID
	 *            登录唯一标识
	 * @throws Exception
	 *             踢出失败
	 */
	public void kickOutUser(String applicationId, String accountId,
			String activeUserSessionID) throws Exception {
		try {
				Application application = applications.get(applicationId);
				if (application.getApplicationId().equals(applicationId)) {
					Hashtable<String, List<ActiveUserSession>> activeUsers = application
							.getActiveUsers();
					List<ActiveUserSession> userSessions = activeUsers
							.get(accountId);
					for (int j = userSessions.size() - 1; j >= 0; j--) {
						String currentSessionID = userSessions.get(j)
								.getActiveUserSessionID();
						if (currentSessionID.equals(activeUserSessionID)) {
							userSessions.remove(j);
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Integer getActiveUserCount() {
		return null;
	}

	/**
	 * 根据应用ID获取该应用用户登录列表
	 * 
	 * @param applicationId
	 *            应用ID
	 * @return 应用用户登录列表
	 * @throws Exception
	 *             获取失败
	 */
	public Hashtable<String, Application> getApplicationsByApplicationId(
			String applicationId) throws Exception {
		Hashtable<String, Application> resultapplications = new Hashtable<String, Application>();
		Application application = applications.get(applicationId);
		if(null != application){
			resultapplications.put(applicationId, application);
		}
		return resultapplications;
	}

	/**
	 * 检查登录用户是否还在活跃状态，不在就踢出用户
	 * 
	 * @throws Exception
	 *             登录状态监测失败
	 */

	public void CheckSelf() throws Exception {
		// 过期时间
		try {
			for (String key : applications.keySet()) {
				Hashtable<String, List<ActiveUserSession>> activeUsers = applications
						.get(key).getActiveUsers();
				Integer userSessionOverTime = applications.get(key)
						.getUserSessionOverTime();
				// 最后活跃时间在lastActiveTime之前的用户踢出
				Date lastActiveTime = DateHelper.addSeconds(new Date(),
						-userSessionOverTime);
				for (String activeUserKey : activeUsers.keySet()) {
					List<ActiveUserSession> userSessionList = activeUsers
							.get(activeUserKey);
					for (int j = userSessionList.size() - 1; j >= 0; j--) {
						ActiveUserSession currentsSession = userSessionList
								.get(j);
						if (lastActiveTime.after(currentsSession
								.getActiveTime())) {
							userSessionList.remove(j);
						}
					}
					if (userSessionList.size() < 1) {
						activeUsers.remove(activeUserKey);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("登录状态监测失败");
			throw e;
		}
	}
}
