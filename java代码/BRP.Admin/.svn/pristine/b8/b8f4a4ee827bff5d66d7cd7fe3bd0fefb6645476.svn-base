package strosoft.sys.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysBasicTableRelation;
import strosoft.sys.model.SysBasicTableRelationManager;

import net.sf.json.JSONObject;


/*
 * 删除基础表关联表
 */
public class DeleteSysBasicTableRelationServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			InstantiationException, IllegalAccessException, Exception {

		SysBasicTableRelation theSysBasicTableRelation = (SysBasicTableRelation) ServiceHandlerHelper
				.getDataEntity(request, getConfigNode(), null, false);
		SysBasicTableRelationManager.getInstance().delete(theSysBasicTableRelation);
		this.writeSuccessResponse(response);

	}
}
