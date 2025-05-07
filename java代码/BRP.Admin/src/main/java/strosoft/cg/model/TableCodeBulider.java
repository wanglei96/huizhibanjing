package strosoft.cg.model;

import net.sf.json.JSONArray;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.DataTypeHelper;
import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.RequiredFieldInfo;
import strosoft.cg.common.ServiceHandlerInfo;
import strosoft.cg.common.UniqueFieldInfo;
import strosoft.cg.data.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TableCodeBulider {
	private CgTable table;
	private TableCodeGenerateContext context;
	private CgProjectInfo fullProject;
	public CgTable getTable() {
		return table;
	}

	public void setTable(CgTable table) {
		this.table = table;
	}
	
	public CgProjectInfo getFullProject() {
		return fullProject;
	}

	public void setFullProject(CgProjectInfo fullProject) {
		this.fullProject = fullProject;
	}
	
	public String getTableEntityCode(){
		TableEntityClassCodeBuilder tableEntityClassCodeBuilder = new TableEntityClassCodeBuilder();
		tableEntityClassCodeBuilder.setContext(context);
		return tableEntityClassCodeBuilder.generate();
		
	}
	
	public String getTableMasterCode(){
		TableMasterClassCodeBuilder builder = new TableMasterClassCodeBuilder();
		builder.setContext(context);
		return builder.generate();
		
	}
	
	public String getTableManagerCode(){
		TableManagerClassCodeBuilder builder = new TableManagerClassCodeBuilder();
		builder.setContext(context);
		return builder.generate();
	}
	
	public String getTableMapperCode(){
		TableMapperCodeBuilder tableMapperCodeBuilder = new TableMapperCodeBuilder();
		tableMapperCodeBuilder.setContext(context);
		return tableMapperCodeBuilder.generate();
		
	}
	
	public List<ServiceHandlerInfo> getTableServiceHandlerLsit(){
		TableServiceHandlerBuilder tableServiceHandlerBuilder = new TableServiceHandlerBuilder();
		tableServiceHandlerBuilder.setContext(context);
		return tableServiceHandlerBuilder.build();
		
	}
	
	public void getContext() throws IOException, Exception{
		TableCodeGenerateContext context = new TableCodeGenerateContext();
		this.context = context;
		ArrayList<LinkedHashMap<String, Object>> columns = MyBatisManager.getInstance().getAllColumns(table.getDataSource(), table.getTableName());
		ArrayList<LinkedHashMap<String, Object>> refernces = MyBatisManager.getInstance().getReferencedColumns(table.getDataSource(), table.getTableName());
		ArrayList<LinkedHashMap<String, Object>> refernced = MyBatisManager.getInstance().getBeReferencedColumns(table.getDataSource(), table.getTableName());
		ArrayList<LinkedHashMap<String, Object>> uniques = MyBatisManager.getInstance().getUniqueColumns(table.getDataSource(), table.getTableName());
		context.setColums(loadColumns(columns));
		context.setPrimaryKeys(loadPrimaryKeys(columns));
		context.setReferenceforeignKeys(loadReferncesForeignKeys(refernces));
		context.setReferncedforeignKeys(loadReferncedForeignKeys(refernced));
		context.setRequeFileds(loadRequiredFieldColumns(columns));
		context.setUniqueFileds(loadUniqueColumns(uniques));
		context.setTableName(this.table.getTableName());
		context.setModuleName(this.table.getModuleName());
		context.setModuleDocument(this.table.getModuleDocument());
		context.setModulePath(this.table.getModulePath());
		context.setSearchFileds(this.table.getSearchItems());
		context.setFormattedTableName(this.table.getFormattedTableName());
		context.setMasterName(this.table.getFormattedTableName()+"Master");
		context.setManagerName(this.table.getFormattedTableName()+"Manager");
		List<CgTablePrimaryKey> primaryKeys = context.getPrimaryKeys();
		if(primaryKeys.size()==1 && primaryKeys.get(0).getShortTypeName().equals("Integer") && primaryKeys.get(0).getExtra().equals("auto_increment") && primaryKeys.get(0).getExtra().equals("auto_increment")){
			context.setPrimaryKeyType("Id");
		}else if(primaryKeys.size()==1 && primaryKeys.get(0).getColumnName().equals("code")){
			context.setPrimaryKeyType("Code");
		}else{
			context.setPrimaryKeyType("Composite");
		}
		//System.out.println("contetx:"+JSONArray.fromObject(context));
	}
	
	public List<CgTableColumn> loadColumns(ArrayList<LinkedHashMap<String, Object>> columns){
		List<CgTableColumn> tableColumns = new ArrayList<CgTableColumn>();
		List<CgTableColumn> xmlTableColumns = table.getTableColumns();
		for(int i=0;i<columns.size();i++){
			CgTableColumn tableColumn = null;
			Integer isContainIndex = -1;
			for(int j=0;j<xmlTableColumns.size();j++){
				String tableColumndItem = CodeGenerateUtil.getParameterName((String)columns.get(i).get("COLUMN_NAME"));
				String xmlColumndItem = xmlTableColumns.get(j).getParameterName();
				if(tableColumndItem.equals(xmlColumndItem)){
					isContainIndex = j;
					break;
				}
			}
			if(isContainIndex>-1){
				tableColumn = xmlTableColumns.get(isContainIndex);
			}else{
				tableColumn = new CgTableColumn();
				tableColumn.setColumnName((String) columns.get(i).get("COLUMN_NAME"));
				tableColumn.setTableName(table.getTableName());
				tableColumn.setParameterName(CodeGenerateUtil.getParameterName((String)columns.get(i).get("COLUMN_NAME")));
			}
			//设置java类型
			tableColumn.setTypeName(DataTypeHelper.getDataTypeBySqlType((String) columns.get(i).get("DATA_TYPE")).get("javaType"));
			tableColumns.add(tableColumn);
		}
		return tableColumns;
	}
	
	public List<CgTablePrimaryKey> loadPrimaryKeys(ArrayList<LinkedHashMap<String, Object>> columns){
		List<CgTablePrimaryKey> primaryKeys = new ArrayList<CgTablePrimaryKey>();
		for(int i=0;i<columns.size();i++){
			if(((String)columns.get(i).get("COLUMN_KEY")).equals("PRI")){
				CgTablePrimaryKey tablePrimaryKey = new CgTablePrimaryKey();
				tablePrimaryKey.setColumnName((String)columns.get(i).get("COLUMN_NAME"));
				tablePrimaryKey.setTableName(table.getTableName());
				tablePrimaryKey.setExtra((String)columns.get(i).get("EXTRA"));
				tablePrimaryKey.setTypeName(DataTypeHelper.getDataTypeBySqlType((String) columns.get(i).get("DATA_TYPE")).get("javaType"));
				tablePrimaryKey.setShortTypeName(CodeGenerateUtil.getTypeString(tablePrimaryKey.getTypeName()));
				tablePrimaryKey.setParameterName(CodeGenerateUtil.getParameterName((String)columns.get(i).get("COLUMN_NAME")));
				for(int j=0;j<this.context.getColums().size();j++){
					if(tablePrimaryKey.getColumnName().equals(this.context.getColums().get(j).getColumnName())){
						tablePrimaryKey.setCaption(this.context.getColums().get(j).getCaption());
						break;
					}
				}
				primaryKeys.add(tablePrimaryKey);
			}
		}
		return primaryKeys;
	}
	
	public List<CgTableForeignKey> loadReferncesForeignKeys(ArrayList<LinkedHashMap<String, Object>> refernces){
		List<CgTableForeignKey>  foreignKeys = new ArrayList<CgTableForeignKey>();
		for(int i=0;i<refernces.size();i++){
			if(null == refernces.get(i).get("REFERENCED_TABLE_NAME")){
				continue;
			}
			CgTableForeignKey tableForeignKey = new CgTableForeignKey();
			tableForeignKey.setReferencedColumnName((String)refernces.get(i).get("REFERENCED_COLUMN_NAME"));
			tableForeignKey.setFormattedReferencedColumnName(CodeGenerateUtil.getParameterName(tableForeignKey.getReferencedColumnName()));
			tableForeignKey.setReferencesColumnName((String)refernces.get(i).get("COLUMN_NAME"));
			tableForeignKey.setReferencesTableName(table.getTableName());
			tableForeignKey.setReferencedTableName((String)refernces.get(i).get("REFERENCED_TABLE_NAME"));
			tableForeignKey.setFormattedReferencesColumnName(CodeGenerateUtil.getParameterName(tableForeignKey.getReferencesColumnName()));
			tableForeignKey.setFormattedReferencedTableName(CodeGenerateUtil.getFormattedTableName(tableForeignKey.getReferencedTableName()));
			tableForeignKey.setFormattedReferencesTableName(CodeGenerateUtil.getFormattedTableName(tableForeignKey.getReferencesTableName()));
			for(int j=0;j<context.getColums().size();j++){
				CgTableColumn currentColumn = context.getColums().get(j);
				if(currentColumn.getColumnName().equals(tableForeignKey.getReferencesColumnName())){
					tableForeignKey.setTypeName(currentColumn.getTypeName());
				}
			}
			foreignKeys.add(tableForeignKey);
		}
		return foreignKeys;
	}
	
	
	public List<CgTableForeignKey> loadReferncedForeignKeys(ArrayList<LinkedHashMap<String, Object>> refernces){
		List<CgTableForeignKey>  foreignKeys = new ArrayList<CgTableForeignKey>();
		for(int i=0;i<refernces.size();i++){
			CgTableForeignKey tableForeignKey = new CgTableForeignKey();
			tableForeignKey.setReferencedColumnName((String)refernces.get(i).get("REFERENCED_COLUMN_NAME"));
			tableForeignKey.setFormattedReferencedColumnName(CodeGenerateUtil.getParameterName(tableForeignKey.getReferencedColumnName()));
			tableForeignKey.setReferencesColumnName((String)refernces.get(i).get("COLUMN_NAME"));
			tableForeignKey.setReferencesTableName((String)refernces.get(i).get("TABLE_NAME"));
			tableForeignKey.setReferencedTableName((String)refernces.get(i).get("REFERENCED_TABLE_NAME"));
			tableForeignKey.setFormattedReferencesTableName(CodeGenerateUtil.getFormattedTableName(tableForeignKey.getReferencesTableName()));
			tableForeignKey.setFormattedReferencesColumnName(CodeGenerateUtil.getParameterName(tableForeignKey.getReferencesColumnName()));
			tableForeignKey.setFormattedReferencedTableName(CodeGenerateUtil.getFormattedTableName(tableForeignKey.getReferencedTableName()));
			for(int j=0;j<fullProject.getModules().size();j++){
				CgModule currentModule = fullProject.getModules().get(j);
				for(int z=0;z<currentModule.getTables().size();z++){
					CgTable currentTable = currentModule.getTables().get(z);
					if(currentTable.getTableName().equals(tableForeignKey.getReferencesTableName())){
						tableForeignKey.setReferencesModuleDocument(currentModule.getModuleDocument());
					}
				}
			}
			for(int j=0;j<context.getColums().size();j++){
				CgTableColumn currentColumn = context.getColums().get(j);
				if(currentColumn.getColumnName().equals(tableForeignKey.getReferencedColumnName())){
					tableForeignKey.setTypeName(currentColumn.getTypeName());
				}
			}
			foreignKeys.add(tableForeignKey);
		}
		return foreignKeys;
	}
	public List<RequiredFieldInfo> loadRequiredFieldColumns(ArrayList<LinkedHashMap<String, Object>> columns){
		List<RequiredFieldInfo> requiredFieldInfos = new ArrayList<RequiredFieldInfo>();
		for(int i=0;i<columns.size();i++){
			if(((String)columns.get(i).get("IS_NULLABLE")).equals("NO")){
				RequiredFieldInfo requiredField = new RequiredFieldInfo();
				requiredField.setDataName(CodeGenerateUtil.getParameterName((String)columns.get(i).get("COLUMN_NAME")));
				for(int j=0;j<table.getTableColumns().size();j++){
					if(table.getTableColumns().get(j).getParameterName().equals(requiredField.getDataName())){
						String caption = table.getTableColumns().get(j).getCaption();
						if(null == caption || caption.equals("") || caption.equals("null")){
							requiredField.setCaption(requiredField.getDataName());
						} else {
							requiredField.setCaption(caption);
						}
					}
				}
				requiredFieldInfos.add(requiredField);
			}
		}
		return requiredFieldInfos;
	}
	
	public List<UniqueFieldInfo> loadUniqueColumns(ArrayList<LinkedHashMap<String, Object>> uniques){
		List<UniqueFieldInfo> uniqueFieldInfos = new ArrayList<UniqueFieldInfo>();
		System.out.println(JSONArray.fromObject(uniques));
		//去重
		for(int i=uniques.size()-1;i>-1;i--){
			String columnName1 = (String)uniques.get(i).get("Column_name");
			for(int j=uniques.size()-1;j>-1;j--){
				String columnName2 = (String)uniques.get(j).get("Column_name");
				if(columnName1.equals(columnName2) && i != j){
					uniques.remove(i);
					break;
				}
			}
		}
		for(int i=0;i<uniques.size();i++){
			UniqueFieldInfo uniqueField = new UniqueFieldInfo();
			uniqueField.setFieldName((String)uniques.get(i).get("Column_name"));
			for(int j=0;j<table.getTableColumns().size();j++){
				CgTableColumn currentTableColumn = table.getTableColumns().get(j);
				if(table.getTableColumns().get(j).getColumnName().equals(uniqueField.getFieldName())){
					uniqueField.setTypeName(table.getTableColumns().get(j).getTypeName());
					uniqueField.setDataName(table.getTableColumns().get(j).getParameterName());
					uniqueField.setFormattedTableName(table.getFormattedTableName());
					String caption = table.getTableColumns().get(j).getCaption();
					if(null == caption || caption.equals("") || caption.equals("null")){
						uniqueField.setCaption(uniqueField.getDataName());
					} else {
						uniqueField.setCaption(caption);
					}
				}
			}
			uniqueFieldInfos.add(uniqueField);
		}
		return uniqueFieldInfos;
	}
	
	
}
