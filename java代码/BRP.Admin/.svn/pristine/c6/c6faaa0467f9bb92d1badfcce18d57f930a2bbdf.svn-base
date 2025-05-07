package strosoft.cg.common;

import java.util.List;

public class ConstructInfo {
	private String visibility;
	private String className;
	private List<MethodParameter> parameters;
	private String methodBody;
	public ConstructInfo(){
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<MethodParameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<MethodParameter> parameters) {
		this.parameters = parameters;
	}
	
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.visibility);
		sb.append(" ");
		sb.append(this.className);
		sb.append("(");
		sb.append(getParameterString());
		sb.append("){");
		sb.append(getMethodBody());
		sb.append("}");
		return sb.toString();
	}
	public String getParameterString(){
		List<MethodParameter> parameters = this.parameters;
		if(null == parameters || parameters.size()<1){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(parameters.get(0).getType());
		sb.append(" ");
		sb.append(parameters.get(0).getName());
		for(int i=1;i<this.parameters.size();i++){
			sb.append(",");
			sb.append(parameters.get(i).getType());
			sb.append(" ");
			sb.append(parameters.get(i).getName());
		}
		return sb.toString();
	}
	
	public String getMethodBody(){
		if(null != this.methodBody){
			return  this.methodBody;
		}
		List<MethodParameter> parameters = this.parameters;
		if(null == parameters || parameters.size()<1){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("super();");
		for(int i=0;i<parameters.size();i++){
			sb.append("this.");
			sb.append(parameters.get(i));
			sb.append(" = ");
			sb.append(parameters.get(i));
			sb.append(";");
		}
		return sb.toString();
	}
	public void setMethodBody(String methodBody) {
		this.methodBody = methodBody;
	}
}
