package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.TableCodeGenerateContext;
import strosoft.cg.data.CgTablePrimaryKey;

import java.util.ArrayList;
import java.util.List;

public class TableMasterAddMethodTransaction extends MethodInfo{
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		//判断主键是否为uuid
		List<CgTablePrimaryKey> primaryKeys = context.getPrimaryKeys();
		if(CodeGenerateUtil.isUUIDPrimaryKey(primaryKeys)){
			sb.append("String uuid = getUUID32();entity.setUuid(uuid);");
		}
		sb.append("int rows = sqlSession.insert(\"");
		sb.append(context.getFormattedTableName());
		sb.append(".insert");
		sb.append(context.getFormattedTableName());
		sb.append("\",entity);");
		sb.append("return rows;");
		return sb.toString();
	}
	
	public void setMethodParameters(){
		this.setMethodName("add");
		this.setReturnType("int");
		this.setVisibility("public");
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
