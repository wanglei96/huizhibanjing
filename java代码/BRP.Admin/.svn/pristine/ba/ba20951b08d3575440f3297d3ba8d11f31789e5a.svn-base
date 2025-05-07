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
public class GetCgProjectListServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqlSession sqlSession = null;
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			String sql = "SELECT configuration FROM sys_build_project";
			ArrayList<String> projects =MyBatisManager.getInstance().getStringList(sqlSession,sql);
			ArrayList<CgProjectInfo> results = new ArrayList<CgProjectInfo>();
			for(int i=0;i<projects.size();i++){
				System.out.println(projects.get(i));
				CgProjectInfo project = CreateCodeManager.getInstance().getProjectConfiguration(projects.get(i));
				results.add(project);
			}
			sqlSession.commit();
			this.writeSuccessResponse(response,results);
		} catch (Exception e){
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

	}
}
