package strosoft.sys.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.util.XmlDocumentHelper;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * 数据管理类
 *
 * @author Admin
 */
public class DataManager {

	private static DataManager _instance;

	public static DataManager getInstance() {
		if (_instance == null) {
			_instance = new DataManager();
		}
		return _instance;
	}

	private DataManager() {

	}

	/**
	 * 初始化系统
	 *
	 * @throws Exception
	 */
	public void resetSystem() throws Exception {

		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			// 清空表数据
			this.resetTables(sqlSession);
			// 初始化权限
			PermissionManager.getInstance().resetAdminPermissions(sqlSession);
			sqlSession.commit();
		} catch (IOException e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 清空表数据
	 * 
	 * @param sqlSession
	 * @throws Exception
	 */
	private void resetTables(SqlSession sqlSession) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		String configFilePath = SysConfig.getRootPath() + "/config/ResetTables.xml";
		Document document = db.parse(configFilePath);
		NodeList name = document.getElementsByTagName("Table");
		ArrayList<String> lstResetTable = new ArrayList<String>();
		MyBatisManager.getInstance().executeUpdate(sqlSession,"SET FOREIGN_KEY_CHECKS = 0");
		for (int i = 0; i < name.getLength(); i++) {
			Node item = name.item(i);
			String tableName = XmlDocumentHelper.getAttribute(item, "Name");
			String sql1 = String.format("TRUNCATE TABLE %s",tableName);
			lstResetTable.add(sql1);
		}
		MyBatisManager.getInstance().executeUpdate(sqlSession, lstResetTable);
		MyBatisManager.getInstance().executeUpdate(sqlSession,"SET FOREIGN_KEY_CHECKS = 1");
	}
}
