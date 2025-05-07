package strosoft.cg.common;

import strosoft.app.data.DataEntity;

public class ResultInfo  extends DataEntity{
	private String resultName;
	private String column;
	private String jdbcType;
	private String property;
	public String getResultName() {
		return resultName;
	}
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		sb.append("\r    <");
		sb.append(this.resultName);
		sb.append(" column='");
		sb.append(this.column);
		sb.append("' property='");
		sb.append(this.property);
		sb.append("' />");
		return sb.toString();
	}
	
}
