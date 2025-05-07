package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.data.CmsIdNameTree;
import strosoft.app.model.CmsCodeNameTableManager;
import strosoft.app.model.CmsIdNameTreeManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

/*
 * 添加CmsId树型表服务处理类
 */
public class AddCmsIdNameTreeServiceHandler extends ServiceHandler {

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
		int newID = CmsIdNameTreeManager.getInstance().add(tableName,
				newCmsIdNameTree);
		this.writeSuccessResponse(response, newID);

	}

	/*
	 * 取得实体
	 */
	private CmsIdNameTree getEntity(JSONObject jData) throws Exception {
		CmsIdNameTree newCmsIdNameTree = new CmsIdNameTree();
		// 站点ID
		int cmsSiteId = JsonHelper.getInt(jData, "cmsSiteId");
		newCmsIdNameTree.setCmsSiteId(cmsSiteId);
		// 名称
		newCmsIdNameTree.setName(JsonHelper.getString(jData, "name"));
		// 描述
		newCmsIdNameTree.setDescription(JsonHelper.getString(jData,
				"description"));
		// 父节点id
		Integer parentId = JsonHelper.getInt(jData, "parentId");
		newCmsIdNameTree.setParentId(parentId);
		// 显示顺序
		String displayOrderCondition = String.format(
				"ifnull(parent_id,0)=%s and cms_site_id=%s", parentId,
				cmsSiteId);
		String tableName = JsonHelper.getString(jData, "tableName");
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == 0) {
			newDisplayOrder = CmsCodeNameTableManager.getInstance()
					.getMaxDisplayOrder(tableName, displayOrderCondition) + 1;
		}
		newCmsIdNameTree.setDisplayOrder(newDisplayOrder);
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
		theServiceHandlerValidator.addRequiredItem(jData, "站点Id", "cmsSiteId");
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
