package strosoft.cms.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import strosoft.cms.data.CmsContentsContext;
import strosoft.cms.data.ViewCmsChannelInfo;

public class CmsTemplateTagContentsHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition = null;
	private Node currentNode = null;

	@Override
	public String process(CmsHtmlGenerateContext context,
			CmsBuildContext cmsBuildContext) {
		currentNode = context.getCurrentNode();
		attributeCondition = getAttributeCondition(currentNode);
		StringBuffer content = new StringBuffer();
		// 获取contents生成参数
		CmsContentsContext contentsContext = new CmsContentsContext(
				attributeCondition);
		SqlSession sqlSession = cmsBuildContext.getSqlSession();
		try {
			// String condition = getSqlCondition(sqlSession,
			// contentsContext,context);
			HashMap<String, Object> conditionMap = getSqlCondition(sqlSession,
					contentsContext, context);
			// 获取栏目id
			/*
			 * String sql = "select id from cms_channel " + condition +
			 * " and cms_site_id=" + context.getCmsSiteId(); List<Integer>
			 * cmsChannelsIds = sqlSession.selectList(
			 * "CmsChannel.selectIntBySql", sql);
			 */
			List<ViewCmsChannelInfo> viewCmsChannelInfos = CmsBuildManager
					.getInstance().getViewCmsChannelInfoListByConditionMap(
							conditionMap, context.getCmsLanguageCode());

			if (viewCmsChannelInfos.size() < 1) {
				// 如果栏目数组为空，返回空字符串
				return "";
			}
			// 检查scope属性,获取栏目id
			/*
			 * List<Integer> newCmsChannelIds = getCmsChannelIds(sqlSession,
			 * contentsContext, context, cmsChannelsIds);
			 */
			List<ViewCmsChannelInfo> newViewCmsChannelInfos = getCmsChannelIds(
					sqlSession, contentsContext, context, viewCmsChannelInfos);
			// 根据groupChannel、groupChannelNot
			List<Integer> newCmsChannelIds = new ArrayList<Integer>();
			if (newViewCmsChannelInfos.size() < 1) {
				return "";
			} else {
				for (int i = 0; i < newViewCmsChannelInfos.size(); i++) {
					newCmsChannelIds.add(newViewCmsChannelInfos.get(i).getId());
				}
			}
			String cmsChannelCondition = getCmsChannelSqlCondition(
					contentsContext, context);
			/*
			 * String sql = "select id from cms_channel " + cmsChannelCondition
			 * + " and id in(" +getIdsString(newCmsChannelIds)+")";
			 * List<Integer> cmsChannelsIdsByGroupChannel=
			 * sqlSession.selectList( "CmsChannel.selectIntBySql", sql);
			 */
			String condition = cmsChannelCondition + " and id in("
					+ getIdsString(newCmsChannelIds) + ")";
			List<Integer> cmsChannelsIdsByGroupChannel = CmsBuildManager
					.getInstance().getCmsChannelLanguageIdsInfoIdsBySql(
							sqlSession, context.getCmsLanguageCode(),
							condition, null, null);
			// 根据显示条件约束，筛选出要显示的内容id
			List<Integer> cmsContentIdList = getSubIdListByScreening(
					sqlSession, cmsChannelsIdsByGroupChannel, contentsContext,
					context);
			// 设置总的翻页内容数
			cmsBuildContext.setTotalContentNum(cmsContentIdList.size());
			// 判断是否设置pageNum属性，进行分页
			return getHtmlAndPaging(contentsContext, content, cmsContentIdList,
					context, cmsBuildContext);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	// 获取栏目查询条件：channelCode、channelindex、channelName、topLevel、upLevel、isTotal
	public HashMap<String, Object> getSqlCondition(SqlSession sqlSession,
			CmsContentsContext contentsContext, CmsHtmlGenerateContext context) {
		// StringBuffer condition = new StringBuffer(" where 1=1");
		HashMap<String, Object> conditionMap = new HashMap<String, Object>();
		List<String> codeList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		List<Integer> idList = new ArrayList<Integer>();
		List<Integer> idAreaList = null;
		// 判断有没有设置栏目
		boolean falg = false;
		// 获取栏目id
		// channelCode
		if (!(contentsContext.getChannelCode() == null)) {
			/*
			 * condition.append(" and code ='" +
			 * contentsContext.getChannelCode() + "'");
			 */
			codeList.add(contentsContext.getChannelCode());
			falg = true;
		}
		// channelIndex
		if (!(contentsContext.getChannelIndex() == null)) {
			/*
			 * condition.append(" and code ='" +
			 * contentsContext.getChannelIndex() + "'");
			 */
			codeList.add(contentsContext.getChannelIndex());
			falg = true;
		}

		// channelName
		if (!(contentsContext.getChannelName() == null)) {
			/*
			 * condition.append(" and name ='" +
			 * contentsContext.getChannelName() + "'");
			 */
			nameList.add(contentsContext.getChannelName());
			falg = true;
		}
		// topLevel
		if (!(contentsContext.getTopLevel() == null)) {
			Integer level = 0;
			/*
			 * int rootCmsChannelId =
			 * CmsChannelManager.getInstance().getIndexId( sqlSession,
			 * context.getCmsSiteId());
			 */
			ViewCmsChannelInfo rootViewCmsChannelInfo = CmsBuildManager
					.getInstance().getRootViewCmsChannelInfoByCmsSiteId(
							context.getCmsSiteId(),
							context.getCmsLanguageCode());
			try {
				level = Integer.parseInt(contentsContext.getTopLevel());
			} catch (Exception e) {
				level = 0;
				e.printStackTrace();
			}

			List<ViewCmsChannelInfo> subViewCmsChannelInfos = CmsBuildManager
					.getInstance()
					.getSubViewCmsChannelInfoListByCmsChannelIdAndCode(
							rootViewCmsChannelInfo.getId(), level,
							context.getCmsLanguageCode());
			if (null == subViewCmsChannelInfos
					|| subViewCmsChannelInfos.size() == 0) {
				idList.add(-1);
			} else {
				idAreaList = new ArrayList<Integer>();
				for (int i = 0; i < subViewCmsChannelInfos.size(); i++) {
					idAreaList.add(subViewCmsChannelInfos.get(i).getId());
				}
			}
			/*
			 * List<Integer> ids = CmsChannelManager.getInstance()
			 * .getSubLevelByIdAndevel(sqlSession, rootCmsChannelId, level); if
			 * (ids.size() < 1) { condition.append(" and 1=2 "); } else {
			 * condition.append(" and id in (" +
			 * CmsChannelManager.getInstance().getIdsString(ids) + ")"); }
			 */
			falg = true;
		}
		// upLevel
		if (!(contentsContext.getUpLevel() == null)
				&& (context.getTemplateType().equalsIgnoreCase("channel") || context
						.getTemplateType().equalsIgnoreCase("content"))) {
			int level = 0;
			Integer cmsChannelId = context.getCurrentChannelId();
			try {
				level = Integer.parseInt(contentsContext.getUpLevel());
			} catch (Exception e) {
				level = 0;
				e.printStackTrace();
			}
			/*
			 * cmsChannelId = CmsChannelManager.getInstance()
			 * .getUpLevelByIdAndLevel(sqlSession, cmsChannelId, level); if
			 * (null == cmsChannelId) { condition.append(" and 1=2 "); } else {
			 * condition.append(" and id =" + cmsChannelId); }
			 */
			ViewCmsChannelInfo viewCmsChannelInfo = CmsBuildManager
					.getInstance().getViewCmsChannelInfoByLevelAndId(
							cmsChannelId, level, context.getCmsLanguageCode());
			if (null == viewCmsChannelInfo) {
				idList.add(-1);
			} else {
				idList.add(viewCmsChannelInfo.getId());
			}
			falg = true;
		}
		// 没有设置栏目
		if (!falg) {
			// condition.append(" and id ='" + context.getCurrentChannelId() +
			// "'");
			idList.add(context.getCurrentChannelId());
		}
		conditionMap.put("idList", idList);
		conditionMap.put("nameList", nameList);
		conditionMap.put("codeList", codeList);
		conditionMap.put("idAreaList", idAreaList);
		return conditionMap;
	}

	// 获取内容id，scope=Self/Children/SelfAndChildren/Descendant/All查询出所有栏目
	public List<ViewCmsChannelInfo> getCmsChannelIds(SqlSession sqlSession,
			CmsContentsContext contentsContext, CmsHtmlGenerateContext context,
			List<ViewCmsChannelInfo> viewCmsChannelInfos) {
		// List<Integer> newCmsChannelIds = new ArrayList<Integer>();
		List<ViewCmsChannelInfo> newViewCmsChannelInfos = new ArrayList<ViewCmsChannelInfo>();
		if (!(contentsContext.getScope() == null)) {
			String scope = contentsContext.getScope();
			if (scope.equalsIgnoreCase("Self")) {
				// newCmsChannelIds = cmsChannelIds;
				newViewCmsChannelInfos = viewCmsChannelInfos;
			} else if (scope.equalsIgnoreCase("Children")) {
				// newCmsChannelIds =
				// CmsChannelManager.getInstance().getSubCmsChannelsIds(sqlSession,
				// cmsChannelIds);
				newViewCmsChannelInfos = CmsBuildManager.getInstance()
						.getSubViewCmsChannelInfoListByCmsChannelIdAndLevel(
								viewCmsChannelInfos, 1,
								context.getCmsLanguageCode());
			} else if (scope.equalsIgnoreCase("SelfAndChildren")) {
				/*
				 * newCmsChannelIds = cmsChannelIds;
				 * newCmsChannelIds.addAll(CmsChannelManager.getInstance()
				 * .getSubCmsChannelsIds(sqlSession, cmsChannelIds));
				 */
				newViewCmsChannelInfos = viewCmsChannelInfos;
				newViewCmsChannelInfos.addAll(CmsBuildManager.getInstance()
						.getSubViewCmsChannelInfoListByCmsChannelIdAndLevel(
								viewCmsChannelInfos, 1,
								context.getCmsLanguageCode()));
			} else if (scope.equalsIgnoreCase("Descendant")) {
				List<Integer> nextLevelCmsChannelIds = new ArrayList<Integer>();
				// 查询cmsChannelIds的第一代子栏目
				/*
				 * nextLevelCmsChannelIds = CmsChannelManager.getInstance()
				 * .getSubCmsChannelsIds(sqlSession, cmsChannelIds);
				 */
				// cmsChannelIds = new ArrayList<Integer>();
				newViewCmsChannelInfos = CmsBuildManager.getInstance()
						.getSubViewCmsChannelInfoListByCmsChannelIdAndLevel(
								viewCmsChannelInfos, 1,
								context.getCmsLanguageCode());
				List<ViewCmsChannelInfo> subViewCmsChannelInfos = new ArrayList<ViewCmsChannelInfo>();
				// 查询该栏目下的所有子栏目id
				/*
				 * while (nextLevelCmsChannelIds.size() > 0) {
				 * cmsChannelIds.addAll(nextLevelCmsChannelIds);
				 * nextLevelCmsChannelIds = CmsChannelManager.getInstance()
				 * .getSubCmsChannelsIds(sqlSession, nextLevelCmsChannelIds); }
				 * newCmsChannelIds = cmsChannelIds;
				 */
				while (null != subViewCmsChannelInfos) {
					newViewCmsChannelInfos.addAll(subViewCmsChannelInfos);
					subViewCmsChannelInfos = CmsBuildManager
							.getInstance()
							.getSubViewCmsChannelInfoListByCmsChannelIdAndLevel(
									subViewCmsChannelInfos, 1,
									context.getCmsLanguageCode());
					if (subViewCmsChannelInfos.size() == 0) {
						subViewCmsChannelInfos = null;
					}
				}
			} else if (scope.equalsIgnoreCase("All")) {
				/*
				 * String sql =
				 * "select id from view_cms_channel where cms_site_id ="
				 * +context.getCmsSiteId(); newCmsChannelIds =
				 * sqlSession.selectList("CmsChannel.selectIntBySql", sql);
				 */
				newViewCmsChannelInfos = CmsBuildManager.getInstance()
						.getViewCmsChannelInfos();
			}
		} else {
			// 没有设置scope属性
			// newCmsChannelIds = cmsChannelIds;
			newViewCmsChannelInfos = viewCmsChannelInfos;
		}
		return newViewCmsChannelInfos;
	}

	// 获取栏目查询条件：groupChannel、groupChannelNot
	public String getCmsChannelSqlCondition(CmsContentsContext contentsContext,
			CmsHtmlGenerateContext context) {
		StringBuffer condition = new StringBuffer(" where 1=1");
		// groupchannel
		if (!(contentsContext.getGroupChannel() == null)) {
			condition
					.append(" and (id in (SELECT cms_channel_id	FROM cms_channel_channel_group WHERE `cms_channel_group_code` IN ( SELECT	`code` FROM	cms_channel_group	WHERE	`name` = '"
							+ contentsContext.getGroupChannel() + "')))");
		}
		// groupChannelNot
		if (!(contentsContext.getGroupChannelNot() == null)) {
			condition
					.append(" and (id not in (SELECT cms_channel_id	FROM cms_channel_channel_group WHERE `cms_channel_group_code` IN ( SELECT	`code` FROM	cms_channel_group	WHERE	`name` = '"
							+ contentsContext.getGroupChannelNot() + "')))");
		}
		return condition.toString();
	}

	// 获取要显示的内容查询条件
	public String getCmsContentIdCondition(CmsContentsContext contentsContext) {
		StringBuffer condition = new StringBuffer(" where 1=1");
		// isColor
		if (!(contentsContext.getIsColor() == null)) {
			if (contentsContext.getIsColor().equalsIgnoreCase("true")) {
				condition.append(" and is_highlight = 1");
			}
			if (contentsContext.getIsColor().equalsIgnoreCase("false")) {
				condition.append(" and is_highlight != 0");
			}
		}
		// isHot
		if (!(contentsContext.getIsHot() == null)) {
			if (contentsContext.getIsHot().equalsIgnoreCase("true")) {
				condition.append(" and is_hot = 1");
			}
			if (contentsContext.getIsHot().equalsIgnoreCase("false")) {
				condition.append(" and is_hot != 0");
			}
		}
		// isTop
		if (!(contentsContext.getIsTop() == null)) {
			if (contentsContext.getIsTop().equalsIgnoreCase("true")) {
				condition.append(" and is_top = 1");
			}
			if (contentsContext.getIsTop().equalsIgnoreCase("false")) {
				condition.append(" and is_top != 0");
			}
		}
		// isRecommend
		if (!(contentsContext.getIsRecommend() == null)) {
			if (contentsContext.getIsRecommend().equalsIgnoreCase("true")) {
				condition.append(" and is_recommend = 1");
			}
			if (contentsContext.getIsRecommend().equalsIgnoreCase("false")) {
				condition.append(" and is_recommend = 0");
			}
		}
		// isFile
		if (!(contentsContext.getIsFile() == null)) {
			if (contentsContext.getIsFile().equalsIgnoreCase("true")) {
				condition.append(" and attachment_file_id is not null");
			}
			if (contentsContext.getIsFile().equalsIgnoreCase("false")) {
				condition.append(" and attachment_file_id id null");
			}
		}
		// isVideo
		if (!(contentsContext.getIsVideo() == null)) {
			if (contentsContext.getIsVideo().equalsIgnoreCase("true")) {
				condition.append(" and video_file_id is not null");
			}
			if (contentsContext.getIsVideo().equalsIgnoreCase("false")) {
				condition.append(" and video_file_id id null");
			}
		}
		// isImage
		if (!(contentsContext.getIsImage() == null)) {
			if (contentsContext.getIsImage().equalsIgnoreCase("true")) {
				condition.append(" and thumb_file_id is not null");
			}
			if (contentsContext.getIsImage().equalsIgnoreCase("false")) {
				condition.append(" and thumb_file_id id null");
			}
		}
		return condition.toString();
	}

	// 获取排序，Order
	public String getOrderBy(SqlSession sqlSession,
			CmsContentsContext contentsContext, CmsHtmlGenerateContext context) {
		String order = contentsContext.getOrder();
		if (order == null || order.equals("")) {
			order = "AddDateBack";
		}
		if (order.equalsIgnoreCase("Default")) {
			return " order by display_order";
		}
		if (order.equalsIgnoreCase("Back")) {
			return " order by display_order desc";
		}
		if (order.equalsIgnoreCase("AddDate")) {
			return " order by publish_date";
		}
		if (order.equalsIgnoreCase("AddDateBack")) {
			return " order by publish_date desc";
		}
		if (order.equalsIgnoreCase("Random")) {
			return " order by rand() ";
		}
		return "";
	}

	// 获取开始显示的编号和显示的数据长度
	public String getStartNumAndLength(SqlSession sqlSession,
			CmsContentsContext contentsContext, CmsHtmlGenerateContext context) {
		String limt = " limit ";
		int startNum = 0;
		int length = -1;
		if (contentsContext.getStartNum() != null) {
			try {
				startNum = Integer.parseInt(contentsContext.getStartNum());
				if (startNum < 0) {
					startNum = 0;
				}
			} catch (Exception e) {
				startNum = 0;
				e.printStackTrace();
			}
		}
		limt += startNum + ",";
		if (contentsContext.getTotalNum() != null) {
			try {
				length = Integer.parseInt(contentsContext.getTotalNum());
				if (length < 0) {
					length = -1;
				}
			} catch (Exception e) {
				length = -1;
				e.printStackTrace();
			}
			if (length == -1) {
				limt += Integer.MAX_VALUE;
			} else {
				limt += length;
			}
		} else {
			limt += Integer.MAX_VALUE;
		}
		return limt;
	}

	// 获取显示条件过滤后的内容idList
	public List<Integer> getSubIdListByScreening(SqlSession sqlSession,
			List<Integer> newCmsChannelsIds,
			CmsContentsContext contentsContext, CmsHtmlGenerateContext context) {
		List<Integer> cmsContentIdList = null;
		if (newCmsChannelsIds.size() < 1) {
			cmsContentIdList = new ArrayList<Integer>();
		} else {
			// 获取子栏目筛选条件
			String cmsContentCondition = getCmsContentIdCondition(contentsContext);
			// 获取子栏目排序
			String orderBy = getOrderBy(sqlSession, contentsContext, context);
			// 获取子栏目开始显示的编号和显示长度
			String limit = getStartNumAndLength(sqlSession, contentsContext,
					context);
			String whereStr = "";
			String ids = getIdsString(newCmsChannelsIds);
			// 执行sql
			String sql = "select id from cms_content " + cmsContentCondition
					+ " and cms_channel_id in (" + ids + ")";
			if (contentsContext.getWhere() != null) {
				whereStr = contentsContext.getWhere();
			}
			sql = sql + whereStr + orderBy + limit;
			cmsContentIdList = sqlSession.selectList(
					"CmsContent.selectIntBySql", sql);
		}
		return cmsContentIdList;
	}

	// 判断是否设置pageNum属性，进行分页
	public String getHtmlAndPaging(CmsContentsContext contentsContext,
			StringBuffer content, List<Integer> cmsContentIdList,
			CmsHtmlGenerateContext context, CmsBuildContext cmsBuildContext)
			throws CloneNotSupportedException {
		int pageNum = getPageNum(contentsContext);
		if (pageNum > 0) {
			StringBuffer pageHtml = new StringBuffer();
			cmsBuildContext.setPageHtml(new ArrayList<StringBuffer>());
			cmsBuildContext.setPageContentHtml(new ArrayList<String>());
			cmsBuildContext.setPageHtmlReplaceStr("<"
					+ UUID.randomUUID().toString() + ">");
			NodeList nlChildren = currentNode.getChildNodes();
			for (int i = 0; i < cmsContentIdList.size(); i++) {
				CmsHtmlGenerateContext cmsHtmlGenerateContext = context.clone();
				cmsHtmlGenerateContext.setCurrentContentId(cmsContentIdList
						.get(i));
				cmsHtmlGenerateContext.setCurrentShowType("Content");
				cmsHtmlGenerateContext.setNum(i);
				cmsHtmlGenerateContext.setTotalNum(cmsContentIdList.size());
				cmsBuildContext.setIsPaging("true");
				if (((i + 1) % pageNum == 0)) {
					// 生成子节点html
					pageHtml.append(createChildNodeHtml(nlChildren,
							cmsHtmlGenerateContext, cmsBuildContext));
					cmsBuildContext.getPageHtml().add(pageHtml);
					pageHtml = new StringBuffer();
				} else {
					// 生成子节点html
					pageHtml.append(createChildNodeHtml(nlChildren,
							cmsHtmlGenerateContext, cmsBuildContext));
				}
			}
			// 当生成0条数据时
			if (cmsContentIdList.size() == 0) {
				cmsBuildContext.getPageHtml().add(new StringBuffer(""));
				cmsBuildContext.setIsPaging("true");
			}
			if ((cmsContentIdList.size()) % pageNum != 0) {
				cmsBuildContext.getPageHtml().add(pageHtml);
			}
			return content.append(cmsBuildContext.getPageHtmlReplaceStr())
					.toString();
		} else {
			for (int i = 0; i < cmsContentIdList.size(); i++) {
				CmsHtmlGenerateContext cmsHtmlGenerateContext = context.clone();
				cmsHtmlGenerateContext.setCurrentContentId(cmsContentIdList
						.get(i));
				cmsHtmlGenerateContext.setNum(i);
				cmsHtmlGenerateContext.setCurrentShowType("Content");
				cmsHtmlGenerateContext.setTotalNum(cmsContentIdList.size());
				NodeList nlChildren = currentNode.getChildNodes();
				// 生成子节点html
				content.append(createChildNodeHtml(nlChildren,
						cmsHtmlGenerateContext, cmsBuildContext));
			}
			return content.toString();
		}
	}

	// 分页参数PageNum
	public int getPageNum(CmsContentsContext contentsContext) {
		Integer pageNum = 0;
		if (!(contentsContext.getPageNum() == null)) {
			try {
				pageNum = Integer.parseInt(contentsContext.getPageNum());
			} catch (Exception e) {
				e.printStackTrace();
				pageNum = 0;
			}
		}
		return pageNum;
	}
}
