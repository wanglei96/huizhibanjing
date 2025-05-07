package strosoft.cms.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.util.DateHelper;
import strosoft.cms.data.CmsChannelContext;
import strosoft.cms.data.CmsContentContext;
import strosoft.cms.data.ViewCmsChannel;
import strosoft.cms.data.ViewCmsChannelInfo;

public class CmsTemplateTagChannelHandler extends CmsTemplateTagHandler {
	private ViewCmsChannelInfo viewCmsChannelInfo = null;
	private HashMap<String, String> attributeCondition = null;

	@Override
	public String process(CmsHtmlGenerateContext context,
			CmsBuildContext cmsBuildContext) {
		Node currentNode = context.getCurrentNode();
		String html = "";
		SqlSession sqlSession = cmsBuildContext.getSqlSession();
		try {
			// 获取属性键值对
			attributeCondition = getAttributeCondition(currentNode);
			// 获取channel生成属性参数
			CmsChannelContext channelContext = new CmsChannelContext(
					attributeCondition);
			/*
			 * String channelCondition = getCmsChannelCondition(sqlSession,
			 * channelContext, context); // 获取栏目id String sql =
			 * "select id from cms_channel " + channelCondition +
			 * " and cms_site_id=" + context.getCmsSiteId() + " limit 1";
			 * Integer cmsChannelsId = sqlSession.selectOne(
			 * "CmsChannel.selectIntBySql", sql); if (null == cmsChannelsId) {
			 * return ""; } // 根据type属性获取对应属性值 viewCmsChannel =
			 * ViewCmsChannelManager.getInstance().getEntity(cmsChannelsId);
			 */
			HashMap<String, Object> conditionMap = getCmsChannelCondition(
					sqlSession, channelContext, context);
			List<ViewCmsChannelInfo> viewCmsChannelInfos = CmsBuildManager
					.getInstance().getViewCmsChannelInfoListByConditionMap(
							conditionMap, context.getCmsLanguageCode());
			if (viewCmsChannelInfos == null || viewCmsChannelInfos.size() == 0) {
				return "";
			}
			viewCmsChannelInfo = viewCmsChannelInfos.get(0);
			String value = getHtmlByType(channelContext, context,
					cmsBuildContext);
			// 根据节点属性处理获取到的属性值
			value = dealWithValueByAttribute(channelContext, value);
			// separator属性
			html = (gethtmlWidthSeparator(value, channelContext, context));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return html;
	}

	// 获取栏目筛选条件
	public HashMap<String, Object> getCmsChannelCondition(
			SqlSession sqlSession, CmsChannelContext channelContext,
			CmsHtmlGenerateContext context) {
		/* StringBuffer condition = new StringBuffer("where 1=1 "); */
		HashMap<String, Object> conditionMap = new HashMap<String, Object>();
		List<String> codeList = new ArrayList<String>();
		List<Integer> idList = new ArrayList<Integer>();
		List<Integer> idAreaList = null;
		// 判断有没有指定栏目
		boolean falg = false;
		if (!(channelContext.getChannelCode() == null)) {
			/*
			 * condition.append(" and code ='" + channelContext.getChannelCode()
			 * + "'");
			 */
			codeList.add(channelContext.getChannelCode());
			falg = true;
		}
		if (!(channelContext.getChannelIndex() == null)) {
			/*
			 * condition.append(" and code ='" + channelContext.getChannelCode()
			 * + "'");
			 */
			codeList.add(channelContext.getChannelIndex());
			falg = true;
		}
		// topLevel
		/*
		 * if (!(channelContext.getTopLevel() == null)) {
		 * System.out.println("topLevel:" + channelContext.getTopLevel());
		 * Integer level = 0; int rootCmsChannelId =
		 * CmsChannelManager.getInstance().getIndexId( sqlSession,
		 * context.getCmsSiteId()); try { level =
		 * Integer.parseInt(channelContext.getTopLevel()); } catch (Exception e)
		 * { level = 0; e.printStackTrace(); } List<Integer> ids =
		 * CmsChannelManager.getInstance() .getSubLevelByIdAndLevel(sqlSession,
		 * rootCmsChannelId, level); if (ids.size() < 1) {
		 * condition.append(" and 1=2 "); } else {
		 * condition.append(" and id in (" +
		 * CmsChannelManager.getInstance().getIdsString(ids) + ")"); } falg =
		 * true; }
		 */
		// upLevel
		if (!(channelContext.getUpLevel() == null)
				&& (context.getTemplateType().equalsIgnoreCase("channel") || context
						.getTemplateType().equalsIgnoreCase("content"))) {
			int level = 0;
			Integer cmsChannelId = context.getCurrentChannelId();
			try {
				level = Integer.parseInt(channelContext.getUpLevel());
			} catch (Exception e) {
				level = 0;
				e.printStackTrace();
			}
			/*
			 * cmsChannelId = CmsChannelManager.getInstance()
			 * .getUpLevelByIdAndLevel(sqlSession, cmsChannelId, level);
			 */
			ViewCmsChannelInfo viewCmsChannelInfo = CmsBuildManager
					.getInstance().getViewCmsChannelInfoByLevelAndId(
							cmsChannelId, level, context.getCmsLanguageCode());
			if (null == viewCmsChannelInfo) {
				// condition.append(" and 1=2 ");
				idList.add(-1);
			} else {
				// condition.append(" and id =" + cmsChannelId);
				idAreaList = new ArrayList<Integer>();
				idAreaList.add(viewCmsChannelInfo.getId());
			}
			falg = true;
		}
		// 没有设置栏目
		if (!falg) {
			/*
			 * condition .append(" and id ='" + context.getCurrentChannelId() +
			 * "'");
			 */
			idList.add(context.getCurrentChannelId());
		}
		conditionMap.put("idList", idList);
		conditionMap.put("codeList", codeList);
		return conditionMap;
	}

	// 根据Type获取对应属性的html
	public String getHtmlByType(CmsChannelContext channelContext,
			CmsHtmlGenerateContext context, CmsBuildContext cmsBuildContext)
			throws IOException, Exception {
		String html = "";
		if (!(channelContext.getType() == null)) {
			String type = channelContext.getType();
			if (type.equalsIgnoreCase("Title")) {
				html = viewCmsChannelInfo.getName();
			} else if (type.equalsIgnoreCase("PageTitle")) {
				html = viewCmsChannelInfo.getTitle();
			} else if (type.equalsIgnoreCase("Id")) {
				html = viewCmsChannelInfo.getId().toString();
			} else if (type.equalsIgnoreCase("ChannelIndex")) {
				html = viewCmsChannelInfo.getCode();
			} else if (type.equalsIgnoreCase("ChannelCode")) {
				html = viewCmsChannelInfo.getCode();
			} else if (type.equalsIgnoreCase("Content")) {
				html = viewCmsChannelInfo.getText();
			} else if (type.equalsIgnoreCase("PageContent")) {
				html = viewCmsChannelInfo.getText();
			} else if (type.equalsIgnoreCase("AddDate")) {
				html = DateHelper.toDateString(viewCmsChannelInfo
						.getCreateTime());
			} else if (type.equalsIgnoreCase("ImageUrl")) {
				html = "<img src='" + SysConfig.getAdminRootUrl()
						+ viewCmsChannelInfo.getImageFilePath() + "'/>";
			} else if (type.equalsIgnoreCase("ItemIndex")) {
				html = context.getNum() + "";
			} else if (type.equalsIgnoreCase("CountOfChannels")) {
				html = MyBatisManager.getInstance().getCount("cms_channel",
						"parent_id =" + viewCmsChannelInfo.getId())
						+ "";
			} else if (type.equalsIgnoreCase("CountOfContents")) {
				html = MyBatisManager.getInstance().getCount("cms_content",
						"cms_channel_id =" + viewCmsChannelInfo.getId())
						+ "";
			} else if (type.equalsIgnoreCase("CountOfImageContents")) {
				html = MyBatisManager.getInstance().getCount(
						"cms_content",
						"thumb_file_id is not null and cms_channel_id ="
								+ viewCmsChannelInfo.getId())
						+ "";
			} else if (type.equalsIgnoreCase("Keywords")) {
				html = viewCmsChannelInfo.getKeyword();
			} else if (type.equalsIgnoreCase("Description")) {
				html = viewCmsChannelInfo.getDescription();
			} else if (type.equalsIgnoreCase("Url")) {
				String url = this.getUrl(channelContext, context,
						cmsBuildContext);
				html = String.format("<a href='%s'>%s</a>", url,
						viewCmsChannelInfo.getName());
			} else if (type.equalsIgnoreCase("LinkUrl")) {
				html = String.format("<a href='%s'>%s</a>",
						viewCmsChannelInfo.getUrl(),
						viewCmsChannelInfo.getName());
			}
		} else {
			html = viewCmsChannelInfo.getName();
		}
		if (null == html) {
			html = "";
		}
		return html.toString();
	}

	/**
	 * 取得栏目跳转地址
	 * 
	 * @param channelContext
	 * @param context
	 * @param cmsBuildContext
	 * @return
	 */
	private String getUrl(CmsChannelContext channelContext,
			CmsHtmlGenerateContext context, CmsBuildContext cmsBuildContext) {
		String url = cmsBuildContext.getCmsBuildTask().getAdminRootUrl()
				+ viewCmsChannelInfo.getCreateFilePath();
		if (viewCmsChannelInfo.getUrl() != null
				&& !viewCmsChannelInfo.getUrl().equals("")) {
			url = viewCmsChannelInfo.getUrl();
		}
		return url;
	}

	// 根据节点属性，处理获取到的属性值
	public String dealWithValueByAttribute(CmsChannelContext channelContext,
			String value) {
		if (!(channelContext.getReplace() == null)
				&& !(channelContext.getTo() == null)) {
			value = value.replaceAll(channelContext.getReplace(),
					channelContext.getTo());
		}
		if (!(channelContext.getStartIndex() == null)) {
			Integer beginIndex = 0;
			try {
				beginIndex = Integer.parseInt(channelContext.getStartIndex());
			} catch (Exception e) {
				e.printStackTrace();
				beginIndex = 0;
			}
			value = value.substring(beginIndex);
		}
		if (!(channelContext.getIsLower() == null)) {
			value = value.toLowerCase();
		}
		if (!(channelContext.getIsUpper() == null)) {
			value = value.toUpperCase();
		}
		if (!(channelContext.getLeftText() == null)) {
			value = channelContext.getLeftText() + value;
		}
		if (!(channelContext.getRightText() == null)) {
			value = value + channelContext.getRightText();
		}
		if (!(channelContext.getWordNum() == null)) {
			Integer wordNum = 0;
			try {
				wordNum = Integer.parseInt(channelContext.getWordNum());
			} catch (Exception e) {
				e.printStackTrace();
				wordNum = 0;
			}
			value = getValueByWordNum(value, wordNum, channelContext);
		}
		return value;
	}

	// separator属性
	public String gethtmlWidthSeparator(String value,
			CmsChannelContext channelContext, CmsHtmlGenerateContext context) {
		if (!(channelContext.getSeparator() == null)) {
			if ((context.getTotalNum() - 1) > context.getNum()) {
				value += channelContext.getSeparator();
			}
		}
		return value;
	}

	// 使用wordNum处理value
	public String getValueByWordNum(String value, int wordNum,
			CmsChannelContext channelContext) {
		if (null == value) {
			return "";
		}
		if (value.length() > wordNum) {
			if (!(channelContext.getEllipsis() == null)) {
				return value = value.substring(0, wordNum)
						+ channelContext.getEllipsis();
			} else {
				return value = value.substring(0, wordNum) + "...";
			}
		} else {
			return value;
		}
	}

	public String delHTMLTag(String htmlStr) {
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		htmlStr = htmlStr.replace(" ", "");
		htmlStr = htmlStr.replaceAll("\\s*|\t|\r|\n", "");
		htmlStr = htmlStr.replace("“", "");
		htmlStr = htmlStr.replace("”", "");
		htmlStr = htmlStr.replaceAll("　", "");
		htmlStr = htmlStr.replaceAll("&nbsp;", "");

		return htmlStr.trim(); // 返回文本字符串
	}
}
