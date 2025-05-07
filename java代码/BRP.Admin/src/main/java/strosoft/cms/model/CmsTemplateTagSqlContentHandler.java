package strosoft.cms.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.w3c.dom.Node;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.util.DateHelper;
import strosoft.cms.data.CmsContentContext;
import strosoft.cms.data.CmsSqlContentContext;
import strosoft.cms.data.ViewCmsContent;

public class CmsTemplateTagSqlContentHandler extends CmsTemplateTagHandler {
	private ViewCmsContent viewCmsContent = null;
	private HashMap<String, String> attributeCondition = null;

	@Override
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) {
		Node currentNode = context.getCurrentNode();
		// 获取属性键值对
		attributeCondition = getAttributeCondition(currentNode);
		// 获取content生成属性参数
		CmsSqlContentContext contentContext = new CmsSqlContentContext(
				attributeCondition);
		String html = "";
		SqlSession sqlSession = null;
		try {
			//获取sqlSession
			sqlSession = getsqlSession(cmsBuildContext, contentContext);
			//获取sql
			String sql = getSql(context, contentContext);
			if(null == sql){
				return "";
			}
			//执行sql
			viewCmsContent = sqlSession.selectOne("ViewCmsContent.selectEntityBysql", sql);
			//根据type获取要显示的属性
			String value = getHtmlByType(context, contentContext);
			// 根据标签属性处理要显示的value值
			value = dealWithValueByAttribute(contentContext, value);
			// separator属性
			html = gethtmlWidthSeparator(value, contentContext, context);
		} catch (Exception e) {
			return "";
		} finally {
			closeSqlSession(cmsBuildContext, contentContext, sqlSession);
		}
		return html;
	}
	
	/**
	 * 获取sqlSession
	 * @param cmsBuildContext
	 * @param contentContext
	 * @return
	 * @throws IOException
	 */
	private SqlSession getsqlSession(CmsBuildContext cmsBuildContext,CmsSqlContentContext contentContext) throws IOException{
		SqlSession sqlSession = null;
		if(null != contentContext.getConnectionString()){
			//定义了数据库连接字符串，创建sqlSession
			String connectionString = contentContext.getConnectionString();
			String userName = contentContext.getUsername();
			String password = contentContext.getPassword();
			String config = getConfig(connectionString, userName, password);
			InputStream in = new ByteArrayInputStream(config.getBytes());
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(in);
			sqlSession = factory.openSession();
			return sqlSession;
		}
		if(null != cmsBuildContext.getSqlSession()){
			//stl:sqlContent包含在stl:sqlContents中传入sqlSession
			sqlSession = cmsBuildContext.getSqlSession();
			return sqlSession;
		}
		//既没有定义连接字符串，又不是在stl:sqlContents标签中，获取系统sqlSession
		return MyBatisManager.getInstance().openSession();
	}
	/**
	 * 获取要执行的sql
	 * @param context
	 * @param contentContext
	 * @return
	 */
	private String getSql(CmsHtmlGenerateContext context,CmsSqlContentContext contentContext){
		if(null != contentContext.getQueryString()){
			//设置了queryString属性，返回queryString的值
			return contentContext.getQueryString();
		}
		if(null != (Integer)context.getCurrentContentId()){
			//未设置queryString属性，但在内容模版或内容列表标签类
			return "select * from view_cms_content where id = "+context.getCurrentContentId();
		}
		//不满足条件，返回空值
		return null;
	}
	/**
	 * 根据type获取要显示的值
	 * @param context
	 * @param contentContext
	 * @return
	 */
	private String getHtmlByType(CmsHtmlGenerateContext context,CmsSqlContentContext contentContext){
		String html = "";
		if (!(contentContext.getType() == null)) {
			String type = contentContext.getType();
			if (type.equalsIgnoreCase("Id")) {
				html = viewCmsContent.getId().toString();
			} else if (type.equalsIgnoreCase("AddUserName")) {
				html = viewCmsContent.getAuthor();
			} else if (type.equalsIgnoreCase("AddDate")) {
				html = DateHelper.toDateString(viewCmsContent.getPublishDate());
			} else if (type.equalsIgnoreCase("Title")) {
				html = viewCmsContent.getTitle();
			} else if (type.equalsIgnoreCase("SubTitle")) {
				html = viewCmsContent.getSubTitle();
			} else if (type.equalsIgnoreCase("Summary")) {
				html = viewCmsContent.getSummary();
			} else if (type.equalsIgnoreCase("ImageUrl")) {
				html = SysConfig.getRootPath()
						+ viewCmsContent.getThumbFilePath();
			} else if (type.equalsIgnoreCase("LinkUrl")) {
				html = SysConfig.getRootPath() + viewCmsContent.getUrl();
			} else if (type.equalsIgnoreCase("FileUrl")) {
				html = viewCmsContent.getAttachmentFilePath();
			} else if (type.equalsIgnoreCase("Author")) {
				html = viewCmsContent.getAuthor();
			} else if (type.equalsIgnoreCase("Source")) {
				html = viewCmsContent.getSource();
			} else if (type.equalsIgnoreCase("Content")) {
				html = viewCmsContent.getText();
			} else if (type.equalsIgnoreCase("ItemIndex")) {
				html = context.getNum() + "";
			}else if (type.equalsIgnoreCase("Url")) {
				html=String.format("<a href='%s'>%s</a>",viewCmsContent.getUrl(),viewCmsContent.getTitle());
			}
		} else {
			html = viewCmsContent.getTitle();
		}
		return html;
	}
	// 根据节点属性，处理获取到的属性值
	public String dealWithValueByAttribute(CmsSqlContentContext contentContext,
			String value) {
		if (!(contentContext.getReplace() == null)
				&& !(contentContext.getTo() == null)) {
			value = value.replaceAll(contentContext.getReplace(),
					contentContext.getTo());
		}
		if (!(contentContext.getStartIndex() == null)) {
			Integer beginIndex = 0;
			try {
				beginIndex = Integer.parseInt(contentContext.getStartIndex());
			} catch (Exception e) {
				e.printStackTrace();
				beginIndex = 0;
			}
			value = value.substring(beginIndex);
		}
		if (!(contentContext.getLength()== null)) {
			Integer length = -1;
			try {
				length = Integer.parseInt(contentContext.getLength());
			} catch (Exception e) {
				e.printStackTrace();
				length = -1;
			}
			value = value.substring(0,length);
		}
		if (!(contentContext.getIsLower() == null)) {
			value = value.toLowerCase();
		}
		if (!(contentContext.getIsUpper() == null)) {
			value = value.toUpperCase();
		}
		if (!(contentContext.getLeftText() == null)) {
			value = contentContext.getLeftText() + value;
		}
		if (!(contentContext.getRightText() == null)) {
			value = value + contentContext.getRightText();
		}
		if (!(contentContext.getWordNum() == null)) {
			Integer wordNum = 0;
			try {
				wordNum = Integer.parseInt(contentContext.getWordNum());
			} catch (Exception e) {
				e.printStackTrace();
				wordNum = 0;
			}
			value = getValueByWordNum(value, wordNum, contentContext);
		}
		return value;
	}

	// separator属性
	public String gethtmlWidthSeparator(String value,
			CmsSqlContentContext contentContext,CmsHtmlGenerateContext context) {
		if (!(contentContext.getSeparator() == null)) {
			if((context.getTotalNum()-1) > context.getNum()){
				value += contentContext.getSeparator();
			}
		}
		return value;
	}

	// 使用wordNum处理value
	public String getValueByWordNum(String value, int wordNum,
			CmsSqlContentContext contentContext) {
		if (value.length() > wordNum) {
			if (!(contentContext.getEllipsis() == null)) {
				return value = value.substring(0, wordNum)
						+ contentContext.getEllipsis();
			} else {
				return value = value.substring(0, wordNum) + "...";
			}
		} else {
			return value;
		}
	}

	
	/**
	 * 关闭sqlSession
	 * @param cmsBuildContext
	 * @param contentContext
	 * @param sqlSession
	 */
	private void closeSqlSession(CmsBuildContext cmsBuildContext,CmsSqlContentContext contentContext,SqlSession sqlSession){
		if(null == cmsBuildContext.getSqlSession() && null != sqlSession){
			//关闭不是传入的sqlSession
			sqlSession.close();
		}
	}


}
