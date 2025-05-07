package strosoft.cms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONObject;

import strosoft.app.common.MyBatisManager;
import strosoft.app.model.ListInfo;
import strosoft.app.service.ServiceHandler;
import strosoft.cms.model.CmsChannelManager;

/*
 * 取得数据表
 */
public class GetCmsChannelIdListWidthPromissionServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		SqlSession sqlSession = null;
		ListInfo listInfo = new ListInfo();
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			List<Integer> cmsChannelId = CmsChannelManager.getInstance().getPermissionChannelIdList(sqlSession, jData);
			listInfo.setDataList(cmsChannelId);
			this.writeSuccessResponse(response,listInfo);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
}
