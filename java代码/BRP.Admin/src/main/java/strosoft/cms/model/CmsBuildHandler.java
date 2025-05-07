package strosoft.cms.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.ibatis.session.SqlSession;
import org.xml.sax.SAXException;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;

/**
 * 生成处理类
 * 
 * @author Administrator
 *
 */
public class CmsBuildHandler {
	protected void process(CmsBuildContext context) throws SQLException, IOException, Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		context.setSqlSession(sqlSession);
		try {
			processByCmsLanguage(context);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
	}

	// 替换分页内容和分页容器
	public String buildPaging(CmsBuildContext context, String html, String pageHtml, String pageContentHtml) {
		html = html.replace(context.getPageHtmlReplaceStr(), pageHtml);
		if (pageContentHtml != null || context.getPageContentHtmlReplaceStr() != null) {
			html = html.replace(context.getPageContentHtmlReplaceStr(), pageContentHtml);
		}
		return html;
	}

	// 设置栏目页分页每页地址
	public List<String> setPagePath(String createFilePath, int length) {
		List<String> pagePathList = new ArrayList<String>();
		String[] createFilePathList = createFilePath.split("\\.");
		pagePathList.add(SysConfig.getRootPath() + createFilePath);
		for (int i = 1; i < length; i++) {
			pagePathList
					.add(SysConfig.getRootPath() + createFilePathList[0] + "_" + (i + 1) + "." + createFilePathList[1]);
		}
		return pagePathList;
	}

	// 清除html中的<#text>和</#text>
	public String clearTextNodes(String html) {
		String removeString = "<#text>";
		html = html.replace(removeString, "");
		removeString = "</#text>";
		html = html.replace(removeString, "");
		return html;
	}

	// 分语言生成
	public void processByCmsLanguage(CmsBuildContext context) throws IOException, SQLException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException, Exception {

	}
}
