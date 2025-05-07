package strosoft.sys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysBasicTable;
import strosoft.sys.model.SysBasicTableFieldManager;
import strosoft.sys.model.SysBasicTableManager;

public class AddCustomTableFieldFromTableServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = jData.getString("tableName");
		String tableSchema = jData.getString("tableSchema");
		String customTable = jData.getString("customTable");
		SysBasicTableFieldManager.getInstance().addCustomFieldFromTable(tableName, tableSchema, customTable);
		this.writeSuccessResponse(response);
	}
}
