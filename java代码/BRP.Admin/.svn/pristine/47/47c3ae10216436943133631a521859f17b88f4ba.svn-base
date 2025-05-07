package strosoft.cms.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.util.DateHelper;
import strosoft.cms.data.CmsChannelContext;
import strosoft.cms.data.CmsPageItemsContext;
import strosoft.cms.data.ViewCmsChannel;

public class CmsTemplateTagPageItemsHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition = null;

	@Override
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) {
		Node currentNode = context.getCurrentNode();
		String html = "";
		SqlSession sqlSession = cmsBuildContext.getSqlSession();
		try {
			//判断是否处在可翻页标签内；stl:channels/stl:content
			if(!isInPagingTag(cmsBuildContext)){
				return "";
			}
			// 获取属性键值对
			attributeCondition = getAttributeCondition(currentNode);
			CmsPageItemsContext pageItemsContext = new CmsPageItemsContext(attributeCondition);
			int pageSize = cmsBuildContext.getPageHtml().size();
			cmsBuildContext.setTotalPage(pageSize);
			//autoHide;是否在无分页时不显示标签内容
			if(isShowPagingContainer(pageItemsContext) && (pageSize<2)){
				return "";
			}
			for(int i=0;i<pageSize;i++){
				NodeList nlChildren = currentNode.getChildNodes();
				context.setCurrentPage(i+1);
				html = (createChildNodeHtml(nlChildren, context,cmsBuildContext));
				cmsBuildContext.getPageContentHtml().add(html);
			}
			//设置翻页容器替换标志
			cmsBuildContext.setPageContentHtmlReplaceStr("<"+UUID.randomUUID().toString()+">");
			html = cmsBuildContext.getPageContentHtmlReplaceStr();
		} catch (Exception e) {
			return "";
		}
		return html;
	}
	//判断是否处在可翻页标签内;stl:channels/stl:content
	public boolean isInPagingTag(CmsBuildContext cmsBuildContext){
		if(cmsBuildContext.getIsPaging()!=null && cmsBuildContext.getIsPaging().equalsIgnoreCase("true")){
			return true;
		}
		return false;
	}
	//判断该项是否生成翻页容器
	public boolean isCreatPagingContent(CmsBuildContext cmsBuildContext){
		if(cmsBuildContext.getIsCreatPagingContent()!=null && cmsBuildContext.getIsCreatPagingContent().equalsIgnoreCase("true")){
			return true;
		}
		return false;
	}
	//autoHide;是否在无分页时不显示标签内容
	public boolean isShowPagingContainer(CmsPageItemsContext pageItemsContext){
		if((pageItemsContext.getAutoHide()!=null) && pageItemsContext.getAutoHide().equalsIgnoreCase("false")){
			return true;
		}
		return false;
	}
	
}
