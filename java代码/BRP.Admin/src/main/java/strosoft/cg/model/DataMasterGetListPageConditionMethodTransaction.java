package strosoft.cg.model;

import java.util.ArrayList;
import java.util.List;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

public class DataMasterGetListPageConditionMethodTransaction extends MethodInfo {
	private String entityName;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getMethodBody() {
		StringBuffer sb = new StringBuffer();
		sb.append("String sql = condition + \" limit \" + pageSize * pageIndex + \",\" + pageSize;");
		sb.append("List<");
		sb.append(entityName);
		sb.append("> list = sqlSession.selectList(\"");
		sb.append(entityName);
		sb.append(".select");
		sb.append(CodeGenerateUtil.upperCase(entityName));
		sb.append("ByCondition\",sql);return list;");
		return sb.toString();
	}

	public void setMethodParameters() {
		this.setMethodName("getList");
		this.setReturnType("List<" + entityName + ">");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		// 会话
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
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
