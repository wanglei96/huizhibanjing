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
import strosoft.cg.data.CgTable;
import strosoft.cg.model.CreateCodeManager;

/*
 * 添加服务处理类
 */
public class GetColumConfigServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqlSession sqlSession = null;
		JSONObject jData = JSONObject.fromObject(request.getParameter("data"));
		String projectId = jData.getString("projectId");
		String moduleName = jData.getString("moduleName");
		String tableName = jData.getString("tableName");
		String type = jData.getString("type");
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			String sql = "SELECT configuration FROM sys_build_project WHERE id = "+projectId;
			ArrayList<String> projects =MyBatisManager.getInstance().getStringList(sqlSession,sql);
			CgTable table = CreateCodeManager.getInstance().getColumConfig(projects.get(0),moduleName,tableName,type);
			sqlSession.commit();
			this.writeSuccessResponse(response,table);
		} catch (Exception e){
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

	}
}
