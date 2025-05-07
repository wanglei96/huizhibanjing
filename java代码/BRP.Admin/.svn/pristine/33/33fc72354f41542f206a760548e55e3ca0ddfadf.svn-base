package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.model.SysUserManager;
import net.sf.json.JSONObject;

/*
 * 保存用户角色服务处理类
 */
public class SetSysUserEnabledServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jData = this.getRequestData(request);
		int sysUserId = JsonHelper.getInt(jData, "id", "primaryKey");
		Boolean enabled = JsonHelper.getBoolean(jData, "enabled");
		SysUserManager.getInstance().setSysUserEnabled(sysUserId, enabled);
		this.writeSuccessResponse(response);
	}
}
