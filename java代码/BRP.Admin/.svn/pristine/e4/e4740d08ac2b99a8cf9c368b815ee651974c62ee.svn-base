package strosoft.tools.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import net.sf.json.JSONObject;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.DatabaseBackup;
import strosoft.sys.data.SysMenu;
import strosoft.sys.model.DatabaseBackupManager;
import strosoft.sys.model.SysMenuManager;
import strosoft.tools.sms.DataDocumentBuilderFactory;
import strosoft.tools.sms.SmsProvider;
import strosoft.tools.sms.SmsProviderFactory;

public class RestoreDataDocumentBuilderServiceHandler extends ServiceHandler{
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SAXException, Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int sysUserId = JsonHelper.getInt(jData, "sysUserId", "SysUserId",
				"sys_user_id");
		String id = JsonHelper.getString(jData,"primaryKey");
		/*if (sysUserId == 0) {
			this.writeErrorResponse(response, "用户ID不能为空！");
			return;
		}*/
		if(id==null||id.equals("null")){
			id="系统初始化";
		}
		String dbURL = DatabaseBackupManager.getName("dbURL");
		String databaseUser = DatabaseBackupManager.getName("databaseUser");
		String databasePassword = DatabaseBackupManager.getName("databasePassword");
		String dbName=DatabaseBackupManager.getName("dbName");
		String backupPath=DatabaseBackupManager.getName("backupPath");
		String sqlFileType=DatabaseBackupManager.getName("sqlFileType");
		if(DatabaseBackupManager.restore(dbURL, databaseUser,databasePassword, SysConfig.getRootPath()+backupPath,id+sqlFileType,dbName)){
			System.out.println("还原成功！");
			this.writeSuccessResponse(response,"还原成功！");
		}else{
			System.out.println("还原失败！");
			this.writeSuccessResponse(response,"还原失败！");
		}
	}
}
