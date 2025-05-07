package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.CgTableForeignKey;

import java.util.ArrayList;
import java.util.List;

public class TableMasterGetListByForeignKeyMethodTransaction extends MethodInfo{
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
		sb.append("> entitys = sqlSession.selectList(\"");
		sb.append(foreignKey.getFormattedReferencesTableName());
		sb.append(".select");
		sb.append(foreignKey.getFormattedReferencesTableName());
		sb.append("By");
		sb.append(CodeGenerateUtil.upperCase(this.foreignKey.getFormattedReferencesColumnName()));
		sb.append("\",");
		sb.append(this.foreignKey.getFormattedReferencesColumnName());
		sb.append(");return entitys;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("getListBy"+CodeGenerateUtil.upperCase(foreignKey.getFormattedReferencesColumnName()));
		this.setReturnType("List<"+foreignKey.getFormattedReferencesTableName()+">");
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
