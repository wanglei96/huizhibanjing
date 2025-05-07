package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.sys.model.Authentication;
import strosoft.sys.model.AuthenticationManager;

public class SysAddApplicationServiceHandler extends ServiceHandler{
	
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			Integer userSessionOverTime=null;
			Boolean duplicateLogin = null;
			String applicationId = request.getParameter("applicationId");
			String userSessionOverTimeString = request.getParameter("userSessionOverTime");
			String duplicateLoginString = request.getParameter("duplicateLogin");
			try{
				userSessionOverTime = Integer.valueOf(userSessionOverTimeString);
			}catch(Exception e){
				//传入非活跃参数有误,设置默认参数30天
				userSessionOverTime = 30*24*60*60;
			}
			try{
				duplicateLogin = Boolean.valueOf(duplicateLoginString);
			}catch(Exception e){
				//传入是否允许同时登陆参数错误,设置默认参数true
				duplicateLogin = true;
			}
			Authentication authentication = new Authentication();
			authentication.setApplicationId(applicationId);
			authentication.setDuplicateLogin(duplicateLogin);
			authentication.setUserSessionOverTime(userSessionOverTime);
			AuthenticationManager.getInstance().addApplication(authentication);
			this.writeSuccessResponse(response);
		}catch(Exception e){
			e.printStackTrace();
			this.writeErrorResponse(response, "添加应用信息失败");
		}

	}
}
