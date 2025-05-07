package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.data.IdNameTree;
import strosoft.app.model.IdNameTreeManager;
import strosoft.app.util.JsonHelper;

/*
 * 添加Id树型表服务处理类
 */
public class AddIdNameTreeServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		IdNameTree newIdNameTree = this.getEntity(jData);
		int newID = IdNameTreeManager.getInstance().add(tableName, newIdNameTree);

		this.writeSuccessResponse(response, newID);
	}

	/*
	 * 取得实体
	 */
	private IdNameTree getEntity(JSONObject jData) throws Exception {
		String tableName = JsonHelper.getString(jData, "tableName");
		IdNameTree newIdNameTree = new IdNameTree();
		// 名称
		newIdNameTree.setName(JsonHelper.getString(jData, "name"));
		// 父节点id
		Integer parentId = JsonHelper.getInt(jData, "parentId");
		newIdNameTree.setParentId(parentId);
		// 描述
		newIdNameTree.setDescription(JsonHelper.getString(jData, "description"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == null || newDisplayOrder == 0) {
			newDisplayOrder = IdNameTreeManager.getInstance().getMaxDisplayOrder(tableName, "parent_id=" + parentId)
					+ 1;
		}
		newIdNameTree.setDisplayOrder(newDisplayOrder);
		return newIdNameTree;
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String name = JsonHelper.getString(jData, "name");
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(response);
		theServiceHandlerValidator.addRequiredItem(jData, "名称", "name");
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
