package strosoft.cms.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import strosoft.app.common.TableDataMaster;
import strosoft.app.common.DetailPageMode;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.app.util.FileHelper;
import strosoft.app.util.JsonHelper;
import strosoft.cms.data.CmsInclude;
import strosoft.cms.data.CmsSite;
import strosoft.cms.model.CmsIncludeManager;
import strosoft.cms.model.CmsTemplateManager;


public class AddCmsIncludeServiceHandler extends ServiceHandler {
	
	private String name;
	private String path;
	private String createdFileExtName;
	private String includeContent;
	private String encode;
	private Integer cmsSiteId;
	@SuppressWarnings("rawtypes")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		CmsInclude newDataEntity = (CmsInclude) ServiceHandlerHelper.getDataEntity(request,
				this.getConfigNode(), null, false);
		if (newDataEntity == null) {
			this.writeErrorResponse(response, "数据实体配置错误！");
			return;
		}
		TableDataMaster dataManager = (TableDataMaster) ServiceHandlerHelper
				.getDataManager(this.getConfigNode());
		if (dataManager == null) {
			this.writeErrorResponse(response, "数据管理类配置错误！");
			return;
		}
		SqlSession sqlSession = null;
		Integer newID = null;
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			CmsSite cmsSite = sqlSession.selectOne("CmsSite.selectCmsSite", cmsSiteId);
			String siteDir = cmsSite.getPath()==null?"":cmsSite.getPath();
			path=siteDir+"/include/"+name+createdFileExtName;
			newDataEntity.setPath(path);
			//检查模板地址是否重复
			boolean isPathRepeat = CmsIncludeManager.getInstance().checkSamePathWidthAdd(newDataEntity);
			if(isPathRepeat){
				this.writeErrorResponse(response, "包含文件已存在！");
				return;
			}
			newID = dataManager.add(newDataEntity);
			String destDirName=SysConfig.getRootPath();
			String fileAbsoluteAddress = destDirName+path;
			File file = FileHelper.createFile(fileAbsoluteAddress);
			FileHelper.writeContents(file,includeContent,encode);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}
		this.writeSuccessResponse(response, newID);
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		//获取数据 
		name=JsonHelper.getString(jData, "name");
		createdFileExtName=JsonHelper.getString(jData, "createdFileExtName");
		includeContent = JsonHelper.getString(jData, "cmsIncludeContent");
		encode=JsonHelper.getString(jData, "encode");
		cmsSiteId=JsonHelper.getInt(jData, "cmsSiteId");
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
