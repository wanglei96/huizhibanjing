package strosoft.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import strosoft.app.service.ServiceHandler;
import strosoft.sso.model.ActiveUserManager;
/**
 * 处理踢出用户请求
 * @author administrator
 *
 */
public class AddApplicationServiceHandler extends ServiceHandler{
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String applicationId = request.getParameter("applicationId");
			Boolean duplicateLogin = Boolean.parseBoolean(request.getParameter("duplicateLogin"));
			Integer userSessionOverTime = Integer.parseInt(request.getParameter("userSessionOverTime"));
			ActiveUserManager.getInstance().addApplication(applicationId, duplicateLogin, userSessionOverTime);
		} catch(Exception e){
			this.writeErrorResponse(response, "添加应用或设置应用配置信息失败");
			return ;
		}
		this.writeSuccessResponse(response);

	}
}
