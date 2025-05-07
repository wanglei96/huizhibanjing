package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import strosoft.app.common.MyBatisManager;
import strosoft.app.util.JsonHelper;

/*
 * 执行命令服务处理类
 */
public class ExecuteCommandServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String commandId = JsonHelper.getString(jData, "commandId");
		String commandType = JsonHelper.getString(jData, "commandType");
		JSONObject jParameters = jData.getJSONObject("parameters");
		int result = MyBatisManager.getInstance().executeCommand(commandId,
				commandType, jParameters);
		this.writeSuccessResponse(response, result);
	}
}
