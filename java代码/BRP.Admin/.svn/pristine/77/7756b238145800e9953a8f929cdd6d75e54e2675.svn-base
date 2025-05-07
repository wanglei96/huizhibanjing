package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.common.UniqueFieldInfo;

import java.util.ArrayList;
import java.util.List;

public class TableMasterExistsUniqueColumnMethod extends MethodInfo{
	private UniqueFieldInfo uniqueFieldInfo;

	public UniqueFieldInfo getUniqueFieldInfo() {
		return uniqueFieldInfo;
	}

	public void setUniqueFieldInfo(UniqueFieldInfo uniqueFieldInfo) {
		this.uniqueFieldInfo = uniqueFieldInfo;
	}


	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("SqlSession sqlSession = MyBatisManager.getInstance().openSession();");
		sb.append("Boolean flag = null;");
		sb.append("try{");
		sb.append("flag = ");
		sb.append("exists");
		sb.append(CodeGenerateUtil.upperCase(uniqueFieldInfo.getDataName()));
		sb.append("(sqlSession,");
		sb.append(this.uniqueFieldInfo.getDataName());
		sb.append(");");
		sb.append("}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return flag;");
		return sb.toString();
	}

	public void setMethodParameters(){
		this.setMethodName("exists"+CodeGenerateUtil.upperCase(uniqueFieldInfo.getDataName()));
		this.setReturnType("boolean");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter parameter = new MethodParameter();
		parameter.setName(uniqueFieldInfo.getDataName());
		parameter.setType(CodeGenerateUtil.getTypeString(uniqueFieldInfo.getTypeName()));
		parameters.add(parameter);
		this.setParameters(parameters);
		
	}
	
	
}
