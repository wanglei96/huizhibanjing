package strosoft.cg.data;

import java.util.List;

import strosoft.cg.common.RequiredFieldInfo;
import strosoft.cg.common.SearchItemInfo;
import strosoft.cg.common.UniqueFieldInfo;

public class TableCodeGenerateContext extends CodeGenerateContext {
	private String formattedTableName;
	private List<CgTableColumn> colums;
	private List<CgTablePrimaryKey> primaryKeys;
	private List<CgTableForeignKey> referenceforeignKeys;
	private List<CgTableForeignKey> referncedforeignKeys;
	private List<RequiredFieldInfo> requeFileds;
	private List<SearchItemInfo> searchFileds;
	private List<UniqueFieldInfo> uniqueFileds;
	private String primaryKeyType;
	private String masterName;
	private String managerName;

	public String getTableName() {
		return this.getDataName();
	}

	public void setTableName(String tableName) {
		this.setDataName(tableName);
	}

	public String getFormattedTableName() {
		return formattedTableName;
	}

	public void setFormattedTableName(String formattedTableName) {
		this.formattedTableName = formattedTableName;
	}

	public List<CgTableColumn> getColums() {
		return colums;
	}

	public void setColums(List<CgTableColumn> colums) {
		this.colums = colums;
	}

	public List<CgTablePrimaryKey> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<CgTablePrimaryKey> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public List<RequiredFieldInfo> getRequeFileds() {
		return requeFileds;
	}

	public void setRequeFileds(List<RequiredFieldInfo> requeFileds) {
		this.requeFileds = requeFileds;
	}

	public List<SearchItemInfo> getSearchFileds() {
		return searchFileds;
	}

	public void setSearchFileds(List<SearchItemInfo> searchFileds) {
		this.searchFileds = searchFileds;
	}

	public String getPrimaryKeyType() {
		return primaryKeyType;
	}

	public void setPrimaryKeyType(String primaryKeyType) {
		this.primaryKeyType = primaryKeyType;
	}

	public List<UniqueFieldInfo> getUniqueFileds() {
		return uniqueFileds;
	}

	public void setUniqueFileds(List<UniqueFieldInfo> uniqueFileds) {
		this.uniqueFileds = uniqueFileds;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public List<CgTableForeignKey> getReferenceforeignKeys() {
		return referenceforeignKeys;
	}

	public void setReferenceforeignKeys(List<CgTableForeignKey> referenceforeignKeys) {
		this.referenceforeignKeys = referenceforeignKeys;
	}

	public List<CgTableForeignKey> getReferncedforeignKeys() {
		return referncedforeignKeys;
	}

	public void setReferncedforeignKeys(List<CgTableForeignKey> referncedforeignKeys) {
		this.referncedforeignKeys = referncedforeignKeys;
	}

}
