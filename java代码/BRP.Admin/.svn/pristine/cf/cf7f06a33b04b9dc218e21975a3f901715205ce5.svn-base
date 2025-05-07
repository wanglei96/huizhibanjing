package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONObject;

import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;

/*
 * 设置Cms默认语言
 */
public class SetDefaultCmsLanguageServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String code = jData.getString("code");
			String cmsSiteId = jData.getString("cmsSiteId");
			//把原默认语言取消默认
			String updateSql = "update cms_language set is_default = 0 where is_default = 1 and cms_site_id ="+cmsSiteId;
			sqlSession.update("CmsLanguage.updateCmsLanguageBySql", updateSql);
			updateSql = "update cms_language set is_default = 1 where code = '"+code+"'";
			sqlSession.update("CmsLanguage.updateCmsLanguageBySql", updateSql);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			this.writeErrorResponse(response, "设置默认语言失败");
		} finally {
			sqlSession.close();
		}
		this.writeSuccessResponse(response);
	}
}
