package strosoft.cg.model;

import strosoft.cg.common.ClassInfo;
import strosoft.cg.common.ConstructInfo;
import strosoft.cg.common.FieldInfo;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.ViewCodeGenerateContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewManagerClassCodeBuilder {
	private ViewCodeGenerateContext context;

	public ViewCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(ViewCodeGenerateContext context) {
		this.context = context;
	}

	public String generate(){
		ClassInfo classInfo = new ClassInfo();
		classInfo.setClassName(context.getManagerName());
		classInfo.setBaseClasses(getBaseClasses());
		classInfo.setImports(getImports());
		classInfo.setConstructs(getConstructInfo());
		classInfo.setFields(getFields());
		classInfo.setInterfaces(getInterfaces());
		classInfo.setMethods(getMethods());
		classInfo.setModuleName(context.getModuleName());
		classInfo.setPackageName(getPackageName());
		classInfo.setVisibility("public");
		return classInfo.toCode();
	}
	
	public List<String> getBaseClasses(){
		List<String> baseClasses = new ArrayList<String>();
		baseClasses.add(context.getMasterName());
		return baseClasses;
	}
	
	public Set<String> getImports(){
		Set<String> imports = new HashSet<String>();
		imports.add("import "+context.getModuleDocument()+".data."+context.getMasterName()+";");
		return imports;
	}
	public List<ConstructInfo> getConstructInfo(){
		List<ConstructInfo> constructInfos = new ArrayList<ConstructInfo>();
		ConstructInfo constructInfo = new ConstructInfo();
		constructInfo.setClassName(context.getManagerName());
		constructInfo.setParameters(null);
		constructInfo.setVisibility("private");
		StringBuffer methodBody = new StringBuffer();
		methodBody.append("this.setTableName(\"");
		methodBody.append(context.getViewName());
		methodBody.append("\");this.setTypeName(\"");
		methodBody.append(context.getFormattedViewName());
		methodBody.append("\");");
		constructInfo.setMethodBody(methodBody.toString());
		constructInfos.add(constructInfo);
		return null;
	}
	
	public List<FieldInfo> getFields(){
		List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();
		FieldInfo fieldInfo = new FieldInfo();
		fieldInfo.setVisibility("private");
		fieldInfo.setTypeName(context.getManagerName());
		fieldInfo.setFieldName("_instance");
		fieldInfo.setModifier("static");
		fieldInfos.add(fieldInfo);
		return fieldInfos;
	}
	
	public String getInterfaces(){
		String interfaces = null;
		return interfaces;
	}
	public String getPackageName(){
		StringBuffer packageName = new StringBuffer();
		packageName.append("package ");
		packageName.append(context.getModuleDocument());
		packageName.append(".model;");
		return packageName.toString();
	}
	
	public List<MethodInfo> getMethods(){
		List<MethodInfo> methods = new ArrayList<MethodInfo>();
		//取得实例
		DataManagerGetInstanceMethod getInstanceMethod = new DataManagerGetInstanceMethod();
		getInstanceMethod.setManagerName(context.getManagerName());
		methods.add(getInstanceMethod);
		//私有构造函数
		DataManagerConstructMethod constructMethod = new DataManagerConstructMethod(context);
		methods.add(constructMethod);
		return methods;
	}
}
