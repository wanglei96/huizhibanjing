package strosoft.cms.data;

import java.util.HashMap;

public class CmsIncludeContext {

	private String file;
	private String isContext;
	private String isDynamic;
	public CmsIncludeContext(){
		
	}
	public CmsIncludeContext(HashMap<String, String> attributeCondition) {
		this.file = attributeCondition.get("file".toLowerCase());
		this.isContext = attributeCondition.get("isContext".toLowerCase());
		this.isDynamic = attributeCondition.get("isDynamic".toLowerCase());
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getIsContext() {
		return isContext;
	}
	public void setIsContext(String isContext) {
		this.isContext = isContext;
	}
	public String getIsDynamic() {
		return isDynamic;
	}
	public void setIsDynamic(String isDynamic) {
		this.isDynamic = isDynamic;
	}
	
	
}
