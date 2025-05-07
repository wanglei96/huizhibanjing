package strosoft.tools.translate;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import strosoft.app.common.SysConfig;
import strosoft.app.util.XmlDocumentHelper;


public class TranslateProviderFactory {

	/*
	 * 取得服务提供者
	 */
	public static TranslateProvider GetProvider(String providerName)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, ParserConfigurationException, SAXException,
			IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		String configFilePath = SysConfig.getRootPath()
				+ "/config/TranslateServiceProviders.xml";

		Document document = db.parse(configFilePath);
		// 获取所有翻译服务提供者
		NodeList nlServiceProvider = document
				.getElementsByTagName("TranslateServiceProvider");

		for (int i = 0; i < nlServiceProvider.getLength(); i++) {
			Node nodeServiceProvider = nlServiceProvider.item(i);

			String serviceProviderName = XmlDocumentHelper.getAttribute(
					nodeServiceProvider, "Name");
			if (!serviceProviderName.equals(providerName)) {
				continue;
			}
			String providerTypeName = XmlDocumentHelper.getAttribute(
					nodeServiceProvider, "Type");
			Class ServiceProviderClass = Class.forName(providerTypeName);
			TranslateProvider translateServiceProvider = (TranslateProvider) ServiceProviderClass
					.newInstance();
			translateServiceProvider.setProviderName(providerName);
			translateServiceProvider.setConfigNode(nodeServiceProvider);
			return translateServiceProvider;
		}
		return new NoneTranslateProvider();
	}
}
