package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

import java.util.ArrayList;
import java.util.List;

public class TableMasterAddDataEntityMethodTransaction extends MethodInfo{
	private String entityName;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("return add(sqlSession,("+entityName+")dataEntity);");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("add");
		this.setReturnType("int");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter parameter = new MethodParameter();
		parameter.setName("dataEntity");
		parameter.setType("DataEntity");
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
		parameters.add(parameter);
		this.setParameters(parameters);
		
	}
}
