package strosoft.app.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.MyBatisManager;
import strosoft.app.util.JsonHelper;

import net.sf.json.JSONObject;


/*
 * 取得所有列服务处理类
 */
public class GetAllColumnsServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			InstantiationException, IllegalAccessException, Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		ArrayList<LinkedHashMap<String, Object>> lstColumns = MyBatisManager
				.getInstance().getAllColumns(tableName);
		this.writeSuccessResponse(response, lstColumns);
	}

}
