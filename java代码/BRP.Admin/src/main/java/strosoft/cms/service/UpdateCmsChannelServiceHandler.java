package strosoft.cms.service;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.DetailPageMode;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.CmsChannelChannelGroup;
import strosoft.cms.model.CmsChannelLanguageManager;
import strosoft.cms.model.CmsChannelManager;
import strosoft.cms.model.CmsLanguageManager;


public class UpdateCmsChannelServiceHandler extends ServiceHandler {

	@SuppressWarnings("rawtypes")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqlSession sqlSession = null;
		try{
			Boolean isValid = this.checkInput(request, response);
			if (!isValid) {
				return;
			}
			//给栏目实体赋值
			CmsChannel newDataEntity = (CmsChannel)ServiceHandlerHelper.getDataEntity(request,
					this.getConfigNode(), null, false);
			
			if (newDataEntity == null) {
				this.writeErrorResponse(response, "数据实体配置错误！");
				return;
			}
			sqlSession = MyBatisManager.getInstance().openSession();
			//获取所属栏目组信息
			JSONObject jData = JSONObject.fromObject(request.getParameter("data"));
			//检查栏目编码是否重复
			String channelCode = newDataEntity.getCode();
			if(null != channelCode){
				String sql = "select count(id) from cms_channel where code = '"+channelCode+"' and id <> "+newDataEntity.getId();
				Integer num = sqlSession.selectOne("CmsChannel.selectIntBySql", sql);
				if(num>0){
					this.writeErrorResponse(response, "栏目编码不能重复");
					return;
				}
			}
			Integer cmsSiteId = jData.getInt("cmsSiteId");
			ArrayList<CmsChannelChannelGroup> lstCmsChannelChannelGroup = CmsChannelManager.getInstance().getCmsChannelChannelGroupList(jData);
			int newID = CmsChannelManager.getInstance().update(sqlSession,newDataEntity, lstCmsChannelChannelGroup);
			//判断是否有多语言支持
			Boolean isOnCmslaunguage = CmsLanguageManager.getInstance().getIsOnCmslaunguage(cmsSiteId);
			//修改对应多语言栏目,必须要有语言编码，且不能为Default
			if(jData.containsKey("cmsLanguageCode") && isOnCmslaunguage && !jData.getString("cmsLanguageCode").equals("Default")){
				//获取语言编码
				String cmsLanguageCode = jData.getString("cmsLanguageCode");
				//获取多语言栏目ID
				Integer cmsChannelLanguageId = jData.getInt("cmsChannelLanguageId");	
				CmsChannelLanguageManager.getInstance().updateByCmsChannel(sqlSession, newDataEntity, cmsLanguageCode, cmsChannelLanguageId);
			}
			sqlSession.commit();
			this.writeSuccessResponse(response, newID);
		}catch (Exception e) {
			e.printStackTrace();
			if(null != sqlSession){
				sqlSession.rollback();
			}
			this.writeErrorResponse(response, "修改栏目 失败");
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
				response, DetailPageMode.Edit);
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
