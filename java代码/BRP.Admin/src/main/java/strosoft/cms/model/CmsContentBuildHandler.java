package strosoft.cms.model;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.ibatis.session.SqlSession;
import org.xml.sax.SAXException;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.util.FileHelper;
import strosoft.cms.data.CmsTemplate;
import strosoft.cms.data.ViewCmsChannel;
import strosoft.cms.data.ViewCmsChannelInfo;
import strosoft.cms.data.ViewCmsContent;
import strosoft.cms.data.ViewCmsContentInfo;

public class CmsContentBuildHandler extends CmsBuildHandler {
	@SuppressWarnings("unused")
	@Override
	public void processByCmsLanguage(CmsBuildContext context) throws Exception{
		CmsBuildTask cmsBuildTask = context.getCmsBuildTask();
		CmsHtmlGenerateContext cmsHtmlGenerateContext = new CmsHtmlGenerateContext();
		int cmsContentId = context.getCmsBuildTask().getContentId();
		cmsHtmlGenerateContext.setCmsSiteId(cmsBuildTask.getSiteId());
		cmsHtmlGenerateContext.setCmsLanguageCode(cmsBuildTask.getCmsLanguageCode());
		CmsSiteManager.getInstance().setCmsHtmlGenerateContextBySiteId(cmsHtmlGenerateContext);
		SqlSession sqlSession = context.getSqlSession();
		ViewCmsContentInfo viewCmsContentInfo = CmsBuildManager.getInstance().getViewCmsContentInfoByCmsContentIdAndCode(sqlSession, cmsBuildTask.getSiteId(), cmsContentId, cmsBuildTask.getCmsLanguageCode());
		//拼接生成内容文件路径
		String cmsContentFilePath = SysConfig.getRootPath()+viewCmsContentInfo.getCreateFilePath();
		Integer cmsChannelId = viewCmsContentInfo.getCmsChannelId();
		ViewCmsChannelInfo viewCmsChannelinfo = CmsBuildManager.getInstance().getViewCmsChannelInfoByCmsChannelIdAndCode(cmsChannelId, cmsBuildTask.getCmsLanguageCode());
		CmsTemplate cmsTemplate = null;
		//未匹配模板
		if(viewCmsContentInfo.getCmsContentTemplateUrl() == null){
			return;
			/*Integer cmsSiteId = viewCmsContent.getCmsSiteId();
			String cmsBuildType = context.getCmsBuildTask().getBuildTaskType();
			cmsTemplate = CmsTemplateManager.getInstance().selectDefaultCmsTemplate(cmsBuildType.toLowerCase(),cmsSiteId);
			if(cmsTemplate == null){
				//未设置默认模板，生成空白页
				sqlSession.close();
				System.out.println("未定义默认模板");
				FileHelper.createFile(cmsContentFilePath);
				return;
			}*/
		}else{
			cmsTemplate = sqlSession.selectOne("CmsTemplate.selectCmsTemplate",viewCmsContentInfo.getCmsContentTemplateId());
		}
		//获取内容对应模板路径
		String templatePath = SysConfig.getRootPath()+viewCmsContentInfo.getCmsContentTemplateUrl();
		String cmsContentPath = viewCmsContentInfo.getCreateFilePath();
		context.setCreatFilePath(viewCmsContentInfo.getCreateFilePath());
		cmsHtmlGenerateContext.setCurrentShowType("content");
		cmsHtmlGenerateContext.setEncoding(cmsTemplate.getEncode());
		cmsHtmlGenerateContext.setChannelUrl(viewCmsChannelinfo.getCreateFilePath());
		cmsHtmlGenerateContext.setCurrentUrl(cmsContentPath);
		cmsHtmlGenerateContext.setCurrentCmsContent(viewCmsContentInfo);
		cmsHtmlGenerateContext.setCurrentChannelId(cmsChannelId);
		cmsHtmlGenerateContext.setCurrentContentId(cmsContentId);
		String html = CmsHtmlGenerator.getInstance().build(templatePath,cmsHtmlGenerateContext,context);
		//处理分页
		if(context.getPageHtml()!=null){
			List<StringBuffer> pageHtmlList = context.getPageHtml();
			List<String> pageContentHtmlList = context.getPageContentHtml();
			List<String> pagePathList = setPagePath(viewCmsContentInfo.getCreateFilePath(), pageHtmlList.size());
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
				FileHelper.writeContents(file,  clearTextNodes(pageHtml), cmsTemplate.getEncode());
			}
		}else{
			html = CmsBuildManager.getInstance().formatHtml(html);
			File file = FileHelper.createFile(cmsContentFilePath);
			FileHelper.writeContents(file,  clearTextNodes(html), cmsTemplate.getEncode());
		}
	}
}
