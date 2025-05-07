package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.data.CmsIdNameTable;
import strosoft.app.model.CmsCodeNameTableManager;
import strosoft.app.model.CmsIdNameTableManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

/*
 * 添加CmsId基础表服务处理类
 */
public class AddCmsIdNameTableServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		CmsIdNameTable newCmsIdNameTable = this.getEntity(jData);
		int newID = CmsIdNameTableManager.getInstance().add(tableName,
				newCmsIdNameTable);
		this.writeSuccessResponse(response, newID);
	}

	/*
	 * 取得实体
	 */
	private CmsIdNameTable getEntity(JSONObject jData) throws Exception {
		int cmsSiteId = JsonHelper.getInt(jData, "cmsSiteId");
		String tableName = JsonHelper.getString(jData, "tableName");
		CmsIdNameTable newCmsIdNameTable = new CmsIdNameTable();
		// 名称
		newCmsIdNameTable.setName(JsonHelper.getString(jData, "name"));
		// 描述
		newCmsIdNameTable.setDescription(JsonHelper.getString(jData,
				"description"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == null || newDisplayOrder == 0) {
			newDisplayOrder = CmsCodeNameTableManager.getInstance()
					.getMaxDisplayOrder(tableName, "cms_site_id=" + cmsSiteId) + 1;
		}
		newCmsIdNameTable.setDisplayOrder(newDisplayOrder);
		// 站点ID
		newCmsIdNameTable.setCmsSiteId(cmsSiteId);
		return newCmsIdNameTable;
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(
				response);
		theServiceHandlerValidator.addRequiredItem(jData, "名称", "name");
		theServiceHandlerValidator.addRequiredItem(jData, "站点Id", "cmsSiteId");
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
