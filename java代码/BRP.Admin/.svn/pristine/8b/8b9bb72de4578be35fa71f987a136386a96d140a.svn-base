package strosoft.cg.common;

import strosoft.app.data.DataEntity;

public class UniqueFieldInfo  extends DataEntity{
	private String dataName;
	private String caption;
	private String fieldName;
	private String typeName;
	private String formattedTableName;
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getFormattedTableName() {
		return formattedTableName;
	}
	public void setFormattedTableName(String formattedTableName) {
		this.formattedTableName = formattedTableName;
	}
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		sb.append("<UniqueField ");
		sb.append(CodeGenerateUtil.getAttributeString("FieldName",this.fieldName));
		sb.append(CodeGenerateUtil.getAttributeString("DataName",this.dataName));
		sb.append(CodeGenerateUtil.getAttributeString("Caption",this.caption));
		sb.append("></UniqueField>");
		return sb.toString();
	}
}
