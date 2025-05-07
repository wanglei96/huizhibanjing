package strosoft.cg.common;

import strosoft.app.data.DataEntity;

import java.util.List;
import java.util.Set;

public class ClassInfo  extends DataEntity {
	private String className;
	private String moduleName;
	private String visibility;
	private String packageName;
	private List<ConstructInfo> constructs;
	private Set<String> imports;
	private List<FieldInfo> fields;
	private List<MethodInfo> methods;
	private List<String> baseClasses;
	private String interfaces;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public List<ConstructInfo> getConstructs() {
		return constructs;
	}
	public void setConstructs(List<ConstructInfo> constructs) {
		this.constructs = constructs;
	}
	public List<FieldInfo> getFields() {
		return fields;
	}
	public void setFields(List<FieldInfo> fields) {
		this.fields = fields;
	}
	public List<MethodInfo> getMethods() {
		return methods;
	}
	public void setMethods(List<MethodInfo> methods) {
		this.methods = methods;
	}
	public List<String> getBaseClasses() {
		return baseClasses;
	}
	public void setBaseClasses(List<String> baseClasses) {
		this.baseClasses = baseClasses;
	}
	public String getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public Set<String> getImports() {
		return imports;
	}
	public void setImports(Set<String> imports) {
		this.imports = imports;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		String packageString = this.packageName;
		String importString = getImportString();
		String visibilitySting = this.visibility;
		String classNameString = this.className;
		String interfaceString = getInterfaceString();
		String baseClasseString = getBaseClasseString();
		String fieldString = getFieldString();
		String constructString = getConstructString();
		String methodString = getMethodString();
		sb.append(packageString);
		sb.append(importString);
		sb.append(visibilitySting);
		sb.append(" class ");
		sb.append(classNameString);
		sb.append(" ");
		sb.append(baseClasseString);
		sb.append(" ");
		sb.append(interfaceString);
		sb.append("{");
		sb.append(fieldString);
		sb.append(constructString);
		sb.append(methodString);
		sb.append("}");
		return sb.toString();
	}
	public String getImportString(){
		StringBuffer importString = new StringBuffer();
		for(String value:this.imports){
			importString.append(value);
		}
		return importString.toString();
	}
	public String getInterfaceString(){
		if(null == this.interfaces){
			return "";
		}
		StringBuffer string = new StringBuffer();
		string.append("implements ");
		string.append(this.interfaces);
		return string.toString();
	}
	public String getBaseClasseString(){
		if(null == this.baseClasses || this.baseClasses.size()<1){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("extends ");
		sb.append(this.baseClasses.get(0));
		for(int i=1;i<this.baseClasses.size();i++){
			sb.append(",");
			sb.append(this.baseClasses.get(i));
		}
		return sb.toString();
	}
	public String getFieldString(){
		if(null == this.fields || this.fields.size()<1){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		List<FieldInfo> fields = this.getFields();
		for(int i=0;i<fields.size();i++){
			sb.append(fields.get(i).toCode());
		}
		return sb.toString();
	}
	public String getConstructString(){
		List<ConstructInfo> constructs = this.constructs;
		if(null == constructs || constructs.size()<1){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<constructs.size();i++){
			sb.append(constructs.get(i).toCode());
		}
		return sb.toString();
	}
	public String getMethodString(){
		List<MethodInfo> methods = this.getMethods();
		if(null == methods || methods.size()<1){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<methods.size();i++){
			sb.append(methods.get(i).toCode());
		}
		return sb.toString();
	}
	
}
