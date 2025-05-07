package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

import java.util.ArrayList;
import java.util.List;

public class DataMasterCreateEntityMethod extends MethodInfo{
	private String entityName;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("return new ");
		sb.append(entityName);
		sb.append("();");
		return sb.toString();
	}
	
	public void setMethodParameters(){
		this.setMethodName("createEntity");
		this.setReturnType(entityName);
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		this.setParameters(parameters);
	}
}
