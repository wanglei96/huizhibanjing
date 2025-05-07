package strosoft.cg.model;

import java.util.ArrayList;
import java.util.List;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

public class DataMasterGetListPageConditionOrderByMethodTransaction extends MethodInfo {
	private String entityName;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Override
	public String getMethodBody() {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"String sql = condition +\" order by \" + orderBy + \" limit \" + pageSize * pageIndex + \" , \" + pageSize;");
		sb.append("List<");
		sb.append(entityName);
		sb.append("> list = sqlSession.selectList(\"");
		sb.append(entityName);
		sb.append(".select");
		sb.append(CodeGenerateUtil.upperCase(entityName));
		sb.append("ByCondition\",sql);return list;");
		return sb.toString();
	}

	@Override
	public void setMethodParameters() {
		this.setMethodName("getList");
		this.setReturnType("List<" + entityName + ">");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transactionParameter = new MethodParameter();
		transactionParameter.setName("sqlSession");
		transactionParameter.setType("SqlSession");
		MethodParameter conditionParameter = new MethodParameter();
		conditionParameter.setName("condition");
		conditionParameter.setType("String");
		MethodParameter orderByParameter = new MethodParameter();
		orderByParameter.setName("orderBy");
		orderByParameter.setType("String");
		MethodParameter pageIndexParameter = new MethodParameter();
		pageIndexParameter.setName("pageIndex");
		pageIndexParameter.setType("int");
		MethodParameter pageSizeParameter = new MethodParameter();
		pageSizeParameter.setName("pageSize");
		pageSizeParameter.setType("int");
		parameters.add(transactionParameter);
		parameters.add(conditionParameter);
		parameters.add(orderByParameter);
		parameters.add(pageIndexParameter);
		parameters.add(pageSizeParameter);
		this.setParameters(parameters);
	}
}
