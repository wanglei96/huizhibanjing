package strosoft.app.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.MyBatisManager;
import strosoft.app.util.ArrayListHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.ResultSetHelper;


import net.sf.json.JSONObject;

/*
 * 取得数据表
 */
public class GetCountServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");

		JSONObject jData = JSONObject.fromObject(data);
		
		String tableName = JsonHelper.getString(jData, "table_name",
				"tableName", "TableName");
		if (tableName == null) {
			this.writeErrorResponse(response, "表名不能为空！");
			return;
		}
		String condition = JsonHelper
				.getString(jData, "condition", "Condition");
		String sqlWhere = "";
		if (condition != null && !condition .equals("")) {
			sqlWhere = " where " + condition;
		}
		String sql = String.format("select count(*) from %s%s", 
				tableName, sqlWhere);
		
		/*System.out.println(sql);
		String resultData = DbHelper.executeJson(sql);
		System.out.println("rs的数据："+resultData);*/
		int count = MyBatisManager.getInstance().executeInt(sql);		
		this.writeSuccessResponse(response, count);

	}

}
