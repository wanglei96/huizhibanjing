package strosoft.tools.translate;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import net.sf.json.JSONArray;
import org.w3c.dom.Node;

import strosoft.app.util.XmlDocumentHelper;

public class BDTranslateProvider extends TranslateProvider {

	private String appId;
	private String securityKey;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	public void setConfigNode(Node configNode) {
		super.setConfigNode(configNode);
		String appId = XmlDocumentHelper.getAttribute(configNode, "AppID");
		this.setAppId(appId);
		String securityKey = XmlDocumentHelper.getAttribute(configNode, "SecurityKey");
		this.setAppId(securityKey);
	}

	public BDTranslateProvider() {

	}

	public BDTranslateProvider(String appId, String securityKey) {
		this.setAppId(appId);
		this.setSecurityKey(securityKey);
	}


	/**
	 * 翻译
	 * @param sourceText
	 * @param sourceLanguage
	 * @param targetLanguage
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String translate(String sourceText, String sourceLanguage,
			String targetLanguage) throws UnsupportedEncodingException {
		BDTransApi api = new BDTransApi(APP_ID, SECURITY_KEY);

		String result = api.getTransResult(sourceText, sourceLanguage, targetLanguage);
		JSONObject jResult = JSONObject.fromObject(result);
		JSONArray jarrTransResult = jResult.getJSONArray("trans_result");
		String translatedText = jarrTransResult.getJSONObject(0).getString("dst");
		return translatedText;
	}
	private static final String APP_ID = "20200601000482348";
	private static final String SECURITY_KEY = "zoQ524yASWF9G8NEpGdZ";

	public static void main(String[] args) throws UnsupportedEncodingException {
		BDTransApi api = new BDTransApi(APP_ID, SECURITY_KEY);

		String query = "高度600米";
		String result = api.getTransResult(query, "auto", "en");
		System.out.println(result);
	}
}
