package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;

public class DataManagerGeInstanceMethod extends MethodInfo{
	private String managerName;
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("if (_instance == null) {_instance = new ");
		sb.append(managerName);
		sb.append("();}return _instance;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setIsStatic(true);
		this.setVisibility("public");
		this.setMethodName("getInstance");
		this.setReturnType(managerName);
	}

	
}
