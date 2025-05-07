package strosoft.cms.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.util.DateHelper;
import strosoft.cms.data.CmsContentContext;
import strosoft.cms.data.ViewCmsContent;
import strosoft.cms.data.ViewCmsContentInfo;

public class CmsTemplateTagContentHandler extends CmsTemplateTagHandler {
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
		SqlSession sqlSession = cmsBuildContext.getSqlSession();
		try {
			// 获取属性键值对
			attributeCondition = getAttributeCondition(currentNode);
			// 获取content生成属性参数
			CmsContentContext contentContext = new CmsContentContext(
					attributeCondition);
			// 获取content
			/*String sql = "select * from view_cms_content where id = "
					+ context.getCurrentContentId() + " limit 1";
			viewCmsContentInfo = sqlSession.selectOne(
					"ViewCmsContent.selectEntityBysql", sql);*/
			viewCmsContentInfo = CmsBuildManager.getInstance().getViewCmsContentInfoByCmsContentIdAndCode(sqlSession, context.getCmsSiteId(), context.getCurrentContentId(), context.getCmsLanguageCode());
			// 获取要显示的value
			String value = getHtmlByType(contentContext, context, cmsBuildContext);
			// 根据标签属性处理要显示的value值
			value = dealWithValueByAttribute(contentContext, value);
			// separator属性
			html = gethtmlWidthSeparator(value, contentContext, context);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return html;
	}

	// 根据Type获取对应属性的html
	public String getHtmlByType(CmsContentContext contentContext,
			CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) throws IOException, Exception {
		String html = "";
		if (!(contentContext.getType() == null)) {
			String type = contentContext.getType();
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
			} else if (type.equalsIgnoreCase("ImageUrl")) {
				html = "<img src='"+SysConfig.getAdminRootUrl()+ viewCmsContentInfo.getThumbFilePath()+"'/>";
			} else if (type.equalsIgnoreCase("VideoUrl")) {
				String videoUrl = SysConfig.getAdminRootUrl()+ viewCmsContentInfo.getVideoFilePath();
				if(contentContext.getEmbed() != null && contentContext.getEmbed().equalsIgnoreCase("true")){
					html = "<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\">\n" +
							"	<param name=\"movie\" value=\"../../css/flvplayer.swf\" />\n" +
							"	<param name=\"quality\" value=\"high\" />\n" +
							"	<param name=\"allowFullScreen\" value=\"true\" />\n" +
							"	<embed src=\"../../css/flvplayer.swf\" allowfullscreen=\"true\" flashvars=\"vcastr_file="+videoUrl+"\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\"></embed>\n" +
							"</object>";
				}else{
					html = videoUrl;
				}
			} else if (type.equalsIgnoreCase("FileUrl")) {
				html = SysConfig.getAdminRootUrl()+viewCmsContentInfo.getAttachmentFilePath();
			} else if (type.equalsIgnoreCase("Author")) {
				html = viewCmsContentInfo.getAuthor();
			} else if (type.equalsIgnoreCase("Source")) {
				html = viewCmsContentInfo.getSource();
			} else if (type.equalsIgnoreCase("Content")) {
				html = viewCmsContentInfo.getText();
			} else if (type.equalsIgnoreCase("ItemIndex")) {
				html = context.getNum() + "";
			}else if (type.equalsIgnoreCase("Url")) {
				html=String.format("<a href='%s'>%s</a>",cmsBuildContext.getCmsBuildTask()
						.getAdminRootUrl()+viewCmsContentInfo.getCreateFilePath(),viewCmsContentInfo.getTitle());
			} else if (type.equalsIgnoreCase("LinkUrl")) {
				html=String.format("<a href='%s'>%s</a>",viewCmsContentInfo.getUrl(),viewCmsContentInfo.getTitle());
			}
		} else {
			html = viewCmsContentInfo.getTitle();
		}
		return html;
	}

	// 根据节点属性，处理获取到的属性值
	public String dealWithValueByAttribute(CmsContentContext contentContext,
			String value) {
		if (!(contentContext.getReplace() == null)
				&& !(contentContext.getTo() == null)) {
			value = value.replaceAll(contentContext.getReplace(),
					contentContext.getTo());
		}
		if (!(contentContext.getStartIndex() == null)) {
			Integer beginIndex = 0;
			try {
				beginIndex = Integer.parseInt(contentContext.getStartIndex());
			} catch (Exception e) {
				e.printStackTrace();
				beginIndex = 0;
			}
			value = value.substring(beginIndex);
		}
		if (!(contentContext.getLength()== null)) {
			Integer length = -1;
			try {
				length = Integer.parseInt(contentContext.getLength());
			} catch (Exception e) {
				e.printStackTrace();
				length = -1;
			}
			value = value.substring(0,length);
		}
		if (!(contentContext.getIsLower() == null)) {
			value = value.toLowerCase();
		}
		if (!(contentContext.getIsUpper() == null)) {
			value = value.toUpperCase();
		}
		if (!(contentContext.getLeftText() == null)) {
			value = contentContext.getLeftText() + value;
		}
		if (!(contentContext.getRightText() == null)) {
			value = value + contentContext.getRightText();
		}
		if (!(contentContext.getWordNum() == null)) {
			Integer wordNum = 0;
			try {
				wordNum = Integer.parseInt(contentContext.getWordNum());
			} catch (Exception e) {
				e.printStackTrace();
				wordNum = 0;
			}
			value = getValueByWordNum(value, wordNum, contentContext);
		}
		return value;
	}

	// separator属性
	public String gethtmlWidthSeparator(String value,
			CmsContentContext contentContext,CmsHtmlGenerateContext context) {
		if (!(contentContext.getSeparator() == null)) {
			if((context.getTotalNum()-1) > context.getNum()){
				value += contentContext.getSeparator();
			}
		}
		return value;
	}

	// 使用wordNum处理value
	public String getValueByWordNum(String value, int wordNum,
			CmsContentContext contentContext) {
		if(null == value){
			return "";
		}
		value = delHTMLTag(value);
		if (value.length() > wordNum) {
			if (!(contentContext.getEllipsis() == null)) {
					return value = value.substring(0, wordNum)
							+ contentContext.getEllipsis();
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
