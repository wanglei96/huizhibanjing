package strosoft.cg.service;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;


import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.MySqlHelper;
import strosoft.cg.data.CgProjectInfo;
import strosoft.cg.model.CreateCodeManager;

/*
 * 添加服务处理类
 */
public class GetCgProjectServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqlSession sqlSession = null;
		JSONObject jData = JSONObject.fromObject(request.getParameter("data"));
		String projectId = jData.getString("projectId");
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			String sql = "SELECT configuration FROM sys_build_project WHERE `id` = "+projectId+"";
			System.out.println("sql = " + sql);
			System.out.println("projectId = " + projectId);
			ArrayList<String> projects =MyBatisManager.getInstance().getStringList(sqlSession,sql);
			CgProjectInfo project = CreateCodeManager.getInstance().getProjectConfiguration(projects.get(0));
			sqlSession.commit();
			this.writeSuccessResponse(response,project);
		} catch (Exception e){
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

	}
}
