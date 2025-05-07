package strosoft.sys.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.TableDataMaster;
import strosoft.app.common.EncryptManager;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysUser;
import strosoft.sys.model.SysUserManager;

import net.sf.json.JSONObject;

/**
 * 修改系统用户密码
 * @author Admin
 *
 */
public class ChangeSysUserPasswordServiceHandler extends ServiceHandler {
	@SuppressWarnings("rawtypes")
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jData = this.getRequestData(request);

		int sysUserId = JsonHelper.getInt(jData, "sysUserId");
		String oldPassword = JsonHelper.getString(jData, "oldPassword");
		String newPassword = JsonHelper.getString(jData, "password");

		SysUser theSysUser = SysUserManager.getInstance().getEntity(sysUserId);
		// 检查原密码是不是正确
		Boolean isValid = EncryptManager.checkPassword(oldPassword, theSysUser.getPassword());
		if (!isValid) {
			this.writeErrorResponse(response, String.format("原密码不正确！"));
			return;
		}
		// 执行sql
		SysUserManager.getInstance().setSysUserPassword(sysUserId, newPassword);
		this.writeSuccessResponse(response);

	}
}
