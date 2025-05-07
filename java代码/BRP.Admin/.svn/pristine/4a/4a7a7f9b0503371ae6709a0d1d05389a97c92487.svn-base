package strosoft.cg.model;

import java.util.ArrayList;
import java.util.List;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.CodeGenerateContext;

public class DataMasterGetListPageMethodTransaction extends MethodInfo {
	private CodeGenerateContext context;

	public DataMasterGetListPageMethodTransaction(CodeGenerateContext context) {
		this.context = context;
	}

	private String entityName;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getMethodBody() {
		StringBuffer sb = new StringBuffer();
		sb.append("String sql = String.format(\"select * from " + context.getDataName()
				+ " limit %s, %s\", pageSize * pageIndex, pageSize);");
		sb.append("List<" + entityName + "> list = sqlSession.selectList(\"" + entityName + ".select" + entityName
				+ "BySql\", sql);");
		sb.append("return list;");
		return sb.toString();
	}

	public void setMethodParameters() {
		this.setMethodName("getList");
		this.setReturnType("List<" + entityName + ">");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		// 会话
		MethodParameter transactionMethodParameter = new MethodParameter();
		transactionMethodParameter.setName("sqlSession");
		transactionMethodParameter.setType("SqlSession");
		parameters.add(transactionMethodParameter);
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
