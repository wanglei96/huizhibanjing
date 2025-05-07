package strosoft.cms.data;

import java.util.HashMap;

public class CmsSqlContentsContext {
	//private String align;// "整体对齐"
	//private String cellPadding;// "填充"
	//private String cellSpacing;// "间距"
	// private String class;//"Css类"
	//private String columns;// "列数"
	private String connectionString;// "数据库链接字符串"
	private String username;
	private String password;
	//private String connectionStringName;// "数据库链接字符串名称"
	//private String direction;// "方向"
	//private String height;// "整体高度"
	//private String isDynamic;// "是否动态显示"
	//private String itemAlign;// "项水平对齐"
	//private String itemClass;// "项Css类"
	//private String itemHeight;// "项高度"
	//private String itemVerticalAlign;// "项垂直对齐"
	//private String itemWidth;// "项宽度"
	//private String layout;// "指定列表布局方式"
	private String order;// "排序"
	private String startNum;// "从第几条信息开始显示"
	private String totalNum;// "显示内容数目"
	private String pageNum;//每一页显示的栏目数目
	//private String width;// "整体宽度"

	public CmsSqlContentsContext() {

	}

	public CmsSqlContentsContext(HashMap<String, String> attributeCondition) {
		this.connectionString = attributeCondition.get("connectionString".toLowerCase());
		this.setUsername(attributeCondition.get("username".toLowerCase()));
		this.setPassword(attributeCondition.get("password".toLowerCase()));
		this.order = attributeCondition.get("order".toLowerCase());
		this.startNum = attributeCondition.get("startNum".toLowerCase());
		this.totalNum = attributeCondition.get("totalNum".toLowerCase());
		this.pageNum = attributeCondition.get("pageNum".toLowerCase());
	}

	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getStartNum() {
		return startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}


}
