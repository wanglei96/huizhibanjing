package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.data.TableCodeGenerateContext;

import java.util.ArrayList;
import java.util.List;

public class TableMasterUpdateMethod extends MethodInfo{
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
		sb.append("rows = update(sqlSession,entity);");
		sb.append("sqlSession.commit();}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return rows;");
		return sb.toString();
	}
	
	public void setMethodParameters(){
		this.setMethodName("update");
		this.setReturnType("int");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		MethodParameter parameter = new MethodParameter();
		parameter.setName("entity");
		parameter.setType(context.getFormattedTableName());
		parameters.add(parameter);
		this.setParameters(parameters);
	}
}
