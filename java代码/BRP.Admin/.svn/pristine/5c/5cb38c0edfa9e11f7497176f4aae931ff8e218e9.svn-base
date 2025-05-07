package strosoft.app.util;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.*;

public class XmlDocumentHelper {

	/*
	 * 取得属性值
	 */
	public static String getAttribute(Node node, String name) {
		return getAttribute(node, name, null);
	}
	/*
	 * 取得属性值
	 */

	public static String getAttribute(Node node, String name, String defaultValue) {
		if (node == null) {
			return defaultValue;
		}
		NamedNodeMap attributes = node.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			Node attribute = attributes.item(i);
			String attributeName = attribute.getNodeName();

			if (attributeName == name) {
				String attributeValue = attribute.getNodeValue();
				return attributeValue;
			}
		}
		return defaultValue;
	}

	/*
	 * 设置节点属性
	 */
	public static void setAttribute(Node node, String name, String value) {
		if (node == null) {
			return;
		}
		if (node instanceof Element) {
			Element element = (Element) node;
			element.setAttribute(name, value);
		}
	}

	/*
	 * 根据节点名称取得节点列表
	 */
	public static ArrayList<Node> selectNodesByNodeName(Node node, String nodeName) {
		ArrayList<Node> lstMatchedNode = new ArrayList<Node>();
		if (node.getNodeName().equals(nodeName)) {
			lstMatchedNode.add(node);
		}
		if (!node.hasChildNodes()) {
			return lstMatchedNode;
		}
		NodeList lstChildNode = node.getChildNodes();
		for (int i = 0; i < lstChildNode.getLength(); i++) {
			Node currentChildNode = lstChildNode.item(i);
			ArrayList<Node> lstMatchedChildNode = selectNodesByNodeName(currentChildNode, nodeName);
			for (Node currentMatchedNode : lstMatchedChildNode) {
				lstMatchedNode.add(currentMatchedNode);
			}
		}
		return lstMatchedNode;
	}

	/*
	 * 取得布尔类型属性值
	 */
	public static Boolean getBoolAttribute(Node node, String name) {
		return getBoolAttribute(node, name, null);
	}

	/*
	 * 取得布尔类型属性值
	 */
	public static Boolean getBoolAttribute(Node node, String name, Boolean defaultValue) {
		if (node == null) {
			return defaultValue;
		}
		NamedNodeMap attributes = node.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			Node attribute = attributes.item(i);
			String attributeName = attribute.getNodeName();

			if (attributeName == name) {
				String strAttributeValue = attribute.getNodeValue();
				try {
					Boolean bAttributeValue = Boolean.valueOf(strAttributeValue);
					return bAttributeValue;
				} catch (Exception ex) {
					return defaultValue;
				}
			}
		}
		return defaultValue;
	}

	/*
	 * 取得整数类型属性值
	 */
	public static Integer getIntAttribute(Node node, String name) {
		return getIntAttribute(node, name, null);
	}

	/*
	 * 取得整数类型属性值
	 */
	public static Integer getIntAttribute(Node node, String name, Integer defaultValue) {
		if (node == null) {
			return defaultValue;
		}
		NamedNodeMap attributes = node.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			Node attribute = attributes.item(i);
			String attributeName = attribute.getNodeName();

			if (attributeName == name) {
				String strAttributeValue = attribute.getNodeValue();
				try {
					Integer intAttributeValue = Integer.valueOf(strAttributeValue);
					return intAttributeValue;
				} catch (Exception ex) {
					return defaultValue;
				}
			}
		}
		return defaultValue;
	}

}
