package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.ViewCodeGenerateContext;

/**
 * 数据管理类构造函数
 * 
 * @author admin
 * 
 */
public class DataManagerConstructMethod extends MethodInfo {
	private ViewCodeGenerateContext context;

	public DataManagerConstructMethod(ViewCodeGenerateContext context) {
		this.context = context;
	}

	public String getMethodBody() {
		StringBuffer sb = new StringBuffer();
		sb.append("this.setTypeName(\"" + context.getFormattedViewName() + "\");");
		return sb.toString();
	}

	public void setMethodParameters() {
		this.setVisibility("private");
		this.setMethodName(context.getManagerName());
		this.setReturnType("");
	}

}
