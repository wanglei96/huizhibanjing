package strosoft.cms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONObject;

import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.cms.model.CmsChannelManager;

/*
 * 添加服务处理类
 */
public class CancleTemplateMatchServiceHandler extends ServiceHandler {
	String templateType = null;
	List<Integer> cmsChannelList = null;
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		String sql = "";
		try {
			if(templateType.equals("channel")){
				for(int i=0;i<cmsChannelList.size();i++){
					sql = "update cms_channel set cms_channel_tempLate_id = null  where id="+cmsChannelList.get(i);
					CmsChannelManager.getInstance().updateCmsChannelBySql(sqlSession, sql);
				}
			}
			if(templateType.equals("content")){
				for(int i=0;i<cmsChannelList.size();i++){
					sql = "update cms_channel set cms_content_tempLate_id = null  where id="+cmsChannelList.get(i);
					CmsChannelManager.getInstance().updateCmsChannelBySql(sqlSession, sql);
				}
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
		this.writeSuccessResponse(response);

	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Boolean isValid = true;
		JSONObject jData = JSONObject.fromObject(request.getParameter("data"));
		templateType = jData.getString("templateType");
		cmsChannelList = JsonHelper.getIntArray(jData,"cmsChannelList");
		if(cmsChannelList.size() == 0){
			isValid = false;
			this.writeErrorResponse(response, "选中栏目不能为空");
		}
		return isValid;
	}

}
