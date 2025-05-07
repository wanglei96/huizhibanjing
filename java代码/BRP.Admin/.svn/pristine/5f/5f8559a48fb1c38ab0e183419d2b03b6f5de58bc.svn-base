package strosoft.sso.service;

import strosoft.app.service.ServiceHandler;
import strosoft.sso.model.ActiveUserManager;
import strosoft.sso.model.ActiveUserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 处理添加登录信息请求
 * @author administrator
 *
 */
public class AddActiveUserServiceHandler  extends ServiceHandler{
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String accountId = request.getParameter("accountId");
			String accountName = request.getParameter("accountName");
			String applicationId = request.getParameter("applicationId");  
			String ipAddress = request.getParameter("ipAddress");
			ActiveUserSession userSession = ActiveUserManager.getInstance().addActiveUser(applicationId,accountId,accountName,ipAddress);
			this.writeSuccessResponse(response,userSession);
			return;
		} catch(Exception e){
			e.printStackTrace();
			this.writeErrorResponse(response, "添加登录信息失败");
			return;
		}

	}
}
