package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.CgViewColumn;

public class ViewEntityGetValueMethod extends MethodInfo{
	private CgViewColumn column;

	public CgViewColumn getColumn() {
		return column;
	}

	public void setColumn(CgViewColumn column) {
		this.column = column;
	}
	
	public String getMethodBody(){
		return "return "+column.getParameterName()+";";
	}
}
