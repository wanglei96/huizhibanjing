package BRP.service;

import BRP.model.CompanyUserRoleManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 * 保存用户角色服务处理类
 */
public class SaveCompanyUserRolesServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jData = this.getRequestData(request);
		int companyUserId = JsonHelper.getInt(jData, "userId");
		List<Integer> lstCompanyRoleId = JsonHelper.getIntArray(jData, "companyRoleIds");
		CompanyUserRoleManager.getInstance().add(companyUserId, lstCompanyRoleId);
		this.writeSuccessResponse(response);

	}
}
