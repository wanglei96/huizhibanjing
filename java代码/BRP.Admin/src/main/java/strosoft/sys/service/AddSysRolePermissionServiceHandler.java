package strosoft.sys.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.TableDataMaster;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.JsonHelper;
import strosoft.sys.model.SysRolePermissionManager;

import net.sf.json.JSONObject;

public class AddSysRolePermissionServiceHandler extends ServiceHandler{
	
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
		int sysRoleId =JsonHelper.getInt(jData, "sysRoleId");
		List<String> lstPermission = JsonHelper.getStringArray(jData, "value");
		SysRolePermissionManager.getInstance().add(sysRoleId, lstPermission);
		this.writeSuccessResponse(response);

	}
}
