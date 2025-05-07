package strosoft.cms.data;

import java.util.HashMap;

public class CmsChannelsContext {
	private String channelCode; //栏目code
	private String channelIndex; //栏目code
	private String channelName; //栏目名称
	private String groupChannel; //指定显示的栏目组
	private String groupChannelNot; //指定不显示的栏目组
	private String isAllChildren; //是否显示所有级别的子栏目
	private String isImage; //仅显示图片栏目
	private String isTotal; //是否从所有栏目中选择
	private String topLevel; //从首页向下的栏目级别
	private String upLevel; //上级栏目的级别
	private String order;
	private String startNum;
	private String totalNum;
	private String pageNum;//每一页显示的栏目数目
	private String Where;
	
	public CmsChannelsContext(){
		
	}
	public CmsChannelsContext(HashMap<String, String> attributeCondition) {
		this.channelCode = attributeCondition.get("channelCode".toLowerCase());
		this.channelIndex = attributeCondition.get("channelIndex".toLowerCase());
		this.channelName = attributeCondition.get("channelName".toLowerCase());
		this.groupChannel = attributeCondition.get("groupChannel".toLowerCase());
		this.groupChannelNot = attributeCondition.get("groupChannelNot".toLowerCase());
		this.isAllChildren = attributeCondition.get("isAllChildren".toLowerCase());
		this.isImage = attributeCondition.get("isImage".toLowerCase());
		this.isTotal = attributeCondition.get("isTotal".toLowerCase());
		this.topLevel = attributeCondition.get("topLevel".toLowerCase());
		this.upLevel = attributeCondition.get("upLevel".toLowerCase());
		this.order = attributeCondition.get("order".toLowerCase());
		this.startNum = attributeCondition.get("startNum".toLowerCase());
		this.totalNum = attributeCondition.get("totalNum".toLowerCase());
		this.setPageNum(attributeCondition.get("pageNum".toLowerCase()));
		this.Where = attributeCondition.get("Where".toLowerCase());
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getGroupChannel() {
		return groupChannel;
	}
	public void setGroupChannel(String groupChannel) {
		this.groupChannel = groupChannel;
	}
	public String getGroupChannelNot() {
		return groupChannelNot;
	}
	public void setGroupChannelNot(String groupChannelNot) {
		this.groupChannelNot = groupChannelNot;
	}
	public String getIsAllChildren() {
		return isAllChildren;
	}
	public void setIsAllChildren(String isAllChildren) {
		this.isAllChildren = isAllChildren;
	}
	public String getIsImage() {
		return isImage;
	}
	public void setIsImage(String isImage) {
		this.isImage = isImage;
	}
	public String getIsTotal() {
		return isTotal;
	}
	public void setIsTotal(String isTotal) {
		this.isTotal = isTotal;
	}
	public String getTopLevel() {
		return topLevel;
	}
	public void setTopLevel(String topLevel) {
		this.topLevel = topLevel;
	}
	public String getUpLevel() {
		return upLevel;
	}
	public void setUpLevel(String upLevel) {
		this.upLevel = upLevel;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getWhere() {
		return Where;
	}
	public void setWhere(String where) {
		Where = where;
	}
	public String getChannelIndex() {
		return channelIndex;
	}
	public void setChannelIndex(String channelIndex) {
		this.channelIndex = channelIndex;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

}
