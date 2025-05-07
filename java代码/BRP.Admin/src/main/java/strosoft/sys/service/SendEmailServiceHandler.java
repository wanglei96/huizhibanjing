package strosoft.sys.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import strosoft.app.common.UploadHelper;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysUploadFile;
import strosoft.sys.model.EmailManager;
import strosoft.sys.model.SysUploadFileManager;

/**
 * 发送邮件服务处理类
 * 
 * @author dl
 * 
 */
public class SendEmailServiceHandler extends ServiceHandler {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			InstantiationException, IllegalAccessException, Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		List<String> lstToAddress = JsonHelper.getStringArray(jData,
				"toAddresses");
		String subject = JsonHelper.getString(jData, "subject");
		String content = JsonHelper.getString(jData, "content");
		EmailManager.getInstance().send(lstToAddress, subject, content);
		this.writeSuccessResponse(response);
	}
}
