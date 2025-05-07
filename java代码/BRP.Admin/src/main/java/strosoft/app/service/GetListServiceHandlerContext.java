package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;

public class GetListServiceHandlerContext {
	public GetListServiceHandlerContext() {
		this.setFields("*");
		this.setPageSize(10);
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getPropertyNameType() {
		return propertyNameType;
	}

	public void setPropertyNameType(String propertyNameType) {
		this.propertyNameType = propertyNameType;
	}

	// 查询字段
	private String fields;
	// 表名
	private String tableName;
	// 条件
	private String condition;
	// 排序字段
	private String orderBy;
	// 分组
	private String groupBy;
	// 页号
	private int pageIndex;
	// 分页大小
	private int pageSize;
	// 记录数
	private int recordCount;
	// 页数
	private int pageCount;
	// 属性格式（默认为全部，all：全部，camel：驼峰，raw：原始字段）
	private String propertyNameType = "all";
	/**
	 * 请求
	 */
	private HttpServletRequest request;

}
