package strosoft.app.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.DetailPageMode;
import strosoft.app.data.CompanyCodeNameTree;
import strosoft.app.model.CompanyCodeNameTreeManager;
import strosoft.app.util.JsonHelper;

/*
 * 更新CompanyCode树型表服务处理类
 */
public class UpdateCompanyCodeNameTreeServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		CompanyCodeNameTree newCompanyCodeNameTree = this.getEntity(jData);
		int result = CompanyCodeNameTreeManager.getInstance().update(tableName, newCompanyCodeNameTree);
		this.writeSuccessResponse(response, result);
	}

	/*
	 * 取得实体
	 */
	private CompanyCodeNameTree getEntity(JSONObject jData) throws Exception {
		// 编码
		String code = JsonHelper.getString(jData, "code");
		// 表名
		String tableName = JsonHelper.getString(jData, "tableName");
		// 站点id
		int companyId = JsonHelper.getInt(jData, "companyId");
		CompanyCodeNameTree newCompanyCodeNameTree = CompanyCodeNameTreeManager.getInstance().getEntity(companyId,
				tableName, code);
		// 名称
		newCompanyCodeNameTree.setName(JsonHelper.getString(jData, "name"));
		// 父节点编码
		newCompanyCodeNameTree.setParentCode(JsonHelper.getString(jData, "parentCode"));
		// 描述
		newCompanyCodeNameTree.setDescription(JsonHelper.getString(jData, "description"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder != null) {
			newCompanyCodeNameTree.setDisplayOrder(newDisplayOrder);
		}
		newCompanyCodeNameTree.setCompanyId(companyId);
		return newCompanyCodeNameTree;
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int companyId = JsonHelper.getInt(jData, "companyId");
		String code = JsonHelper.getString(jData, "code");
		String tableName = JsonHelper.getString(jData, "tableName");
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(response, DetailPageMode.Edit);
		theServiceHandlerValidator.addRequiredItem(jData, "编码", "code");
		theServiceHandlerValidator.addRequiredItem(jData, "名称", "name");
		theServiceHandlerValidator.addRequiredItem(jData, "站点Id", "companyId");
		HashMap<String, Object> hmPrimaryKey = new HashMap<String, Object>();
		hmPrimaryKey.put("code", code);
		hmPrimaryKey.put("company_id", companyId);
		theServiceHandlerValidator.addUniqueItem(jData, "编码", "code", tableName, "code", "company_id=" + companyId,
				hmPrimaryKey);
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
