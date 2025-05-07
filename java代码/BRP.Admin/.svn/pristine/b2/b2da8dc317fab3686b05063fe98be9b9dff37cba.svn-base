package strosoft.cms.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import net.sf.json.JSONObject;
import strosoft.app.common.DetailPageMode;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.CmsChannelChannelGroup;
import strosoft.cms.model.CmsChannelLanguageManager;
import strosoft.cms.model.CmsChannelManager;
import strosoft.cms.model.CmsLanguageManager;

/*
 * 添加服务处理类
 */
public class AddCmsChannelServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		// 给栏目实体赋值
		CmsChannel newDataEntity = (CmsChannel) ServiceHandlerHelper.getDataEntity(request, this.getConfigNode(), null,
				false);
		if (newDataEntity == null) {
			this.writeErrorResponse(response, "数据实体配置错误！");
			return;
		}
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			// 获取所属栏目组信息
			JSONObject jData = JSONObject.fromObject(request.getParameter("data"));
			Integer cmsSiteId = jData.getInt("cmsSiteId");
			// 检查栏目编码是否重复
			String channelCode = newDataEntity.getCode();
			if (null != channelCode) {
				String sql = "select count(id) from cms_channel where code = '" + channelCode + "'";
				Integer num = sqlSession.selectOne("CmsChannel.selectIntBySql", sql);
				if (num > 0) {
					this.writeErrorResponse(response, "栏目编码不能重复");
					return;
				}
			}
			ArrayList<CmsChannelChannelGroup> lstCmsChannelChannelGroup = CmsChannelManager.getInstance()
					.getCmsChannelChannelGroupList(jData);
			int newID = CmsChannelManager.getInstance().add(sqlSession, newDataEntity, lstCmsChannelChannelGroup);
			// 判断是否有多语言支持
			Boolean isOnCmslaunguage = CmsLanguageManager.getInstance().getIsOnCmslaunguage(cmsSiteId);
			// 添加对应多语言栏目,必须要有语言编码，且不能为Default
			if (jData.containsKey("cmsLanguageCode") && isOnCmslaunguage
					&& !jData.getString("cmsLanguageCode").equals("Default")) {
				// 获取语言编码
				String cmsLanguageCode = jData.getString("cmsLanguageCode");
				newDataEntity.setId(newID);
				CmsChannelLanguageManager.getInstance().addByCmsChannel(sqlSession, newDataEntity, cmsLanguageCode);
			}
			sqlSession.commit();
			// 获取所属栏目组信息
			this.writeSuccessResponse(response, newID);
		} catch (Exception e) {
			if (null != sqlSession) {
				sqlSession.rollback();
			}
			e.printStackTrace();
			this.writeErrorResponse(response, "新增栏目失败");
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}

	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");

		JSONObject jData = JSONObject.fromObject(data);

		Node configNode = this.getConfigNode();
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(response, DetailPageMode.Add);
		// 添加必填字段
		ServiceHandlerHelper.addRequiredFields(theServiceHandlerValidator, configNode, jData);
		// 添加唯一字段
		ServiceHandlerHelper.addUniqueFields(theServiceHandlerValidator, configNode, jData);
		Boolean isValid = theServiceHandlerValidator.check();

		return isValid;
	}

}
