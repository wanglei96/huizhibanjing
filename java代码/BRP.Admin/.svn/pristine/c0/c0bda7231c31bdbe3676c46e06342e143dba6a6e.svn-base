package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.data.CompanyIdNameTree;
import strosoft.app.model.CompanyIdNameTreeManager;
import strosoft.app.util.JsonHelper;

/*
 * 更新CompanyId树型表服务处理类
 */
public class UpdateCompanyIdNameTreeServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		CompanyIdNameTree newCompanyIdNameTree = this.getEntity(jData);
		int result = CompanyIdNameTreeManager.getInstance().update(tableName, newCompanyIdNameTree);
		this.writeSuccessResponse(response, result);
	}

	/*
	 * 取得实体
	 */
	private CompanyIdNameTree getEntity(JSONObject jData) throws Exception {
		// id
		int id = JsonHelper.getInt(jData, "id");
		// 表名
		String tableName = JsonHelper.getString(jData, "tableName");
		// 站点id
		int companyId = JsonHelper.getInt(jData, "companyId");
		CompanyIdNameTree newCompanyIdNameTree = CompanyIdNameTreeManager.getInstance().getEntity(tableName, id);
		// 名称
		newCompanyIdNameTree.setName(JsonHelper.getString(jData, "name"));
		// 父节点id
		newCompanyIdNameTree.setParentId(JsonHelper.getInt(jData, "parentId"));
		// 描述
		newCompanyIdNameTree.setDescription(JsonHelper.getString(jData, "description"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder != null) {
			newCompanyIdNameTree.setDisplayOrder(newDisplayOrder);
		}
		newCompanyIdNameTree.setCompanyId(companyId);
		return newCompanyIdNameTree;
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
