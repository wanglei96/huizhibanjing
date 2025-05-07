package BRP.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BRP.model.CompanyUserManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import net.sf.json.JSONObject;

/*
 * 保存用户角色服务处理类
 */
public class SetCompanyUserEnabledServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jData = this.getRequestData(request);
		int companyUserId = JsonHelper.getInt(jData, "id", "primaryKey");
		Boolean enabled = JsonHelper.getBoolean(jData, "enabled");
		CompanyUserManager.getInstance().setCompanyUserEnabled(companyUserId, enabled);
		this.writeSuccessResponse(response);
	}
}
