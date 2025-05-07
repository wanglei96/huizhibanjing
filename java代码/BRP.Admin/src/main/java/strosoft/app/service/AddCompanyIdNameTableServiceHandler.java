package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.data.CompanyIdNameTable;
import strosoft.app.model.CompanyCodeNameTableManager;
import strosoft.app.model.CompanyIdNameTableManager;
import strosoft.app.util.JsonHelper;

/*
 * 添加CompanyId基础表服务处理类
 */
public class AddCompanyIdNameTableServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		CompanyIdNameTable newCompanyIdNameTable = this.getEntity(jData);
		int newID = CompanyIdNameTableManager.getInstance().add(tableName, newCompanyIdNameTable);
		this.writeSuccessResponse(response, newID);
	}

	/*
	 * 取得实体
	 */
	private CompanyIdNameTable getEntity(JSONObject jData) throws Exception {
		int companyId = JsonHelper.getInt(jData, "companyId");
		String tableName = JsonHelper.getString(jData, "tableName");
		CompanyIdNameTable newCompanyIdNameTable = new CompanyIdNameTable();
		// 名称
		newCompanyIdNameTable.setName(JsonHelper.getString(jData, "name"));
		// 描述
		newCompanyIdNameTable.setDescription(JsonHelper.getString(jData, "description"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == null || newDisplayOrder == 0) {
			newDisplayOrder = CompanyCodeNameTableManager.getInstance().getMaxDisplayOrder(tableName,
					"company_id=" + companyId) + 1;
		}
		newCompanyIdNameTable.setDisplayOrder(newDisplayOrder);
		// 站点ID
		newCompanyIdNameTable.setCompanyId(companyId);
		return newCompanyIdNameTable;
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(response);
		theServiceHandlerValidator.addRequiredItem(jData, "名称", "name");
		theServiceHandlerValidator.addRequiredItem(jData, "站点Id", "companyId");
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
