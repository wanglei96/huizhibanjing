package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.TableCodeGenerateContext;

public class TableMasterGetDataEntityMethodTransaction extends MethodInfo{
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("DataEntity newDataEntity = getEntity(");
		sb.append(getTableTypeCallMethodParametersStringWhithSqlSession(context));
		sb.append("); return newDataEntity;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("getDataEntity");
		this.setReturnType("DataEntity");
		this.setVisibility("public");
		this.setParameters(getTableMethodParametersWhitSqlSession(context));
	}
}
