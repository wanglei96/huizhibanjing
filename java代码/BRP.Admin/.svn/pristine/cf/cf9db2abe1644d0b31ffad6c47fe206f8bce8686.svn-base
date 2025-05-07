package strosoft.cg.common;

import strosoft.app.data.DataEntity;

public class SearchItemInfo  extends DataEntity{
	private String columnName;
	private String operation;
	private String parameterName;
	private String treeTableName;
	private String caption;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getTreeTableName() {
		return treeTableName;
	}
	public void setTreeTableName(String treeTableName) {
		this.treeTableName = treeTableName;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String toCode(){
		StringBuffer sb = new StringBuffer();
		sb.append("<SearchItem ");
		sb.append(CodeGenerateUtil.getAttributeString("Operation",this.operation));
		sb.append(CodeGenerateUtil.getAttributeString("FieldName",this.columnName));
		if(operation.equals("timespan")){
			sb.append(CodeGenerateUtil.getAttributeString("BeginDateParameterName",this.parameterName+"Begin"));
			sb.append(CodeGenerateUtil.getAttributeString("EndDateParameterName",this.parameterName+"End"));
		}else{
			sb.append(CodeGenerateUtil.getAttributeString("ParameterName",this.parameterName));
			sb.append(CodeGenerateUtil.getAttributeString("TreeTableName",this.treeTableName));
		}
		sb.append("></SearchItem>");
		return sb.toString();
	}
	
	
}
