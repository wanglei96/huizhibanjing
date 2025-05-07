package strosoft.cms.model;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import strosoft.app.common.SysConfig;
import strosoft.app.util.XmlDocumentHelper;
import strosoft.cms.data.CmsTemplateTag;

public class CmsTemplateTagHandlerFactory {

	private static ArrayList<CmsTemplateTag> lstCmsTemplateTag = null;

	public static CmsTemplateTagHandler GetHandler(String nodeName) {

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			String configFilePath = SysConfig.getRootPath()
					+ "/WEB-INF/CmsTemplateTag.xml";
			Document document = db.parse(configFilePath);
			// 获取所有book节点的集合
			NodeList nlTagHandler = document.getElementsByTagName("Element");

			for (int i = 0; i < nlTagHandler.getLength(); i++) {
				Node nodeTagHandler = nlTagHandler.item(i);
				String tagHandlerName = XmlDocumentHelper.getAttribute(
						nodeTagHandler, "Name");
				// System.out.println("---------------------------------tagHandlerName:"+tagHandlerName);
				if (!tagHandlerName.equals(nodeName)) {
					continue;
				}
				String handlerTypeName = XmlDocumentHelper.getAttribute(
						nodeTagHandler, "Type");
				Class serviceHandlerClass = Class.forName(handlerTypeName);
				CmsTemplateTagHandler cmsTemplateTagHandler = (CmsTemplateTagHandler) serviceHandlerClass
						.newInstance();

				return cmsTemplateTagHandler;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CmsTemplateTagHandler();
	}
}
