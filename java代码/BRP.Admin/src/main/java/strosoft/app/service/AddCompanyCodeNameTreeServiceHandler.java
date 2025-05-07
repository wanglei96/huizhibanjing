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
 * 添加CompanyCode树型表服务处理类
 */
public class AddCompanyCodeNameTreeServiceHandler extends ServiceHandler {

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
		int newID = CompanyCodeNameTreeManager.getInstance().add(tableName, newCompanyCodeNameTree);
		this.writeSuccessResponse(response, newID);

	}

	/*
	 * 取得实体
	 */
	private CompanyCodeNameTree getEntity(JSONObject jData) throws Exception {
		CompanyCodeNameTree newCompanyCodeNameTree = new CompanyCodeNameTree();
		int companyId = JsonHelper.getInt(jData, "companyId");
		// 站点id
		newCompanyCodeNameTree.setCompanyId(companyId);
		// 编码
		newCompanyCodeNameTree.setCode(JsonHelper.getString(jData, "code"));
		// 名称
		newCompanyCodeNameTree.setName(JsonHelper.getString(jData, "name"));
		// 父节点编码
		String parentCode = JsonHelper.getString(jData, "parentCode");
		newCompanyCodeNameTree.setParentCode(parentCode);
		// 描述
		newCompanyCodeNameTree.setDescription(JsonHelper.getString(jData, "description"));
		// 显示顺序
		String tableName = JsonHelper.getString(jData, "tableName");
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == null) {
			String displayOrderCondition = this.getDisplayOrderCondition(parentCode, companyId);
			newDisplayOrder = CompanyCodeNameTreeManager.getInstance().getMaxDisplayOrder(tableName,
					displayOrderCondition) + 1;
		}
		newCompanyCodeNameTree.setDisplayOrder(newDisplayOrder);
		return newCompanyCodeNameTree;
	}

	/*
	 * 取得显示顺序条件
	 */
	private String getDisplayOrderCondition(String parentCode, int companyId) {
		if (parentCode == null) {
			parentCode = "";
		}
		return String.format("ifnull(parent_code,'')='%s' and company_id = %s", parentCode, companyId);
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
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(response, DetailPageMode.Add);
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
