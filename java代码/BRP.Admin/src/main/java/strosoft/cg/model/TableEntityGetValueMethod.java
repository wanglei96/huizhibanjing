package strosoft.cg.model;

import strosoft.cg.common.MethodInfo;
import strosoft.cg.data.CgTableColumn;

public class TableEntityGetValueMethod extends MethodInfo{
	private CgTableColumn column;

	public CgTableColumn getColumn() {
		return column;
	}

	public void setColumn(CgTableColumn column) {
		this.column = column;
	}
	
	public String getMethodBody(){
		return "return "+column.getParameterName()+";";
	}
}
