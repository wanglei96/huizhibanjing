package strosoft.cg.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import strosoft.cg.common.ClassInfo;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.ViewCodeGenerateContext;

public class ViewMasterClassCodeBuilder {
	private ViewCodeGenerateContext context;

	public ViewCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(ViewCodeGenerateContext context) {
		this.context = context;
	}

	public String generate() {
		ClassInfo classInfo = new ClassInfo();
		classInfo.setClassName(context.getMasterName());
		classInfo.setBaseClasses(getBaseClasses());
		classInfo.setImports(getImports());
		classInfo.setInterfaces(getInterfaces());
		classInfo.setMethods(getMethods());
		classInfo.setModuleName(context.getModuleName());
		classInfo.setPackageName(getPackageName());
		classInfo.setVisibility("public");
		return classInfo.toCode();
	}

	public List<String> getBaseClasses() {
		List<String> baseClasses = new ArrayList<String>();
		baseClasses.add("ViewDataMaster");
		return baseClasses;
	}

	public Set<String> getImports() {
		Set<String> imports = new HashSet<String>();
		imports.add("import strosoft.app.common.ViewDataMaster;");
		imports.add("import strosoft.app.data.DataEntity;");
		imports.add("import org.apache.ibatis.session.SqlSession;");
		imports.add("import strosoft.app.common.MyBatisManager;");
		imports.add("import java.io.IOException;");
		imports.add("import java.util.*;");
		imports.add("import " + context.getModuleDocument() + ".data." + context.getFormattedViewName() + ";");
		return imports;
	}

	public String getInterfaces() {
		String interfaces = null;
		return interfaces;
	}

	public List<MethodInfo> getMethods() {
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		// 获取
		methodInfos.add(getDataMasterGetListMethod());
		methodInfos.add(getDataMasterGetListMethodTransaction());
		methodInfos.add(getDataMasterGetListConditionMethod());
		methodInfos.add(getDataMasterGetListConditionMethodTransaction());
		methodInfos.add(getDataMasterGetListConditionOrderByMethod());
		methodInfos.add(getDataMasterGetListConditionOrderByMethodTransaction());
		methodInfos.add(getDataMasterGetListPageMethod());
		methodInfos.add(getDataMasterGetListPageMethodTransaction());
		methodInfos.add(getDataMasterGetListPageConditionMethod());
		methodInfos.add(getDataMasterGetListPageConditionMethodTransaction());
		methodInfos.add(getDataMasterGetListPageConditionOrderByMethod());
		methodInfos.add(getDataMasterGetListPageConditionOrderByMethodTransaction());
		methodInfos.add(getDataMasterGetCountMethod());
		methodInfos.add(getDataMasterGetCountMethodTransaction());
		// 创建实体
		methodInfos.add(getDataMasterCreateEntityMethod());
		methodInfos.add(getDataMasterCreateDataEntityMethod());
		return methodInfos;
	}

	public String getPackageName() {
		StringBuffer packageName = new StringBuffer();
		packageName.append("package ");
		packageName.append(context.getModuleDocument());
		packageName.append(".data;");
		return packageName.toString();
	}

	public MethodInfo getDataMasterGetListMethod() {
		DataMasterGetListMethod methodInfo = new DataMasterGetListMethod();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListMethodTransaction() {
		DataMasterGetListMethodTransaction methodInfo = new DataMasterGetListMethodTransaction();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListConditionMethod() {
		DataMasterGetListConditionMethod methodInfo = new DataMasterGetListConditionMethod();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListConditionMethodTransaction() {
		DataMasterGetListConditionMethodTransaction methodInfo = new DataMasterGetListConditionMethodTransaction();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListConditionOrderByMethod() {
		DataMasterGetListConditionOrderByMethod methodInfo = new DataMasterGetListConditionOrderByMethod();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListConditionOrderByMethodTransaction() {
		DataMasterGetListConditionOrderByMethodTransaction methodInfo = new DataMasterGetListConditionOrderByMethodTransaction();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageMethod() {
		DataMasterGetListPageMethod methodInfo = new DataMasterGetListPageMethod();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageMethodTransaction() {
		DataMasterGetListPageMethodTransaction methodInfo = new DataMasterGetListPageMethodTransaction(context);
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageConditionMethod() {
		DataMasterGetListPageConditionMethod methodInfo = new DataMasterGetListPageConditionMethod();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageConditionMethodTransaction() {
		DataMasterGetListPageConditionMethodTransaction methodInfo = new DataMasterGetListPageConditionMethodTransaction();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageConditionOrderByMethod() {
		DataMasterGetListPageConditionOrderByMethod methodInfo = new DataMasterGetListPageConditionOrderByMethod();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageConditionOrderByMethodTransaction() {
		DataMasterGetListPageConditionOrderByMethodTransaction methodInfo = new DataMasterGetListPageConditionOrderByMethodTransaction();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetCountMethod() {
		DataMasterGetCountMethod methodInfo = new DataMasterGetCountMethod();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetCountMethodTransaction() {
		DataMasterGetCountMethodTransaction methodInfo = new DataMasterGetCountMethodTransaction();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterCreateDataEntityMethod() {
		DataMasterCreateDataEntityMethod methodInfo = new DataMasterCreateDataEntityMethod();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

	public MethodInfo getDataMasterCreateEntityMethod() {
		DataMasterCreateEntityMethod methodInfo = new DataMasterCreateEntityMethod();
		methodInfo.setEntityName(context.getFormattedViewName());
		return methodInfo;
	}

}
