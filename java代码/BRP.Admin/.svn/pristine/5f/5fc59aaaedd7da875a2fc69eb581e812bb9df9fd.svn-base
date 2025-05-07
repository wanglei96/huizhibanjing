package strosoft.cms.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.SysConfig;
import strosoft.app.util.FileHelper;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.CmsSite;
import strosoft.cms.data.CmsTemplate;

public class CmsSiteManager extends IdDataManager {

	private static CmsSiteManager _instance;

	public static CmsSiteManager getInstance() {
		if (_instance == null) {
			_instance = new CmsSiteManager();
		}
		return _instance;
	}

	private CmsSiteManager() {
		this.setTableName("cms_site");
		this.setTypeName("CmsSite");
	}

	/*
	 * 取得实体
	 */
	public CmsSite getEntity(int CmsSiteId) throws Exception {
		return (CmsSite) super.getDataEntity(CmsSiteId);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CmsSite> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	/*
	 * 设置生产页面时的站点参数
	 */
	public void setCmsHtmlGenerateContextBySiteId(CmsHtmlGenerateContext cmsHtmlGenerateContext) throws Exception {
		CmsSite cmsSite = getEntity(cmsHtmlGenerateContext.getCmsSiteId());
		cmsHtmlGenerateContext.setRootUrl(SysConfig.getAdminRootUrl());
		cmsHtmlGenerateContext.setSiteDir(cmsSite.getPath() == null ? "" : cmsSite.getPath());
		cmsHtmlGenerateContext.setSiteName(cmsSite.getName());
		// 网站路径
		String siteUrl = SysConfig.getContextPath() + (cmsSite.getPath() == null ? "" : "/" + cmsSite.getPath());
		cmsHtmlGenerateContext.setSiteUrl(siteUrl);
	}

	// 新建站点
	public int add(SqlSession sqlSession, CmsSite cmsSite) throws Exception {
		String path = cmsSite.getPath();
		// 新增站点
		Integer newId = super.add(sqlSession, cmsSite);
		// 站点根文件夹
		String siteDir = SysConfig.getRootPath() + path;
		// 创建系统默认首页模板
		String indexPath = siteDir + "/templates/T_系统首页模板.xml";
		CmsTemplate indexTemplate = new CmsTemplate();
		indexTemplate.setCmsSiteId(newId);
		indexTemplate.setCmsTemplateTypeCode("Home");
		indexTemplate.setCreatedFileExtName(".html");
		indexTemplate.setEncode("UTF-8");
		indexTemplate.setName("系统首页模板");
		indexTemplate.setPath(path + "/templates/T_系统首页模板.xml");
		indexTemplate.setRelatedFileName("T_系统首页模板");
		indexTemplate.setUrl("index");
		Integer indexTemplateId = CmsTemplateManager.getInstance().add(sqlSession, indexTemplate);
		// 创建首页栏目
		CmsChannel index = new CmsChannel();
		index.setContentType("UTF-8");
		index.setCmsSiteId(newId);
		index.setName("index");
		index.setParentId(0);
		index.setDisplayOrder(0);
		index.setCmsChannelTemplateId(indexTemplateId);
		CmsChannelManager.getInstance().add(sqlSession, index);
		// 创建系统默认栏目模板
		String channelTemplatePath = siteDir + "/templates/channel/T_系统栏目模板.xml";
		CmsTemplate channelTemplate = indexTemplate;
		channelTemplate.setId(null);
		channelTemplate.setCmsTemplateTypeCode("Channel");
		channelTemplate.setName("系统栏目模板");
		channelTemplate.setPath(path + "/templates/channel/T_系统栏目模板.xml");
		channelTemplate.setRelatedFileName("T_系统栏目模板");
		channelTemplate.setUrl(null);
		CmsTemplateManager.getInstance().add(sqlSession, channelTemplate);
		// 创建系统默认内容模板
		String contentTemplatePath = siteDir + "/templates/content/T_系统内容模板.xml";
		CmsTemplate contentCmsTemplate = channelTemplate;
		contentCmsTemplate.setId(null);
		contentCmsTemplate.setCmsTemplateTypeCode("Content");
		contentCmsTemplate.setName("系统内容模板");
		contentCmsTemplate.setPath(path + "/templates/content/T_系统内容模板.xml");
		contentCmsTemplate.setRelatedFileName("T_系统内容模板");
		CmsTemplateManager.getInstance().add(sqlSession, contentCmsTemplate);
		// 创建首页模板
		FileHelper.createFile(indexPath);
		// 创建栏目模板
		FileHelper.createFile(channelTemplatePath);
		// 创建内容模板
		FileHelper.createFile(contentTemplatePath);
		return newId;
	}

}
