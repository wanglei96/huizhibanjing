package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.TableCodeGenerateContext;
import strosoft.cg.data.CgTablePrimaryKey;

import java.util.ArrayList;
import java.util.List;

public class TableMasterAddListMethodTransaction extends MethodInfo{
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("if(null == list || list.size() == 0){return 0;}");
		//判断主键是否为uuid
		List<CgTablePrimaryKey> primaryKeys = context.getPrimaryKeys();
		if(CodeGenerateUtil.isUUIDPrimaryKey(primaryKeys)){
			sb.append("for(int i=0;i<list.size();i++){String uuid = getUUID32();list.get(i).setUuid(uuid);}");
		}
		sb.append("int rows = sqlSession.insert(\"");
		sb.append(context.getFormattedTableName());
		sb.append(".insert");
		sb.append(context.getFormattedTableName()+"s");
		sb.append("\",list);");
		sb.append("return rows;");
		return sb.toString();
		
	}
	
	
	public void setMethodParameters(){
		this.setMethodName("addList");
		this.setReturnType("int");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
		MethodParameter parameter = new MethodParameter();
		parameter.setName("list");
		parameter.setType("List<"+context.getFormattedTableName()+">");
		parameters.add(parameter);
		this.setParameters(parameters);
	}
	
}
