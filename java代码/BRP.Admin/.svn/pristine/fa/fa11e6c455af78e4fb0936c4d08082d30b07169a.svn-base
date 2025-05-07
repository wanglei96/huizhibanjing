package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.TableCodeGenerateContext;

import java.util.ArrayList;
import java.util.List;

public class TableMasterUpdateMethodTransaction extends MethodInfo{
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("int rows = sqlSession.update(\"");
		sb.append(context.getFormattedTableName());
		sb.append(".update");
		sb.append(context.getFormattedTableName());
		sb.append("\",entity);");
		sb.append("return rows;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("update");
		this.setReturnType("int");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("IOException");
		this.setExceptions(exceptions);
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
		MethodParameter parameter = new MethodParameter();
		parameter.setName("entity");
		parameter.setType(context.getFormattedTableName());
		parameters.add(parameter);
		this.setParameters(parameters);
		
	}
	
}
