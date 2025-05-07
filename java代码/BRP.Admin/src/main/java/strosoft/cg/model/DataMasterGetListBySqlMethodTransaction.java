package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

import java.util.ArrayList;
import java.util.List;

public class DataMasterGetListBySqlMethodTransaction extends MethodInfo{
	private String entityName;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("List<");
		sb.append(entityName);
		sb.append("> list = sqlSession.selectList(\"");
		sb.append(entityName);
		sb.append(".select");
		sb.append(CodeGenerateUtil.upperCase(entityName));
		sb.append("BySql\",sql);return list;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("getListBySql");
		this.setReturnType("List<"+entityName+">");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		MethodParameter condition = new MethodParameter();
		condition.setName("sql");
		condition.setType("String");
		parameters.add(transaction);
		parameters.add(condition);
		this.setParameters(parameters);
		
	}
}
