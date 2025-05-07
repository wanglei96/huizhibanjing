package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.common.UniqueFieldInfo;

import java.util.ArrayList;
import java.util.List;

public class TableMasterExistsUniqueColumnMethodTransaction extends MethodInfo{
	private UniqueFieldInfo uniqueFieldInfo;
	
	public UniqueFieldInfo getUniqueFieldInfo() {
		return uniqueFieldInfo;
	}

	public void setUniqueFieldInfo(UniqueFieldInfo uniqueFieldInfo) {
		this.uniqueFieldInfo = uniqueFieldInfo;
	}
	
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("Integer rows = sqlSession.selectOne(\"");
		sb.append(uniqueFieldInfo.getFormattedTableName());
		sb.append(".selectIntBySql");
		sb.append("\",\"select count(*) from ");
		sb.append(CodeGenerateUtil.camelToUnderline((uniqueFieldInfo.getFormattedTableName())));
		sb.append(" where ");
		sb.append(this.uniqueFieldInfo.getFieldName());
		sb.append("= '\"+");
		sb.append(this.uniqueFieldInfo.getDataName());
		sb.append("+\"'\");return rows>0?true:false;");
		return sb.toString();
	}

	public void setMethodParameters(){
		this.setMethodName("exists"+CodeGenerateUtil.upperCase(uniqueFieldInfo.getDataName()));
		this.setReturnType("boolean");
		this.setVisibility("public");
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter transaction = new MethodParameter();
		transaction.setName("sqlSession");
		transaction.setType("SqlSession");
		parameters.add(transaction);
		MethodParameter parameter = new MethodParameter();
		parameter.setName(uniqueFieldInfo.getDataName());
		parameter.setType(CodeGenerateUtil.getTypeString(uniqueFieldInfo.getTypeName()));
		parameters.add(parameter);
		this.setParameters(parameters);
		
	}
	
}
