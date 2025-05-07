package strosoft.cg.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import strosoft.cg.common.ClassInfo;
import strosoft.cg.common.ConstructInfo;
import strosoft.cg.common.FieldInfo;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.UniqueFieldInfo;
import strosoft.cg.data.TableCodeGenerateContext;
import strosoft.cg.data.CgTableForeignKey;

public class TableMasterClassCodeBuilder {
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}

	public String generate() {
		ClassInfo classInfo = new ClassInfo();
		classInfo.setClassName(context.getMasterName());
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

	public List<String> getBaseClasses() {
		List<String> baseClasses = new ArrayList<String>();
		if (null == context.getPrimaryKeyType()) {

		} else if (context.getPrimaryKeyType().equals("Id")) {
			baseClasses.add("IdDataManager");

		} else if (context.getPrimaryKeyType().equals("Code")) {
			baseClasses.add("CodeDataManager");

		} else if (context.getPrimaryKeyType().equals("Composite")) {
			baseClasses.add("TableDataMaster");

		}
		return baseClasses;
	}

	public Set<String> getImports() {
		Set<String> imports = new HashSet<String>();
		if (null == context.getPrimaryKeyType()) {

		} else if (context.getPrimaryKeyType().equals("Id")) {
			imports.add("import strosoft.app.common.IdDataManager;");

		} else if (context.getPrimaryKeyType().equals("Code")) {
			imports.add("import strosoft.app.common.CodeDataManager;");

		} else if (context.getPrimaryKeyType().equals("Composite")) {
			imports.add("import strosoft.app.common.TableDataMaster;");

		}
		imports.add("import org.apache.ibatis.session.SqlSession;");
		imports.add("import strosoft.app.common.MyBatisManager;");
		imports.add("import strosoft.app.data.DataEntity;");
		imports.add("import java.io.IOException;");
		imports.add("import java.util.*;");
		imports.add("import java.math.*;");
		imports.add("import " + context.getModuleDocument() + ".data." + context.getFormattedTableName() + ";");
		imports.addAll(getCreatedManagerInport());
		return imports;
	}

	// 引人其它的manager类
	public List<String> getCreatedManagerInport() {
		List<String> imports = new ArrayList<String>();
		for (int i = 0; i < context.getReferncedforeignKeys().size(); i++) {
			CgTableForeignKey currentKey = context.getReferncedforeignKeys().get(i);
			StringBuffer sb = new StringBuffer();
			sb.append("import ");
			sb.append(currentKey.getReferencesModuleDocument());
			sb.append(".model.");
			sb.append(currentKey.getFormattedReferencesTableName());
			sb.append("Manager;");
			imports.add(sb.toString());
		}
		return imports;
	}

	public List<ConstructInfo> getConstructInfo() {
		List<ConstructInfo> constructInfos = new ArrayList<ConstructInfo>();
		ConstructInfo constructInfo = new ConstructInfo();
		constructInfo.setClassName(context.getMasterName());
		constructInfo.setParameters(null);
		constructInfo.setVisibility("public");
		StringBuffer methodBody = new StringBuffer();
		methodBody.append("this.setTableName(\"");
		methodBody.append(context.getTableName());
		methodBody.append("\");this.setTypeName(\"");
		methodBody.append(context.getFormattedTableName());
		methodBody.append("\");");
		constructInfo.setMethodBody(methodBody.toString());
		constructInfos.add(constructInfo);
		return null;
	}

	public List<FieldInfo> getFields() {
		List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();
		FieldInfo fieldInfo = new FieldInfo();
		fieldInfo.setVisibility("private");
		fieldInfo.setTypeName(context.getMasterName());
		fieldInfo.setFieldName("_instance");
		fieldInfo.setModifier("static");
		fieldInfos.add(fieldInfo);
		return null;
	}

	public String getInterfaces() {
		String interfaces = null;
		return interfaces;
	}

	public List<MethodInfo> getMethods() {
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		// 添加
		methodInfos.add(getTableMasterAddMethod());
		methodInfos.add(getTableMasterAddMethodTransaction());
		methodInfos.add(getTableMasterAddDataEntityMethod());
		methodInfos.add(getTableMasterAddDataEntityMethodTransaction());
		// 批量添加
		methodInfos.add(getTableMasterAddListMethod());
		methodInfos.add(getTableMasterAddListMethodTransaction());
		// 删除
		methodInfos.add(getTableMasterDeleteMethod());
		methodInfos.add(getTableMasterDeleteMethodTransaction());
		methodInfos.add(getTableMasterDeleteByEntityMethod());
		methodInfos.add(getTableMasterDeleteByEntityMethodTransaction());
		if (null != context.getPrimaryKeyType() && !context.getPrimaryKeyType().equals("Composite")) {
			methodInfos.add(getTableMasterBatchDeleteMethod());
			methodInfos.add(getTableMasterBatchDeleteMethodTransaction());
		}
		// methodInfos.add(getTableMasterBatchDeleteByEntityMethod());
		methodInfos.add(getTableMasterBatchDeleteByEntityMethodTransaction());
		// 获取
		methodInfos.add(getTableMasterGetDataEntityMethod());
		methodInfos.add(getTableMasterGetDataEntityMethodTransaction());
		methodInfos.add(getTableMasterGetEntityMethod());
		methodInfos.add(getTableMasterGetEntityMethodTransaction());
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
		methodInfos.add(getDataMasterGetListBySqlMethod());
		methodInfos.add(getDataMasterGetListBySqlMethodTransaction());
		// 获取个数
		methodInfos.add(getDataMasterGetCountMethod());
		methodInfos.add(getDataMasterGetCountMethodTransaction());
		// 修改
		methodInfos.add(getTableMasterUpdateMethod());
		methodInfos.add(getTableMasterUpdateMethodTransaction());
		// 创建实体
		methodInfos.add(getDataMasterCreateEntityMethod());
		methodInfos.add(getDataMasterCreateDataEntityMethod());
		// 清空表
		methodInfos.add(getTableMasterClearMethod());
		methodInfos.add(getTableMasterClearMethodTransaction());
		// 根据外键删除
		methodInfos.addAll(getTableMasterDeleteByForeignKeyMethod());
		methodInfos.addAll(getTableMasterDeleteByForeignKeyMethodTransaction());
		// 根据外键查询
		methodInfos.addAll(getTableMasterGetListByForeignKeyMethod());
		methodInfos.addAll(getTableMasterGetListByForeignKeyMethodTransaction());
		// 根据唯一约束查询
		methodInfos.addAll(getTableMasterGetEntityByUniqueColumnMethod());
		methodInfos.addAll(getTableMasterGetEntityByUniqueColumnMethodTransaction());
		// 检查唯一约束字段是否存在
		methodInfos.addAll(getTableMasterExistsUniqueColumnMethod());
		methodInfos.addAll(getTableMasterExistsUniqueColumnMethodTransaction());
		return methodInfos;
	}

	public String getPackageName() {
		StringBuffer packageName = new StringBuffer();
		packageName.append("package ");
		packageName.append(context.getModuleDocument());
		packageName.append(".data;");
		return packageName.toString();
	}

	public MethodInfo getTableMasterAddMethod() {
		if (context.getPrimaryKeyType().equals("Id")) {
			IdTableMasterAddMethod methodInfo = new IdTableMasterAddMethod();
			methodInfo.setEntityName(context.getFormattedTableName());
			return methodInfo;
		} else {
			TableMasterAddMethod methodInfo = new TableMasterAddMethod();
			methodInfo.setEntityName(context.getFormattedTableName());
			return methodInfo;
		}
	}

	public MethodInfo getTableMasterAddMethodTransaction() {
		if (context.getPrimaryKeyType().equals("Id")) {
			IdTableMasterAddMethodTransaction methodInfo = new IdTableMasterAddMethodTransaction();
			methodInfo.setEntityName(context.getFormattedTableName());
			return methodInfo;
		} else {
			TableMasterAddMethodTransaction methodInfo = new TableMasterAddMethodTransaction();
			methodInfo.setContext(context);
			return methodInfo;
		}
	}

	public MethodInfo getTableMasterAddDataEntityMethod() {
		TableMasterAddDataEntityMethod methodInfo = new TableMasterAddDataEntityMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getTableMasterAddDataEntityMethodTransaction() {
		TableMasterAddDataEntityMethodTransaction methodInfo = new TableMasterAddDataEntityMethodTransaction();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getTableMasterAddListMethod() {
		TableMasterAddListMethod methodInfo = new TableMasterAddListMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getTableMasterAddListMethodTransaction() {
		TableMasterAddListMethodTransaction methodInfo = new TableMasterAddListMethodTransaction();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterDeleteMethod() {
		TableMasterDeleteMethod methodInfo = new TableMasterDeleteMethod();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterDeleteMethodTransaction() {
		TableMasterDeleteMethodTransaction methodInfo = new TableMasterDeleteMethodTransaction();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterDeleteByEntityMethod() {
		TableMasterDeleteByEntityMethod methodInfo = new TableMasterDeleteByEntityMethod();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterDeleteByEntityMethodTransaction() {
		TableMasterDeleteByEntityMethodTransaction methodInfo = new TableMasterDeleteByEntityMethodTransaction();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterBatchDeleteMethod() {
		TableMasterBatchDeleteMethod methodInfo = new TableMasterBatchDeleteMethod();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterBatchDeleteMethodTransaction() {
		TableMasterBatchDeleteMethodTransaction methodInfo = new TableMasterBatchDeleteMethodTransaction();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterBatchDeleteByEntityMethod() {
		TableMasterBatchDeleteByEntityMethod methodInfo = new TableMasterBatchDeleteByEntityMethod();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterBatchDeleteByEntityMethodTransaction() {
		TableMasterBatchDeleteByEntityMethodTransaction methodInfo = new TableMasterBatchDeleteByEntityMethodTransaction();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterGetDataEntityMethod() {
		TableMasterGetDataEntityMethod methodInfo = new TableMasterGetDataEntityMethod();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterGetDataEntityMethodTransaction() {
		TableMasterGetDataEntityMethodTransaction methodInfo = new TableMasterGetDataEntityMethodTransaction();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterGetEntityMethod() {
		TableMasterGetEntityMethod methodInfo = new TableMasterGetEntityMethod();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterGetEntityMethodTransaction() {
		TableMasterGetEntityMethodTransaction methodInfo = new TableMasterGetEntityMethodTransaction();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListMethod() {
		DataMasterGetListMethod methodInfo = new DataMasterGetListMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListMethodTransaction() {
		DataMasterGetListMethodTransaction methodInfo = new DataMasterGetListMethodTransaction();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListConditionMethod() {
		DataMasterGetListConditionMethod methodInfo = new DataMasterGetListConditionMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListConditionMethodTransaction() {
		DataMasterGetListConditionMethodTransaction methodInfo = new DataMasterGetListConditionMethodTransaction();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListConditionOrderByMethod() {
		DataMasterGetListConditionOrderByMethod methodInfo = new DataMasterGetListConditionOrderByMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListConditionOrderByMethodTransaction() {
		DataMasterGetListConditionOrderByMethodTransaction methodInfo = new DataMasterGetListConditionOrderByMethodTransaction();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageMethod() {
		DataMasterGetListPageMethod methodInfo = new DataMasterGetListPageMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageMethodTransaction() {
		DataMasterGetListPageMethodTransaction methodInfo = new DataMasterGetListPageMethodTransaction(context);
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageConditionMethod() {
		DataMasterGetListPageConditionMethod methodInfo = new DataMasterGetListPageConditionMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageConditionMethodTransaction() {
		DataMasterGetListPageConditionMethodTransaction methodInfo = new DataMasterGetListPageConditionMethodTransaction();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageConditionOrderByMethod() {
		DataMasterGetListPageConditionOrderByMethod methodInfo = new DataMasterGetListPageConditionOrderByMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListPageConditionOrderByMethodTransaction() {
		DataMasterGetListPageConditionOrderByMethodTransaction methodInfo = new DataMasterGetListPageConditionOrderByMethodTransaction();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListBySqlMethod() {
		DataMasterGetListBySqlMethod methodInfo = new DataMasterGetListBySqlMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetListBySqlMethodTransaction() {
		DataMasterGetListBySqlMethodTransaction methodInfo = new DataMasterGetListBySqlMethodTransaction();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetCountMethod() {
		DataMasterGetCountMethod methodInfo = new DataMasterGetCountMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterGetCountMethodTransaction() {
		DataMasterGetCountMethodTransaction methodInfo = new DataMasterGetCountMethodTransaction();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getTableMasterUpdateMethod() {
		TableMasterUpdateMethod methodInfo = new TableMasterUpdateMethod();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getTableMasterUpdateMethodTransaction() {
		TableMasterUpdateMethodTransaction methodInfo = new TableMasterUpdateMethodTransaction();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public List<MethodInfo> getTableMasterDeleteByForeignKeyMethod() {
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		for (int i = 0; i < context.getReferenceforeignKeys().size(); i++) {
			CgTableForeignKey foreignKey = context.getReferenceforeignKeys().get(i);
			TableMasterDeleteByForeignKeyMethod methodInfo = new TableMasterDeleteByForeignKeyMethod();
			methodInfo.setForeignKey(foreignKey);
			methodInfos.add(methodInfo);
		}
		return methodInfos;
	}

	public List<MethodInfo> getTableMasterDeleteByForeignKeyMethodTransaction() {
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();

		for (int i = 0; i < context.getReferenceforeignKeys().size(); i++) {
			CgTableForeignKey foreignKey = context.getReferenceforeignKeys().get(i);
			TableMasterDeleteByForeignKeyMethodTransaction methodInfo = new TableMasterDeleteByForeignKeyMethodTransaction();
			methodInfo.setForeignKey(foreignKey);
			methodInfos.add(methodInfo);
		}
		return methodInfos;
	}

	public List<MethodInfo> getTableMasterGetListByForeignKeyMethod() {
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		for (int i = 0; i < context.getReferenceforeignKeys().size(); i++) {
			CgTableForeignKey foreignKey = context.getReferenceforeignKeys().get(i);
			TableMasterGetListByForeignKeyMethod methodInfo = new TableMasterGetListByForeignKeyMethod();
			methodInfo.setForeignKey(foreignKey);
			methodInfos.add(methodInfo);
		}
		return methodInfos;
	}

	public List<MethodInfo> getTableMasterGetListByForeignKeyMethodTransaction() {
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		for (int i = 0; i < context.getReferenceforeignKeys().size(); i++) {
			CgTableForeignKey foreignKey = context.getReferenceforeignKeys().get(i);
			TableMasterGetListByForeignKeyMethodTransaction methodInfo = new TableMasterGetListByForeignKeyMethodTransaction();
			methodInfo.setForeignKey(foreignKey);
			methodInfos.add(methodInfo);
		}
		return methodInfos;
	}

	public List<MethodInfo> getTableMasterGetEntityByUniqueColumnMethod() {
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		for (int i = 0; i < context.getUniqueFileds().size(); i++) {
			UniqueFieldInfo uniqueField = context.getUniqueFileds().get(i);
			TableMasterGetEntityByUniqueColumnMethod methodInfo = new TableMasterGetEntityByUniqueColumnMethod();
			methodInfo.setUniqueField(uniqueField);
			methodInfos.add(methodInfo);
		}
		return methodInfos;
	}

	public List<MethodInfo> getTableMasterGetEntityByUniqueColumnMethodTransaction() {
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		for (int i = 0; i < context.getUniqueFileds().size(); i++) {
			UniqueFieldInfo uniqueField = context.getUniqueFileds().get(i);
			TableMasterGetEntityByUniqueColumnMethodTransaction methodInfo = new TableMasterGetEntityByUniqueColumnMethodTransaction();
			methodInfo.setUniqueField(uniqueField);
			methodInfos.add(methodInfo);
		}
		return methodInfos;
	}

	public List<MethodInfo> getTableMasterExistsUniqueColumnMethod() {
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		for (int i = 0; i < context.getUniqueFileds().size(); i++) {
			UniqueFieldInfo uniqueFieldInfo = context.getUniqueFileds().get(i);
			TableMasterExistsUniqueColumnMethod methodInfo = new TableMasterExistsUniqueColumnMethod();
			methodInfo.setUniqueFieldInfo(uniqueFieldInfo);
			methodInfos.add(methodInfo);
		}
		return methodInfos;
	}

	public List<MethodInfo> getTableMasterExistsUniqueColumnMethodTransaction() {
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		for (int i = 0; i < context.getUniqueFileds().size(); i++) {
			UniqueFieldInfo uniqueFieldInfo = context.getUniqueFileds().get(i);
			TableMasterExistsUniqueColumnMethodTransaction methodInfo = new TableMasterExistsUniqueColumnMethodTransaction();
			methodInfo.setUniqueFieldInfo(uniqueFieldInfo);
			methodInfos.add(methodInfo);
		}
		return methodInfos;
	}

	public MethodInfo getTableMasterClearMethod() {
		TableMasterClearMethod methodInfo = new TableMasterClearMethod();
		return methodInfo;
	}

	public MethodInfo getTableMasterClearMethodTransaction() {
		TableMasterClearMethodTransaction methodInfo = new TableMasterClearMethodTransaction();
		methodInfo.setContext(context);
		return methodInfo;
	}

	public MethodInfo getDataMasterCreateDataEntityMethod() {
		DataMasterCreateDataEntityMethod methodInfo = new DataMasterCreateDataEntityMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

	public MethodInfo getDataMasterCreateEntityMethod() {
		DataMasterCreateEntityMethod methodInfo = new DataMasterCreateEntityMethod();
		methodInfo.setEntityName(context.getFormattedTableName());
		return methodInfo;
	}

}
