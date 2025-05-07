package strosoft.cg.data;

import strosoft.app.data.DataEntity;
import strosoft.cg.common.CodeGenerateUtil;

public class CgTablePrimaryKey  extends DataEntity{
	private String tableName;
	private String caption;
	private String columnName;
	private String typeName;
	private String keyName;
	private Boolean sequance;
	private String parameterName;
	private String shortTypeName;
	private String extra;
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
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public Boolean getSequance() {
		return sequance;
	}
	public void setSequance(Boolean sequance) {
		this.sequance = sequance;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getShortTypeName() {
		return shortTypeName;
	}
	public void setShortTypeName(String shortTypeName) {
		this.shortTypeName = shortTypeName;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		sb.append("<PrimaryKey ");
		sb.append(CodeGenerateUtil.getAttributeString("FieldName",this.columnName));
		sb.append(CodeGenerateUtil.getAttributeString("Caption",this.caption));
		sb.append(CodeGenerateUtil.getAttributeString("DataName",this.parameterName));
		sb.append("></PrimaryKey>");
		return sb.toString();
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
}
