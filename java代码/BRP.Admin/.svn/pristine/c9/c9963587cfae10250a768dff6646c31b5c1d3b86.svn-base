package strosoft.sys.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysRolePermission;
import strosoft.sys.model.SysRolePermissionManager;

import net.sf.json.JSONObject;

public class GetSysRolePermissionListBySysRoleIdServiceHandler extends ServiceHandler{
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			NoSuchAlgorithmException {
		JSONObject jData = this.getRequestData(request);
		String sysRoleId = JsonHelper.getString(jData, "sysRoleId");
		ArrayList<SysRolePermission>  lstSysRolePermission = SysRolePermissionManager.getInstance().getListBySysRoleId(sysRoleId);
		this.writeSuccessResponse(response,lstSysRolePermission);

	}
}
