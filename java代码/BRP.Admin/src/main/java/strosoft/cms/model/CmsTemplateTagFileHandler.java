package strosoft.cms.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import strosoft.app.common.SysConfig;
import strosoft.app.util.DateHelper;
import strosoft.cms.data.CmsFileContext;
import strosoft.cms.data.ViewCmsContentInfo;

public class CmsTemplateTagFileHandler extends CmsTemplateTagHandler {
	private ViewCmsContentInfo viewCmsContentInfo = null;
	private HashMap<String, String> attributeCondition = null;

	@Override
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) {
		// 不在内容模板，或不在contents标签中返回""
		if (null == context.getCurrentShowType() || !context.getCurrentShowType().equalsIgnoreCase("content")) {
			return "";
		}
		Node currentNode = context.getCurrentNode();
		String html = "";
		try {
			// 获取属性键值对
			attributeCondition = getAttributeCondition(currentNode);
			// 获取content生成属性参数
			CmsFileContext fileContext = new CmsFileContext(
					attributeCondition);
			viewCmsContentInfo = CmsBuildManager.getInstance().getViewCmsContentInfoByCmsContentIdAndCode(cmsBuildContext.getSqlSession(), context.getCmsSiteId(), context.getCurrentContentId(), context.getCmsLanguageCode());
			if(null != viewCmsContentInfo.getAttachmentFileName()){
				System.out.println(viewCmsContentInfo.getCmsChannelId()+"/"+viewCmsContentInfo.getId());
			}
			//获取获取标签内内容html
			String subHtml = getSubNodesHtml(fileContext, context, cmsBuildContext, currentNode);
			//获取标签属性
			String attribute = getOriginalAttribute(attributeCondition);
			//获取文件链接和文件名
			String fileUrl = getFileUrl(fileContext);
			html = "<a "+attribute+fileUrl+">"+subHtml+"</a>";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return html;
	}
	//获取文件链接
	public String getFileUrl(CmsFileContext fileContext){
		String fileUrl = "";
		if (!(fileContext.getSrc() == null)) {
			fileUrl = " href='"+fileContext.getSrc()+"' ";
		} else if(null != viewCmsContentInfo.getAttachmentFilePath()){
			String url = SysConfig.getAdminRootUrl()+"api/Service?serviceName=DownloadCmsFileServiceHandler&data={\"channelId\":"+viewCmsContentInfo.getCmsChannelId()+",\"contentId\":"+viewCmsContentInfo.getId()+"}";
			String fileName = viewCmsContentInfo.getAttachmentFileName();
			fileUrl = " href='"+url+"'";
		}
		return fileUrl;
	}
	//获取标签内内容
	public String getSubNodesHtml(CmsFileContext fileContext,CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext,Node currentNode) throws Exception{
		String html = "";
		NodeList nlChildren = currentNode.getChildNodes();
		html = createChildNodeHtml(nlChildren, context, cmsBuildContext);
		if(null == html || html.equals("")){
			// 获取content
			viewCmsContentInfo = CmsBuildManager.getInstance().getViewCmsContentInfoByCmsContentIdAndCode(cmsBuildContext.getSqlSession(), context.getCmsSiteId(), context.getCurrentContentId(), context.getCmsLanguageCode());
			// 获取要显示的value
			String value = viewCmsContentInfo.getTitle();
			// 根据标签属性处理要显示的value值
			value = dealWithValueByAttribute(fileContext, value);
			// separator属性
			html = gethtmlWidthSeparator(value, fileContext, context);
		}
		return html;
	}
	// 根据Type获取对应属性的html
	public String getHtmlByType(CmsFileContext fileContext,
			CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) throws IOException, Exception {
		String html = "";
		if (!(fileContext.getType() == null)) {
			String type = fileContext.getType();
			if (type.equalsIgnoreCase("Id")) {
				html = viewCmsContentInfo.getId().toString();
			} else if (type.equalsIgnoreCase("AddUserName")) {
				html = viewCmsContentInfo.getAuthor();
			} else if (type.equalsIgnoreCase("AddDate")) {
				html = DateHelper.toDateString(viewCmsContentInfo.getPublishDate());
			} else if (type.equalsIgnoreCase("Title")) {
				html = viewCmsContentInfo.getTitle();
			} else if (type.equalsIgnoreCase("SubTitle")) {
				html = viewCmsContentInfo.getSubTitle();
			} else if (type.equalsIgnoreCase("Summary")) {
				html = viewCmsContentInfo.getSummary();
			} else if (type.equalsIgnoreCase("Description")) {
				html = viewCmsContentInfo.getDescription();
			} else if (type.equalsIgnoreCase("Keywords")) {
				html = viewCmsContentInfo.getKeywords();
			} else if (type.equalsIgnoreCase("Author")) {
				html = viewCmsContentInfo.getAuthor();
			} else if (type.equalsIgnoreCase("Source")) {
				html = viewCmsContentInfo.getSource();
			} else if (type.equalsIgnoreCase("Content")) {
				html = viewCmsContentInfo.getText();
			} else if (type.equalsIgnoreCase("ItemIndex")) {
				html = context.getNum() + "";
			}
		} else {
			html = viewCmsContentInfo.getTitle();
		}
		return html;
	}

	// 根据节点属性，处理获取到的属性值
	public String dealWithValueByAttribute(CmsFileContext fileContext,
			String value) {
		if (!(fileContext.getReplace() == null)
				&& !(fileContext.getTo() == null)) {
			value = value.replaceAll(fileContext.getReplace(),
					fileContext.getTo());
		}
		if (!(fileContext.getStartIndex() == null)) {
			Integer beginIndex = 0;
			try {
				beginIndex = Integer.parseInt(fileContext.getStartIndex());
			} catch (Exception e) {
				e.printStackTrace();
				beginIndex = 0;
			}
			value = value.substring(beginIndex);
		}
		if (!(fileContext.getLength()== null)) {
			Integer length = -1;
			try {
				length = Integer.parseInt(fileContext.getLength());
			} catch (Exception e) {
				e.printStackTrace();
				length = -1;
			}
			value = value.substring(0,length);
		}
		if (!(fileContext.getIsLower() == null)) {
			value = value.toLowerCase();
		}
		if (!(fileContext.getIsUpper() == null)) {
			value = value.toUpperCase();
		}
		if (!(fileContext.getLeftText() == null)) {
			value = fileContext.getLeftText() + value;
		}
		if (!(fileContext.getRightText() == null)) {
			value = value + fileContext.getRightText();
		}
		if (!(fileContext.getWordNum() == null)) {
			Integer wordNum = 0;
			try {
				wordNum = Integer.parseInt(fileContext.getWordNum());
			} catch (Exception e) {
				e.printStackTrace();
				wordNum = 0;
			}
			value = getValueByWordNum(value, wordNum, fileContext);
		}
		return value;
	}

	// separator属性
	public String gethtmlWidthSeparator(String value,
			CmsFileContext fileContext,CmsHtmlGenerateContext context) {
		if (!(fileContext.getSeparator() == null)) {
			if((context.getTotalNum()-1) > context.getNum()){
				value += fileContext.getSeparator();
			}
		}
		return value;
	}

	// 使用wordNum处理value
	public String getValueByWordNum(String value, int wordNum,
			CmsFileContext fileContext) {
		if(null == value){
			return "";
		}
		value = delHTMLTag(value);
		if (value.length() > wordNum) {
			if (!(fileContext.getEllipsis() == null)) {
					return value = value.substring(0, wordNum)
							+ fileContext.getEllipsis();
				} else {
					return value = value.substring(0, wordNum) + "...";
				}
			} else {
				return value;
			}
		
	}
	
	public String delHTMLTag(String htmlStr){ 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 
        
        htmlStr=htmlStr.replace(" ","");
        htmlStr=htmlStr.replaceAll("\\s*|\t|\r|\n","");
        htmlStr=htmlStr.replace("“","");
        htmlStr=htmlStr.replace("”","");
        htmlStr=htmlStr.replaceAll("　","");
        htmlStr=htmlStr.replaceAll("&nbsp;","");
          
        return htmlStr.trim(); //返回文本字符串 
    }

}
