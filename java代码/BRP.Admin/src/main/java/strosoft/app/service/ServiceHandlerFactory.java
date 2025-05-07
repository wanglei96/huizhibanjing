package strosoft.app.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import strosoft.app.common.SysConfig;
import strosoft.app.util.XmlDocumentHelper;

public class ServiceHandlerFactory {

	/*
	 * 取得服务处理类
	 */
	public static ServiceHandler GetHandler(String serviceName)
			throws Exception {
		ArrayList<Node> lstServiceHandlerNode = getServiceHandlerNodeList("/config/ServiceHandlers.xml");
		// 创建服务处理类对象并返回
		for (int i = 0; i < lstServiceHandlerNode.size(); i++) {
			Node nodeServiceHandler = lstServiceHandlerNode.get(i);

			String serviceHandlerName = XmlDocumentHelper.getAttribute(
					nodeServiceHandler, "Name");
			if (!serviceHandlerName.equals(serviceName)) {
				continue;
			}
			String handlerTypeName = XmlDocumentHelper.getAttribute(
					nodeServiceHandler, "Type");
			Class serviceHandlerClass = Class.forName(handlerTypeName);
			ServiceHandler serviceHandler = (ServiceHandler) serviceHandlerClass
					.newInstance();
			serviceHandler.setServiceName(serviceName);
			serviceHandler.setConfigNode(nodeServiceHandler);
			return serviceHandler;
		}
		return null;
	}

	/*
	 * 取得服务处理类节点列表
	 */
	private static ArrayList<Node> getServiceHandlerNodeList(
			String relativeConfigFilePath) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		String configFilePath = SysConfig.getRootPath()
				+ relativeConfigFilePath;
		Document document = null;
		try {
			document = db.parse(configFilePath);
		} catch (Exception e) {
			throw new Exception(String.format("加载%s出错：%s", relativeConfigFilePath, e.getMessage()));
		}
		// 取得服务处理类配置节点列表
		ArrayList<Node> lstServiceHandlerNode = new ArrayList<Node>();
		// 添加配置文件中节点
		NodeList nlServiceHandler = document
				.getElementsByTagName("ServiceHandler");
		for (int i = 0; i < nlServiceHandler.getLength(); i++) {
			Node nodeServiceHandler = nlServiceHandler.item(i);
			lstServiceHandlerNode.add(nodeServiceHandler);
		}
		// 添加包含文件中节点
		NodeList nlInclude = document.getElementsByTagName("Include");
		for (int i = 0; i < nlInclude.getLength(); i++) {
			Node nodeInclude = nlInclude.item(i);
			String src = XmlDocumentHelper.getAttribute(nodeInclude, "src");
			ArrayList<Node> lstChildServiceHandlerNode = getServiceHandlerNodeList(src);
			lstServiceHandlerNode.addAll(lstChildServiceHandlerNode);
		}
		return lstServiceHandlerNode;
	}
}
