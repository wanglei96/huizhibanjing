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
import strosoft.sys.model.SysUserRoleManager;

import net.sf.json.JSONObject;

/*
 * 保存用户角色服务处理类
 */
public class SaveSysUserRolesServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jData = this.getRequestData(request);
		int sysUserId = JsonHelper.getInt(jData, "id");
		List<Integer> lstSysRoleId = JsonHelper.getIntArray(jData, "sysRoleIds");
		SysUserRoleManager.getInstance().add(sysUserId, lstSysRoleId);
		this.writeSuccessResponse(response);

	}
}
