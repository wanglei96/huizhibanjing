package strosoft.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.sso.model.ActiveUserManager;
/**
 * 处理更新用户最后活跃时间请求
 * @author administrator
 *
 */
public class UpdateActiveTimeServiceHandler extends ServiceHandler{
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String accountId = request.getParameter("accountId");
			String applicationId = request.getParameter("applicationId");
			String activeUserSessionID = request.getParameter("activeUserSessionID");
			ActiveUserManager.getInstance().updateActiveUser(applicationId,accountId, activeUserSessionID);
		} catch(Exception e){
			this.writeErrorResponse(response, "修改活跃时间失败");
		}
		this.writeSuccessResponse(response);

	}

}
