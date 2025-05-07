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

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.TableDataMaster;
import strosoft.app.common.DetailPageMode;
import strosoft.app.common.SysConfig;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.app.util.FileHelper;
import strosoft.app.util.JsonHelper;
import strosoft.cms.data.CmsSite;
import strosoft.cms.data.CmsTemplate;
import strosoft.cms.model.CmsTemplateManager;


public class UpdateCmsTemplateServiceHandler extends ServiceHandler {
	

	@SuppressWarnings("rawtypes")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		CmsTemplate cmsTemplate = (CmsTemplate) ServiceHandlerHelper.getDataEntity(request,
				this.getConfigNode(), null, false);
		
		if (cmsTemplate == null) {
			this.writeErrorResponse(response, "数据实体配置错误！");
			return;
		}
		
		SqlSession sqlSession = null;
		try{
			//获取站点名称
			sqlSession = MyBatisManager.getInstance().openSession();
			CmsSite cmsSite = sqlSession.selectOne("CmsSite.selectCmsSite", cmsTemplate.getCmsSiteId());
			String data = request.getParameter("data");
			JSONObject jData = JSONObject.fromObject(data);
			String relatedFileName = cmsTemplate.getRelatedFileName();
			String createdFileExtName = cmsTemplate.getCreatedFileExtName();
			String siteDir = cmsSite.getPath()==null?"":cmsSite.getPath()+"/";
			String destDirName=SysConfig.getRootPath();
			//检查地址是否已经存在
			
			String filePath = "";
			//获取模板文件的旧地址,删除旧文件
			CmsTemplate oldCmsTemplate = (CmsTemplate) CmsTemplateManager.getInstance().getDataEntity(cmsTemplate.getId());
			String oldfilePath = siteDir+oldCmsTemplate.getPath();
			File oldFile = new File(oldfilePath);
			if(oldFile.exists()&&oldFile.isFile()){
				 oldFile.delete();
			}
			if(cmsTemplate.getCmsTemplateTypeCode().equals("Home")){
				filePath = siteDir+"templates/"+relatedFileName+createdFileExtName;
			}else if(cmsTemplate.getCmsTemplateTypeCode().equals("Channel")){
				filePath = siteDir+"templates/channel/"+relatedFileName+createdFileExtName;
			}else if(cmsTemplate.getCmsTemplateTypeCode().equals("Content")){
				filePath = siteDir+"templates/content/"+relatedFileName+createdFileExtName;
			}else if(cmsTemplate.getCmsTemplateTypeCode().equals("Custom")){
				filePath = siteDir+"templates/custom/"+relatedFileName+createdFileExtName;
			}
			cmsTemplate.setPath(filePath);
			//检查模板地址是否重复
			boolean isPathRepeat = CmsTemplateManager.getInstance().checkSamePathWithUpdate(cmsTemplate);
			if(isPathRepeat){
				this.writeErrorResponse(response, "模板地址已存在！");
				return;
			}
			String XMLContent = jData.getString("templateContent");
			System.out.println("XMLContent:"+XMLContent);
			boolean isXMLFormat = CmsTemplateManager.getInstance().checkXMLFormat(XMLContent);
			if(XMLContent.equals("") || isXMLFormat){
				//修改数据
				String fileAddress = destDirName+filePath;
				File file = FileHelper.createFile(fileAddress);
				FileHelper.writeContents(file,jData.getString("templateContent"), cmsTemplate.getEncode());
				int newID = CmsTemplateManager.getInstance().update(sqlSession, cmsTemplate);
				sqlSession.commit();
				this.writeSuccessResponse(response, newID);
				
			}else{
				this.writeErrorResponse(response, "内容不是XML格式!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			this.writeErrorResponse(response, "修改模版失败");
		} finally{
			sqlSession.close();
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
	
	

	/**
	 * 
	 * @param fileName  文件名称
	 * @param templateContent  内容
	 */
	public void createFile(String relatedFileName,String createdFileName,String createdFileExtName,String templateContent,String encode) {
		//文件路径
		//String destDirName=SysConfig.getRootPath();
		//储存文件
		//File file = FileHelper.createFile(destDirName+relatedFileName+createdFileExtName);
		//File file  = CreateFileUtil.createFile(proFileName+fileName);
		//往文件中写内容
		//FileHelper.WriteContents(file, templateContent,encode);
		
	}
	
	
}
