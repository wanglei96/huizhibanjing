package strosoft.cms.data;

import java.util.HashMap;

public class CmsAudioContext {

	private String isAutoPlay;
	private String isDynamic;
	private String isLoop;
	private String isPreload;
	private String playUrl;
	private String type;
	
	public CmsAudioContext() {
		// TODO Auto-generated constructor stub
	}
	
	public CmsAudioContext(HashMap<String,String> attributeCondition) {
		// TODO Auto-generated constructor stub
		if(attributeCondition.get("isAutoPlay".toLowerCase())==null){
			this.isAutoPlay="";
		}else
		{
		this.isAutoPlay=attributeCondition.get("isAutoPlay".toLowerCase());
		}
		this.isDynamic=attributeCondition.get("isDynamic".toLowerCase());
		this.isLoop=attributeCondition.get("isLoop".toLowerCase());
		this.isPreload=attributeCondition.get("isPreload".toLowerCase());
		this.playUrl=attributeCondition.get("playUrl".toLowerCase());
		this.type=attributeCondition.get("type".toLowerCase());
		attributeCondition.remove("isAutoPlay".toLowerCase());
		attributeCondition.remove("isDynamic".toLowerCase());
		attributeCondition.remove("isLoop".toLowerCase());
		attributeCondition.remove("isPreload".toLowerCase());
		attributeCondition.remove("playUrl".toLowerCase());
		attributeCondition.remove("type".toLowerCase());
		
		
	}
	
	
	public String getIsAutoPlay() {
		return isAutoPlay;
	}
	public void setIsAutoPlay(String isAutoPlay) {
		this.isAutoPlay = isAutoPlay;
	}
	public String getIsDynamic() {
		return isDynamic;
	}
	public void setIsDynamic(String isDynamic) {
		this.isDynamic = isDynamic;
	}
	public String getIsLoop() {
		return isLoop;
	}
	public void setIsLoop(String isLoop) {
		this.isLoop = isLoop;
	}
	public String getIsPreload() {
		return isPreload;
	}
	public void setIsPreload(String isPreload) {
		this.isPreload = isPreload;
	}
	public String getPlayUrl() {
		return playUrl;
	}
	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
