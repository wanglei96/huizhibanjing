package strosoft.cg.common;

import strosoft.app.data.DataEntity;

import java.util.List;

public class MapperInfo  extends DataEntity{
	private String namespace;
	private ResultMapInfo resultMap;
	private List<SqlCommandInfo> sqlCommands;
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public ResultMapInfo getResultMap() {
		return resultMap;
	}
	public void setResultMap(ResultMapInfo resultMap) {
		this.resultMap = resultMap;
	}
	public List<SqlCommandInfo> getSqlCommands() {
		return sqlCommands;
	}
	public void setSqlCommands(List<SqlCommandInfo> sqlCommands) {
		this.sqlCommands = sqlCommands;
	}
	
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		String version = getVersionString();
		String namepaceStartString = getNamepaceString();
		String resultMapString = getResultMapString();
		String sqlCommandsString = getSqlCommandsString();
		String namepaceEndString = "\r</mapper>";
		sb.append(version);
		sb.append(namepaceStartString);
		sb.append(resultMapString);
		sb.append(sqlCommandsString);
		sb.append(namepaceEndString);
		return sb.toString();
	}
	public String getVersionString(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("\r");
		sb.append("<!DOCTYPE mapper PUBLIC '-//mybatis.org//DTD Mapper 3.0//EN' 'http://mybatis.org/dtd/mybatis-3-mapper.dtd'>");
		return sb.toString();
	}
	public String getNamepaceString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\r");
		sb.append("<mapper namespace='"+this.namespace+"'>");
		return sb.toString();
	}
	public String getResultMapString(){
		return this.resultMap.toCode();
	}
	public String getSqlCommandsString(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<this.sqlCommands.size();i++){
			SqlCommandInfo sqlCommand = this.sqlCommands.get(i);
			sb.append(sqlCommand.toCode());
		}
		return sb.toString();
	}
	
	
}
