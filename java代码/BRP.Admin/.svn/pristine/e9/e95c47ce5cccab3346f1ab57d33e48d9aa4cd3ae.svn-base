package strosoft.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.sso.model.ActiveUserManager;
import strosoft.sso.model.ActiveUserSession;
/**
 * 处理是否为活跃用户请求，当updateActiveTime为true时更新用户最后活跃时间
 * @author administrator
 *
 */
public class IsActiveUserServiceHandler extends ServiceHandler{
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String accountId = request.getParameter("accountId");
			String applicationId = request.getParameter("applicationId");
			String activeUserSessionID = request.getParameter("activeUserSessionID");
			Boolean updateActiveTime = Boolean.parseBoolean(request.getParameter("updateActiveTime"));
			Boolean isActive = null;
			isActive = ActiveUserManager.getInstance().isActiveUser(applicationId, accountId, activeUserSessionID);
			if(updateActiveTime){
				ActiveUserManager.getInstance().updateActiveUser(applicationId, accountId, activeUserSessionID);
			}
			this.writeSuccessResponse(response,isActive);
		} catch(Exception e){
			e.printStackTrace();
			this.writeErrorResponse(response, "检查是否为活跃用户失败");
		}

	}
}
