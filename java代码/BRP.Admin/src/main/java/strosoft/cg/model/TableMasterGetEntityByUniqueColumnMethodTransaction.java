package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.common.UniqueFieldInfo;

import java.util.ArrayList;
import java.util.List;

public class TableMasterGetEntityByUniqueColumnMethodTransaction extends MethodInfo{
	private UniqueFieldInfo uniqueField;
	
	public UniqueFieldInfo getUniqueField() {
		return uniqueField;
	}
	public void setUniqueField(UniqueFieldInfo uniqueField) {
		this.uniqueField = uniqueField;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append(uniqueField.getFormattedTableName());
		sb.append(" entity = sqlSession.selectOne(\"select");
		sb.append(uniqueField.getFormattedTableName());
		sb.append("ByCondition\",\"");
		sb.append(uniqueField.getFieldName());
		sb.append("='\"+");
		sb.append(uniqueField.getDataName());
		sb.append("+\"'\");return entity;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("getEntityBy"+CodeGenerateUtil.upperCase(uniqueField.getDataName()));
		this.setReturnType(uniqueField.getFormattedTableName());
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
		MethodParameter parameter = new MethodParameter();
		parameter.setName(uniqueField.getDataName());
		parameter.setType(CodeGenerateUtil.getTypeString(uniqueField.getTypeName()));
		parameters.add(parameter);
		this.setParameters(parameters);
		
	}
}
