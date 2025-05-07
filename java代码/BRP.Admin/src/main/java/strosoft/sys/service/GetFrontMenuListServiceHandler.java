package strosoft.sys.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.EncryptManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysMenu;
import strosoft.sys.model.SysMenuManager;
import strosoft.sys.model.SysUserManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 取得用户菜单列表
 */
public class GetFrontMenuListServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			NoSuchAlgorithmException {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int sysUserId = JsonHelper.getInt(jData, "sysUserId", "SysUserId",
				"sys_user_id");
		if (sysUserId == 0) {
			this.writeErrorResponse(response, "用户ID不能为空！");
			return;
		}
		ArrayList<SysMenu> lstSysMenu = SysMenuManager.getInstance()
				.getFrontListBySysUserId(sysUserId);
	/*	JSONArray json =JSONArray.fromObject(lstSysMenu);
		System.out.println(json.toString());*/
		this.writeSuccessResponse(response, lstSysMenu);

	}
}
