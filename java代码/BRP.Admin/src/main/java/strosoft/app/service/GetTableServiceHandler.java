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
public class GetTableServiceHandler extends ServiceHandler {

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
		String orderBy = JsonHelper.getString(jData, "order_by", "orderBy",
				"OrderBy");
		String fields = JsonHelper.getString(jData, "fields", "Fields");
		String groupBy = JsonHelper.getString(jData, "group_by", "groupBy",
				"GroupBy");

		String pageIndex = JsonHelper.getString(jData, "page_index",
				"pageIndex", "PageIndex");
		String pageSize = JsonHelper.getString(jData, "page_size", "pageSize",
				"PageSize");
		String sqlFields = "*";
		if (fields != null && !fields.equals("")) {
			sqlFields = fields;
		}
		String sqlWhere = "";
		if (condition != null && !condition .equals("")) {
			sqlWhere = " where " + condition;
		}
		String sqlGroupBy = "";
		if (groupBy != null && !groupBy .equals("")) {
			sqlGroupBy = " group by  " + groupBy;
		}
		String sqlOrderBy = "";
		if (orderBy != null && !orderBy .equals("")) {
			sqlOrderBy = " order by  " + orderBy;
		}
		String sqlLimit = "";
		if (pageIndex != null && !pageIndex .equals("")) {
			sqlLimit = " limit "
					+ String.valueOf(Integer.parseInt(pageIndex)
							* Integer.parseInt(pageSize)) + "," + pageSize;
		}
		String sql = String.format("select %s from %s%s%s%s%s", sqlFields,
				tableName, sqlWhere, sqlGroupBy, sqlOrderBy, sqlLimit);
		
		/*System.out.println(sql);
		String resultData = DbHelper.executeJson(sql);
		System.out.println("rs的数据："+resultData);*/
		ArrayList<LinkedHashMap<String,Object>> executeHashMapList = MyBatisManager.getInstance().executeHashMapList(sql);		
		this.writeSuccessResponse(response, executeHashMapList);

	}

}
