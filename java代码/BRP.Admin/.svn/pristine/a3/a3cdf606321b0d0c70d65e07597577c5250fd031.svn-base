package strosoft.cms.service;

import java.io.File;
import java.util.logging.FileHandler;

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
import strosoft.cms.data.CmsSite;
import strosoft.cms.data.CmsTemplate;
import strosoft.cms.model.CmsTemplateManager;


/*
 * 添加服务处理类
 */
public class AddCmsTemplateServiceHandler extends ServiceHandler {

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
		TableDataMaster dataManager = (TableDataMaster) ServiceHandlerHelper
				.getDataManager(this.getConfigNode());
		if (dataManager == null) {
			this.writeErrorResponse(response, "数据管理类配置错误！");
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		System.out.println(jData);
		String relatedFileName = cmsTemplate.getRelatedFileName();
		String destDirName=SysConfig.getRootPath();
		//获取站点名称
		SqlSession sqlSession = null;
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			CmsSite cmsSite = sqlSession.selectOne("CmsSite.selectCmsSite", cmsTemplate.getCmsSiteId());
			String siteDir = cmsSite.getPath()==null?"":cmsSite.getPath()+"/";
			String newfilePath = "";
			if(cmsTemplate.getCmsTemplateTypeCode().equals("Home")){
				newfilePath = siteDir+"templates/"+relatedFileName+".html";
			}else if(cmsTemplate.getCmsTemplateTypeCode().equals("Channel")){
				newfilePath = siteDir+"templates/channel/"+relatedFileName+".html";
			}else if(cmsTemplate.getCmsTemplateTypeCode().equals("Content")){
				newfilePath = siteDir+"templates/content/"+relatedFileName+".html";
			}else if(cmsTemplate.getCmsTemplateTypeCode().equals("Custom")){
				newfilePath = siteDir+"templates/custom/"+relatedFileName+".html";
			}
			cmsTemplate.setPath(newfilePath);
			//检查模板地址是否重复
			boolean isPathRepeat = CmsTemplateManager.getInstance().checkSamePathWidthAdd(cmsTemplate);
			if(isPathRepeat){
				this.writeErrorResponse(response, "模板地址已存在！");
				return;
			}
			String XMLContent = jData.getString("templateContent");
			boolean isXMLFormat = CmsTemplateManager.getInstance().checkXMLFormat(XMLContent);
			if(XMLContent.equals("") || isXMLFormat){
				//将数据插入数据库
				int newID = dataManager.add(cmsTemplate);
				String fileAddress = destDirName+newfilePath;
				File file = FileHelper.createFile(fileAddress);
				FileHelper.writeContents(file,XMLContent, cmsTemplate.getEncode());
				this.writeSuccessResponse(response,newID);
				sqlSession.commit();
			}else{
				this.writeErrorResponse(response, "内容不是XML格式!");
				sqlSession.commit();
			}			
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
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
