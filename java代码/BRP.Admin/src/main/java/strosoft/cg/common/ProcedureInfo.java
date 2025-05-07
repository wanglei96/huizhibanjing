package strosoft.cg.common;

import strosoft.app.data.DataEntity;

import java.util.Map;

public class ProcedureInfo  extends DataEntity{
	private String name;
	private String moduleName;
	private Map<String,String>  parameters;
	private Integer inputParamterCount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	public Integer getInputParamterCount() {
		return inputParamterCount;
	}
	public void setInputParamterCount(Integer inputParamterCount) {
		this.inputParamterCount = inputParamterCount;
	}
	
	
}
