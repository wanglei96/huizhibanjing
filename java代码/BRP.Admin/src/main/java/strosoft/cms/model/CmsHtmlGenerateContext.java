package strosoft.cms.model;

import org.w3c.dom.Node;

import strosoft.cms.data.ViewCmsChannel;
import strosoft.cms.data.ViewCmsChannelInfo;
import strosoft.cms.data.ViewCmsContent;
import strosoft.cms.data.ViewCmsContentInfo;

/*
 * Html生成环境参数
 */
public class CmsHtmlGenerateContext implements Cloneable {
	private String templateType;// 模板类型
	private Node currentNode;// 当前节点
	private int currentChannelId;// 栏目ID
	private int currentContentId;// 栏目ID
	private String currentChannelGroupCode;// 栏目组
	// private HashMap<String, String> conditions; //筛选条件
	private ViewCmsContentInfo currentCmsContent;
	private ViewCmsChannelInfo currentCmsChannel;
	private int num;// 当前编号,显示顺序,整个数组中的第几项
	private int numOnPage;// 当前页面的第几项
	private String order;// 排序
	private int totalNum;
	private String encoding;// 编码
	private Integer cmsSiteId;// 所属站点
	private String poweredBy;// PoweredBy 链接
	private String siteName;// 站点名称
	private String siteDir;// 站点文件夹，如果为总站点，值为空
	private String siteUrl;// 站点根目录地址
	private String rootUrl;// 系统根目录地址
	private String currentUrl;// 当前页地址
	private String channelUrl;// 栏目页地址
	private String currentShowType;// 当前显示栏目还是内容
	private int currentPage;// 当前页
	private String cmsLanguageCode;// 语言编码

	@Override
	protected CmsHtmlGenerateContext clone() throws CloneNotSupportedException {
		CmsHtmlGenerateContext newContext = (CmsHtmlGenerateContext) super
				.clone();
		return newContext;
	}

	public String getPoweredBy() {
		return poweredBy;
	}

	public void setPoweredBy(String poweredBy) {
		this.poweredBy = poweredBy;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteDir() {
		return siteDir;
	}

	public void setSiteDir(String siteDir) {
		this.siteDir = siteDir;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getRootUrl() {
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	public String getCurrentUrl() {
		return currentUrl;
	}

	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public int getCurrentChannelId() {
		return currentChannelId;
	}

	public void setCurrentChannelId(int currentChannelId) {
		this.currentChannelId = currentChannelId;
	}

	public ViewCmsContentInfo getCurrentCmsContent() {
		return currentCmsContent;
	}

	public void setCurrentCmsContent(ViewCmsContentInfo currentCmsContent) {
		this.currentCmsContent = currentCmsContent;
	}

	public ViewCmsChannelInfo getCurrentCmsChannel() {
		return currentCmsChannel;
	}

	public void setCurrentCmsChannel(ViewCmsChannelInfo currentCmsChannel) {
		this.currentCmsChannel = currentCmsChannel;
	}

	public String getCurrentChannelGroupCode() {
		return currentChannelGroupCode;
	}

	public void setCurrentChannelGroupCode(String currentChannelGroupCode) {
		this.currentChannelGroupCode = currentChannelGroupCode;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Integer getCmsSiteId() {
		return cmsSiteId;
	}

	public void setCmsSiteId(Integer cmsSiteId) {
		this.cmsSiteId = cmsSiteId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCurrentShowType() {
		return currentShowType;
	}

	public void setCurrentShowType(String currentShowType) {
		this.currentShowType = currentShowType;
	}

	public int getCurrentContentId() {
		return currentContentId;
	}

	public void setCurrentContentId(int currentContentId) {
		this.currentContentId = currentContentId;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumOnPage() {
		return numOnPage;
	}

	public void setNumOnPage(int numOnPage) {
		this.numOnPage = numOnPage;
	}

	public String getCmsLanguageCode() {
		return cmsLanguageCode;
	}

	public void setCmsLanguageCode(String cmsLanguageCode) {
		this.cmsLanguageCode = cmsLanguageCode;
	}

}
