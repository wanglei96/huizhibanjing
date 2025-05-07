package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

import java.util.ArrayList;
import java.util.List;

public class DataMasterGetCountMethod extends MethodInfo{
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
		sb.append("Integer count = null;");
		sb.append("try{");
		sb.append("count = getCount(sqlSession,condition);sqlSession.commit();");
		sb.append("}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return count;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("getCount");
		this.setReturnType("Integer");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter parameter = new MethodParameter();
		parameter.setName("condition");
		parameter.setType("String");
		parameters.add(parameter);
		this.setParameters(parameters);
	}
	
}
