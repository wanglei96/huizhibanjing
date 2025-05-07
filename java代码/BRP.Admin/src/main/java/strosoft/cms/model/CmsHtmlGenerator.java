package strosoft.cms.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import strosoft.app.util.FileHelper;

/*
 * Html生成器
 */
public class CmsHtmlGenerator {

	private static CmsHtmlGenerator _instance;

	public static CmsHtmlGenerator getInstance() {
		if (_instance == null) {
			_instance = new CmsHtmlGenerator();
		}
		return _instance;
	}

	private CmsHtmlGenerator() {
	}

	/*
	 * 取得Html字符串
	 */
	public String build(String templateFilePath,
			CmsHtmlGenerateContext context, CmsBuildContext cmsBuildContext)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IOException, ParserConfigurationException,
			SAXException {
		String content = FileHelper.readAllText(templateFilePath,
				context.getEncoding());
		if (null == content) {
			return "未找到模板文件";
		}
		if (content == null || content.equals("")) {
			return "";
		}
		content = analysisStlEntity(content, context);
		content = analysisStlInclude(content, context, cmsBuildContext);
		content = analysisStlEntity(content, context);
		content = analysisStlInclude(content, context, cmsBuildContext);
		content = analysisStlEntity(content, context);
		String removeString = "<#text>";
		content = content.replace(removeString, "");
		removeString = "</#text>";
		content = content.replace(removeString, "");
		Pattern p = Pattern.compile(">(\\s*|\n|\t|\r)<");
		Matcher m = p.matcher(content);
		content = m.replaceAll("><");
		// 读取模板文件内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(content);
		InputSource is = new InputSource(sr);
		StringBuffer sbHtml = new StringBuffer();
		try {
			Document document = db.parse(is);
			NodeList nlRoot = document.getChildNodes();
			// removeTextNode(nlRoot.item(0));
			/*
			 * for (int i = 0; i < nlRoot.getLength(); i++) { // 获取节点 Node
			 * currentNode = nlRoot.item(i); // 获取节点名 String nodeName =
			 * currentNode.getNodeName(); // String nodeValue =
			 * currentNode.getTextContent(); // 查找节点名对应的处理类
			 * CmsTemplateTagHandler handler = CmsTemplateTagHandlerFactory
			 * .GetHandler(nodeName); context.setCurrentNode(currentNode); //
			 * 执行对应节点名的解析，返回html String nodeHtml = handler.process(context,
			 * cmsBuildContext); sbHtml.append(nodeHtml); }
			 */
			sbHtml.append(createChildNodeHtml(nlRoot, context, cmsBuildContext));
		} catch (Exception e) {
			sr.close();
			e.printStackTrace();
			System.out.println("文件解析错误");
			return "<meta http-equiv='Content-Type' content='text/html;charset=utf-8'>文件解析错误";
		}
		String html = sbHtml.toString();
		return html;

	}

	protected void removeTextNode(Node root) {
		if (root.hasChildNodes()) {
			NodeList children = root.getChildNodes();
			int count = children.getLength();
			for (int i = count - 1; i >= 0; i--) {// 需要从后往前删除，防止出现沙漏效应
				Node child = children.item(i);
				if (child.getNodeType() == Node.TEXT_NODE) {
					child.getParentNode().removeChild(child);
				} else {
					removeTextNode(child);
				}
			}
		}
	}

	public String createChildNodeHtml(NodeList nlChildren,
			CmsHtmlGenerateContext context, CmsBuildContext cmsBuildContext) {
		StringBuffer html = new StringBuffer();
		for (int j = 0; j < nlChildren.getLength(); j++) {
			Node nodeChild = nlChildren.item(j);
			String nodeName = nodeChild.getNodeName();
			CmsTemplateTagHandler handler = CmsTemplateTagHandlerFactory
					.GetHandler(nodeName);
			context.setCurrentNode(nodeChild);
			String htmlChild = handler.process(context, cmsBuildContext);
			html.append(htmlChild);
		}
		return html.toString();
	}

	/**
	 * 解析基本实体
	 * @param content
	 * @param context
	 * @return
	 */
	public String analysisStlEntity(String content,
			CmsHtmlGenerateContext context) {
		// 站点名称
		String resultXml = content.replace("{Stl.SiteName}",
				context.getSiteName());
		// 站点文件夹，如果为总站点，值为空
		resultXml = resultXml.replace("{Stl.SiteDir}", context.getSiteDir());
		// 站点根目录地址
		resultXml = resultXml.replace("{Stl.SiteUrl}", context.getSiteUrl());
		// 系统根目录地址
		resultXml = resultXml.replace("{Stl.RootUrl}", context.getRootUrl());
		return resultXml;
	}

	// 解析<stl:include/>标签
	public String analysisStlInclude(String content,
			CmsHtmlGenerateContext context, CmsBuildContext cmsBuildContext)
			throws ParserConfigurationException {
		// 读取模板文件内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		String removeString = "<#text>";
		content = content.replace(removeString, "");
		removeString = "</#text>";
		content = content.replace(removeString, "");
		StringReader sr = new StringReader(content);
		InputSource is = new InputSource(sr);
		StringBuffer sbHtml = new StringBuffer();
		try {
			Document document = db.parse(is);
			NodeList nlRoot = document.getChildNodes();
			// removeTextNode(nlRoot.item(0));
			sbHtml.append(findStlInclude(nlRoot, context, cmsBuildContext));
			content = sbHtml.toString();
			removeString = "<#cdata-section>";
			content = content.replace(removeString, "<![CDATA[");
			removeString = "</#cdata-section>";
			content = content.replace(removeString, "]]>");
		} catch (Exception e) {
			sr.close();
			e.printStackTrace();
			System.out.println("文件解析错误");
			return "文件解析错误";
		}

		return content;
	}

	// 查找<stl:include/>标签
	public String findStlInclude(NodeList nlRoot,
			CmsHtmlGenerateContext context, CmsBuildContext cmsBuildContext) {
		StringBuffer sbHtml = new StringBuffer();
		for (int i = 0; i < nlRoot.getLength(); i++) {
			// 获取节点
			Node currentNode = nlRoot.item(i);
			// 获取节点名
			String nodeName = currentNode.getNodeName();
			// String nodeValue = currentNode.getTextContent();
			// 查找节点名对应的处理类
			if (nodeName.equals("stl:include")) {
				CmsTemplateTagHandler handler = CmsTemplateTagHandlerFactory
						.GetHandler(nodeName);
				context.setCurrentNode(currentNode);
				// 执行对应节点名的解析，返回html
				String nodeHtml = handler.process(context, cmsBuildContext);
				sbHtml.append(nodeHtml);
			} else {
				sbHtml.append(getOriginalHtml(context, cmsBuildContext,
						currentNode));
			}
		}
		return sbHtml.toString();
	}

	// 不是<stl:include/>标签.判断节点有cmsLanguageCodes属性,如果有，是否包含当前语言编码，不包含返回null
	public String getOriginalHtml(CmsHtmlGenerateContext context,
			CmsBuildContext cmsBuildContext, Node currentNode) {
		StringBuffer sb = new StringBuffer();
		String elementName = currentNode.getNodeName();
		sb.append("<" + elementName);
		// 生成属性Html
		NamedNodeMap nnmAttribute = currentNode.getAttributes();
		if (nnmAttribute != null) {
			for (int i = 0; i < nnmAttribute.getLength(); i++) {
				sb.append(" ");
				Node attribute = nnmAttribute.item(i);
				// 获取属性名
				String attributeName = attribute.getNodeName();
				// 判断当前属性名是否为cmsLanguageCodes,如果是，是否包含当前语言编码，不包含则此节点不生成
				if (attributeName.equals("cmsLanguageCodes")) {
					String attributeValue = attribute.getNodeValue();
					String currentCmsLanguageCode = context
							.getCmsLanguageCode();
					Boolean flag = containsCurrentCmsLanguageCode(
							attributeValue, currentCmsLanguageCode);
					if (flag) {
						continue;
					} else {
						return "";
					}
				}
				// 获取属性值
				String attributeValue = attribute.getNodeValue();
				sb.append(attributeName + "='" + attributeValue + "'");
			}
		}

		NodeList nlChildren = currentNode.getChildNodes();
		sb.append(">");
		// 生成子节点Html
		sb.append(findStlInclude(nlChildren, context, cmsBuildContext));
		if (nlChildren.getLength() == 0) {
			sb.append(currentNode.getTextContent());
		}
		sb.append("</" + elementName + ">");
		return sb.toString();
	}

	// 是否包含当前语言编码
	public Boolean containsCurrentCmsLanguageCode(String attributeValue,
			String currentCmsLanguageCode) {
		String[] codeList = attributeValue.split(",");
		for (int j = 0; j < codeList.length; j++) {
			Boolean flag = CmsBuildManager.getInstance().equalsCmsLanguageCode(
					codeList[j], currentCmsLanguageCode);
			if (flag) {
				return true;
			}
		}
		return false;
	}
}
