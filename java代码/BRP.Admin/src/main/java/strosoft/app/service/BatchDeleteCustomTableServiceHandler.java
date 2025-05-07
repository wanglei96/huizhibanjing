package strosoft.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.model.CustomTableTableManager;
import strosoft.app.util.JsonHelper;
import net.sf.json.*;

/*
 * 批量删除服务处理类
 */
public class BatchDeleteCustomTableServiceHandler extends ServiceHandler {

	@SuppressWarnings("unchecked")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取request中的请求参数字符串
		String data = request.getParameter("data");
		//将参数字符串转为JSONObject类型
		JSONObject jData = JSONObject.fromObject(data);
		//获取表名
		String tableName = jData.getString("tableName");
		//获取主键列表
		JSONArray primaryKeys = jData.getJSONArray("primaryKeys");
		List<String> primaryKeyStrList = new ArrayList<String>();
		for(int i=0;i<primaryKeys.size();i++){
			String item = primaryKeys.get(i).toString();
			primaryKeyStrList.add(item);
		}
		CustomTableTableManager.getInstance().batchDelete(tableName,primaryKeyStrList);
		this.writeSuccessResponse(response);
	}
}
