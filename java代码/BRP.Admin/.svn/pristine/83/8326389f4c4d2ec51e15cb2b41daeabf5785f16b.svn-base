package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.TableCodeGenerateContext;
import strosoft.cg.data.CgTableForeignKey;

import java.util.ArrayList;
import java.util.List;

public class TableMasterDeleteByEntityMethodTransaction extends MethodInfo{
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<context.getReferncedforeignKeys().size();i++){
			CgTableForeignKey currentKey = context.getReferncedforeignKeys().get(i);
			sb.append(currentKey.getFormattedReferencesTableName());
			sb.append("Manager.getInstance().deleteBy");
			sb.append(CodeGenerateUtil.upperCase(currentKey.getFormattedReferencesColumnName()));
			sb.append("(sqlSession,(("+context.getFormattedTableName()+")entity).get");
			sb.append(CodeGenerateUtil.upperCase(currentKey.getFormattedReferencedColumnName()));
			sb.append("());");
		}
		sb.append("int rows = sqlSession.delete(\"");
		sb.append(context.getFormattedTableName());
		sb.append(".delete");
		sb.append(context.getFormattedTableName());
		sb.append("\",entity);");
		sb.append("return rows;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("delete");
		this.setReturnType("int");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
		MethodParameter parameter = new MethodParameter();
		parameter.setName("entity");
		parameter.setType("DataEntity");
		parameters.add(parameter);
		this.setParameters(parameters);
	}
	
	
}
