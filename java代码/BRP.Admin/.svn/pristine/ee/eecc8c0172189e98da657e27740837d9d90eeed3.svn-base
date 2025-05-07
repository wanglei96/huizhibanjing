package strosoft.cg.common;

import strosoft.app.data.DataEntity;

public class RequiredFieldInfo  extends DataEntity{
	private String dataName;
	private String caption;
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
	
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		sb.append("<RequiredField ");
		sb.append(CodeGenerateUtil.getAttributeString("DataName",this.dataName));
		sb.append(CodeGenerateUtil.getAttributeString("Caption",this.caption));
		sb.append("></RequiredField>");
		return sb.toString();
	}
}
