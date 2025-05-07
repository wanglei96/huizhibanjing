package strosoft.sys.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysBasicTable;
import strosoft.sys.model.SysBasicTableManager;

public class AddSysBasicTableServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		SysBasicTable newSysBasicTable = this.getEntity(jData);

		String sqlCreateTable = SysBasicTableManager.getInstance()
				.getCreateTableSql(newSysBasicTable);
		MyBatisManager.getInstance().executeUpdate(sqlCreateTable);
		int newID = SysBasicTableManager.getInstance().add(newSysBasicTable);
		this.writeSuccessResponse(response, newID);

	}

	/*
	 * 把JSON对象封装成实体 取得实体
	 */
	private SysBasicTable getEntity(JSONObject jData)
			throws ClassNotFoundException, SQLException {
		// String tableName = JsonHelper.getString(jData, "tableName");

		SysBasicTable newSysBasicTable = new SysBasicTable();
		newSysBasicTable.setTableName(JsonHelper.getString(jData, "tableName"));
		newSysBasicTable.setTitle(JsonHelper.getString(jData, "title"));
		newSysBasicTable.setSysBasicTableTypeCode(JsonHelper.getString(jData,
				"sysBasicTableTypeCode"));
		newSysBasicTable.setDescription(JsonHelper.getString(jData,
				"description"));
		return newSysBasicTable;
	}
}
