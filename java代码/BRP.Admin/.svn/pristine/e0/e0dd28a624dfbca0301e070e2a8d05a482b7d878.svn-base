package strosoft.sys.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import strosoft.app.common.SysConfig;
import strosoft.app.util.DateHelper;
import strosoft.app.util.DocumentHelper;
import strosoft.sys.data.DatabaseBackup;

/**
 * 数据库备份管理类
 * 
 * @author Admin
 *
 */
public class DatabaseBackupManager {

	private static DatabaseBackupManager _instance;

	public static DatabaseBackupManager getInstance() {
		if (_instance == null) {
			_instance = new DatabaseBackupManager();
		}
		return _instance;
	}

	private DatabaseBackupManager() {
	}

	/**
	 * Java代码实现MySQL数据库导出
	 * 
	 * @author GaoHuanjie
	 * @param hostIP       MySQL数据库所在服务器地址IP
	 * @param userName     进入数据库所需要的用户名
	 * @param password     进入数据库所需要的密码
	 * @param savePath     数据库导出文件保存路径
	 * @param fileName     数据库导出文件文件名
	 * @param databaseName 要导出的数据库名
	 * @return 返回true表示导出成功，否则返回false。
	 * @throws Exception
	 */
	public static boolean exportDatabaseTool(String hostIP, String port, String userName, String password,
			String savePath, String fileName, String databaseName) throws Exception {
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {// 如果目录不存在
			saveFile.mkdirs();// 创建文件夹
		}
		if (!savePath.endsWith(File.separator)) {
			savePath = savePath + File.separator;
		}
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
			String mysql = "mysqldump -h " + hostIP + " -P" + port + " -u" + userName + " -p" + password
					+ " --set-charset=UTF8 " + databaseName;
			Runtime rt = Runtime.getRuntime();
			String os = System.getProperty("os.name");
			Process child = null;
			if (os.toLowerCase().startsWith("win")) {
				child = rt.exec(getName("mysqlURL") + mysql);
			} else if (os.toLowerCase().startsWith("lin")) {
				child = rt.exec(new String[] { "sh", "-c", mysql });
			} else {
				System.out.println("other");
			}
			InputStreamReader inputStreamReader = new InputStreamReader(child.getInputStream(), "utf8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				printWriter.println(line);
			}
			printWriter.flush();
			if (child.waitFor() == 0) {// 0 表示线程正常终止。
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * @param savePath     数据库备份的脚本路径
	 * @param fileName     数据库备份的文件名
	 * @param hostIP       IP地址
	 * @param databaseName 数据库名称
	 * @param userName     用户名
	 * @param password     密码
	 * @return
	 */
	public static boolean restore(String hostIP, String userName, String password, String savePath, String fileName,
			String databaseName) throws Exception {
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {// 如果目录不存在
			saveFile.mkdirs();// 创建文件夹
		}
		if (!savePath.endsWith(File.separator)) {
			savePath = savePath + File.separator;
		}
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		OutputStreamWriter outputStreamWriter = null;
		try {
			String mysql = "mysql.exe -h " + hostIP + " -u" + userName + " -p" + password
					+ " --default-character-set=utf8 " + databaseName;
			Runtime newRuntime = Runtime.getRuntime();
			String os = System.getProperty("os.name");
			Process child = null;
			if (os.toLowerCase().startsWith("win")) {
				child = newRuntime.exec(getName("mysqlURL") + mysql);
			} else if (os.toLowerCase().startsWith("lin")) {
				child = newRuntime.exec(new String[] { "sh", "-c", mysql });
			} else {
				System.out.println("other");
			}
			outputStreamWriter = new OutputStreamWriter(child.getOutputStream(), "utf8");
			printWriter = new PrintWriter(outputStreamWriter);
			bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(savePath + fileName), "utf-8"));
			String line;
			// StringBuffer sb = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				printWriter.println(line);
				// sb.append(line + "\r\n");
			}
//            line = sb.toString();
//            outputStreamWriter.write(line);
//            outputStreamWriter.flush();
//            outputStreamWriter.close();
			if (printWriter != null) {
				printWriter.close();
			}
			if (child.waitFor() == 0) {// 0 表示线程正常终止。
				return true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (outputStreamWriter != null) {
					outputStreamWriter.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean deleteBackupFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return true;
		}
		if (!file.isFile()) {
			return false;
		}
		return file.delete();
	}

	public static String getName(String name) throws Exception {
		String rootPath = SysConfig.getRootPath();
		Properties theProperties = new Properties();
		FileInputStream theFileInputStream = new FileInputStream(rootPath + "/config/config.properties");
		theProperties.load(theFileInputStream);
		return theProperties.getProperty(name);
	}

	public static void main(String[] args) throws Exception {
		try {
			if (restore("127.0.0.1", "root", "root", "E:\\backupDatabase\\", "20190617162342842.sql", "stro_cms")) {
				System.out.println("数据库恢复成功！！！");
			} else {
				System.out.println("数据库恢复失败！！！");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得数据库备份配置文件路径
	 * 
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws Exception
	 */
	public List<DatabaseBackup> getList() throws SAXException, IOException, Exception {
		Document doc = this.getDatabaseBackupDocument();
		List<DatabaseBackup> lstDatabaseBackup = new ArrayList<DatabaseBackup>();
		NodeList nlDatabaseBackup = doc.getDocumentElement().getElementsByTagName("DatabaseBackup");
		for (int i = 0; i < nlDatabaseBackup.getLength(); i++) {
			Element currentElement = (Element) nlDatabaseBackup.item(i);
			DatabaseBackup newDatabaseBackup = new DatabaseBackup();
			newDatabaseBackup.setId(currentElement.getAttribute("ID"));
			newDatabaseBackup.setName(currentElement.getAttribute("Name"));
			newDatabaseBackup.setTime(currentElement.getAttribute("Time"));
			newDatabaseBackup.setDescription(currentElement.getAttribute("Description"));
			lstDatabaseBackup.add(newDatabaseBackup);
		}
		return lstDatabaseBackup;
	}

	/**
	 * 取得数据库备份配置文档
	 * 
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	private Document getDatabaseBackupDocument() throws SAXException, IOException, ParserConfigurationException {
		String configFilePath = SysConfig.getRealDatabaseBackupConfigFilePath();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder newDocumentBuilder = factory.newDocumentBuilder();
		Document doc = newDocumentBuilder.parse(configFilePath);
		return doc;
	}

	/**
	 * 获取属性名数组
	 */
	private String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	public void start() {
		System.out.println("自动 备份启动了");
		if (SysConfig.getDatabaseBackupMode().equals("Interval")) {
			System.out.println("自动 备份启动了");
			this.startIntervalBackup();
		} else if (SysConfig.getDatabaseBackupMode().equals("Time")) {
			System.out.println("自动 备份启动了");
			this.startTimeModeBackup();
		}
	}

	/**
	 * 启动时间间隔模式备份
	 */
	private void startIntervalBackup() {
		TimerTask newTimerTask = new TimerTask() {
			@Override
			public void run() {
				try {

					String id = DateHelper.getDateString();
					String name = DateHelper.getDateString();
					String time = DateHelper.getDateString();
					String description = "系统自动备份";
					DatabaseBackup newDatabaseBackup = new DatabaseBackup(id, name + ".sql", time, description);

					String databaseHost = SysConfig.getDatabaseHost();
					String databasePort = SysConfig.getDatabasePort();

					String databaseUser = SysConfig.getDatabaseUser();
					String databasePassword = SysConfig.getDatabasePassword();
					String databaseName = SysConfig.getDatabaseName();
					String databaseBackupDirectory = SysConfig.getDatabaseBackupPath();
					Boolean success = DatabaseBackupManager.exportDatabaseTool(databaseHost, databasePort, databaseUser,
							databasePassword, SysConfig.getRealDatabaseBackupPath(), id + ".sql", databaseName);
					if (success) {
						DatabaseBackupManager.getInstance().add(newDatabaseBackup);
						System.out.println("数据库成功备份！！！");

					} else {

						System.out.println("数据库备份失败！！！");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		new Timer().scheduleAtFixedRate(newTimerTask, 1000L, SysConfig.getDatabaseBackupInterval() * 1000);

	}

	/**
	 * 启动定时模式备份
	 */
	private void startTimeModeBackup() {
		System.out.println(SysConfig.getDatabaseBackupTime());
		final String[] arrBackupTime = SysConfig.getDatabaseBackupTime().split("\\|");
		TimerTask newTimerTask = new TimerTask() {
			@Override
			public void run() {
				try {

					String newTime = DateHelper.getTimeString();
					for (int i = 0; i < arrBackupTime.length; i++) {
						if (newTime.equals(arrBackupTime[i])) {
							String id = DateHelper.getDateString();
							String name = DateHelper.getDateString();
							String time = DateHelper.getDateString();
							String description = "系统自动备份";
							DatabaseBackup newDatabaseBackup = new DatabaseBackup(id, name + ".sql", time, description);

							String databaseHost = SysConfig.getDatabaseHost();
							String databasePort = SysConfig.getDatabasePort();

							String databaseUser = SysConfig.getDatabaseUser();
							String databasePassword = SysConfig.getDatabasePassword();
							String databaseName = SysConfig.getDatabaseName();
							String databaseBackupDirectory = SysConfig.getDatabaseBackupPath();
							Boolean success = DatabaseBackupManager.exportDatabaseTool(databaseHost, databasePort,
									databaseUser, databasePassword, SysConfig.getRealDatabaseBackupPath(), id + ".sql",
									databaseName);
							if (success) {
								DatabaseBackupManager.getInstance().add(newDatabaseBackup);
								System.out.println("数据库成功备份！！！");

							} else {

								System.out.println("数据库备份失败！！！");
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		new Timer().scheduleAtFixedRate(newTimerTask, 1000L, 1000);

	}

	/**
	 * 添加数据库备份
	 * 
	 * @param newDatabaseBackup
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public void add(DatabaseBackup newDatabaseBackup) throws SAXException, IOException, ParserConfigurationException {
		Document doc = this.getDatabaseBackupDocument();
		Element elRoot = doc.getDocumentElement();
		Element elDatabaseBackup = doc.createElement("DatabaseBackup");
		elDatabaseBackup.setAttribute("ID", newDatabaseBackup.getId());
		elDatabaseBackup.setAttribute("Name", newDatabaseBackup.getName());
		elDatabaseBackup.setAttribute("Time", newDatabaseBackup.getTime());
		elDatabaseBackup.setAttribute("Description", newDatabaseBackup.getDescription());
		elRoot.appendChild(elDatabaseBackup);
		saveXml(doc);
	}

	// 事务提交
	private void saveXml(Document doc) {// 将Document输出到文件
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty("indent", "yes");
			DOMSource source = new DOMSource();
			source.setNode(doc);
			StreamResult result = new StreamResult();
			String name = doc.getBaseURI().substring(6, doc.getBaseURI().length());
			result.setOutputStream(new FileOutputStream(name));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public void delete(String id) throws SAXException, IOException, ParserConfigurationException {
		Document doc = this.getDatabaseBackupDocument();
		String xpath = "//DatabaseBackup[@ID='" + id + "']";
		Element elDatabaseBackup = (Element) DocumentHelper.selectSingleNode(doc.getDocumentElement(), xpath);
		if (elDatabaseBackup != null) {
			elDatabaseBackup.getParentNode().removeChild(elDatabaseBackup);
			DocumentHelper.save(doc);
		}

	}
}
