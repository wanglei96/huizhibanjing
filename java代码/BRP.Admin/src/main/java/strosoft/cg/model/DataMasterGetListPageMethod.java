package strosoft.cg.model;

import java.util.ArrayList;
import java.util.List;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

public class DataMasterGetListPageMethod extends MethodInfo {
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
		sb.append("list = getList(sqlSession,pageIndex,pageSize);");
		sb.append("}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;}");
		sb.append("finally {sqlSession.close();}");
		sb.append("return list;");
		return sb.toString();
	}

	public void setMethodParameters() {
		this.setMethodName("getList");
		this.setReturnType("List<" + entityName + ">");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		// 当前页
		MethodParameter pageindex = new MethodParameter();
		pageindex.setName("pageIndex");
		pageindex.setType("int");
		parameters.add(pageindex);
		// 分页大小
		MethodParameter pagesize = new MethodParameter();
		pagesize.setName("pageSize");
		pagesize.setType("int");
		parameters.add(pagesize);
		this.setParameters(parameters);

	}

}
