package strosoft.cg.common;

import strosoft.app.data.DataEntity;

public class FieldInfo  extends DataEntity{
	private String visibility;
	private String fieldName;
	private String typeName;
	private String caption;
	private String modifier;
	
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
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.visibility);
		sb.append(" ");
		sb.append(getModifierString());
		sb.append(" ");
		sb.append(this.typeName);
		sb.append(" ");
		sb.append(this.fieldName);
		sb.append(";");
		sb.append(getCaptionString());
		return sb.toString();
	}

	
	public String getModifierString(){
		if(null == this.modifier){
			return "";
		}
		return this.modifier;
	}
	
	public String getCaptionString(){
		if(null == this.caption){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("//");
		sb.append(this.caption);
		sb.append(";");
		return sb.toString();
	}
	
}
