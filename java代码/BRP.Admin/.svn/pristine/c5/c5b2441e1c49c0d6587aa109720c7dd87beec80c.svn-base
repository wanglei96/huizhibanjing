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
public class KickOutActiveUserServiceHandler extends ServiceHandler{
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String userSessionListStr = request.getParameter("userSessionList");
			JSONArray userSessionList = JSONArray.fromObject(userSessionListStr);
			for(int i=0;i<userSessionList.size();i++){
				JSONObject current = userSessionList.getJSONObject(i);
				String applicationId = current.getString("applicationId");
				String accountId = current.getString("accountId");
				String activeUserSessionID = current.getString("token");
				ActiveUserManager.getInstance().kickOutUser(applicationId,accountId, activeUserSessionID);
				ActiveUserManager.getInstance().CheckSelf();
			}
		} catch(Exception e){
			this.writeErrorResponse(response, "踢出失败");
			return ;
		}
		this.writeSuccessResponse(response);

	}
}
