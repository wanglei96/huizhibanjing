package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

import java.util.ArrayList;
import java.util.List;

public class IdTableMasterAddMethodTransaction extends MethodInfo{
	private String entityName;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("int rows = sqlSession.insert(\"");
		sb.append(entityName);
		sb.append(".insert");
		sb.append(entityName);
		sb.append("\",entity);int newId = Integer.valueOf(entity.getId().toString());");
		sb.append("if (newId > 0) {return newId;} else {return rows;}");
		return sb.toString();
	}
	
	public void setMethodParameters(){
		this.setMethodName("add");
		this.setReturnType("int");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
		MethodParameter parameter = new MethodParameter();
		parameter.setName("entity");
		parameter.setType(entityName);
		parameters.add(parameter);
		this.setParameters(parameters);
	}
	
}
