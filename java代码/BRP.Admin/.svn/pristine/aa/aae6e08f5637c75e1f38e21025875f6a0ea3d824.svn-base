package strosoft.sys.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.TableDataMaster;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysBasicTable;
import strosoft.sys.model.SysBasicTableManager;

/*
 * 取得基础表脚本
 */
public class GetSysBasicTableSqlServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int id = JsonHelper.getInt(jData, "id");
		String sql = SysBasicTableManager.getInstance().createSql(id);
		this.writeSuccessResponse(response, sql);
	}
}
