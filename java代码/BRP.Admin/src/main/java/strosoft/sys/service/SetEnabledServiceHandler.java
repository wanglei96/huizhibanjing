package strosoft.sys.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import net.sf.json.JSONObject;
import strosoft.app.common.UploadHelper;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.data.SysUploadFile;
import strosoft.sys.model.DataManager;
import strosoft.sys.model.SysBasicTableManager;
import strosoft.sys.model.SysUploadFileManager;

/**
 * 设置启用状态服务处理类
 * 
 * @author Admin
 *
 */
public class SetEnabledServiceHandler extends ServiceHandler {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jData = this.getRequestData(request);

		String tableName = JsonHelper.getString(jData, "tableName");
		String primaryKeyName = JsonHelper.getString(jData, "primaryKeyName");
		String primaryKey = JsonHelper.getString(jData, "primaryKey");
		Boolean enabled = JsonHelper.getBoolean(jData, "enabled");
		int result = SysBasicTableManager.getInstance().setEnabled(tableName, primaryKeyName, primaryKey, enabled);
		this.writeSuccessResponse(response, result);
	}
}
