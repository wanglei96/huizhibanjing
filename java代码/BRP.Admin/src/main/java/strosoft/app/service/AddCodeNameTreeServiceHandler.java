package strosoft.app.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import strosoft.app.common.EncryptManager;
import strosoft.app.data.CodeNameTree;
import strosoft.app.model.CodeNameTableManager;
import strosoft.app.model.CodeNameTreeManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

import strosoft.sys.data.*;

/*
 * 添加Code树型表服务处理类
 */
public class AddCodeNameTreeServiceHandler extends ServiceHandler {

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
		int newID = CodeNameTreeManager.getInstance().add(tableName,
				newCodeNameTree);
		this.writeSuccessResponse(response, newID);

	}

	/*
	 * 取得实体
	 */
	private CodeNameTree getEntity(JSONObject jData) throws Exception {
		String tableName = JsonHelper.getString(jData, "tableName");
		CodeNameTree newCodeNameTree = new CodeNameTree();
		// 编码
		newCodeNameTree.setCode(JsonHelper.getString(jData, "code"));
		// 名称
		newCodeNameTree.setName(JsonHelper.getString(jData, "name"));
		// 父节点编码
		String parentCode = JsonHelper.getString(jData, "parentCode");
		newCodeNameTree.setParentCode(parentCode);
		// 描述
		newCodeNameTree.setDescription(JsonHelper.getString(jData,
				"description"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == null) {
			String displayOrderCondition = this
					.getDisplayOrderCondition(parentCode);
			newDisplayOrder = CodeNameTreeManager.getInstance()
					.getMaxDisplayOrder(tableName, displayOrderCondition) + 1;
		}		
		newCodeNameTree.setDisplayOrder(newDisplayOrder);
		return newCodeNameTree;
	}

	/*
	 * 取得显示顺序条件
	 */
	private String getDisplayOrderCondition(String parentCode) {
		if (parentCode == null) {
			parentCode = "";
		}
		return String.format("ifnull(parent_code,'')='%s'", parentCode);
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
