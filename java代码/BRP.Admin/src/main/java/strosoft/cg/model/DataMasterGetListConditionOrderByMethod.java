package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

import java.util.ArrayList;
import java.util.List;

public class DataMasterGetListConditionOrderByMethod extends MethodInfo{
	private String entityName;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("SqlSession sqlSession = MyBatisManager.getInstance().openSession();");
		sb.append("List<");
		sb.append(entityName);
		sb.append("> list = null;");
		sb.append("try{");
		sb.append("list = getList(sqlSession,condition,orderBy);");
		sb.append("}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return list;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("getList");
		this.setReturnType("List<"+entityName+">");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter condition = new MethodParameter();
		condition.setName("condition");
		condition.setType("String");
		MethodParameter orderBy = new MethodParameter();
		orderBy.setName("orderBy");
		orderBy.setType("String");
		parameters.add(condition);
		parameters.add(orderBy);
		this.setParameters(parameters);
		
	}
	
}
