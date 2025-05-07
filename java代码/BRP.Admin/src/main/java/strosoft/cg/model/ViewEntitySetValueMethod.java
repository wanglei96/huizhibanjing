package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.CgViewColumn;

public class ViewEntitySetValueMethod extends MethodInfo{
	private CgViewColumn column= null;

	public CgViewColumn getColumn() {
		return column;
	}

	public void setColumn(CgViewColumn column) {
		this.column = column;
	}
	
	public String getMethodBody(){
		return "this."+column.getParameterName()+" = "+column.getParameterName()+";";
	}

}
