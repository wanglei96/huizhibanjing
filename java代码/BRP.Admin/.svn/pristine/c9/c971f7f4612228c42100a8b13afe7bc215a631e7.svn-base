package strosoft.tools.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.tools.sms.SmsProvider;
import strosoft.tools.sms.SmsProviderFactory;

import net.sf.json.JSONObject;


/*
 * 发送短信服务处理类
 */
public class SendSmsServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			InstantiationException, IllegalAccessException, Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String phone = JsonHelper.getString(jData, "phone");
		String content = JsonHelper.getString(jData, "content");
		SmsProvider theSmsProvider = SmsProviderFactory
				.GetProvider(SysConfig.getSmsProviderName());
		theSmsProvider.sendSms(phone, content);
		this.writeSuccessResponse(response);
	}
}
