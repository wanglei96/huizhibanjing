package strosoft.sso.service;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.sso.model.ActiveUserManager;
import strosoft.sso.model.Application;
/**
 * 处理根据应用ID获取该应用所有登录用户信息列表
 * @author administrator
 *
 */
public class GetApplicationsUserListServiceHandler extends ServiceHandler{
	
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String applicationId = request.getParameter("applicationId");
			Hashtable<String, Application> applications = null;
			if(applicationId.equals("GetAllApplications")){
				applications =  ActiveUserManager.getInstance().getApplications();
			}else{
				 applications =  ActiveUserManager.getInstance().getApplicationsByApplicationId(applicationId);
			}
			this.writeSuccessResponse(response,applications);
		}catch(Exception e){
			this.writeErrorResponse(response, "获取登录用户列表失败");
		}
	}
}