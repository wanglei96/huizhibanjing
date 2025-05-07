package strosoft.sys.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysRolePermission;
import strosoft.sys.data.ViewSysMenuSysFunction;
import strosoft.sys.model.SysMenuManager;
import strosoft.sys.model.SysRolePermissionManager;
import strosoft.sys.model.ViewSysMenuSysFunctionManager;

/**
 * 取得指定角色的菜单列表
 * @author Admin
 *
 */
public class GetMenuListBySysRoleIdServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jData = this.getRequestData(request);
		//角色ID
		Integer sysRoleId = JsonHelper.getInt(jData, "sysRoleId");
		//菜单类型
		String sysMenuTypeCode = JsonHelper.getString(jData, "sysMenuTypeCode");
		JSONObject jResult = SysMenuManager.getInstance().getListBySysRoleIdAndSysMenuTypeCode(sysRoleId, sysMenuTypeCode);
		this.writeSuccessResponse(response, jResult);
	}
}
