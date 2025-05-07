package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.TableCodeGenerateContext;
import strosoft.cg.data.CgTablePrimaryKey;

public class TableMasterGetEntityMethodTransaction extends MethodInfo{
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append(context.getFormattedTableName());
		sb.append(" entity = createEntity();");
		for(int i=0;i<context.getPrimaryKeys().size();i++){
			CgTablePrimaryKey currentKey =  context.getPrimaryKeys().get(i);
			sb.append("entity.set");
			sb.append(CodeGenerateUtil.upperCase(currentKey.getParameterName()));
			sb.append("(");
			sb.append(currentKey.getParameterName());
			sb.append(");");
		}
		sb.append(context.getFormattedTableName());
		sb.append(" newEntity = sqlSession.selectOne(\"");
		sb.append(context.getFormattedTableName());
		sb.append(".select");
		sb.append(context.getFormattedTableName());
		sb.append("\",entity);return newEntity;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("getEntity");
		this.setReturnType(context.getFormattedTableName());
		this.setVisibility("public");
		this.setParameters(getTableMethodParametersWhitSqlSession(context));
		
	}
}
