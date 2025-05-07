package BRP.service;

import BRP.model.CompanyUserRoleManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 取得用户拥有的角色列表
 * @author Admin
 *
 */
public class GetCompanyRoleListByCompanyUserIdServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		// 获取用户ID
		int companyUserId = jData.getInt("userId");
		int companyId = jData.getInt("companyId");
		JSONObject jResult = CompanyUserRoleManager.getInstance().getJSONArrayByCompanyUserId(companyUserId,companyId);
		this.writeSuccessResponse(response, jResult);
	}
}
