package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.TableCodeGenerateContext;

import java.util.ArrayList;
import java.util.List;

public class TableMasterDeleteMethod extends MethodInfo{
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
		sb.append("rows = delete(");
		sb.append(getTableTypeCallMethodParametersStringWhithSqlSession(context));
		sb.append(");sqlSession.commit();}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return rows;");
		return sb.toString();
	}
	
	public void setMethodParameters(){
		this.setMethodName("delete");
		this.setReturnType("int");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		this.setParameters(getTableMethodParameters(context));
	}
	
	
}
