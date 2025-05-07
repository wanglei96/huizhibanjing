package strosoft.app.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import strosoft.app.common.DetailPageMode;
import strosoft.app.data.CodeNameTable;
import strosoft.app.model.CodeNameTableManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

import strosoft.sys.data.*;

/*
 * 更新服务处理类
 */
public class UpdateCodeNameTableServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		CodeNameTable newCodeNameTable = this.getEntity(jData);
		int result = CodeNameTableManager.getInstance().update(tableName,
				newCodeNameTable);
		this.writeSuccessResponse(response, result);
	}

	/*
	 * 取得实体
	 */
	private CodeNameTable getEntity(JSONObject jData) throws Exception {
		String code = JsonHelper.getString(jData, "code");
		String tableName = JsonHelper.getString(jData, "tableName");
		CodeNameTable newCodeNameTable = CodeNameTableManager.getInstance()
				.getEntity(tableName, code);
		// 名称
		newCodeNameTable.setName(JsonHelper.getString(jData, "name"));
		// 描述
		newCodeNameTable.setDescription(JsonHelper.getString(jData,
				"description"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder != null) {
			newCodeNameTable.setDisplayOrder(newDisplayOrder);
		}
		// 是否启用
		Boolean enabled = JsonHelper.getBoolean(jData, "enabled");
		newCodeNameTable.setEnabled(enabled);
		return newCodeNameTable;
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(
				response, DetailPageMode.Edit);
		theServiceHandlerValidator.addRequiredItem(jData, "编码", "code");
		theServiceHandlerValidator.addRequiredItem(jData, "名称", "name");
		HashMap<String, Object> hmPrimaryKeys = new HashMap<String, Object>();
		String code = JsonHelper.getString(jData, "code");
		hmPrimaryKeys.put("code", code);
		theServiceHandlerValidator.addUniqueItem(jData, "编码", "code",
				tableName, "code", hmPrimaryKeys);
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
