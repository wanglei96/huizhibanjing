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

/**
 * 首页生成处理类
 * 
 * @author Administrator
 * 
 */
public class CmsIndexBuildHandler extends CmsBuildHandler {
	@Override
	public void processByCmsLanguage(CmsBuildContext context) throws Exception {
		CmsBuildTask cmsBuildTask = context.getCmsBuildTask();
		int cmsTemplateId = cmsBuildTask.getTemplateId();
		SqlSession sqlSession = context.getSqlSession();
		CmsTemplate cmsTemplate =sqlSession.selectOne("CmsTemplate.selectCmsTemplate",cmsTemplateId);
		//获取栏目对应模板路径
		String templatePath = SysConfig.getRootPath()+cmsTemplate.getPath();
		CmsHtmlGenerateContext cmsHtmlGenerateContext = new CmsHtmlGenerateContext();
		cmsHtmlGenerateContext.setTemplateType(cmsBuildTask.getBuildTaskType());
		cmsHtmlGenerateContext.setEncoding(cmsTemplate.getEncode());
		cmsHtmlGenerateContext.setCmsSiteId(cmsBuildTask.getSiteId());
		cmsHtmlGenerateContext.setCmsLanguageCode(cmsBuildTask.getCmsLanguageCode());
		CmsSiteManager.getInstance().setCmsHtmlGenerateContextBySiteId(cmsHtmlGenerateContext);
		ViewCmsChannelInfo viewCmsChannelInfo = CmsBuildManager.getInstance().getRootViewCmsChannelInfoByCmsSiteId(cmsHtmlGenerateContext.getCmsSiteId(),cmsBuildTask.getCmsLanguageCode());
		if(viewCmsChannelInfo == null){
			return;
		}
		Integer currentChannelId = viewCmsChannelInfo.getId();
		cmsHtmlGenerateContext.setCurrentChannelId(currentChannelId);
		//拼接生成内容文件路径
		String cmsChannelPath = SysConfig.getRootPath()+viewCmsChannelInfo.getCreateFilePath();
		String cmsIndexPath = viewCmsChannelInfo.getCreateFilePath();
		context.setCreatFilePath(viewCmsChannelInfo.getCreateFilePath());
		cmsHtmlGenerateContext.setChannelUrl(cmsIndexPath);
		cmsHtmlGenerateContext.setCurrentUrl(cmsIndexPath);
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
