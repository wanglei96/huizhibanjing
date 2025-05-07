package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.ViewCodeGenerateContext;

public class ViewEntityCopyEntityMethod extends MethodInfo{
	private ViewCodeGenerateContext context= null;

	public ViewCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(ViewCodeGenerateContext context) {
		this.context = context;
	}
	public String getMethodBody(){
		StringBuffer methodBody = new StringBuffer();
		methodBody.append(context.getFormattedViewName());
		methodBody.append(" entity = null;");
		methodBody.append("try{entity = (");
		methodBody.append(context.getFormattedViewName());
		methodBody.append(") super.clone();} catch (CloneNotSupportedException e) {e.printStackTrace();}return entity;");
		return methodBody.toString();
	}
	
	public void copy() throws CloneNotSupportedException{
		super.clone();
	}


}
