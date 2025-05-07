package strosoft.cms.data;

import java.util.HashMap;

public class CmsStrAContext {
		
	private String channelIndex;
	private String channelCode;
	private String channelName;
	private String context;
	private String host;
	
	private String href;
	private String isDynamic;
	
	private String parent;
	private String queryString;
	private String topLevel;
	private String upLevel;
	private String target;
	public CmsStrAContext() {
		
	}
	//getParent
	public CmsStrAContext(HashMap<String, String> attributeCondition) {
		this.channelIndex=attributeCondition.get("channelIndex".toLowerCase());
		this.setChannelCode(attributeCondition.get("channelCode".toLowerCase()));
		this.channelName=attributeCondition.get("channelName".toLowerCase());
		this.context=attributeCondition.get("context".toLowerCase());
		this.host=attributeCondition.get("host".toLowerCase());
		this.href=attributeCondition.get("href".toLowerCase());
		this.isDynamic=attributeCondition.get("isDynamic".toLowerCase());
		
		if(attributeCondition.get("parent".toLowerCase())==null){
			this.parent="";
		}else{
		this.parent=attributeCondition.get("parent".toLowerCase());
		}
		this.queryString=attributeCondition.get("queryString".toLowerCase());
		this.topLevel=attributeCondition.get("topLevel".toLowerCase());
		this.upLevel=attributeCondition.get("upLevel".toLowerCase());
		this.target=attributeCondition.get("target".toLowerCase());
		attributeCondition.remove("channelIndex".toLowerCase());
		attributeCondition.remove("channelName".toLowerCase());
		attributeCondition.remove("context".toLowerCase());
		attributeCondition.remove("host".toLowerCase());
		attributeCondition.remove("href".toLowerCase());
		attributeCondition.remove("isDynamic".toLowerCase());
		attributeCondition.remove("parent".toLowerCase());
		attributeCondition.remove("queryString".toLowerCase());
		attributeCondition.remove("topLevel".toLowerCase());
		attributeCondition.remove("upLevel".toLowerCase());
		attributeCondition.remove("target".toLowerCase());
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
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
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getIsDynamic() {
		return isDynamic;
	}
	public void setIsDynamic(String isDynamic) {
		this.isDynamic = isDynamic;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
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
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	
	
	
}
