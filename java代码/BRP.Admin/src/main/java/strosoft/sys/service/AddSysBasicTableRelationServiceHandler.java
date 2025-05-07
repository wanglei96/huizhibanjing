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
import strosoft.sys.data.SysBasicTableRelation;
import strosoft.sys.model.SysBasicTableRelationManager;

public class AddSysBasicTableRelationServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		SysBasicTableRelation newSysBasicTableRelation = this.getEntity(jData);

		String[] sqlCreateRelation = SysBasicTableRelationManager.getInstance()
				.getCreateRelationSql(newSysBasicTableRelation);
		MyBatisManager.getInstance().executeUpdate(sqlCreateRelation);
		SysBasicTableRelationManager.getInstance().add(
				newSysBasicTableRelation);
		this.writeSuccessResponse(response, newSysBasicTableRelation);

	}

	/*
	 * 取得实体
	 */
	private SysBasicTableRelation getEntity(JSONObject jData)
			throws ClassNotFoundException, SQLException {
		SysBasicTableRelation newSysBasicTableRelation = new SysBasicTableRelation();
		newSysBasicTableRelation.setBasicTableName(JsonHelper.getString(jData,
				"basicTableName"));
		newSysBasicTableRelation.setRelatedTableName(JsonHelper.getString(
				jData, "relatedTableName"));
		newSysBasicTableRelation.setRelatedColumnName(JsonHelper.getString(
				jData, "relatedColumnName"));
		return newSysBasicTableRelation;
	}
}
