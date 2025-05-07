package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.data.CompanyIdNameTree;
import strosoft.app.model.CmsCodeNameTableManager;
import strosoft.app.model.CompanyIdNameTreeManager;
import strosoft.app.util.JsonHelper;

/*
 * 添加CompanyId树型表服务处理类
 */
public class AddCompanyIdNameTreeServiceHandler extends ServiceHandler {

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
		int newID = CompanyIdNameTreeManager.getInstance().add(tableName, newCompanyIdNameTree);
		this.writeSuccessResponse(response, newID);

	}

	/*
	 * 取得实体
	 */
	private CompanyIdNameTree getEntity(JSONObject jData) throws Exception {
		CompanyIdNameTree newCompanyIdNameTree = new CompanyIdNameTree();
		// 站点ID
		int companyId = JsonHelper.getInt(jData, "companyId");
		newCompanyIdNameTree.setCompanyId(companyId);
		// 名称
		newCompanyIdNameTree.setName(JsonHelper.getString(jData, "name"));
		// 描述
		newCompanyIdNameTree.setDescription(JsonHelper.getString(jData, "description"));
		// 父节点id
		Integer parentId = JsonHelper.getInt(jData, "parentId");
		newCompanyIdNameTree.setParentId(parentId);
		// 显示顺序
		String displayOrderCondition = String.format("ifnull(parent_id,0)=%s and company_id=%s", parentId, companyId);
		String tableName = JsonHelper.getString(jData, "tableName");
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == 0) {
			newDisplayOrder = CmsCodeNameTableManager.getInstance().getMaxDisplayOrder(tableName, displayOrderCondition)
					+ 1;
		}
		newCompanyIdNameTree.setDisplayOrder(newDisplayOrder);
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
		theServiceHandlerValidator.addRequiredItem(jData, "站点Id", "companyId");
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
