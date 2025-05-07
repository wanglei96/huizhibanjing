package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.TableCodeGenerateContext;

import java.util.ArrayList;
import java.util.List;

public class TableMasterClearMethodTransaction extends MethodInfo{
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
		sb.append("List<");
		sb.append(context.getFormattedTableName());
		sb.append("> list = getList(sqlSession);");
		sb.append("for(int i=0;i<list.size();i++){");
		sb.append("rows += delete(sqlSession,list.get(i));}return rows;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("clear");
		this.setReturnType("int");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
		this.setParameters(parameters);
		
	}
}
