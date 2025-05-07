package strosoft.app.test;

import java.util.Date;

import strosoft.app.client.ServiceClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SeviceClientTest {
	public static void main(String[] args) throws Exception {
		Integer i = null;
		Date d = null;
		System.out.print(i);
		System.out.println("-----");
		String path = System.getProperty("java.class.path");
		System.out.println(path);
		String serviceAddress = "http://localhost:19958/Admin/API/Service.ashx";
		ServiceClient theServiceClient = new ServiceClient(serviceAddress);
		JSONObject jData = new JSONObject();
		jData.put("tableName","SysUser");
		JSONArray result = (JSONArray)theServiceClient.remoteCall("GetTable", jData);
		System.out.println(result);
		//登录

		JSONObject jData2 = new JSONObject();
		String strResult = theServiceClient.call("GetMessageCount", jData2);
		int result2 = Integer.parseInt(strResult);
		System.out.println(result2);
	}
}
