package strosoft.app.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import strosoft.app.common.DetailPageMode;
import strosoft.app.common.EncryptManager;
import strosoft.app.data.CmsCodeNameTable;
import strosoft.app.model.CmsCodeNameTableManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

import strosoft.sys.data.*;

/*
 * 添加CmsCode基础表服务处理类
 */
public class AddCmsCodeNameTableServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		CmsCodeNameTable newCmsCodeNameTable = this.getEntity(jData);
		int newID = CmsCodeNameTableManager.getInstance().add(tableName,
				newCmsCodeNameTable);
		this.writeSuccessResponse(response, newID);

	}

	/*
	 * 取得实体
	 */
	private CmsCodeNameTable getEntity(JSONObject jData) throws Exception {
		int cmsSiteId = JsonHelper.getInt(jData, "cmsSiteId");
		String tableName = JsonHelper.getString(jData, "tableName");
		CmsCodeNameTable newCmsCodeNameTable = new CmsCodeNameTable();
		// 编码
		newCmsCodeNameTable.setCode(JsonHelper.getString(jData, "code"));
		// 名称
		newCmsCodeNameTable.setName(JsonHelper.getString(jData, "name"));
		// 描述
		newCmsCodeNameTable.setDescription(JsonHelper.getString(jData,
				"description"));
		// 显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == null) {
			newDisplayOrder = CmsCodeNameTableManager.getInstance()
					.getMaxDisplayOrder(tableName, "cms_site_id=" + cmsSiteId) + 1;
		}
		newCmsCodeNameTable.setDisplayOrder(newDisplayOrder);
		// 站点ID
		newCmsCodeNameTable.setCmsSiteId(cmsSiteId);
		return newCmsCodeNameTable;
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int cmsSiteId = JsonHelper.getInt(jData, "cmsSiteId");
		String code = JsonHelper.getString(jData, "code");
		String tableName = JsonHelper.getString(jData, "tableName");
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(
				response, DetailPageMode.Add);
		theServiceHandlerValidator.addRequiredItem(jData, "编码", "code");
		theServiceHandlerValidator.addRequiredItem(jData, "名称", "name");
		theServiceHandlerValidator.addRequiredItem(jData, "站点Id", "cmsSiteId");
		HashMap<String, Object> hmPrimaryKey = new HashMap<String, Object>();
		hmPrimaryKey.put("code", code);
		hmPrimaryKey.put("cms_site_id", cmsSiteId);
		theServiceHandlerValidator.addUniqueItem(jData, "编码", "code",
				tableName, "code", "cms_site_id=" + cmsSiteId, hmPrimaryKey);
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
