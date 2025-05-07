package strosoft.cms.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

/*
 * 生成上下文环境
 */
public class CmsBuildContext {
	private CmsBuildTask cmsBuildTask;
	private String creatFilePath;
	private int totalPage;//总页数
	private int totalContentNum;//总翻页内容数
	private List<StringBuffer> pageHtml;//翻页的内容
	private String pageHtmlReplaceStr;//翻页内容的替换标注
	private String CurrentPageHtml;//返回当前页的翻页内容
	private String CurrentPageContentHtml;//返回当前页的翻页容器
	private String isPaging;//是否在翻页可翻页的标签内，stl:channels/stl:contents
	private String isCreatPagingContent;//该项是否生成翻页容器
	private List<String> pageContentHtml;//翻页容器的内容
	private String pageContentHtmlReplaceStr;//翻页容器的替换标注
	private SqlSession sqlSession;//查询数据库传递的sqlSession

	public CmsBuildTask getCmsBuildTask() {
		return cmsBuildTask;
	}

	public void setCmsBuildTask(CmsBuildTask cmsBuildTask) {
		this.cmsBuildTask = cmsBuildTask;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<StringBuffer> getPageHtml() {
		return pageHtml;
	}
	public void setPageHtml(List<StringBuffer> pageHtml) {
		this.pageHtml = pageHtml;
	}
	public String getPageHtmlReplaceStr() {
		return pageHtmlReplaceStr;
	}
	public void setPageHtmlReplaceStr(String pageHtmlReplaceStr) {
		this.pageHtmlReplaceStr = pageHtmlReplaceStr;
	}

	public String getCurrentPageHtml() {
		return CurrentPageHtml;
	}

	public void setCurrentPageHtml(String currentPageHtml) {
		CurrentPageHtml = currentPageHtml;
	}

	public String getCurrentPageContentHtml() {
		return CurrentPageContentHtml;
	}

	public void setCurrentPageContentHtml(String currentPageContentHtml) {
		CurrentPageContentHtml = currentPageContentHtml;
	}

	public String getIsPaging() {
		return isPaging;
	}

	public void setIsPaging(String isPaging) {
		this.isPaging = isPaging;
	}

	public String getIsCreatPagingContent() {
		return isCreatPagingContent;
	}

	public void setIsCreatPagingContent(String isCreatPagingContent) {
		this.isCreatPagingContent = isCreatPagingContent;
	}
	
	public List<String> getPageContentHtml() {
		return pageContentHtml;
	}

	public void setPageContentHtml(List<String> pageContentHtml) {
		this.pageContentHtml = pageContentHtml;
	}

	public String getPageContentHtmlReplaceStr() {
		return pageContentHtmlReplaceStr;
	}

	public void setPageContentHtmlReplaceStr(String pageContentHtmlReplaceStr) {
		this.pageContentHtmlReplaceStr = pageContentHtmlReplaceStr;
	}

	public String getCreatFilePath() {
		return creatFilePath;
	}

	public void setCreatFilePath(String creatFilePath) {
		this.creatFilePath = creatFilePath;
	}

	public int getTotalContentNum() {
		return totalContentNum;
	}

	public void setTotalContentNum(int totalContentNum) {
		this.totalContentNum = totalContentNum;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
