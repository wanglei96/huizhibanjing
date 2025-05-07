package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.CgTableColumn;

public class TableEntitySetValueMethod extends MethodInfo{
	private CgTableColumn column= null;

	public CgTableColumn getColumn() {
		return column;
	}

	public void setColumn(CgTableColumn column) {
		this.column = column;
	}
	
	public String getMethodBody(){
		return "this."+column.getParameterName()+" = "+column.getParameterName()+";";
	}

}
