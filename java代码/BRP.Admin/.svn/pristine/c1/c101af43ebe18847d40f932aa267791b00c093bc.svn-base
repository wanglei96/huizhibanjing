package strosoft.app.common;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import strosoft.app.util.HttpHelper;

public class ServiceClient {

	/**
	 * 调用服务
	 * 
	 * @param serviceName
	 * @param mapParameters
	 * @return
	 * @throws Exception
	 */
	public static String call(String serviceName, Map<String, String> mapParameters) throws Exception {
		String serviceAddress = SysConfig.getVerificationServiceAddress();
		String url = serviceAddress + "?service_name=" + serviceName;
		/*
		 * String urlds = serviceAddress + "?service_name=" + serviceName; for (String
		 * key : mapParameters.keySet()) { urlds += "&'" + key + "'=" +
		 * mapParameters.get(key).toString(); } System.out.println(urlds);
		 */
		String resultData = null;
		try {
			resultData = HttpHelper.post(url, null, mapParameters, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("请求失败");
		}
		// System.out.println(resultData);
		JSONObject result = JSONObject.fromObject(resultData);
		if (result.getString("success").equalsIgnoreCase("false")) {
			throw new Exception(result.getString("errorMessage"));
		}
		return resultData;
	}

	/**
	 * 调用服务
	 * 
	 * @param serviceName
	 * @param mapParameters
	 * @return
	 * @throws Exception
	 */
	public static Object callService(String serviceName, JSONObject jData) throws Exception {
		HashMap<String, String> hmData = new HashMap<String, String>();
		hmData.put("data", jData.toString());
		String strResult = call(serviceName, hmData);
		JSONObject jResult = JSONObject.fromObject(strResult);
		if (jResult.getString("success").equalsIgnoreCase("false")) {
			throw new Exception(jResult.getString("errorMessage"));
		}
		if (jResult.containsKey("data")) {
			Object jResultData = jResult.get("data");
			return jResultData;
		}
		return null;

	}
}
