package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.CgTableForeignKey;

import java.util.ArrayList;
import java.util.List;

public class TableMasterDeleteByForeignKeyMethodTransaction extends MethodInfo{
	private CgTableForeignKey foreignKey;
	public CgTableForeignKey getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(CgTableForeignKey foreignKey) {
		this.foreignKey = foreignKey;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("List<");
		sb.append(foreignKey.getFormattedReferencesTableName());
		sb.append("> list = getListBy");
		sb.append(CodeGenerateUtil.upperCase(foreignKey.getFormattedReferencesColumnName()));
		sb.append("(sqlSession,");
		sb.append(foreignKey.getFormattedReferencesColumnName());
		sb.append(");");
		sb.append("int rows = batchDeleteByEntity(sqlSession,list);return rows;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setForeignKey(foreignKey);
		this.setMethodName("deleteBy"+CodeGenerateUtil.upperCase(foreignKey.getFormattedReferencesColumnName()));
		this.setReturnType("int");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
		MethodParameter parameter = new MethodParameter();
		parameter.setName(foreignKey.getFormattedReferencesColumnName());
		parameter.setType(CodeGenerateUtil.getTypeString(foreignKey.getTypeName()));
		parameters.add(parameter);
		this.setParameters(parameters);
		
	}
}
