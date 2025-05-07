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
import strosoft.sys.model.SysUploadFileManager;

/**
 * 系统初始化服务处理类
 * 
 * @author Admin
 *
 */
public class ResetSystemServiceHandler extends ServiceHandler {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataManager.getInstance().resetSystem();
		this.writeSuccessResponse(response);
	}
}
