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
import strosoft.sys.data.SysRole;
import strosoft.sys.data.SysUser;
import strosoft.sys.data.SysUserRole;
import strosoft.sys.model.SysRoleManager;
import strosoft.sys.model.SysUserManager;
import strosoft.sys.model.SysUserRoleManager;

/**
 * 取得用户拥有的角色列表
 * @author Admin
 *
 */
public class GetSysRoleListBySysUserIdServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		// 获取用户ID
		int sysUserId = jData.getInt("id");
		JSONObject jResult = SysUserRoleManager.getInstance().getJSONArrayBySysUserId(sysUserId);
		this.writeSuccessResponse(response, jResult);
	}
}
