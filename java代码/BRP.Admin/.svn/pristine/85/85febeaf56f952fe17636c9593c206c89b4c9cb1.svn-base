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
//public class DeleteDataDocumentBuilderServiceHandler extends ServiceHandler{
//
//	@Override
//	public void process(HttpServletRequest request, HttpServletResponse response)
//			throws SAXException, Exception {
//
//		String data = request.getParameter("data");
//		JSONObject jData = JSONObject.fromObject(data);
//		int sysUserId = JsonHelper.getInt(jData, "sysUserId", "SysUserId",
//				"sys_user_id");
//		String id = JsonHelper.getString(jData,"primaryKey");
//		/*if (sysUserId == 0) {
//			this.writeErrorResponse(response, "用户ID不能为空！");
//			return;
//		}*/
//		String configFilePath = SysConfig.getRootPath()
//				+ "/config/DataList.xml";
//		//File xmlFile = new File("/WebRoot/WEB-INF/DataList.xml");
//		String backupPath=MySQLDatabaseBackup.getName("backupPath");
//		String sqlFileType=MySQLDatabaseBackup.getName("sqlFileType");
//		if(MySQLDatabaseBackup.delFile(SysConfig.getRootPath()+backupPath,id+sqlFileType)){
//			Document db = DataDocumentBuilderFactory.getDocumentBuilder().parse(configFilePath);
//			DataDocumentBuilderFactory.delete(db, new SqlFile(id));
//			System.out.println("删除成功！");
//			this.writeSuccessResponse(response,"删除成功！");
//		}else{
//			System.out.println("删除失败！");
//			this.writeSuccessResponse(response,"删除失败！");
//		}
//
//	}
//}
