package strosoft.cg.common;

import strosoft.app.data.DataEntity;

public class SqlCommandInfo  extends DataEntity{
	private String itemType;
	private String id;
	private String parameterType;
	private String useGeneratedKeys;
	private String keyProperty;
	private String resultMap;
	private String resultType;
	private String sql;
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	public String getUseGeneratedKeys() {
		return useGeneratedKeys;
	}
	public void setUseGeneratedKeys(String useGeneratedKeys) {
		this.useGeneratedKeys = useGeneratedKeys;
	}
	public String getKeyProperty() {
		return keyProperty;
	}
	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}
	public String getResultMap() {
		return resultMap;
	}
	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		String idString = getIdString();
		String parameterTypeString = getParameterTypeString();
		String resultTypeString = getResultTypeString();
		String resultMapString = getResultMapString();
		String keyPropertyString = getKeyPropertyString();
		String useGeneratedKeysString = getUseGeneratedKeysString();
		sb.append("\r  <");
		sb.append(this.itemType);
		sb.append(idString);
		sb.append(parameterTypeString);
		sb.append(resultTypeString);
		sb.append(resultMapString);
		sb.append(keyPropertyString);
		sb.append(useGeneratedKeysString);
		sb.append(">");
		sb.append("\r    ");
		sb.append(this.sql);
		sb.append("\r  </");
		sb.append(this.itemType);
		sb.append(">");
		return sb.toString();
	}
	public String getIdString(){
		StringBuffer sb = new StringBuffer();
		sb.append(" id='");
		sb.append(this.id);
		sb.append("'");
		return sb.toString();
	}
	public String getResultTypeString(){
		if(this.resultType == null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" resultType='");
		sb.append(this.resultType);
		sb.append("'");
		return sb.toString();
	}
	public String getResultMapString(){
		if(this.resultMap == null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" resultMap='");
		sb.append(this.resultMap);
		sb.append("'");
		return sb.toString();
	}
	public String getKeyPropertyString(){
		if(null == this.keyProperty){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" keyProperty='");
		sb.append(this.keyProperty);
		sb.append("'");
		return sb.toString();
	}
	public String getUseGeneratedKeysString(){
		if(null == this.useGeneratedKeys){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" useGeneratedKeys='");
		sb.append(this.useGeneratedKeys);
		sb.append("'");
		return sb.toString();
	}
	public String getParameterTypeString(){
		if(null == this.parameterType){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" parameterType='");
		sb.append(this.parameterType);
		sb.append("'");
		return sb.toString();
	}
}

