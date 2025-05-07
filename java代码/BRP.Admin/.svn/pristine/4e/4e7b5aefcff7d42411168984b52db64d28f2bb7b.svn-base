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
import strosoft.tools.translate.TranslateProvider;
import strosoft.tools.translate.TranslateProviderFactory;

import net.sf.json.JSONObject;

/*
 * 发送短信服务处理类
 */
public class TranslateServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			InstantiationException, IllegalAccessException, Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String sourceText = JsonHelper.getString(jData, "sourceText");
		String sourceLanguage = JsonHelper.getString(jData, "sourceLanguage");
		String targetLanguage = JsonHelper.getString(jData, "targetLanguage");
		TranslateProvider theSmsProvider = TranslateProviderFactory
				.GetProvider(SysConfig.getTranslateProviderName());
		String result = theSmsProvider.translate(sourceText, sourceLanguage,
				targetLanguage);
		this.writeSuccessResponse(response, result);
	}
}
