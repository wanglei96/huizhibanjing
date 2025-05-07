package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;

import java.util.ArrayList;
import java.util.List;

public class TableMasterClearMethod extends MethodInfo{
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("SqlSession sqlSession = MyBatisManager.getInstance().openSession();");
		sb.append("int rows = 0;");
		sb.append("try{");
		sb.append("rows = ");
		sb.append("clear");
		sb.append("(sqlSession);");
		sb.append("sqlSession.commit();}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return rows;");
		return sb.toString();
	}

	public void setMethodParameters(){
		this.setMethodName("clear");
		this.setReturnType("int");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		this.setParameters(parameters);
	}
	
}
