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
import strosoft.app.data.CmsCodeNameTree;
import strosoft.app.model.CodeNameTableManager;
import strosoft.app.model.CmsCodeNameTreeManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

import strosoft.sys.data.*;

/*
 * 添加CmsCode树型表服务处理类
 */
public class AddCmsCodeNameTreeServiceHandler extends ServiceHandler {

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
		int newID = CmsCodeNameTreeManager.getInstance().add(tableName,
				newCmsCodeNameTree);
		this.writeSuccessResponse(response, newID);

	}

	/*
	 * 取得实体
	 */
	private CmsCodeNameTree getEntity(JSONObject jData) throws Exception {
		CmsCodeNameTree newCmsCodeNameTree = new CmsCodeNameTree();
		int cmsSiteId = JsonHelper.getInt(jData, "cmsSiteId");
		// 站点id
		newCmsCodeNameTree.setCmsSiteId(cmsSiteId);
		// 编码
		newCmsCodeNameTree.setCode(JsonHelper.getString(jData, "code"));
		// 名称
		newCmsCodeNameTree.setName(JsonHelper.getString(jData, "name"));
		// 父节点编码
		String parentCode = JsonHelper.getString(jData, "parentCode");
		newCmsCodeNameTree.setParentCode(parentCode);
		// 描述
		newCmsCodeNameTree.setDescription(JsonHelper.getString(jData,
				"description"));
		// 显示顺序
		String tableName = JsonHelper.getString(jData, "tableName");
		Integer newDisplayOrder = JsonHelper.getInt(jData, "displayOrder");
		if (newDisplayOrder == null) {
			String displayOrderCondition = this.getDisplayOrderCondition(
					parentCode, cmsSiteId);
			newDisplayOrder = CmsCodeNameTreeManager.getInstance()
					.getMaxDisplayOrder(tableName, displayOrderCondition) + 1;
		}
		newCmsCodeNameTree.setDisplayOrder(newDisplayOrder);
		return newCmsCodeNameTree;
	}

	/*
	 * 取得显示顺序条件
	 */
	private String getDisplayOrderCondition(String parentCode, int cmsSiteId) {
		if (parentCode == null) {
			parentCode = "";
		}
		return String.format(
				"ifnull(parent_code,'')='%s' and cms_site_id = %s", parentCode,
				cmsSiteId);
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
