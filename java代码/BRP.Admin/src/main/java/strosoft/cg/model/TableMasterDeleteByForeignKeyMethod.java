package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.CgTableForeignKey;

import java.util.ArrayList;
import java.util.List;

public class TableMasterDeleteByForeignKeyMethod extends MethodInfo{
	private CgTableForeignKey foreignKey;
	public CgTableForeignKey getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(CgTableForeignKey foreignKey) {
		this.foreignKey = foreignKey;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("SqlSession sqlSession = MyBatisManager.getInstance().openSession();");
		sb.append("int rows = 0;");
		sb.append("try{");
		sb.append("rows = ");
		sb.append("deleteBy");
		sb.append(CodeGenerateUtil.upperCase(foreignKey.getFormattedReferencesColumnName()));
		sb.append("(sqlSession,");
		sb.append(this.foreignKey.getFormattedReferencesColumnName());
		sb.append(");");
		sb.append("sqlSession.commit();}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return rows;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setForeignKey(foreignKey);
		this.setMethodName("deleteBy"+CodeGenerateUtil.upperCase(foreignKey.getFormattedReferencesColumnName()));
		this.setReturnType("int");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter parameter = new MethodParameter();
		parameter.setName(foreignKey.getFormattedReferencesColumnName());
		parameter.setType(CodeGenerateUtil.getTypeString(foreignKey.getTypeName()));
		parameters.add(parameter);
		this.setParameters(parameters);
		
	}
	
	
}
