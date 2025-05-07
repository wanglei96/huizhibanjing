package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.TableCodeGenerateContext;

import java.util.ArrayList;
import java.util.List;

public class TableMasterBatchDeleteMethod extends MethodInfo{
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("SqlSession sqlSession = MyBatisManager.getInstance().openSession();");
		sb.append("int rows = 0;");
		sb.append("try{");
		sb.append("for(int i=0;i<list.size();i++){");
		sb.append("rows += delete(sqlSession,list.get(i));");
		sb.append("}sqlSession.commit();");
		sb.append("}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();rows=0;throw e;} finally {sqlSession.close();}return rows;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("batchDelete");
		this.setReturnType("int");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter parameter = new MethodParameter();
		parameter.setName("list");
		parameter.setType("List<"+context.getPrimaryKeys().get(0).getShortTypeName()+">");
		parameters.add(parameter);
		this.setParameters(parameters);
	}
	
}
