package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.TableCodeGenerateContext;

import java.util.ArrayList;
import java.util.List;

public class TableMasterGetDataEntityMethod extends MethodInfo{
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
		sb.append("DataEntity newDataEntity = null;");
		sb.append("try{");
		sb.append("newDataEntity = getDataEntity(");
		sb.append(getTableTypeCallMethodParametersStringWhithSqlSession(context));
		sb.append(");");
		sb.append("}catch(Exception  e){");
		sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return newDataEntity;");
		return sb.toString();
	}
	public void setMethodParameters(){
		this.setMethodName("getDataEntity");
		this.setReturnType("DataEntity");
		this.setVisibility("public");
		List<String> exceptions = new ArrayList<String>();
		exceptions.add("Exception");
		this.setExceptions(exceptions);
		this.setParameters(getTableMethodParameters(context));
	}
	
}
