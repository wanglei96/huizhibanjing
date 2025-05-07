package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.sys.model.AuthenticationManager;

public class GetApplicationsLoginListServiceHandler extends ServiceHandler{
	
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//是否开启登录状态验证
		Boolean userSessionCheckEnabled = AuthenticationManager.getInstance().getUserSessionCheckEnabled();
		if(!userSessionCheckEnabled){
			this.writeSuccessResponse(response);
			return;
		}
		try {	
			String data = request.getParameter("data");
			JSONObject jData = JSONObject.fromObject(data);
			String applicationId = jData.getString("applicationId");
			if(null == applicationId || applicationId.equals("")){
				applicationId = SysConfig.getApplicationId();
			}
			JSONObject result = AuthenticationManager.getInstance().getActiveUserList(applicationId);
			this.writeSuccessResponse(response,result);
		}catch(Exception e){
			e.printStackTrace();
			this.writeErrorResponse(response, "获取登录用户列表失败");
			return;
		}

	}
}
