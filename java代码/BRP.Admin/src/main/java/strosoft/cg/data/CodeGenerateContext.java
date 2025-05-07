package strosoft.cg.data;

public class CodeGenerateContext {

	private String dataName;
	private String moduleName;
	private String moduleDocument;
	private String modulePath;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDocument() {
		return moduleDocument;
	}

	public void setModuleDocument(String moduleDocument) {
		this.moduleDocument = moduleDocument;
	}

	public String getModulePath() {
		return modulePath;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
}
