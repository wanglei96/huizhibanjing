package strosoft.app.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.MyBatisManager;


/*
 * 取得所有表服务处理类
 */
public class GetAllTablesServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			InstantiationException, IllegalAccessException, Exception {
		ArrayList<LinkedHashMap<String, Object>> lstTable = MyBatisManager
				.getInstance().getAllTables();
		this.writeSuccessResponse(response, lstTable);
	}

}
