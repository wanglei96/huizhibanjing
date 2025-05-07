package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONObject;

import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;

/*
 * 改变站点Cms多语言状态
 */
public class SwitchIsOnCmsLanguageServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		System.out.println(data);
		JSONObject jData = JSONObject.fromObject(data);
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String value = jData.getString("value");
			String cmsSiteId = jData.getString("cmsSiteId");
			Integer isCmsLanguage = 0;
			if(value.equals("1")){
				isCmsLanguage = 1;
			}
			//切换CMS多语言
			String updateSql = "update cms_site set is_cms_language = "+isCmsLanguage+" where id ="+cmsSiteId;
			MyBatisManager.getInstance().executeUpdate(sqlSession, updateSql);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			this.writeErrorResponse(response, "切换CMS多语言失败");
		} finally {
			sqlSession.close();
		}
		this.writeSuccessResponse(response);
	}
}
