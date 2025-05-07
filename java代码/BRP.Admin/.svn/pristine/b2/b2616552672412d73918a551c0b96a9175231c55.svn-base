package strosoft.cg.model;

import java.util.ArrayList;
import java.util.List;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

public class DataMasterGetListPageConditionMethod extends MethodInfo {
	private String entityName;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getMethodBody() {
		StringBuffer sb = new StringBuffer();
		sb.append("SqlSession sqlSession = MyBatisManager.getInstance().openSession();");
		sb.append("List<");
		sb.append(entityName);
		sb.append("> list = null;");
		sb.append("try{");
		sb.append("list = getList(sqlSession, condition, pageIndex, pageSize);");
		sb.append("}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return list;");
		return sb.toString();
	}

	public void setMethodParameters() {
		this.setMethodName("getListPage");
		this.setReturnType("List<" + entityName + ">");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		// 查询条件
		MethodParameter conditionMethodParameter = new MethodParameter();
		conditionMethodParameter.setName("condition");
		conditionMethodParameter.setType("String");
		parameters.add(conditionMethodParameter);
		// 当前页
		MethodParameter pageIndexMethodParameter = new MethodParameter();
		pageIndexMethodParameter.setName("pageIndex");
		pageIndexMethodParameter.setType("int");
		parameters.add(pageIndexMethodParameter);
		// 分页大小
		MethodParameter pageSizeMethodParameter = new MethodParameter();
		pageSizeMethodParameter.setName("pageSize");
		pageSizeMethodParameter.setType("int");
		parameters.add(pageSizeMethodParameter);
		this.setParameters(parameters);

	}

}
