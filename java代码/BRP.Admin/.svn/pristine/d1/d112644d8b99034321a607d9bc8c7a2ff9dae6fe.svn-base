package strosoft.app.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import strosoft.app.model.ServiceMonitor;

/*
 * 取得服务状态列表
 */
public class GetServiceStatusListServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException,
			ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {
		JSONArray jarrServiceStatus = ServiceMonitor.getInstance().getServiceStatusList();
		this.writeSuccessResponse(response, jarrServiceStatus);
	}

}
