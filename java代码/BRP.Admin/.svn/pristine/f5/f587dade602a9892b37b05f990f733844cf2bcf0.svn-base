package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.TableCodeGenerateContext;

import java.util.ArrayList;
import java.util.List;

public class TableMasterGetEntityMethod extends MethodInfo{
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
		sb.append(context.getFormattedTableName());
		sb.append(" newEntity = null;");
		sb.append("try{");
		sb.append("newEntity = getEntity(");
		sb.append(getTableTypeCallMethodParametersStringWhithSqlSession(context));
		sb.append(");}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return newEntity;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("getEntity");
		this.setReturnType(context.getFormattedTableName());
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		this.setParameters(getTableMethodParameters(context));
		
	}
	
}
