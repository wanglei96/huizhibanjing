package strosoft.cg.model;

import strosoft.cg.common.*;
import strosoft.cg.data.TableCodeGenerateContext;
import strosoft.cg.data.CgTableColumn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableEntityClassCodeBuilder{
	private TableCodeGenerateContext context = null;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	
	public String generate(){
		ClassInfo classInfo = new ClassInfo();
		List<MethodInfo> methodInfos = getMethods();
		List<FieldInfo> fieldInfos = getFields();
		Set<String> imports = getImports();
		List<String> baseClasses = new ArrayList<String>();
		baseClasses.add("DataEntity");
		String packageString = getPackageString();
		classInfo.setClassName(context.getFormattedTableName());
		classInfo.setFields(fieldInfos);
		classInfo.setImports(imports);
		classInfo.setMethods(methodInfos);
		classInfo.setPackageName(packageString);
		classInfo.setVisibility("public");
		classInfo.setBaseClasses(baseClasses);
		classInfo.setInterfaces("Cloneable");
		return classInfo.toCode();
	}
	
	public List<MethodInfo> getMethods(){
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		methodInfos.addAll(getSetGetMethodInfos());
		methodInfos.add(getTableEntityCopyEntityMethodinfos());
		methodInfos.add(getTableToJsonMethodinfos());
		return methodInfos;
	}
	
	public List<PropertyInfo> getProperties(){
		List<PropertyInfo> propertyInfos = new ArrayList<PropertyInfo>();
		return propertyInfos;
	}
	
	public String getPackageString(){
		return "package "+context.getModuleDocument()+".data;";
	}
	
	
	public List<FieldInfo> getFields(){
		List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();
		for(int i=0;i<context.getColums().size();i++){
			CgTableColumn currColumn = context.getColums().get(i);
			FieldInfo fieldInfo = new FieldInfo();    
			fieldInfo.setVisibility("private");
			fieldInfo.setFieldName(currColumn.getParameterName());
			fieldInfo.setTypeName(CodeGenerateUtil.getTypeString(currColumn.getTypeName()));
			if(null != currColumn.getCaption()){
				fieldInfo.setCaption(currColumn.getCaption());
			}
			fieldInfos.add(fieldInfo);
		}
		return fieldInfos;
	}
	
	public Set<String> getImports(){
		Set<String> imports = new HashSet<String>();
		imports.add("import net.sf.json.JSONObject;");
		imports.add("import strosoft.app.util.JsonHelper;");
		imports.add("import strosoft.app.data.DataEntity;");
		for(int i=0;i<context.getColums().size();i++){
			CgTableColumn currentColumn = context.getColums().get(i);
			imports.add("import "+currentColumn.getTypeName()+";");
		}
		return imports;
	}

	public MethodInfo getTableToJsonMethodinfos(){
		DataEntityToJsonMethod dataEntityToJsonMethod = new DataEntityToJsonMethod();
		dataEntityToJsonMethod.setIsAbstract(false);
		dataEntityToJsonMethod.setIsOverride(false);
		dataEntityToJsonMethod.setIsStatic(false);
		dataEntityToJsonMethod.setMethodName("toJson");
		dataEntityToJsonMethod.setVisibility("public");
		dataEntityToJsonMethod.setReturnType("JSONObject");
		return dataEntityToJsonMethod;
	}
	
	public MethodInfo getTableEntityCopyEntityMethodinfos(){
		TableEntityCopyEntityMethod tableEntityCopyEntityMethod = new TableEntityCopyEntityMethod();
		tableEntityCopyEntityMethod.setIsAbstract(false);
		tableEntityCopyEntityMethod.setIsOverride(false);
		tableEntityCopyEntityMethod.setIsStatic(false);
		tableEntityCopyEntityMethod.setMethodName("copy");
		tableEntityCopyEntityMethod.setVisibility("public");
		tableEntityCopyEntityMethod.setReturnType(context.getFormattedTableName());
		tableEntityCopyEntityMethod.setContext(context);
		return tableEntityCopyEntityMethod;
	}
	
	public List<MethodInfo> getSetGetMethodInfos(){
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		for(int i=0;i<context.getColums().size();i++){
			CgTableColumn currColumn = context.getColums().get(i);
			TableEntityGetValueMethod dataEntityGetValueMethod = new TableEntityGetValueMethod();
			dataEntityGetValueMethod.setIsAbstract(false);
			dataEntityGetValueMethod.setIsOverride(false);
			dataEntityGetValueMethod.setIsStatic(false);
			dataEntityGetValueMethod.setVisibility("public");
			dataEntityGetValueMethod.setColumn(currColumn);
			dataEntityGetValueMethod.setReturnType(CodeGenerateUtil.getTypeString(currColumn.getTypeName()));
			dataEntityGetValueMethod.setMethodName("get"+CodeGenerateUtil.upperCase(currColumn.getParameterName()));
			TableEntitySetValueMethod dataEntitySetValueMethod = new TableEntitySetValueMethod();
			dataEntitySetValueMethod.setIsAbstract(false);
			dataEntitySetValueMethod.setIsOverride(false);
			dataEntitySetValueMethod.setIsStatic(false);
			dataEntitySetValueMethod.setVisibility("public");
			dataEntitySetValueMethod.setColumn(currColumn);
			dataEntitySetValueMethod.setReturnType("void");
			dataEntitySetValueMethod.setMethodName("set"+CodeGenerateUtil.upperCase(currColumn.getParameterName()));
			List<MethodParameter> parameters = new ArrayList<MethodParameter>();
			MethodParameter parameter = new MethodParameter();
			parameter.setName(currColumn.getParameterName());
			parameter.setType(CodeGenerateUtil.getTypeString(currColumn.getTypeName()));
			parameters.add(parameter);
			dataEntitySetValueMethod.setParameters(parameters);
			methodInfos.add(dataEntitySetValueMethod);
			methodInfos.add(dataEntityGetValueMethod);
		}
		return methodInfos;
	}
}
