package strosoft.cms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.cms.data.CmsLanguage;
import strosoft.cms.model.CmsLanguageManager;

/*
 * 获取CMS语言列表
 */
public class GetCmsLanguageListCustomServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqlSession sqlSession = null;
		try {
			String data = request.getParameter("data");
			System.out.println("data:" + data);
			JSONObject jData = JSONObject.fromObject(data);
			Integer cmsSiteId = JsonHelper.getInt(jData, "cmsSiteId");
			JSONObject result = new JSONObject();
			if (cmsSiteId == null) {
				result.put("isOnCmslaunguage", false);
				this.writeSuccessResponse(response, result);
				return;
			}
			sqlSession = MyBatisManager.getInstance().openSession();
			result.put("isOnCmslaunguage", true);
			// 查看是否设置多语言支持,获取默认语言
			Boolean isOnCmslaunguage = CmsLanguageManager.getInstance()
					.getIsOnCmslaunguage(cmsSiteId);
			if (!isOnCmslaunguage) {
				result.put("isOnCmslaunguage", false);
				this.writeSuccessResponse(response, result);
				return;
			}
			List<CmsLanguage> cmsLanguages = CmsLanguageManager.getInstance()
					.getList(sqlSession, " cms_site_id = " + cmsSiteId,
							" display_order desc");
			if (cmsLanguages.size() > 0) {
				result.put("defaultCode", cmsLanguages.get(0).getCode());
			}
			for (int i = 0; i < cmsLanguages.size(); i++) {
				CmsLanguage cmsLanguage = cmsLanguages.get(i);
				if (null != cmsLanguage.getIsDefault()
						&& cmsLanguage.getIsDefault().equals("1")) {
					result.put("defaultCode", cmsLanguage.getCode());
				}
			}
			result.put("cmsLanguages", cmsLanguages);
			this.writeSuccessResponse(response, result);
		} catch (Exception e) {
			e.printStackTrace();
			if (null != sqlSession) {
				sqlSession.rollback();
			}
			this.writeErrorResponse(response, "加载语言列表失败");
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
	}
}
