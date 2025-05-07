package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import strosoft.app.common.DetailPageMode;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.cms.data.CmsContent;
import strosoft.cms.model.CmsContentLanguageManager;
import strosoft.cms.model.CmsContentManager;
import strosoft.cms.model.CmsLanguageManager;

/*
 * 添加服务处理类
 */
public class UpdateCmsContentServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SqlSession sqlSession = null;
			try{
				Boolean isValid = this.checkInput(request, response);
				if (!isValid) {
					return;
				}
				//给栏目实体赋值
				CmsContent newDataEntity = (CmsContent)ServiceHandlerHelper.getDataEntity(request,
						this.getConfigNode(), null, false);
				
				if (newDataEntity == null) {
					this.writeErrorResponse(response, "数据实体配置错误！");
					return;
				}
				sqlSession = MyBatisManager.getInstance().openSession();
				//获取所属栏目组信息
				String data = request.getParameter("data");
				JSONObject jData = JSONObject.fromObject(data);
				Integer cmsSiteId = jData.getInt("cmsSiteId");
				int newID = CmsContentManager.getInstance().update(sqlSession,newDataEntity);
				//判断是否有多语言支持
				Boolean isOnCmslaunguage = CmsLanguageManager.getInstance().getIsOnCmslaunguage(cmsSiteId);
				//修改对应多语言栏目,必须要有语言编码，且不能为Default
				if(jData.containsKey("cmsLanguageCode") && isOnCmslaunguage && !jData.getString("cmsLanguageCode").equals("Default")){
					//获取语言编码
					String cmsLanguageCode = jData.getString("cmsLanguageCode");
					//获取多语言栏目ID
					Integer cmsContentLanguageId = jData.getInt("cmsContentLanguageId");	
					CmsContentLanguageManager.getInstance().updateByCmsContent(sqlSession, newDataEntity, cmsLanguageCode, cmsContentLanguageId);
				}
				sqlSession.commit();
				this.writeSuccessResponse(response, newID);
			}catch (Exception e) {
				e.printStackTrace();
				if(null != sqlSession){
					sqlSession.rollback();
				}
				this.writeErrorResponse(response, "修改内容失败");
			} finally{
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
				response, DetailPageMode.Add);
		// 添加必填字段
		ServiceHandlerHelper.addRequiredFields(theServiceHandlerValidator,
				configNode, jData);
		// 添加唯一字段
		ServiceHandlerHelper.addUniqueFields(theServiceHandlerValidator,
				configNode, jData);
		Boolean isValid = theServiceHandlerValidator.check();

		return isValid;
	}

}
