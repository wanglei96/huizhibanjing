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

/**
 * 删除数据库备份
 * 
 * @author Admin
 *
 */
public class DeleteDatabaseBackupServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws SAXException, Exception {

		JSONObject jData = this.getRequestData(request);
		String id = JsonHelper.getString(jData, "primaryKey", "id", "ID");
		String backupFilePath = SysConfig.getRealDatabaseBackupPath() + "/" + id + ".sql";
		Boolean success = DatabaseBackupManager.getInstance().deleteBackupFile(backupFilePath);
		if (success) {
			DatabaseBackupManager.getInstance().delete(id);
			System.out.println("删除成功！");
			this.writeSuccessResponse(response, "删除成功！");
		} else {
			System.out.println("删除失败！");
			this.writeSuccessResponse(response, "删除失败！");
		}

	}
}
