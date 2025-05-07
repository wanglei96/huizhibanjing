package strosoft.cms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.cms.data.ViewCmsChannelInfo;
import strosoft.cms.model.CmsBuildManager;
import strosoft.cms.model.CmsBuildTask;
import strosoft.cms.model.CmsContentManager;
import strosoft.cms.model.ViewCmsChannelInfoManager;

/*
 * 添加服务处理类
 */
public class CmsBuildServiceHandler extends ServiceHandler {
	public static List<String> cmsLanguageCodeList = null;
	public static List<Integer> cmsChannelList = null;
	public static List<Integer> cmsHomeList = null;
	public static List<Integer> cmsCustomList = null;
	public static Integer siteId = null;
	public static String buildTaskType = null;
	public static String cmsBuildRange = null;
	public static String adminRootUrl = null;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		// 查询所有栏目viewCmsChannelInfos,放入CmsBuildManager缓存
		addViewCmsChannelInfos();
		if (buildTaskType.equals("Channel")) {
			// 生成类型为栏目时，添加栏目到生成列表
			AddCmsChannelToBuildTaskList(buildTaskType);
		} else if (buildTaskType.equals("Content")) {
			// 生成类型为内容时，添加内容到生成列表
			AddCmsContentToBuildTaskList(buildTaskType);
		} else if (buildTaskType.equals("Home")) {
			// 生成类型为首页时，添加首页到生成列表
			AddCmsHomeToBuildTaskList(buildTaskType);
		} else if (buildTaskType.equals("Custom")) {
			// 生成类型为定制页时，添加定制页到生成列表
			AddCmsCustomToBuildTaskList(buildTaskType);
		} else if (buildTaskType.equals("Site")) {
			// 生成类型为站点时，添加所有到生成列表
			AddAllToBuildTaskList();
		}

		this.writeSuccessResponse(response);

	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		// 设置语言编码列表
		if (jData.containsKey("cmsLanguageCode") && !(jData.get("cmsLanguageCode") instanceof JSONNull)) {
			cmsLanguageCodeList = JsonHelper.getStringArray(jData, "cmsLanguageCode");
		}
		// 设置栏目id列表
		cmsChannelList = JsonHelper.getIntArray(jData, "cmsChannelList");
		// 设置生成首页id列表
		cmsHomeList = JsonHelper.getIntArray(jData, "cmsHomeList");
		// 设置生成定制页id列表
		cmsCustomList = JsonHelper.getIntArray(jData, "cmsCustomList");
		// 设置生成站点id,
		siteId = JsonHelper.getInt(jData, "cmsSiteId");
		// 设置生成时间范围
		cmsBuildRange = JsonHelper.getString(jData, "cmsBuildRange");
		// 获取生成类型
		buildTaskType = JsonHelper.getString(jData, "buildTaskType");
		// 获取后台根地址
		adminRootUrl = SysConfig.getAdminRootUrl();
		Boolean isValid = true;
		if ((buildTaskType.equals("Channel") || buildTaskType.equals("Content"))
				&& JsonHelper.getIntArray(jData, "cmsChannelList").size() < 1) {
			isValid = false;
			this.writeErrorResponse(response, "所选栏目不能为空");
		}

		return isValid;
	}

	/**
	 * 生成类型为栏目时，添加栏目
	 */
	private void AddCmsChannelToBuildTaskList(String buildTaskType) {
		// 根据栏目ID和语言编码获取对应多语言栏目输出实体列表
		for (int i = 0; i < cmsChannelList.size(); i++) {
			if (null == cmsLanguageCodeList || cmsLanguageCodeList.size() < 1) {
				CmsBuildTask cmsBuildTask = new CmsBuildTask();
				cmsBuildTask.setBuildTaskType(buildTaskType);
				cmsBuildTask.setChannelId(cmsChannelList.get(i));
				cmsBuildTask.setSiteId(siteId);
				cmsBuildTask.setAdminRootUrl(adminRootUrl);
				cmsBuildTask.setCmsLanguageCode(null);
				// 向lstCmsBuildTask中添加cmsBuildTask
				CmsBuildManager.getInstance().addCmsBuildTask(cmsBuildTask);
				continue;
			}
			for (int j = 0; j < cmsLanguageCodeList.size(); j++) {
				CmsBuildTask cmsBuildTask = new CmsBuildTask();
				cmsBuildTask.setBuildTaskType(buildTaskType);
				cmsBuildTask.setChannelId(cmsChannelList.get(i));
				cmsBuildTask.setSiteId(siteId);
				cmsBuildTask.setAdminRootUrl(adminRootUrl);
				cmsBuildTask.setCmsLanguageCode(cmsLanguageCodeList.get(j));
				// 向lstCmsBuildTask中添加cmsBuildTask
				CmsBuildManager.getInstance().addCmsBuildTask(cmsBuildTask);
			}
		}
	}

	/**
	 * 生成类型为内容时，添加内容
	 * 
	 * @throws Exception
	 */
	private void AddCmsContentToBuildTaskList(String buildTaskType) throws Exception {
		List<Integer> cmsContentIdList = CmsContentManager.getInstance()
				.getContentIdListByCmsChannelIdList(cmsChannelList);
		for (int i = 0; i < cmsContentIdList.size(); i++) {
			if (null == cmsLanguageCodeList || cmsLanguageCodeList.size() < 1) {
				CmsBuildTask cmsBuildTask = new CmsBuildTask();
				cmsBuildTask.setBuildTaskType(buildTaskType);
				cmsBuildTask.setContentId(cmsContentIdList.get(i));
				cmsBuildTask.setSiteId(siteId);
				cmsBuildTask.setAdminRootUrl(adminRootUrl);
				cmsBuildTask.setCmsLanguageCode(null);
				// 向lstCmsBuildTask中添加cmsBuildTask
				CmsBuildManager.getInstance().addCmsBuildTask(cmsBuildTask);
				continue;
			}
			for (int j = 0; j < cmsLanguageCodeList.size(); j++) {
				CmsBuildTask cmsBuildTask = new CmsBuildTask();
				cmsBuildTask.setBuildTaskType(buildTaskType);
				cmsBuildTask.setContentId(cmsContentIdList.get(i));
				cmsBuildTask.setSiteId(siteId);
				cmsBuildTask.setAdminRootUrl(adminRootUrl);
				cmsBuildTask.setCmsLanguageCode(cmsLanguageCodeList.get(j));
				// 向lstCmsBuildTask中添加cmsBuildTask
				CmsBuildManager.getInstance().addCmsBuildTask(cmsBuildTask);
			}
		}
	}

	/**
	 * 生成类型为首页时，添加首页
	 */
	private void AddCmsHomeToBuildTaskList(String buildTaskType) {
		for (int i = 0; i < cmsHomeList.size(); i++) {
			if (null == cmsLanguageCodeList || cmsLanguageCodeList.size() < 1) {
				CmsBuildTask cmsBuildTask = new CmsBuildTask();
				cmsBuildTask.setBuildTaskType(buildTaskType);
				cmsBuildTask.setTemplateId(cmsHomeList.get(i));
				cmsBuildTask.setSiteId(siteId);
				cmsBuildTask.setAdminRootUrl(adminRootUrl);
				cmsBuildTask.setCmsLanguageCode(null);
				// 向lstCmsBuildTask中添加cmsBuildTask
				CmsBuildManager.getInstance().addCmsBuildTask(cmsBuildTask);
				continue;
			}
			for (int j = 0; j < cmsLanguageCodeList.size(); j++) {
				CmsBuildTask cmsBuildTask = new CmsBuildTask();
				cmsBuildTask.setBuildTaskType(buildTaskType);
				cmsBuildTask.setTemplateId(cmsHomeList.get(i));
				cmsBuildTask.setSiteId(siteId);
				cmsBuildTask.setAdminRootUrl(adminRootUrl);
				cmsBuildTask.setCmsLanguageCode(cmsLanguageCodeList.get(j));
				// 向lstCmsBuildTask中添加cmsBuildTask
				CmsBuildManager.getInstance().addCmsBuildTask(cmsBuildTask);
			}
		}
	}

	/**
	 * 生成类型为定制页时，添加定制页
	 */
	private void AddCmsCustomToBuildTaskList(String buildTaskType) {
		for (int i = 0; i < cmsCustomList.size(); i++) {
			if (null == cmsLanguageCodeList || cmsLanguageCodeList.size() < 1) {
				CmsBuildTask cmsBuildTask = new CmsBuildTask();
				cmsBuildTask.setBuildTaskType(buildTaskType);
				cmsBuildTask.setTemplateId(cmsCustomList.get(i));
				cmsBuildTask.setSiteId(siteId);
				cmsBuildTask.setAdminRootUrl(adminRootUrl);
				cmsBuildTask.setCmsLanguageCode(null);
				// 向lstCmsBuildTask中添加cmsBuildTask
				CmsBuildManager.getInstance().addCmsBuildTask(cmsBuildTask);
				continue;
			}
			for (int j = 0; j < cmsLanguageCodeList.size(); j++) {
				CmsBuildTask cmsBuildTask = new CmsBuildTask();
				cmsBuildTask.setBuildTaskType(buildTaskType);
				cmsBuildTask.setTemplateId(cmsCustomList.get(i));
				cmsBuildTask.setSiteId(siteId);
				cmsBuildTask.setAdminRootUrl(adminRootUrl);
				cmsBuildTask.setCmsLanguageCode(cmsLanguageCodeList.get(j));
				// 向lstCmsBuildTask中添加cmsBuildTask
				CmsBuildManager.getInstance().addCmsBuildTask(cmsBuildTask);
			}
		}
	}

	/**
	 * 生成类型为站点时，添加所有
	 * 
	 * @throws Exception
	 */
	private void AddAllToBuildTaskList() throws Exception {
		// 添加栏目
		cmsChannelList = CmsBuildManager.getInstance().selectChannelIdListBySiteIdAndType(siteId);
		AddCmsChannelToBuildTaskList("Channel");
		// 添加内容
		AddCmsContentToBuildTaskList("Content");
		// 添加首页
		cmsHomeList = CmsBuildManager.getInstance().selectTemplateIdListBySiteIdAndType(siteId, "Home");
		AddCmsHomeToBuildTaskList("Home");
		// 添加定制页
		cmsCustomList = CmsBuildManager.getInstance().selectTemplateIdListBySiteIdAndType(siteId, "Custom");
		AddCmsCustomToBuildTaskList("Custom");
	}

	/**
	 * 查询所有栏目viewCmsChannelInfos,放入CmsBuildManager缓存
	 * 
	 * @throws Exception
	 */
	private void addViewCmsChannelInfos() throws Exception {
		Boolean building = CmsBuildManager.getInstance().getBuilding();
		if (!building) {
			// 在没有其它生成任务时，加载栏目缓存
			List<ViewCmsChannelInfo> lstViewCmsChannelInfo = ViewCmsChannelInfoManager.getInstance()
					.getViewCmsChannelInfoList(siteId);
			CmsBuildManager.getInstance().setViewCmsChannelInfos(lstViewCmsChannelInfo);
		}
	}
}
