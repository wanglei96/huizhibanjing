package strosoft.cms.data;

import java.util.HashMap;

public class CmsImageContext {
	private String altSrc; // "当指定的图片不存在时显示的图片地址"
	private String channelIndex; // "栏目索引"
	private String channelCode; // "栏目索引"
	private String channelName; // "栏目名称"
	private String height; // "高度"
	//private String isDynamic; // "是否动态显示"
	//private String isOriginal; // "如果是引用内容，是否获取所引用内容的值"
	//private String no; // "显示字段的顺序"
	private String parent; // "显示父栏目"
	private String src; // "显示的图片地址"
	private String topLevel; // "从首页向下的栏目级别"
	//private String type; // "指定存储图片的字段"
	private String upLevel; // "上级栏目的级别"
	private String width; // "宽度"
	
	public CmsImageContext() {
	}
	public CmsImageContext(HashMap<String, String> attributeCondition) {
		this.altSrc = attributeCondition.get("altSrc".toLowerCase());
		this.channelIndex = attributeCondition.get("channelIndex".toLowerCase());
		this.channelCode = attributeCondition.get("channelCode".toLowerCase());
		this.channelName = attributeCondition.get("channelName".toLowerCase());
		this.height = attributeCondition.get("height".toLowerCase());
		this.parent = attributeCondition.get("parent".toLowerCase());
		this.src = attributeCondition.get("src".toLowerCase());
		this.topLevel = attributeCondition.get("topLevel".toLowerCase());
		this.upLevel = attributeCondition.get("upLevel".toLowerCase());
		this.width = attributeCondition.get("width".toLowerCase());
		attributeCondition.remove("altSrc".toLowerCase());
		attributeCondition.remove("channelIndex".toLowerCase());
		attributeCondition.remove("channelCode".toLowerCase());
		attributeCondition.remove("channelName".toLowerCase());
		attributeCondition.remove("height".toLowerCase());
		attributeCondition.remove("parent".toLowerCase());
		attributeCondition.remove("src".toLowerCase());
		attributeCondition.remove("topLevel".toLowerCase());
		attributeCondition.remove("upLevel".toLowerCase());
		attributeCondition.remove("width".toLowerCase());
	}
	
	public String getAltSrc() {
		return altSrc;
	}
	public void setAltSrc(String altSrc) {
		this.altSrc = altSrc;
	}
	public String getChannelIndex() {
		return channelIndex;
	}
	public void setChannelIndex(String channelIndex) {
		this.channelIndex = channelIndex;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
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
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

}
