package strosoft.app.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import strosoft.app.common.EncryptManager;
import strosoft.app.data.CmsIdNameTree;
import strosoft.app.data.CmsIdNameTree;
import strosoft.app.model.CmsIdNameTreeManager;
import strosoft.app.model.CmsIdNameTreeManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

import strosoft.sys.data.*;

/*
 * 更新id树型表服务处理类
 */
public class UpdateCmsIdNameTreeServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		CmsIdNameTree newCmsIdNameTree = this.getEntity(jData);
		int result = CmsIdNameTreeManager.getInstance().update(tableName,
				newCmsIdNameTree);
		this.writeSuccessResponse(response, result);
	}

	/*
	 * 取得实体
	 */
	private CmsIdNameTree getEntity(JSONObject jData) throws Exception {
		// id
		int id = JsonHelper.getInt(jData, "id");
		// 表名
		String tableName = JsonHelper.getString(jData, "tableName");
		// 站点id
		int cmsSiteId = JsonHelper.getInt(jData, "cmsSiteId");
		CmsIdNameTree newCmsIdNameTree = CmsIdNameTreeManager.getInstance()
				.getEntity(tableName, id);
		// 名称
		newCmsIdNameTree.setName(JsonHelper.getString(jData, "name"));
		// 父节点id
		newCmsIdNameTree.setParentId(JsonHelper.getInt(jData, "parentId"));
		// 描述
		newCmsIdNameTree.setDescription(JsonHelper.getString(jData,
				"description"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder != null) {
			newCmsIdNameTree.setDisplayOrder(newDisplayOrder);
		}
		newCmsIdNameTree.setCmsSiteId(cmsSiteId);
		return newCmsIdNameTree;
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
