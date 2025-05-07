package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.model.ApproveManager;

/*
 * 审核通过服务处理类
 */
public class ApproveServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		String primaryKeyFieldName = JsonHelper.getString(jData, "primaryKeyFieldName");
		String primaryKeyValue = JsonHelper.getString(jData, "primaryKeyValue");
		int result = ApproveManager.getInstance().approve(tableName, primaryKeyFieldName,
				primaryKeyValue);
		this.writeSuccessResponse(response, result);
	}
}
