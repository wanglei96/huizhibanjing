package strosoft.cg.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;

/*
 * 添加服务处理类
 */
public class DeleteCgProjectServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqlSession sqlSession = null;
		JSONObject jData = JSONObject.fromObject(request.getParameter("data"));
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			Integer projectId = jData.getInt("projectId");
			String sql = "delete from sys_build_project where id=" + projectId;
			sqlSession.delete("CgProject.deleteStringBysql", sql);
			sqlSession.commit();
			this.writeSuccessResponse(response);
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
			this.writeErrorResponse(response, "删除项目失败");
		} finally {
			sqlSession.close();
		}

	}
}
