package strosoft.cms.model;

/**
 * 生成任务
 * @author Administrator
 *
 */
public class CmsBuildTask {
	private String buildTaskType;
	private int channelId;
	private int contentId;
	private int templateId;
	private int siteId;
	private String encoding;
	private String adminRootUrl;
	//private List<String> cmsLanguageCodeList;//多语言编码列表
	private int currentIndex;//按语言生成的次序
	private String cmsLanguageCode;
	
	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getBuildTaskType() {
		return buildTaskType;
	}

	public void setBuildTaskType(String buildTaskType) {
		this.buildTaskType = buildTaskType;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getAdminRootUrl() {
		return adminRootUrl;
	}

	public void setAdminRootUrl(String adminRootUrl) {
		this.adminRootUrl = adminRootUrl;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public String getCmsLanguageCode() {
		return cmsLanguageCode;
	}

	public void setCmsLanguageCode(String cmsLanguageCode) {
		this.cmsLanguageCode = cmsLanguageCode;
	}

}
