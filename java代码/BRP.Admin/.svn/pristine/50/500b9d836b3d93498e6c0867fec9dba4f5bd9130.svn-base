package BRP.service;

import net.sf.json.JSONObject;
import strosoft.app.common.TableDataMaster;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.JsonHelper;
import BRP.model.CompanyRolePermissionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 保存角色权限
 * @author Admin
 *
 */
public class SaveCompanyRolePermissionsServiceHandler extends ServiceHandler{
	
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DataEntity newDataEntity = ServiceHandlerHelper.getDataEntity(request,
				this.getConfigNode(), null, false);
		if (newDataEntity == null) {
			this.writeErrorResponse(response, "数据实体配置错误！");
			return;
		}
		TableDataMaster dataManager = (TableDataMaster) ServiceHandlerHelper
				.getDataManager(this.getConfigNode());
		if (dataManager == null) {
			this.writeErrorResponse(response, "数据管理类配置错误！");
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int companyRoleId =JsonHelper.getInt(jData, "companyRoleId");
		List<String> lstPermission = JsonHelper.getStringArray(jData, "permissions");
		CompanyRolePermissionManager.getInstance().add(companyRoleId, lstPermission);
		this.writeSuccessResponse(response);

	}
}
