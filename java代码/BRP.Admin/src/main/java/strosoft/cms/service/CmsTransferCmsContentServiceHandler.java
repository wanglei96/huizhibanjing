package strosoft.cms.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import strosoft.app.common.DetailPageMode;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.app.util.JsonHelper;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.CmsChannelChannelGroup;
import strosoft.cms.data.CmsContent;
import strosoft.cms.data.CmsContentLanguage;
import strosoft.cms.model.CmsChannelChannelGroupManager;
import strosoft.cms.model.CmsChannelManager;
import strosoft.cms.model.CmsContentLanguageManager;
import strosoft.cms.model.CmsContentManager;
import strosoft.cms.model.CmsTransferManager;

import net.sf.json.JSONObject;


public class CmsTransferCmsContentServiceHandler extends ServiceHandler{
	String deleteFlag = null;
	String cmsSiteId = null;
	int parentId;
	List<Integer> cmsContentIdList = null;
	List<CmsContent> cmsContentList = new ArrayList<CmsContent>();
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			InstantiationException, IllegalAccessException, Exception {
			Boolean isValid = this.checkInput(request, response);
			if (!isValid) {
				return;
			}
		
		String data = request.getParameter("data");

		JSONObject jData = JSONObject.fromObject(data);
		
		deleteFlag=JsonHelper.getString(jData, "deleteFlag");
		cmsSiteId = JsonHelper.getString(jData, "cmsSiteId");
		parentId = JsonHelper.getInt(jData, "cmsChannelId");
		cmsContentIdList = JsonHelper.getIntArray(jData, "primaryKeys");
		transfer(response);
		
	}
	/*
	 * 通用转移服务
	 */
	public void transfer(HttpServletResponse response) throws IOException, SQLException{
		System.out.println("开始转移--------------------------------");
		//获取要转移的cmsChannel
		//循环确定转移cmsChannel之间的关系
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			//
			for(int i=0;i<cmsContentIdList.size();i++){
				CmsContent cmsContent = CmsContentManager.getInstance().getEntity(sqlSession,cmsContentIdList.get(i));
				cmsContentList.add(cmsContent);
			}
			for(int i=(cmsContentList.size()-1);i>-1;i--){
				CmsContent cmsContet = cmsContentList.get(i);
				int oldContentId = cmsContet.getId();
				if(deleteFlag.equals("1")){
					//转移删除
						//转移内容
						transferCmsContent(sqlSession,cmsContet);
						//删除内容
						deleteCmsContent(sqlSession,oldContentId);
				} else if (deleteFlag.equals("0")){
					//转移后不删除
						//转移内容
						transferCmsContent(sqlSession,cmsContet);
				}
			}
			sqlSession.commit();
			this.writeSuccessResponse(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
	}
	/*
	 * 转移内容
	 */
	private void transferCmsContent(SqlSession sqlSession,CmsContent cmsContet) throws Exception{
		cmsContet.setCmsChannelId(parentId);
		List<CmsContentLanguage> cmsContentLanguageList = CmsContentLanguageManager.getInstance().getListByCmsContentId(sqlSession, cmsContet.getId());
		Integer newId = CmsContentManager.getInstance().addCmsContent(sqlSession, cmsContet);
		//转移内容组
		//转移多语言栏目
		List<CmsContentLanguage> newCmsContentLanguageList = new ArrayList<CmsContentLanguage>();
		for(int i=0;i<cmsContentLanguageList.size();i++){
			CmsContentLanguage cmsContentLanguage = cmsContentLanguageList.get(i);
			cmsContentLanguage.setCmsContentId(newId);
			cmsContentLanguage.setId(null);
			newCmsContentLanguageList.add(cmsContentLanguage);
		}
		if(newCmsContentLanguageList.size()>0){
			CmsContentLanguageManager.getInstance().addList(sqlSession, newCmsContentLanguageList);
		}
	}
	/*
	 * 删除旧内容
	 */
	private void deleteCmsContent(SqlSession sqlSession,int oldContentId) throws Exception{
		//删除多语言内容
		CmsContentLanguageManager.getInstance().deleteByCmsContentId(sqlSession, oldContentId);
		//删除内容
		CmsContentManager.getInstance().delete(sqlSession, oldContentId);
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
