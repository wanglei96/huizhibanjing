package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.TableCodeGenerateContext;

import java.util.ArrayList;
import java.util.List;

public class TableMasterBatchDeleteByEntityMethodTransaction extends MethodInfo{
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("int rows = 0;");
		sb.append("for(int i=0;i<list.size();i++){");
		sb.append("rows += delete(sqlSession,list.get(i));");
		sb.append("}");
		sb.append("return rows;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("batchDeleteByEntity");
		this.setReturnType("int");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
		MethodParameter parameter = new MethodParameter();
		parameter.setName("list");
		parameter.setType("List<? extends DataEntity>");
		parameters.add(parameter);
		this.setParameters(parameters);
	}
	
}
