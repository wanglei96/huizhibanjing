package strosoft.app.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.model.ServiceMonitor;

/*
 *清空服务状态列表
 */
public class ClearServiceStatusServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException,
			ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {
		ServiceMonitor.getInstance().clearServiceStatus();
		this.writeSuccessResponse(response);
	}

}
