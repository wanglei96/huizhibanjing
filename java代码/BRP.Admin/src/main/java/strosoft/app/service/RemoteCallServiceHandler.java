package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.client.ServiceClient;
import net.sf.json.JSONObject;

/*
 * 调用远程服务接口
 */
public class RemoteCallServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String serviceName = jData.getString("serviceName");
		ServiceClient theServiceClient = new ServiceClient();
		// 如果服务地址不为空，则设置服务地址
		if (jData.containsKey("serviceAddress")) {
			String serviceAddress = jData.getString("serviceAddress");
			if (serviceAddress != null && !serviceAddress.equals("")) {
				theServiceClient.setServiceAddress(serviceAddress);
			}
		}
		Object objResult = theServiceClient.remoteCall(serviceName, jData);
		this.writeSuccessResponse(response, objResult);
	}
}
