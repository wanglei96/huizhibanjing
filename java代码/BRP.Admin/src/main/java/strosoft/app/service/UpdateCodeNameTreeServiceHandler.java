package strosoft.app.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.DetailPageMode;
import strosoft.app.data.CodeNameTree;
import strosoft.app.model.CodeNameTreeManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

/*
 * 更新code树型表服务处理类
 */
public class UpdateCodeNameTreeServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		CodeNameTree newCodeNameTree = this.getEntity(jData);
		int result = CodeNameTreeManager.getInstance().update(tableName,
				newCodeNameTree);
		this.writeSuccessResponse(response, result);
	}

	/*
	 * 取得实体
	 */
	private CodeNameTree getEntity(JSONObject jData) throws Exception {
		String code = JsonHelper.getString(jData, "code");
		String tableName = JsonHelper.getString(jData, "tableName");
		CodeNameTree newCodeNameTree = CodeNameTreeManager.getInstance()
				.getEntity(tableName, code);
		// 名称
		newCodeNameTree.setName(JsonHelper.getString(jData, "name"));
		// 描述
		newCodeNameTree.setDescription(JsonHelper.getString(jData,
				"description"));
		// 上级节点编码
		newCodeNameTree.setParentCode(JsonHelper.getString(jData,
				"parentCode"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder != null) {
			newCodeNameTree.setDisplayOrder(newDisplayOrder);
		}
		return newCodeNameTree;
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
		String code = JsonHelper.getString(jData, "code");
		HashMap<String, Object> hmPrimaryKeys = new HashMap<String, Object>();
		hmPrimaryKeys.put("code", code);
		theServiceHandlerValidator.addUniqueItem(jData, "编码", "code",
				tableName, "code", hmPrimaryKeys);
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
