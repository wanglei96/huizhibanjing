package strosoft.cms.data;

import java.util.HashMap;

public class CmsMetaContext {
	private String name;
	
	public CmsMetaContext() {
		
	}

	public CmsMetaContext(HashMap<String, String> attributeCondition) {
		this.name = attributeCondition.get("name".toLowerCase());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
