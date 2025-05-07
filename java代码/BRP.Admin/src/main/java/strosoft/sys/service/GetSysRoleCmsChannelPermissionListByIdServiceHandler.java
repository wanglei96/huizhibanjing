package strosoft.sys.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysRoleCmsChannelPermission;
import strosoft.sys.data.SysRolePermission;
import strosoft.sys.model.SysRoleCmsChannelPermissionManager;
import strosoft.sys.model.SysRolePermissionManager;

import net.sf.json.JSONObject;

public class GetSysRoleCmsChannelPermissionListByIdServiceHandler extends ServiceHandler{
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			NoSuchAlgorithmException {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String sysRoleId = JsonHelper.getString(jData, "sysRoleId");
		int cmsSiteId = JsonHelper.getInt(jData, "cmsSiteId");
		ArrayList<SysRoleCmsChannelPermission>  lstSysRolePermission = SysRoleCmsChannelPermissionManager.getInstance().getListBySysRoleIdAndCmsSiteId(sysRoleId,cmsSiteId);
		this.writeSuccessResponse(response,lstSysRolePermission);

	}
}
