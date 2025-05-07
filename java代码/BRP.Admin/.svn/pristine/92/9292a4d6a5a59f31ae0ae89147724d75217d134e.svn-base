package strosoft.cms.model;

import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import strosoft.app.common.MyBatisManager;
import strosoft.cms.data.CmsMetaContext;

public class CmsTemplateTagMetaHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition = null;

	@Override
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) {
		Node currentNode = context.getCurrentNode();
		String html = "";
		try {
			// 获取属性键值对
			attributeCondition = getAttributeCondition(currentNode);
			// 获取channel生成属性参数
			CmsMetaContext cmsMetaContext = new CmsMetaContext(
					attributeCondition);
			String name = getName(cmsMetaContext);
			String content = getContent(context, cmsBuildContext, currentNode);
			html = "<meta name='"+name+"' content='"+content+"'></meta>";
		} catch (Exception e) {
			return "";
		}
		return html;
	}
	
	private String getName(CmsMetaContext cmsMetaContext){
		String name = "";
		if(null != cmsMetaContext.getName()){
			name = cmsMetaContext.getName();
		}
		return name;
	}
	
	private String getContent(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext,Node currentNode){
		String content = "";
		try {
			NodeList nlChildren = currentNode.getChildNodes();
			CmsHtmlGenerateContext cmsHtmlGenerateContext = context.clone();
			// 生成子节点html
			String childStr = (createChildNodeHtml(nlChildren,
					cmsHtmlGenerateContext, cmsBuildContext));
			if (!(null == childStr || childStr.equals(""))) {
				content = childStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return content;
	}
}

