package strosoft.app.model;

public class ListInfo {
	private int pageCount;
	private int pageIndex;
	private int recordCount;
	private int pageSize;
	private Object DataList;
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Object getDataList() {
		return DataList;
	}
	public void setDataList(Object dataList) {
		DataList = dataList;
	}
}
