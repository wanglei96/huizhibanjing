package strosoft.cms.model;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CmsTemplateTagHeaderTemplateHandler extends CmsTemplateTagHandler {
	//private HashMap<String, String> attributeCondition = null;

	@Override
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) {
		Node currentNode = context.getCurrentNode();
		// 获取属性键值对
		//attributeCondition = getAttributeCondition(currentNode);
		String html = "";
		try {
			//生成子节点
			NodeList nlChildren = currentNode.getChildNodes();
			html = createChildNodeHtml(nlChildren, context, cmsBuildContext);
		} catch (Exception e) {
			return "";
		}
		return html;
	}
}
