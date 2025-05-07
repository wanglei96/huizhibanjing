package strosoft.app.client;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import strosoft.app.common.SysConfig;
import strosoft.app.util.HttpHelper;

public class ServiceClient {

	private String serviceAddress = null;

	public String getServiceAddress() {
		if (this.serviceAddress == null || this.serviceAddress.equals("")) {
			return SysConfig.getServiceAddress();
		}
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public ServiceClient() {

	}

	public ServiceClient(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	/**
	 * 调用服务
	 * 
	 * @param serviceName
	 * @param jData
	 * @return
	 * @throws Exception
	 */
	public Object remoteCall(String serviceName, JSONObject jData) throws Exception {
		HashMap<String, String> mapParameters = new HashMap<String, String>();
		mapParameters.put("data", jData.toString());
		String result = call(serviceName, mapParameters);
		JSONObject jResult = JSONObject.fromObject(result);
		String success = jResult.getString("success");
		if (!success.equals("true")) {
			String errorMessage = jResult.getString("errorMessage");
			throw new Exception(errorMessage);
		}
		try {
			// 返回JSONObject对象
			JSONObject jResultData = jResult.getJSONObject("data");
			return jResultData;
		} catch (Exception ex) {
			try {
				// 返回JSONArray对象
				JSONArray jarrResultData = jResult.getJSONArray("data");
				return jarrResultData;
			} catch (Exception ex2) {
				try {
					// 返回Boolean值
					Boolean bResultData = jResult.getBoolean("data");
					return bResultData;
				} catch (Exception ex3) {
					try {
						// 返回int值
						int iResultData = jResult.getInt("data");
						return iResultData;
					} catch (Exception ex4) {
						try {
							// 返回double值
							double dblResultData = jResult.getDouble("data");
							return dblResultData;
						} catch (Exception ex5) {
							try {
								// 返回long值
								long lngResultData = jResult.getLong("data");
								return lngResultData;
							} catch (Exception ex6) {
								// 返回String值
								return jResult.getString("data");
							}
						}
					}
				}
			}

		}
	}

	/**
	 * 调用服务
	 * 
	 * @param serviceName
	 * @param mapParameters
	 * @return
	 * @throws Exception
	 */
	public String call(String serviceName, HashMap<String, String> mapParameters) throws Exception {
		String serviceAddress = this.getServiceAddress();
		String url = serviceAddress + "?serviceName=" + serviceName;
		String result = HttpHelper.post(url, null, mapParameters, null);
		return result;
	}

	/**
	 * 调用服务
	 * @param serviceName
	 * @param jData
	 * @return
	 * @throws Exception
	 */
	public String call(String serviceName, JSONObject jData) throws Exception {
		HashMap<String, String> mapParameters = new HashMap<String, String>();
		if (jData != null) {
			mapParameters.put("data", jData.toString());
		} else {
			mapParameters.put("data", null);
		}
		return call(serviceName, mapParameters);
	}
}
