package strosoft.cms.model;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.ibatis.session.SqlSession;
import org.xml.sax.SAXException;

import strosoft.app.common.SysConfig;
import strosoft.app.util.FileHelper;
import strosoft.cms.data.CmsTemplate;
import strosoft.cms.data.ViewCmsChannelInfo;

public class CmsChannelBuildHandler extends CmsBuildHandler {
	
	public void processByCmsLanguage(CmsBuildContext context) throws Exception{
		CmsBuildTask cmsBuildTask = context.getCmsBuildTask();
		SqlSession sqlSession = context.getSqlSession();
		int cmsChannelId = context.getCmsBuildTask().getChannelId();
		ViewCmsChannelInfo viewCmsChannelInfo = CmsBuildManager.getInstance().getViewCmsChannelInfoByCmsChannelIdAndCode(cmsChannelId, cmsBuildTask.getCmsLanguageCode());
		CmsHtmlGenerateContext cmsHtmlGenerateContext = new CmsHtmlGenerateContext();
		cmsHtmlGenerateContext.setTemplateType(cmsBuildTask.getBuildTaskType());
		cmsHtmlGenerateContext.setCmsSiteId(cmsBuildTask.getSiteId());
		CmsSiteManager.getInstance().setCmsHtmlGenerateContextBySiteId(cmsHtmlGenerateContext);
		cmsHtmlGenerateContext.setCurrentChannelId(cmsChannelId);
		cmsHtmlGenerateContext.setCmsLanguageCode(cmsBuildTask.getCmsLanguageCode());
		//拼接生成内容文件路径 
		String cmsChannelPath = SysConfig.getRootPath()+viewCmsChannelInfo.getCreateFilePath();
		Integer cmsTemplateId = viewCmsChannelInfo.getCmsChannelTemplateId();
		//未匹配模板
		if(viewCmsChannelInfo.getCmsTemplateUrl() == null){
			return;
			/*Integer cmsSiteId = viewCmsChannel.getCmsSiteId();
			String cmsBuildType = context.getCmsBuildTask().getBuildTaskType();
			CmsTemplate viewDefaultCmsTemplate = CmsTemplateManager.getInstance().selectDefaultCmsTemplate(cmsBuildType.toLowerCase(),cmsSiteId);
			if(viewDefaultCmsTemplate == null){
				//未设置默认模板，生成空白页
				System.out.println("未定义默认模板");
				FileHelper.createFile(cmsChannelPath);
				return;
			}else{
				//已设置默认模板，按照 默认模板进行生成
				cmsHtmlGenerateContext.setEncoding(viewDefaultCmsTemplate.getEncode());
				cmsTemplateId = viewDefaultCmsTemplate.getId();
			}*/
		}
		
		//获取模板文件
		CmsTemplate cmsTemplate =sqlSession.selectOne("CmsTemplate.selectCmsTemplate",cmsTemplateId);
		//获取栏目对应模板路径
		String templatePath = SysConfig.getRootPath()+cmsTemplate.getPath();
		cmsHtmlGenerateContext.setEncoding(cmsTemplate.getEncode());
		cmsHtmlGenerateContext.setChannelUrl(viewCmsChannelInfo.getCreateFilePath());
		cmsHtmlGenerateContext.setCurrentUrl(viewCmsChannelInfo.getCreateFilePath());
		String html = CmsHtmlGenerator.getInstance().build(templatePath,cmsHtmlGenerateContext,context);
		//处理分页
		if(context.getPageHtml()!=null){
			List<StringBuffer> pageHtmlList = context.getPageHtml();
			List<String> pageContentHtmlList = context.getPageContentHtml();
			List<String> pagePathList = setPagePath(viewCmsChannelInfo.getCreateFilePath(), pageHtmlList.size());
			for(int i=0;i<pageHtmlList.size();i++){
				//替换分页内容和分页容器
				String pageHtml = "";
				if(pageContentHtmlList != null && pageContentHtmlList.size()>0){
					pageHtml = buildPaging(context, html ,pageHtmlList.get(i).toString(),pageContentHtmlList.get(i));
				}else{
					//设置autoHide="false"
					pageHtml = buildPaging(context, html ,pageHtmlList.get(i).toString(),null);
				}
				pageHtml = CmsBuildManager.getInstance().formatHtml(pageHtml);
				File file = FileHelper.createFile(pagePathList.get(i));
				FileHelper.writeContents(file, clearTextNodes(pageHtml), cmsTemplate.getEncode());
			}
		}else{
			html = CmsBuildManager.getInstance().formatHtml(html);
			File file = FileHelper.createFile(cmsChannelPath);
			FileHelper.writeContents(file, clearTextNodes(html), cmsTemplate.getEncode());
		}
	}
}

