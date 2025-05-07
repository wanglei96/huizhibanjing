package strosoft.cg.data;

import strosoft.app.data.DataEntity;

public class CgTableColumn  extends DataEntity{
	private String tableName;
	private String columnName;
	private String typeName;
	private String caption;
	private String isSearchItem;
	private String searchOperation;
	private String parameterName;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getIsSearchItem() {
		return isSearchItem;
	}
	public void setIsSearchItem(String isSearchItem) {
		this.isSearchItem = isSearchItem;
	}
	public String getSearchOperation() {
		return searchOperation;
	}
	public void setSearchOperation(String searchOperation) {
		this.searchOperation = searchOperation;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
}
