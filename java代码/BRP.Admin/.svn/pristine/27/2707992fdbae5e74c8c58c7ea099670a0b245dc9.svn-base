package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONObject;

import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.cms.model.CmsChannelManager;

/*
 * 添加服务处理类
 */
public class SetDefaultTemplateServiceHandler extends ServiceHandler {
	
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		String sql = "";
		JSONObject jData = JSONObject.fromObject(request.getParameter("data"));
		String templateType = jData.getString("template");
		int id = jData.getInt("id");
		int cmsSiteId = jData.getInt("cmsSiteId");
		try {
			System.out.println(jData);
			//取消该站点原默认模板
			sql = "update cms_template set is_system_default_"+templateType.toLowerCase()+"_template = null ";
			sql += "where cms_site_id="+cmsSiteId+" and cms_template_type_code='"+templateType+"' ";
			sql +="and is_system_default_"+templateType.toLowerCase()+"_template = 1";
			CmsChannelManager.getInstance().updateCmsChannelBySql(sqlSession, sql);
			//设置该站点默认模板
			sql = "update cms_template set is_system_default_"+templateType.toLowerCase()+"_template = 1 ";
			sql += "where id="+id;
			CmsChannelManager.getInstance().updateCmsChannelBySql(sqlSession, sql);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		
		this.writeSuccessResponse(response);

	}

	
}
