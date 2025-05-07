package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.TableCodeGenerateContext;

public class TableMasterDeleteMethodTransaction extends MethodInfo{
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("int rows = delete(sqlSession,getEntity(");
		sb.append(getTableTypeCallMethodParametersStringWhithSqlSession(context));
		sb.append("));return rows;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("delete");
		this.setReturnType("int");
		this.setVisibility("public");
		this.setParameters(getTableMethodParametersWhitSqlSession(context));
	}
	
}
