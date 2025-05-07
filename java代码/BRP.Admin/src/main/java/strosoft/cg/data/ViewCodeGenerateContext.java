package strosoft.cg.data;

import java.util.List;

import strosoft.cg.common.SearchItemInfo;

public class ViewCodeGenerateContext extends CodeGenerateContext {
	private String formattedViewName;
	private String masterName;
	private String managerName;
	private List<CgViewColumn> columns;
	private List<SearchItemInfo> searchFileds;

	public String getViewName() {
		return this.getDataName();
	}

	public void setViewName(String viewName) {
		this.setDataName(viewName);
	}

	public List<CgViewColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<CgViewColumn> columns) {
		this.columns = columns;
	}

	public String getFormattedViewName() {
		return formattedViewName;
	}

	public void setFormattedViewName(String formattedViewName) {
		this.formattedViewName = formattedViewName;
	}

	public List<SearchItemInfo> getSearchFileds() {
		return searchFileds;
	}

	public void setSearchFileds(List<SearchItemInfo> searchFileds) {
		this.searchFileds = searchFileds;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

}
