package strosoft.app.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import strosoft.app.common.EncryptManager;
import strosoft.app.data.IdNameTable;
import strosoft.app.model.CodeNameTableManager;
import strosoft.app.model.IdNameTableManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

import strosoft.sys.data.*;

/*
 * 添加Id基础表服务处理类
 */
public class AddIdNameTableServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		IdNameTable newIdNameTable = this.getEntity(jData);
		int newID = IdNameTableManager.getInstance().add(tableName, newIdNameTable);
		this.writeSuccessResponse(response, newID);

	}

	/*
	 * 取得实体
	 */
	private IdNameTable getEntity(JSONObject jData)
			throws Exception {
		String tableName = JsonHelper.getString(jData, "tableName");
		IdNameTable newIdNameTable = new IdNameTable();
		//名称
		newIdNameTable.setName(JsonHelper.getString(jData, "name"));
		//描述
		newIdNameTable.setDescription(JsonHelper.getString(jData,
				"description"));
		//显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == null || newDisplayOrder == 0) {
			newDisplayOrder = CodeNameTableManager.getInstance()
					.getMaxDisplayOrder(tableName) + 1;
		}
		newIdNameTable.setDisplayOrder(newDisplayOrder);
		// 是否启用
		Boolean enabled = JsonHelper.getBoolean(jData, "enabled");
		newIdNameTable.setEnabled(enabled);
		return newIdNameTable;
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String name = JsonHelper.getString(jData, "name");
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(
				response);
		theServiceHandlerValidator.addRequiredItem(jData, "名称", "name");
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
