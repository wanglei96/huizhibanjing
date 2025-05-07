package strosoft.app.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import strosoft.app.common.EncryptManager;
import strosoft.app.data.IdNameTree;
import strosoft.app.model.IdNameTreeManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

import strosoft.sys.data.*;

/*
 * 更新id树型表服务处理类
 */
public class UpdateIdNameTreeServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		IdNameTree newIdNameTree = this.getEntity(jData);
		int result = IdNameTreeManager.getInstance().update(tableName,
				newIdNameTree);
		this.writeSuccessResponse(response, result);
	}

	/*
	 * 取得实体
	 */
	private IdNameTree getEntity(JSONObject jData) throws Exception {
		int id = JsonHelper.getInt(jData, "id");
		String tableName = JsonHelper.getString(jData, "tableName");
		IdNameTree newIdNameTree = IdNameTreeManager.getInstance().getEntity(
				tableName, id);
		// 名称
		newIdNameTree.setName(JsonHelper.getString(jData, "name"));
		// 描述
		newIdNameTree
				.setDescription(JsonHelper.getString(jData, "description"));
		// 父节点ID
		Integer parentId = JsonHelper.getInt(jData, "parentId");
		newIdNameTree.setParentId(parentId);
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder != null) {
			newIdNameTree.setDisplayOrder(newDisplayOrder);
		}
		return newIdNameTree;
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
