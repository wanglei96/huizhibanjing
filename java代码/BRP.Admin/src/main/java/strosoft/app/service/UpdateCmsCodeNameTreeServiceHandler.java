package strosoft.app.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.DetailPageMode;
import strosoft.app.data.CmsCodeNameTree;
import strosoft.app.model.CmsCodeNameTreeManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

/*
 * 更新CmsCode树型表服务处理类
 */
public class UpdateCmsCodeNameTreeServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		CmsCodeNameTree newCmsCodeNameTree = this.getEntity(jData);
		int result = CmsCodeNameTreeManager.getInstance().update(tableName,
				newCmsCodeNameTree);
		this.writeSuccessResponse(response, result);
	}

	/*
	 * 取得实体
	 */
	private CmsCodeNameTree getEntity(JSONObject jData) throws Exception {
		// 编码
		String code = JsonHelper.getString(jData, "code");
		// 表名
		String tableName = JsonHelper.getString(jData, "tableName");
		// 站点id
		int cmsSiteId = JsonHelper.getInt(jData, "cmsSiteId");
		CmsCodeNameTree newCmsCodeNameTree = CmsCodeNameTreeManager
				.getInstance().getEntity(cmsSiteId, tableName, code);
		// 名称
		newCmsCodeNameTree.setName(JsonHelper.getString(jData, "name"));
		// 父节点编码
		newCmsCodeNameTree.setParentCode(JsonHelper.getString(jData,
				"parentCode"));
		// 描述
		newCmsCodeNameTree.setDescription(JsonHelper.getString(jData,
				"description"));
		//显示顺序
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder != null) {
			newCmsCodeNameTree.setDisplayOrder(newDisplayOrder);
		}
		newCmsCodeNameTree.setCmsSiteId(cmsSiteId);
		return newCmsCodeNameTree;
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
				response, DetailPageMode.Edit);
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
