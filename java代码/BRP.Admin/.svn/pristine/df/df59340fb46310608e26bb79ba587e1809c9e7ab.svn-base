package strosoft.cms.model;

import java.util.HashMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import strosoft.cms.data.ViewCmsContent;

public class CmsTemplateTagItemTemplateHandler extends CmsTemplateTagHandler {
	private ViewCmsContent viewCmsContent = null;
	private HashMap<String, String> attributeCondition = null;

	@Override
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) {
		Node currentNode = context.getCurrentNode();
		// 获取属性键值对
		attributeCondition = getAttributeCondition(currentNode);
		String html = "";
		try {
			//判断是否是偶数行,不是偶数行返回空字符串
			if(null == (Integer)context.getNumOnPage() || context.getNumOnPage()%2 == 1){
				return "";
			}
			//生成子节点
			NodeList nlChildren = currentNode.getChildNodes();
			html = createChildNodeHtml(nlChildren, context, cmsBuildContext);
		} catch (Exception e) {
			return "";
		}
		return html;
	}
}
