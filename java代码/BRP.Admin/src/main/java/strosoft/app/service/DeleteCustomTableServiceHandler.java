package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.model.CustomTableTableManager;
import strosoft.app.util.*;
import net.sf.json.JSONObject;

/*
 * 删除服务处理类
 */
public class DeleteCustomTableServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取request参数字符串
		String data = request.getParameter("data");
		//将参数字符串转为JSONObject类型
		JSONObject jData = JSONObject.fromObject(data);
		//获取表名
		String tableName = JsonHelper.getString(jData, "tableName");
		//获取主键值
		String primaryKey = JsonHelper.getString(jData, "primaryKey");
		CustomTableTableManager.getInstance().delete(tableName,primaryKey);
		this.writeSuccessResponse(response);

	}
}
