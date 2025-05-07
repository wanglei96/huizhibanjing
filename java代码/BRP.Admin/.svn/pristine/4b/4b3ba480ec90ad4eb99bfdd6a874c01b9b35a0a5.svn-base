package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.sys.model.PermissionManager;

public class ResetAdminPermissionsServiceHandler extends ServiceHandler{
	@SuppressWarnings("rawtypes")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PermissionManager.getInstance().resetAdminPermissions();

		PermissionManager.getInstance().resetAllCompanyAdminPermissions();
		this.writeSuccessResponse(response);

	}
}
