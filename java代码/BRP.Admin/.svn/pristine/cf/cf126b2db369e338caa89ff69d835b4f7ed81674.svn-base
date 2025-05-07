package strosoft.sys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.model.SysBasicTableManager;

/*
 * 取得基础表脚本
 */
public class GetSysBasicTableSqlsServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		List<Integer> lstId = JsonHelper.getIntArray(jData, "ids");
		String sql = SysBasicTableManager.getInstance().createSql(lstId);
		this.writeSuccessResponse(response, sql);

	}
}
