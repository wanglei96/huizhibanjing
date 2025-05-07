package strosoft.cms.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.cms.model.CmsContentManager;

public class BatchDeleteCmsContentServiceHandler extends ServiceHandler {
	@SuppressWarnings("unchecked")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String data = request.getParameter("data");

		JSONObject jData = JSONObject.fromObject(data);
		//获取idList
		List<Integer> lstId = JsonHelper.getIntArray(jData, "primaryKeys",
				"ids");
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			for(int i=0;i<lstId.size();i++){
				CmsContentManager.getInstance().delete(sqlSession,lstId.get(i));
			}
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		
		this.writeSuccessResponse(response,lstId.size());
	}
}
