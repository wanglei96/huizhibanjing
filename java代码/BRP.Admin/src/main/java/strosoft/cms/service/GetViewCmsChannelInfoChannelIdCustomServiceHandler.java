package strosoft.cms.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.DetailPageMode;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.app.util.JsonHelper;
import strosoft.cms.data.CmsChannelLanguage;
import strosoft.cms.data.CmsLanguage;
import strosoft.cms.data.ViewCmsChannelInfo;
import strosoft.cms.model.CmsChannelLanguageManager;
import strosoft.cms.model.CmsLanguageManager;
import strosoft.cms.model.ViewCmsChannelInfoManager;

/*
 * 根据栏目ID获取多语言栏目信息CmsChannelInfo
 */
public class GetViewCmsChannelInfoChannelIdCustomServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		SqlSession sqlSession = null;
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			Integer cmsChannelId = jData.getInt("id");
			Integer cmsSiteId = jData.getInt("cmsSiteId");
			JSONObject result = new JSONObject();
			//获取该栏目下的多语言栏目
			HashMap<String, ViewCmsChannelInfo> cmsChannelLanguages = ViewCmsChannelInfoManager.getInstance().getViewCmsChannelInfoListByCmsChannelId(sqlSession, cmsChannelId,cmsSiteId);
			//获取多语言类型
			result.put("id", cmsChannelId);
			result.put("dataList", JsonHelper.toJsonObject(cmsChannelLanguages));
			System.out.println(result);
			this.writeSuccessResponse(response,result);
		}catch (Exception e) {
			e.printStackTrace();
			this.writeErrorResponse(response, "获取栏目信息失败");	
		}finally{
			if(null != sqlSession){
				sqlSession.close();
			}
		}
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");

		JSONObject jData = JSONObject.fromObject(data);

		Node configNode = this.getConfigNode();
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(
				response, DetailPageMode.View);
		// 添加必填字段
		ServiceHandlerHelper.addRequiredFields(theServiceHandlerValidator,
				configNode, jData);
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}

}
