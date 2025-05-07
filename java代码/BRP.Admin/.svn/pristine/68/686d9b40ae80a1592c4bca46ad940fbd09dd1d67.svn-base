package strosoft.sys.service;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import strosoft.app.common.SysConfig;
import strosoft.app.util.StringHelper;
import strosoft.app.util.XmlDocumentHelper;
import strosoft.sys.model.ExportHandler;

public class ExportHandlerFactory {

	/*
	 * 取得导出处理类
	 */
	public static ExportHandler GetHandler(String exportName) throws Exception {
		ArrayList<Node> lstExportHandlerNode = getExportHandlerNodeList("/config/ExportConfig.xml");
		// 创建服务处理类对象并返回
		for (int i = 0; i < lstExportHandlerNode.size(); i++) {
			Node nodeExportHandler = lstExportHandlerNode.get(i);

			String exportHandlerName = XmlDocumentHelper.getAttribute(nodeExportHandler, "Name");
			if (!exportHandlerName.equals(exportName)) {
				continue;
			}
			String handlerTypeName = XmlDocumentHelper.getAttribute(nodeExportHandler, "Type");
			if (StringHelper.isNullOrEmpty(handlerTypeName)) {
				handlerTypeName = "strosoft.sys.model.ExportHandler";
			}
			Class serviceHandlerClass = Class.forName(handlerTypeName);
			ExportHandler newExportHandler = (ExportHandler) serviceHandlerClass.newInstance();
			newExportHandler.setExportName(exportName);
			newExportHandler.setConfigNode(nodeExportHandler);
			return newExportHandler;
		}
		return null;
	}

	/*
	 * 取得服务处理类节点列表
	 */
	private static ArrayList<Node> getExportHandlerNodeList(String relativeConfigFilePath) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		String configFilePath = SysConfig.getRootPath() + relativeConfigFilePath;
		Document document = null;
		try {
			document = db.parse(configFilePath);
		} catch (Exception e) {
			throw new Exception(String.format("加载%s出错：%s", relativeConfigFilePath, e.getMessage()));
		}
		// 取得服务处理类配置节点列表
		ArrayList<Node> lstServiceHandlerNode = new ArrayList<Node>();
		// 添加配置文件中节点
		NodeList nlServiceHandler = document.getElementsByTagName("ExportData");
		for (int i = 0; i < nlServiceHandler.getLength(); i++) {
			Node nodeServiceHandler = nlServiceHandler.item(i);
			lstServiceHandlerNode.add(nodeServiceHandler);
		}
		return lstServiceHandlerNode;
	}
}
