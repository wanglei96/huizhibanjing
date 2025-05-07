package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.sys.model.AuthenticationManager;

public class CheckLoginServiceHandler extends ServiceHandler{
	
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			//是否开启登录状态验证
			Boolean userSessionCheckEnabled = AuthenticationManager.getInstance().getUserSessionCheckEnabled();
			if(!userSessionCheckEnabled){
				this.writeSuccessResponse(response, "NoCheck");
				return;
			}
			String token = request.getParameter("token");
			String accountId = request.getParameter("accountId");
			String applicationId =AuthenticationManager.getInstance().getParameterApplicationId(request);
			Boolean isActive = AuthenticationManager.getInstance().isActiveUser(applicationId,accountId, token,false);
			this.writeSuccessResponse(response,isActive);
		}catch(Exception e){
			this.writeErrorResponse(response, "检查登录状态失败");
		}

	}
}
