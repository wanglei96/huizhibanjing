package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.TableCodeGenerateContext;

public class TableEntityCopyEntityMethod extends MethodInfo{
	private TableCodeGenerateContext context= null;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer methodBody = new StringBuffer();
		methodBody.append(context.getFormattedTableName());
		methodBody.append(" entity = null;");
		methodBody.append("try{entity = (");
		methodBody.append(context.getFormattedTableName());
		methodBody.append(") super.clone();} catch (CloneNotSupportedException e) {e.printStackTrace();}return entity;");
		return methodBody.toString();
	}
	
	public void copy() throws CloneNotSupportedException{
		super.clone();
	}


}
