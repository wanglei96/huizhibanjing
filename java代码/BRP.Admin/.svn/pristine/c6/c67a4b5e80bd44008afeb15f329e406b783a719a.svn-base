package strosoft.cg.model;

import strosoft.cg.common.*;
import strosoft.cg.data.ViewCodeGenerateContext;
import strosoft.cg.data.CgViewColumn;

import java.util.ArrayList;
import java.util.List;

public class ViewMapperCodeBuilder {
	private ViewCodeGenerateContext context;

	public ViewCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(ViewCodeGenerateContext context) {
		this.context = context;
	}
	public String generate(){
		MapperInfo mapperInfo = new MapperInfo();
		mapperInfo.setNamespace(context.getFormattedViewName());
		mapperInfo.setResultMap(getResultMap());
		List<SqlCommandInfo> sqlCommands = getSqlCommands();
		//通过sql查询
		sqlCommands.addAll(getSqlCommandsWithSql());
		//通过条件查询
		sqlCommands.addAll(getSqlCommandsWithCondition());
		//查询count
		sqlCommands.addAll(getSqlCommandsWithGetCount());
		mapperInfo.setSqlCommands(sqlCommands);
		return mapperInfo.toCode();
	}
	public ResultMapInfo getResultMap(){
		ResultMapInfo resultMapInfo = new ResultMapInfo();
		resultMapInfo.setId(context.getFormattedViewName()+"ResultMap");
		resultMapInfo.setType(context.getModuleDocument()+".data."+context.getFormattedViewName());
		List<ResultInfo> resultInfos = new ArrayList<ResultInfo>();
		for(int i=0;i<context.getColumns().size();i++){
			CgViewColumn currentColumn = context.getColumns().get(i);
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setColumn(currentColumn.getColumnName());
			resultInfo.setProperty(currentColumn.getParameterName());
			resultInfo.setResultName("result");
			resultInfos.add(resultInfo);
		}
		resultMapInfo.setResults(resultInfos);
		return resultMapInfo;
	}
	public List<SqlCommandInfo> getSqlCommands(){
		List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
		SqlCommandInfo selectListSqlCommand = new SqlCommandInfo();
		selectListSqlCommand.setId("select"+context.getFormattedViewName()+"s");
		selectListSqlCommand.setItemType("select");
		selectListSqlCommand.setResultMap(context.getFormattedViewName()+"ResultMap");
		selectListSqlCommand.setSql("select * from "+context.getViewName());
		sqlCommands.add(selectListSqlCommand);
		for(int i=0;i<context.getColumns().size();i++){
			CgViewColumn currentColum = context.getColumns().get(i);
			//如果有id字段，生成按照id查询
			//如果有code字段生成安装code查询
			if(currentColum.getColumnName().equals("id") || currentColum.getColumnName().equals("code")){
				SqlCommandInfo sqlCommand = new SqlCommandInfo();
				sqlCommand.setId("select"+context.getFormattedViewName()+"By"+CodeGenerateUtil.upperCase(currentColum.getParameterName()));
				sqlCommand.setItemType("select");
				sqlCommand.setResultMap(context.getFormattedViewName()+"ResultMap");
				sqlCommand.setParameterType(currentColum.getTypeName());
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("select * from ");
				sqlString.append(context.getViewName());
				sqlString.append(" where ");
				sqlString.append(currentColum.getColumnName());
				sqlString.append("= #{");
				sqlString.append(currentColum.getParameterName());
				sqlString.append("}");
				sqlCommand.setSql(sqlString.toString());
				sqlCommands.add(sqlCommand);
			}
		}
		
		return sqlCommands;
	}
	//直接传入sql的查
	public List<SqlCommandInfo> getSqlCommandsWithSql(){
		List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
		//根据sql查询
		SqlCommandInfo selectSqlCommand = new SqlCommandInfo();
		selectSqlCommand.setId("select"+context.getFormattedViewName()+"BySql");
		selectSqlCommand.setItemType("select");
		selectSqlCommand.setParameterType("java.lang.String");
		selectSqlCommand.setResultMap(context.getFormattedViewName()+"ResultMap");
		selectSqlCommand.setSql("${value}");
		sqlCommands.add(selectSqlCommand);
		return sqlCommands;
	}
	//传入condition的查
	public List<SqlCommandInfo> getSqlCommandsWithCondition(){
		List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
		//根据sql查询
		SqlCommandInfo selectSqlCommand = new SqlCommandInfo();
		selectSqlCommand.setId("select"+context.getFormattedViewName()+"ByCondition");
		selectSqlCommand.setItemType("select");
		selectSqlCommand.setParameterType("java.lang.String");
		selectSqlCommand.setResultMap(context.getFormattedViewName()+"ResultMap");
		selectSqlCommand.setSql("select * from "+context.getViewName()+" where ${value}");
		sqlCommands.add(selectSqlCommand);
		return sqlCommands;
	}
	//查询count
	public List<SqlCommandInfo> getSqlCommandsWithGetCount(){
		List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
		//根据sql查询int
		SqlCommandInfo selectIntSqlCommand = new SqlCommandInfo();
		selectIntSqlCommand.setId("selectIntBySql");
		selectIntSqlCommand.setItemType("select");
		selectIntSqlCommand.setParameterType("java.lang.String");
		selectIntSqlCommand.setResultType("java.lang.Integer");
		selectIntSqlCommand.setSql("${value}");
		//根据条件查询count
		SqlCommandInfo selectCountSqlCommand = new SqlCommandInfo();
		selectCountSqlCommand.setId("selectCountByCondition");
		selectCountSqlCommand.setItemType("select");
		selectCountSqlCommand.setParameterType("java.lang.String");
		selectCountSqlCommand.setResultType("java.lang.Integer");
		selectCountSqlCommand.setSql("select count(*) from "+context.getViewName()+" where ${value}");
		sqlCommands.add(selectCountSqlCommand);
		sqlCommands.add(selectIntSqlCommand);
		return sqlCommands;
	}
}
