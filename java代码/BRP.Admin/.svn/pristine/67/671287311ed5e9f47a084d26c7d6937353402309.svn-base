package strosoft.app.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.DetailPageMode;
import strosoft.app.data.CompanyCodeNameTable;
import strosoft.app.model.CompanyCodeNameTableManager;
import strosoft.app.util.JsonHelper;

/*
 * 添加CompanyCode基础表服务处理类
 */
public class AddCompanyCodeNameTableServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		CompanyCodeNameTable newCompanyCodeNameTable = this.getEntity(jData);
		int newID = CompanyCodeNameTableManager.getInstance().add(tableName, newCompanyCodeNameTable);
		this.writeSuccessResponse(response, newID);

	}

	/*
	 * 取得实体
	 */
	private CompanyCodeNameTable getEntity(JSONObject jData) throws Exception {
		int companyId = JsonHelper.getInt(jData, "companyId");
		String tableName = JsonHelper.getString(jData, "tableName");
		CompanyCodeNameTable newCompanyCodeNameTable = new CompanyCodeNameTable();
		// 编码
		newCompanyCodeNameTable.setCode(JsonHelper.getString(jData, "code"));
		// 名称
		newCompanyCodeNameTable.setName(JsonHelper.getString(jData, "name"));
		// 描述
		newCompanyCodeNameTable.setDescription(JsonHelper.getString(jData, "description"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == null) {
			newDisplayOrder = CompanyCodeNameTableManager.getInstance().getMaxDisplayOrder(tableName,
					"company_id=" + companyId) + 1;
		}
		newCompanyCodeNameTable.setDisplayOrder(newDisplayOrder);
		// 站点ID
		newCompanyCodeNameTable.setCompanyId(companyId);
		return newCompanyCodeNameTable;
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
