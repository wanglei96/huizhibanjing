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
import strosoft.cms.data.CmsChannelLanguage;
import strosoft.cms.data.CmsContent;
import strosoft.cms.data.CmsContentLanguage;
import strosoft.cms.model.CmsChannelChannelGroupManager;
import strosoft.cms.model.CmsChannelLanguageManager;
import strosoft.cms.model.CmsChannelManager;
import strosoft.cms.model.CmsContentLanguageManager;
import strosoft.cms.model.CmsContentManager;
import strosoft.cms.model.CmsTransferManager;

import net.sf.json.JSONObject;


public class CmsTransferServiceHandler extends ServiceHandler{
	private static int BASIC_ROOT_ID = -500 ;//所选栏目树的root的默认父栏目id
	String dataNameList=null;
	String deleteFlag = null;
	String cmsSiteId = null;
	int parentId;
	List<Integer> oldChannelIdList = null;
	String cmsContentTransferCode = null;
	List<CmsChannel> cmsChannelList = new ArrayList<CmsChannel>();
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
		oldChannelIdList = JsonHelper.getIntArray(jData, "oldChannelId");
		cmsContentTransferCode = JsonHelper.getString(jData, "cmsContentTransferCode");
		System.out.println("deleteFlag:"+deleteFlag+",cmsSiteId:"+cmsSiteId+",parentId:"+parentId+",oldChannelIdList:"+oldChannelIdList+",cmsContentTransferCode:"+cmsContentTransferCode);
		transfer(response);
		
	}
	/*
	 * 通用转移服务
	 */
	public void transfer(HttpServletResponse response) throws Exception{
		System.out.println("开始转移--------------------------------");
		//获取要转移的cmsChannel
		cmsChannelList = getCmsChannelListByIds();
		//循环确定转移cmsChannel之间的关系
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			//
			transferByCmsChannel(sqlSession,BASIC_ROOT_ID,parentId,response);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
	}
	public List<CmsChannel> getCmsChannelListByIds() throws Exception{
		for(Integer id : oldChannelIdList){
			CmsChannel cmsChanel = CmsTransferManager.getInstance().getCmsChannelById(id);
			cmsChannelList.add(cmsChanel);
		}
		return cmsChannelList;
	}
	//转移
	public void transferByCmsChannel(SqlSession sqlSession,int oldParentId,int newParentId,HttpServletResponse response) throws Exception{
		List<CmsChannel> cmsRootChannelList = new ArrayList<CmsChannel>();
		Iterator<CmsChannel> iter1 = cmsChannelList.iterator();  
		while(iter1.hasNext()){  
			CmsChannel cmsChannel1 = iter1.next();
			boolean isRootFlag = true;
		    Iterator<CmsChannel> iter2 = cmsChannelList.iterator();
		    if(oldParentId == BASIC_ROOT_ID){
		    	while(iter2.hasNext()){  
					CmsChannel cmsChannel2 = iter2.next();
					if(cmsChannel1.getParentId().equals(cmsChannel2.getId())){
						isRootFlag = false;
					} 
				}
		    	if(isRootFlag){
					//添加到根节点list中
					cmsRootChannelList.add(cmsChannel1);
				}
		    }else if(cmsChannel1.getParentId() == oldParentId){
		    	cmsRootChannelList.add(cmsChannel1);
		    }
		}
		for(int i=0;i<cmsRootChannelList.size();i++){
			CmsChannel cmsChannelRoot = cmsRootChannelList.get(i);
			//根据参数控制转移类型
			transferTypeControll(sqlSession,cmsChannelRoot,newParentId,response);
		}
	}
	//根据参数控制转移类型
	public void transferTypeControll(SqlSession sqlSession,CmsChannel cmsChannelRoot,int newParentId,HttpServletResponse response) throws Exception{
		/*
		 * 转移
			1、将栏目修改成指定栏目
			2、三种转移类型
				①转移栏目 仅栏目的cms_site_id与parent_id
				②转移栏目中的内容 修改内容中的cms_channel_id
				③转移栏目及其中的内容 两张表都要修改
			3、转移后是否删除
				是:
		 */
		if (deleteFlag.equals("1")) {
			//转移后删除
			if (cmsContentTransferCode.equals("Channel")) {
				int oldId = cmsChannelRoot.getId();
				//设置新栏目父id
				cmsChannelRoot.setParentId(newParentId);
				//新增channel
				int newId =	transferChannel(sqlSession,cmsChannelRoot);
				//转移栏目组
				transferChannelGroup(sqlSession,oldId,newId);
				//转移选中的子栏目
				transferByCmsChannel(sqlSession,oldId,newId,response);
				//删除多语言栏目
				CmsChannelLanguageManager.getInstance().deleteByCmsChannelId(sqlSession, oldId);
				//删除栏目
				CmsChannelManager.getInstance().deletOne(sqlSession,oldId);
				this.writeSuccessResponse(response);
			}else if (cmsContentTransferCode.equals("Content")) {
				int oldId = cmsChannelRoot.getId();
				//新栏目id
				int newId = newParentId;
				//转移内容
				transferContent(sqlSession,oldId,newId);
				//根据cmsChannelId删除旧内容
				CmsContentManager.getInstance().deleteByCmsChannelId(sqlSession,oldId);
				//转移选中的子栏目中的内容
				transferByCmsChannel(sqlSession,oldId,newId,response);
				this.writeSuccessResponse(response);
			}else if (cmsContentTransferCode.equals("All")) {
				int oldId = cmsChannelRoot.getId();
				//设置新栏目父id
				cmsChannelRoot.setParentId(newParentId);
				//新增channel
				int newId =	transferChannel(sqlSession,cmsChannelRoot);
				//转移内容
				transferContent(sqlSession,oldId,newId);
				//根据cmsChannelId删除旧内容
				CmsContentManager.getInstance().deleteByCmsChannelId(sqlSession,oldId);
				//转移栏目组
				transferChannelGroup(sqlSession,oldId,newId);
				//转移选中的子栏目
				transferByCmsChannel(sqlSession,oldId,newId,response);
				//删除栏目
				CmsChannelManager.getInstance().deletOne(sqlSession,oldId);
				this.writeSuccessResponse(response);
			}
			
		}else if (deleteFlag.equals("0")) {
			//转移不删除
			if (cmsContentTransferCode.equals("Channel")) {
				int oldId = cmsChannelRoot.getId();
				//设置新栏目父id
				cmsChannelRoot.setParentId(newParentId);
				//新增channel
				int newId =	transferChannel(sqlSession,cmsChannelRoot);
				//转移栏目组
				transferChannelGroup(sqlSession,oldId,newId);
				//转移选中的子栏目
				transferByCmsChannel(sqlSession,oldId,newId,response);
				this.writeSuccessResponse(response);
			}else if (cmsContentTransferCode.equals("Content")) {
				int oldId = cmsChannelRoot.getId();
				//新栏目id
				int newId = newParentId;
				//转移内容
				transferContent(sqlSession,oldId,newId);
				//转移选中的子栏目中的内容
				transferByCmsChannel(sqlSession,oldId,newId,response);
				this.writeSuccessResponse(response);
			}else if (cmsContentTransferCode.equals("All")) {
				int oldId = cmsChannelRoot.getId();
				//设置新栏目父id
				cmsChannelRoot.setParentId(newParentId);
				//新增channel
				int newId =	transferChannel(sqlSession,cmsChannelRoot);
				//转移内容
				transferContent(sqlSession,oldId,newId);
				//转移栏目组
				transferChannelGroup(sqlSession,oldId,newId);
				//转移选中的子栏目
				transferByCmsChannel(sqlSession,oldId,newId,response);
				this.writeSuccessResponse(response);
			}
		}else {
			this.writeWarningResponse(response, "是否删除！");
		}
	}
	//转移内容
	public void transferContent(SqlSession sqlSession,int oldId,int newId) throws Exception{
		System.out.println("转移内容................................");
		List<Integer> contentIdList = new ArrayList<Integer>();
		List<CmsContentLanguage> newCmsContentLanguageList = new ArrayList<CmsContentLanguage>();
		//获取内容
		List<CmsContent> cmsContentList = CmsContentManager.getInstance().getContentList(sqlSession,oldId);
		//转移内容
		for(int i=(cmsContentList.size()-1);i>-1;i--){
			CmsContent cmsContent = cmsContentList.get(i);
			Integer cmsContentId = cmsContent.getId();
			contentIdList.add(cmsContentId);
			//设置新内容cmsChannelId
			cmsContent.setCmsChannelId(newId);
			//新增内容
			int newContentId = CmsContentManager.getInstance().addCmsContent(sqlSession, cmsContent);
			//根据内容id转移内容组关系
			//新增多语言内容
			List<CmsContentLanguage> cmsContentLanguageList = CmsContentLanguageManager.getInstance().getListByCmsContentId(sqlSession, cmsContentId);
			for(int j=0;j<cmsContentLanguageList.size();j++){
				CmsContentLanguage cmsContentLanguage = cmsContentLanguageList.get(j);
				cmsContentLanguage.setCmsContentId(newContentId);
				cmsContentLanguage.setId(null);
				newCmsContentLanguageList.add(cmsContentLanguage);
			}
		}
		if(newCmsContentLanguageList.size()>0){
			CmsContentLanguageManager.getInstance().addList(sqlSession, newCmsContentLanguageList);
		}
	}
	//转移栏目组
	public void transferChannelGroup(SqlSession sqlSession,int oldId,int newId) throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException{
		System.out.println("转移栏目组................................");
		//根据oldId获取栏目组信息
		List<CmsChannelChannelGroup> cmsChannelChannelGroups = CmsChannelChannelGroupManager.getInstance().getByCmsChannelId(sqlSession, oldId);
		//根据newId新增栏目组信息
		for(int i=0;i<cmsChannelChannelGroups.size();i++){
			cmsChannelChannelGroups.get(i).setCmsChannelId(newId);
			CmsChannelChannelGroupManager.getInstance().add(sqlSession, cmsChannelChannelGroups.get(i));
		}
	}
	//新增channel
	public int transferChannel(SqlSession sqlSession,CmsChannel rootChannel) throws Exception{
		System.out.println("新增channel................................");
		List<CmsChannelLanguage> cmsChannelLanguages = CmsChannelLanguageManager.getInstance().getListByCmsChannelId(sqlSession, rootChannel.getId());
		//设置新栏目code为""
		rootChannel.setCode("");
		//设置新增栏目的栏目模板和内容模板为null
		rootChannel.setCmsChannelTemplateId(null);
		rootChannel.setCmsContentTemplateId(null);
		int newId = CmsChannelManager.getInstance().addCmsChannel(sqlSession,rootChannel);
		//新增多语言栏目
		for(int i=0;i<cmsChannelLanguages.size();i++){
			CmsChannelLanguage cmsChannelLanguage = cmsChannelLanguages.get(i);
			cmsChannelLanguage.setCmsChannelId(newId);
			cmsChannelLanguage.setId(null);
		}
		if(cmsChannelLanguages.size()>0){
			CmsChannelLanguageManager.getInstance().addList(sqlSession, cmsChannelLanguages);
		}
		return newId;
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
