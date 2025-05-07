package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.cms.model.CmsIncludeManager;


/*
 * 添加服务处理类
 */
public class DeleteCmsIncludeServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int id = JsonHelper.getInt(jData, "primaryKey", "id");
		System.out.println("id"+id);
		int deleteNum = 0;
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try{
			deleteNum  = CmsIncludeManager.getInstance().deleteOne(sqlSession,id);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		this.writeSuccessResponse(response,deleteNum);
	}
}
