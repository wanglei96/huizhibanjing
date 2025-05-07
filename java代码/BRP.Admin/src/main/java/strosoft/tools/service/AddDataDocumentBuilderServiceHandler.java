//package strosoft.tools.service;
//
//import java.io.File;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.w3c.dom.Document;
//import org.xml.sax.SAXException;
//
//import net.sf.json.JSONObject;
//import strosoft.app.common.SysConfig;
//import strosoft.app.service.ServiceHandler;
//import strosoft.app.util.JsonHelper;
//import strosoft.sys.data.SysMenu;
//import strosoft.sys.model.SysMenuManager;
//import strosoft.tools.sms.DataDocumentBuilderFactory;
//import strosoft.tools.sms.MySQLDatabaseBackup;
//import strosoft.tools.sms.SmsProvider;
//import strosoft.tools.sms.SmsProviderFactory;
//import strosoft.tools.sms.SqlFile;
//
//public class AddDataDocumentBuilderServiceHandler extends ServiceHandler{
//
//	@Override
//	public void process(HttpServletRequest request, HttpServletResponse response)
//			throws SAXException, Exception {
//
//		String data = request.getParameter("data");
//		JSONObject jData = JSONObject.fromObject(data);
//		int sysUserId = JsonHelper.getInt(jData, "sysUserId", "SysUserId",
//				"sys_user_id");
//		/*if (sysUserId == 0) {
//			this.writeErrorResponse(response, "用户ID不能为空！");
//			return;
//		}*/
//		String id = JsonHelper.getString(jData, "id");
//		String name = JsonHelper.getString(jData,"name");
//		String time = JsonHelper.getString(jData, "time");
//		String description = JsonHelper.getString(jData, "description");
//		String configFilePath = SysConfig.getRootPath()
//				+ "/config/DataList.xml";
//		String dbURL = MySQLDatabaseBackup.getName("dbURL");
//		String databaseUser = MySQLDatabaseBackup.getName("databaseUser");
//		String databasePassword = MySQLDatabaseBackup.getName("databasePassword");
//		String dbName=MySQLDatabaseBackup.getName("dbName");
//		String backupPath=MySQLDatabaseBackup.getName("backupPath");
//		String sqlFileType=MySQLDatabaseBackup.getName("sqlFileType");
//		if (MySQLDatabaseBackup.exportDatabaseTool(dbURL, databaseUser, databasePassword, SysConfig.getRootPath()+backupPath,id+sqlFileType,dbName)) {
//			Document db = DataDocumentBuilderFactory.getDocumentBuilder().parse(configFilePath);
//			DataDocumentBuilderFactory.add(db, new SqlFile(id,name+sqlFileType,time,description));
//			System.out.println("数据库成功备份！！！");
//			this.writeSuccessResponse(response,"数据库成功备份！！！");
//        } else {
//        	this.writeSuccessResponse(response,"数据库备份失败！！！");
//        	System.out.println("数据库备份失败！！！");
//        }
//	}
//}
