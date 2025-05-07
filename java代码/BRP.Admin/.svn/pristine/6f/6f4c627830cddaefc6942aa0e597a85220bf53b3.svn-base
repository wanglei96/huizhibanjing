package strosoft.sys.model;

import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import strosoft.app.client.ServiceClient;
import strosoft.app.common.SysConfig;
import strosoft.app.util.FileHelper;

/*
 * 审核管理类
 */
public class AuthenticationManager {
	private Boolean userSessionCheckEnabled = false;
	private List<String> unCheckServiceNameList;

	private static AuthenticationManager _instance;

	public static AuthenticationManager getInstance() {
		if (_instance == null) {
			_instance = new AuthenticationManager();
		}
		return _instance;
	}

	private AuthenticationManager() {
		Boolean userSessionCheckEnabled = SysSettingManager.getInstance()
				.getBoolSetting("UserSessionCheckEnabled", false);
		this.setUserSessionCheckEnabled(userSessionCheckEnabled);
		try {
			// 获取不检查登录状态服务名称列表
			unCheckServiceNameList = getUnCheckServiceNameListConfig();
		} catch (IOException e) {
			System.out.println("读取配置文件失败");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			System.out.println("转换为xml格式失败");
			e.printStackTrace();
		}
	}

	public Boolean getUserSessionCheckEnabled() {
		return userSessionCheckEnabled;
	}

	public void setUserSessionCheckEnabled(Boolean userSessionCheckEnabled) {
		this.userSessionCheckEnabled = userSessionCheckEnabled;
	}

	/**
	 * 向登录验证服务器发送“是否为活跃用户”请求
	 * 
	 * @param sysUserId
	 *            系统用户ID
	 * @param activeUserSessionID
	 *            登录唯一标识
	 * @return 是否为活跃用户
	 * @throws Exception
	 *             请求失败
	 */
	public Boolean isActiveUser(String applicationId, String accountId,
			String activeUserSessionID, Boolean updateActiveTime)
			throws Exception {
		HashMap<String, String> mapParameters = new HashMap<String, String>();
		mapParameters.put("accountId", accountId);
		mapParameters.put("applicationId", applicationId);
		mapParameters.put("activeUserSessionID", activeUserSessionID);
		if (null == updateActiveTime) {
			updateActiveTime = false;
		}
		mapParameters.put("updateActiveTime", updateActiveTime.toString());
		ServiceClient theServiceClient = new ServiceClient();
		String result = theServiceClient.call("IsActiveUser", mapParameters);
		Boolean isActive = JSONObject.fromObject(result).getBoolean("data");
		return isActive;
	}

	/**
	 * 向登录验证服务器发送“添加登录用户信息”请求
	 * 
	 * @param sysUserId
	 *            系统用户ID
	 * @param ipAddress
	 *            用户登录IP地址
	 * @return 登录唯一标识
	 * @throws Exception
	 *             添加登录用户信息失败
	 */
	public String addActiveUser(String applicationId, String accountId,String accountName,
			String ipAddress) throws Exception {
		HashMap<String, String> mapParameters = new HashMap<String, String>();
		mapParameters.put("accountId", accountId);
		mapParameters.put("accountName", accountName);
		mapParameters.put("applicationId", applicationId);
		mapParameters.put("ipAddress", ipAddress);

		ServiceClient theServiceClient = new ServiceClient();
		String result = theServiceClient.call("AddActiveUser", mapParameters);
		JSONObject resultdata = JSONObject.fromObject(result);
		String activeUserSessionID = resultdata.getJSONObject("data")
				.getString("activeUserSessionID");
		return activeUserSessionID;
	}

	/**
	 * 向登录验证服务器发送“踢出登录用户”请求
	 * 
	 * @param userSessionList
	 *            要踢出的用户列表
	 * @throws Exception
	 *             踢出失败
	 */
	public void kickOutActiveUser(
			JSONArray userSessionList) throws Exception {
		HashMap<String, String> mapParameters = new HashMap<String, String>();
		mapParameters.put("userSessionList", userSessionList.toString());
		ServiceClient theServiceClient = new ServiceClient();
		theServiceClient.call("KickOutActiveUser", mapParameters);
	}

	/**
	 * 向登录验证服务器发送“获取登录用户信息列表”请求
	 * 
	 * @return 登录用户信息列表
	 * @throws Exception
	 *             获取登录用户信息失败
	 */
	public JSONObject getActiveUserList(String applicationId)
			throws Exception {
		HashMap<String, String> mapParameters = new HashMap<String, String>();
		mapParameters.put("applicationId", applicationId);
		ServiceClient theServiceClient = new ServiceClient();
		String resultData = theServiceClient.call("GetApplicationsUserList",
				mapParameters);
		JSONObject resultJsonObject = JSONObject.fromObject(resultData);
		JSONObject data = resultJsonObject.getJSONObject("data");
		return data;
	}

	public String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = request.getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 向验证服务器发送添加应用配置信息
	 * 
	 * @throws Exception
	 */
	public String addApplication(Authentication authentication) throws Exception {
		// 设置验证服务
		if (!this.userSessionCheckEnabled) {
			return "success:true,验证服务未开启";
		}
		HashMap<String, String> mapParameters = new HashMap<String, String>();
		String applicationId = authentication.getApplicationId();
		String duplicateLogin = authentication.getDuplicateLogin().toString();
		String userSessionOverTime = authentication.getUserSessionOverTime()
				.toString();
		mapParameters.put("applicationId", applicationId);
		mapParameters.put("duplicateLogin", duplicateLogin);
		mapParameters.put("userSessionOverTime", userSessionOverTime);
		ServiceClient theServiceClient = new ServiceClient();
		String result = theServiceClient.call("AddApplication", mapParameters);
		return result;
	}

	/**
	 * 检查是否为非检查请求
	 * 
	 * @param serviceName
	 *            请求服务名
	 * @return
	 */
	public Boolean isUncheckServiceName(String serviceName) {
		return unCheckServiceNameList.contains(serviceName);
	}

	/**
	 * 请求时检查是否为活跃用户
	 * 
	 * @param sysUserId系统用户ID
	 * @param token登录唯一标识
	 * @return 活跃状态
	 */
	public String checkLoginStatus(String applicationId, String accountId,
			String token) {
		try {
			Boolean isActive = AuthenticationManager.getInstance()
					.isActiveUser(applicationId, accountId, token, true);
			if (!isActive) {
				return "请重新登录";
			}
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "更新活跃时间失败";
		}
	}

	/**
	 * 从UncheckServicesConfig读取不检查登录状态的服务名称列表
	 * 
	 * @return 不检查登录状态的服务名称列表
	 * @throws IOException
	 *             读取配置文件失败
	 * @throws ParserConfigurationException
	 *             文件内容字符串转.xml格式失败
	 */
	public List<String> getUnCheckServiceNameListConfig() throws IOException,
			ParserConfigurationException {
		List<String> serviceNameList = new ArrayList<String>();
		// 读取内容
		String rootPath = SysConfig.getRootPath();
		String configuration = FileHelper.readAllText(rootPath
				+ "/config/UncheckServicesConfig.xml", "utf-8");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		Document document;
		try {
			document = db.parse(is);
			NodeList documentChild = document.getElementsByTagName("Service");
			for (int i = 0; i < documentChild.getLength(); i++) {
				Element e = (Element) documentChild.item(i);
				String serviceName = e.getAttribute("Name");
				serviceNameList.add(serviceName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return serviceNameList;
	}

	/**
	 * 系统启动时，向验证服务器添加系统的应用信息
	 * 
	 * @throws Exception
	 */
	public String sendVerificationConfig() throws Exception {
		// 添加系统验证服务
		String sysApplicationId = SysConfig.getApplicationId();
		Boolean duplicateLogin = SysSettingManager.getInstance().getBoolSetting(
				"DuplicateLogin", false);
		Integer userSessionOverTime = SysSettingManager.getInstance().getIntSetting(
				"UserSessionOverTime", 30 * 60);
		Authentication sysAuthentication = new Authentication();
		sysAuthentication.setApplicationId(sysApplicationId);
		sysAuthentication.setDuplicateLogin(duplicateLogin);
		sysAuthentication.setUserSessionOverTime(userSessionOverTime);
		// 向验证服务器发送添加应用信息请求
		return this.addApplication(sysAuthentication);
	}

	public String getParameterApplicationId(HttpServletRequest request) {
		String applicationId = request.getParameter("applicationId");
		if (null == applicationId) {
			applicationId = SysConfig.getApplicationId();
		}
		return applicationId;
	}
}
