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
import strosoft.app.util.StringHelper;
import strosoft.sys.data.DatabaseBackup;
import strosoft.sys.data.SysMenu;
import strosoft.sys.model.DatabaseBackupManager;
import strosoft.sys.model.SysMenuManager;
import strosoft.tools.sms.DataDocumentBuilderFactory;
import strosoft.tools.sms.SmsProvider;
import strosoft.tools.sms.SmsProviderFactory;

/**
 * 备份数据库
 * 
 * @author Admin
 *
 */
public class AddDatabaseBackupServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws SAXException, Exception {

		JSONObject jData = this.getRequestData(request);
		String id = JsonHelper.getString(jData, "id");
		String name = JsonHelper.getString(jData, "name");
		String time = JsonHelper.getString(jData, "time");
		String description = JsonHelper.getString(jData, "description");
		DatabaseBackup newDatabaseBackup = new DatabaseBackup(id, name + ".sql", time, description);

		String databaseHost = SysConfig.getDatabaseHost();
		String databasePort = SysConfig.getDatabasePort();
		if (StringHelper.isNullOrEmpty(databasePort)) {
			databasePort = "3306";
		}
		String databaseUser = SysConfig.getDatabaseUser();
		String databasePassword = SysConfig.getDatabasePassword();
		String databaseName = SysConfig.getDatabaseName();
		String databaseBackupDirectory = SysConfig.getDatabaseBackupPath();
		Boolean success = DatabaseBackupManager.exportDatabaseTool(databaseHost, databasePort, databaseUser,
				databasePassword, SysConfig.getRealDatabaseBackupPath(), id + ".sql", databaseName);
		if (success) {
			DatabaseBackupManager.getInstance().add(newDatabaseBackup);
			System.out.println("数据库成功备份！！！");
			this.writeSuccessResponse(response, "数据库成功备份！！！");
		} else {
			this.writeSuccessResponse(response, "数据库备份失败！！！");
			System.out.println("数据库备份失败！！！");
		}
	}
}
