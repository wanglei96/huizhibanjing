package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.sys.model.AuthenticationManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SysKickOutActiveUserServiceHandler extends ServiceHandler{
	
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String data = request.getParameter("data");
			JSONObject jData = JSONObject.fromObject(data);
			JSONArray userSessionList = jData.getJSONArray("userSessionList");
			AuthenticationManager.getInstance().kickOutActiveUser(userSessionList);
			this.writeSuccessResponse(response,"踢出成功");
		}catch(Exception e){
			this.writeErrorResponse(response, "踢出失败");
		}

	}
}
