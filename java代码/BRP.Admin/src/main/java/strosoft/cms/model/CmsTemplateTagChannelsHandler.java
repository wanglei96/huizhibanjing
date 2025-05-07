package strosoft.cms.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import strosoft.app.common.MyBatisManager;
import strosoft.cms.data.CmsChannelsContext;
import strosoft.cms.data.ViewCmsChannelInfo;

public class CmsTemplateTagChannelsHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition = null;
	private Node currentNode = null;

	@Override
	public String process(CmsHtmlGenerateContext context,
			CmsBuildContext cmsBuildContext) {
		StringBuffer content = new StringBuffer();
		currentNode = context.getCurrentNode();
		// 获取节点属性
		attributeCondition = getAttributeCondition(currentNode);
		// 获取channels生成参数
		CmsChannelsContext channelsContext = new CmsChannelsContext(
				attributeCondition);
		SqlSession sqlSession = cmsBuildContext.getSqlSession();
		try {
			// String condition
			// =getSqlCondition(sqlSession,channelsContext,context);
			HashMap<String, Object> conditionMap = getSqlCondition(sqlSession,
					channelsContext, context);
			// 获取栏目id
			/*
			 * String sql =
			 * "select id from cms_channel "+condition+" and cms_site_id="
			 * +context.getCmsSiteId(); List<Integer> cmsChannelsIds =
			 * sqlSession.selectList("CmsChannel.selectIntBySql", sql);
			 * if(cmsChannelsIds.size()<1 ){ //如果栏目数组为空，返回空字符串 return ""; }
			 */
			List<ViewCmsChannelInfo> lstViewCmsChannelInfo = CmsBuildManager
					.getInstance().getViewCmsChannelInfoListByConditionMap(
							conditionMap, context.getCmsLanguageCode());
			// 检查isAllChildren属性,获取子栏目id
			// List<Integer> subCmsChannelsIds =
			// getSubCmsChannelIds(sqlSession,channelsContext,context,cmsChannelsIds);
			List<ViewCmsChannelInfo> lstSubViewCmsChannelsInfo = getSubCmsChannelIds(
					sqlSession, channelsContext, context, lstViewCmsChannelInfo);
			this.sortChannelList(lstSubViewCmsChannelsInfo);
			// 根据显示条件约束，筛选出要显示的子栏目
			List<Integer> showCmsChannelIdList = getSubIdListByScreening(
					sqlSession, lstSubViewCmsChannelsInfo, channelsContext,
					context);
			// 设置总的翻页内容数
			cmsBuildContext.setTotalContentNum(showCmsChannelIdList.size());
			// 判断是否设置pageNum属性，进行分页
			return getHtmlAndPaging(channelsContext, content,
					showCmsChannelIdList, context, cmsBuildContext);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 按显示顺序排序
	 * @param lstSubViewCmsChannelsInfo
	 */
	private void sortChannelList(
			List<ViewCmsChannelInfo> lstSubViewCmsChannelsInfo) {
		for (int x = 0; x < lstSubViewCmsChannelsInfo.size() - 1; x++) {
			for (int y = x + 1; y < lstSubViewCmsChannelsInfo.size(); y++) {
				if (lstSubViewCmsChannelsInfo.get(x).getDisplayOrder() > lstSubViewCmsChannelsInfo
						.get(y).getDisplayOrder()) {
					ViewCmsChannelInfo tmpViewCmsChannelInfo = lstSubViewCmsChannelsInfo
							.get(x);
					lstSubViewCmsChannelsInfo.set(x,
							lstSubViewCmsChannelsInfo.get(y));
					lstSubViewCmsChannelsInfo.set(y, tmpViewCmsChannelInfo);
				}
			}
		}

	}

	/*
	 * 根据节点属性名称和值进行返回数据
	 */
	// 获取栏目查询条件：channelCode、channelindex、channelName、topLevel、upLevel、isTotal
	public HashMap<String, Object> getSqlCondition(SqlSession sqlSession,
			CmsChannelsContext channelsContext, CmsHtmlGenerateContext context) {
		// StringBuffer condition = new StringBuffer(" where 1=1");
		HashMap<String, Object> conditionMap = new HashMap<String, Object>();
		List<String> codeList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		List<Integer> idList = new ArrayList<Integer>();
		List<Integer> idAreaList = null;
		// 判断有没有设置栏目模板
		boolean falg = false;
		// 获取栏目id
		// channelCode
		if (!(channelsContext.getChannelCode() == null)) {
			// condition.append(" and code ='"+channelsContext.getChannelCode()+"'");
			codeList.add(channelsContext.getChannelCode());
			falg = true;
		}
		// channelIndex
		if (!(channelsContext.getChannelIndex() == null)) {
			// condition.append(" and code ='"+channelsContext.getChannelIndex()+"'");
			codeList.add(channelsContext.getChannelIndex());
			falg = true;
		}

		// channelName
		if (!(channelsContext.getChannelName() == null)) {
			// condition.append(" and name ='"+channelsContext.getChannelName()+"'");
			nameList.add(channelsContext.getChannelName());
			falg = true;
		}
		// topLevel
		if (!(channelsContext.getTopLevel() == null)) {
			Integer level = 0;
			// int rootCmsChannelId =
			// CmsChannelManager.getInstance().getIndexId(sqlSession,context.getCmsSiteId());
			ViewCmsChannelInfo rootViewCmsChannelInfo = CmsBuildManager
					.getInstance().getRootViewCmsChannelInfoByCmsSiteId(
							context.getCmsSiteId(),
							context.getCmsLanguageCode());
			try {
				level = Integer.parseInt(channelsContext.getTopLevel());
			} catch (Exception e) {
				level = 0;
				e.printStackTrace();
			}
			// List<Integer> ids
			// =CmsChannelManager.getInstance().getSubLevelByIdAndLevel(sqlSession,
			// rootCmsChannelId, level);
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
			 * if(ids.size()<1){ condition.append(" and 1=2 "); }else{
			 * condition.
			 * append(" and id in ("+CmsChannelManager.getInstance().getIdsString
			 * (ids)+")"); }
			 */
			falg = true;
		}
		// upLevel
		if (!(channelsContext.getUpLevel() == null)
				&& (context.getTemplateType().equalsIgnoreCase("channel") || context
						.getTemplateType().equalsIgnoreCase("content"))) {
			int level = 0;
			Integer cmsChannelId = context.getCurrentChannelId();
			try {
				level = Integer.parseInt(channelsContext.getUpLevel());
			} catch (Exception e) {
				level = 0;
				e.printStackTrace();
			}
			// cmsChannelId =
			// CmsChannelManager.getInstance().getUpLevelByIdAndLevel(sqlSession,cmsChannelId,level);
			ViewCmsChannelInfo viewCmsChannelInfo = CmsBuildManager
					.getInstance().getViewCmsChannelInfoByLevelAndId(
							cmsChannelId, level, context.getCmsLanguageCode());
			if (null == viewCmsChannelInfo) {
				idList.add(-1);
			} else {
				idList.add(viewCmsChannelInfo.getId());
			}
			/*
			 * if(null == cmsChannelId){ condition.append(" and 1=2 "); }else{
			 * condition.append(" and id ="+cmsChannelId); }
			 */
			falg = true;
		}
		// isTotal
		if (!(channelsContext.getIsTotal() == null)
				&& channelsContext.getIsTotal().equalsIgnoreCase("true")) {
			// condition.append(" union select parent_id from view_cms_channel where parent_id = 0");
			falg = true;
		}
		// 没有设置栏目
		if (!falg) {
			// 设置了栏目组，获取所有栏目
			/*
			 * if(!(channelsContext.getGroupChannel()==null)){
			 * 
			 * }else{//没有设置栏目组
			 * condition.append(" and id ='"+context.getCurrentChannelId()+"'");
			 * }
			 */
			// condition.append(" and id ='"+context.getCurrentChannelId()+"'");
			idList.add(context.getCurrentChannelId());
		}
		conditionMap.put("idList", idList);
		conditionMap.put("codeList", codeList);
		conditionMap.put("nameList", nameList);
		conditionMap.put("idAreaList", idAreaList);
		return conditionMap;
	}

	// 获取子栏目查询条件：groupChannel、groupChannelNot、isImage、where
	public String getSubCmsChannelSqlCondition(
			CmsChannelsContext channelsContext, CmsHtmlGenerateContext context) {
		StringBuffer condition = new StringBuffer(" where 1=1");
		// groupchannel
		if (!(channelsContext.getGroupChannel() == null)) {
			condition
					.append(" and (id in (SELECT cms_channel_id	FROM cms_channel_channel_group WHERE `cms_channel_group_code` IN ( SELECT	`code` FROM	cms_channel_group	WHERE code = '"
							+ channelsContext.getGroupChannel()
							+ "' or `name` = '"
							+ channelsContext.getGroupChannel() + "')))");
		}
		// groupChannelNot
		if (!(channelsContext.getGroupChannelNot() == null)) {
			condition
					.append(" and (id not in (SELECT cms_channel_id	FROM cms_channel_channel_group WHERE `cms_channel_group_code` IN ( SELECT	`code` FROM	cms_channel_group	WHERE code = '"
							+ channelsContext.getGroupChannel()
							+ "' or 	`name` = '"
							+ channelsContext.getGroupChannelNot() + "')))");
		}
		// where
		if (!(channelsContext.getWhere() == null)) {
			condition.append(" and (" + channelsContext.getWhere() + ")");
		}
		// isImage
		if (!(channelsContext.getIsImage() == null)
				&& channelsContext.getIsImage().equalsIgnoreCase("true")) {
			condition.append(" and image_file_id is not null");
		}

		return condition.toString();
	}

	// 获取子栏目查询级别，isAllChildren==true,查询出所有级别子栏目
	public List<ViewCmsChannelInfo> getSubCmsChannelIds(SqlSession sqlSession,
			CmsChannelsContext channelsContext, CmsHtmlGenerateContext context,
			List<ViewCmsChannelInfo> viewCmsChannelInfos) {
		/*
		 * List<Integer> cmsChannelIds = cmsChannelsIds; List<Integer>
		 * nextLevelCmsChannelIds = new ArrayList<Integer>();
		 * //isAllChildren==true //查询cmsChannelIds的第一代子栏目 nextLevelCmsChannelIds
		 * = CmsChannelManager.getInstance().getSubCmsChannelsIds(sqlSession,
		 * cmsChannelIds); cmsChannelIds = new ArrayList<Integer>();
		 * if(!(channelsContext.getIsAllChildren()==null) &&
		 * channelsContext.getIsAllChildren().equalsIgnoreCase("true")){
		 * //查询该栏目下的所有子栏目id while(nextLevelCmsChannelIds.size()>0){
		 * cmsChannelIds.addAll(nextLevelCmsChannelIds); nextLevelCmsChannelIds
		 * = CmsChannelManager.getInstance().getSubCmsChannelsIds(sqlSession,
		 * nextLevelCmsChannelIds); } }else{
		 * cmsChannelIds.addAll(nextLevelCmsChannelIds); } return cmsChannelIds;
		 */
		// 查询cmsChannelIds的第一代子栏目
		List<ViewCmsChannelInfo> subViewCmsChannelInfos = new ArrayList<ViewCmsChannelInfo>();
		List<ViewCmsChannelInfo> nextLevelChannelInfos = CmsBuildManager
				.getInstance()
				.getSubViewCmsChannelInfoListByCmsChannelIdAndLevel(
						viewCmsChannelInfos, 1, context.getCmsLanguageCode());
		if (!(channelsContext.getIsAllChildren() == null)
				&& channelsContext.getIsAllChildren().equalsIgnoreCase("true")) {
			// 查询该栏目下的所有子栏目id
			while (nextLevelChannelInfos.size() > 0) {
				subViewCmsChannelInfos.addAll(nextLevelChannelInfos);
				nextLevelChannelInfos = CmsBuildManager.getInstance()
						.getSubViewCmsChannelInfoListByCmsChannelIdAndLevel(
								viewCmsChannelInfos, 1,
								context.getCmsLanguageCode());
			}
		} else {
			subViewCmsChannelInfos.addAll(nextLevelChannelInfos);
		}
		return nextLevelChannelInfos;
	}

	// 获取排序，Order
	public String getOrderBy(SqlSession sqlSession,
			CmsChannelsContext channelsContext, CmsHtmlGenerateContext context) {
		String orderBy = "";
		if (!(channelsContext.getOrder() == null)) {
			if (channelsContext.getOrder().equalsIgnoreCase("Default")) {

			}
			if (channelsContext.getOrder().equalsIgnoreCase("Back")) {
				orderBy = " order by desc";
			}
			if (channelsContext.getOrder().equalsIgnoreCase("AddDate")) {
				orderBy = " order by create_time";
			}
			if (channelsContext.getOrder().equalsIgnoreCase("AddDateBack")) {
				orderBy = " order by create_time desc";
			}
			if (channelsContext.getOrder().equalsIgnoreCase("Random")) {
				orderBy = " order by rand() ";
			}
		}
		return orderBy;
	}

	// 获取开始显示的编号和显示的数据长度
	public String getStartNumAndLength(SqlSession sqlSession,
			CmsChannelsContext channelsContext, CmsHtmlGenerateContext context) {
		String limt = " limit ";
		int startNum = 0;
		int length = -1;
		if (channelsContext.getStartNum() != null) {
			try {
				startNum = Integer.parseInt(channelsContext.getStartNum());
				if (startNum < 0) {
					startNum = 0;
				}
			} catch (Exception e) {
				startNum = 0;
				e.printStackTrace();
			}
		}
		limt += startNum + ",";
		if (channelsContext.getTotalNum() != null) {
			try {
				length = Integer.parseInt(channelsContext.getTotalNum());
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

	// 获取显示条件过滤后的栏目idList
	public List<Integer> getSubIdListByScreening(SqlSession sqlSession,
			List<ViewCmsChannelInfo> subViewCmsChannelsInfos,
			CmsChannelsContext channelsContext, CmsHtmlGenerateContext context) {
		List<Integer> cmsChannelIdList = null;
		if (null == subViewCmsChannelsInfos
				|| subViewCmsChannelsInfos.size() < 1) {
			cmsChannelIdList = new ArrayList<Integer>();
		} else {
			List<Integer> subCmsChannelsIds = new ArrayList<Integer>();
			for (int i = 0; i < subViewCmsChannelsInfos.size(); i++) {
				subCmsChannelsIds.add(subViewCmsChannelsInfos.get(i).getId());
			}
			// 获取子栏目筛选条件
			String subCmsChannelCondition = getSubCmsChannelSqlCondition(
					channelsContext, context);
			// 获取子栏目排序
			String orderBy = getOrderBy(sqlSession, channelsContext, context);
			if(orderBy == null || orderBy.equals("")){
				orderBy = "display_order";
			}
			// 获取子栏目开始显示的编号和显示长度
			String limit = getStartNumAndLength(sqlSession, channelsContext,
					context);
			String ids = CmsChannelManager.getInstance().getIdsString(
					subCmsChannelsIds);
			String condition = subCmsChannelCondition + " and id in (" + ids
					+ ") and cms_site_id = " + context.getCmsSiteId();
			// 执行sql
			cmsChannelIdList = CmsBuildManager.getInstance()
					.getCmsChannelLanguageIdsInfoIdsBySql(sqlSession,
							context.getCmsLanguageCode(), condition, orderBy,
							limit);
		}
		return cmsChannelIdList;
	}

	// 判断是否设置pageNum属性，进行分页
	public String getHtmlAndPaging(CmsChannelsContext channelsContext,
			StringBuffer content, List<Integer> showCmsChannelIdList,
			CmsHtmlGenerateContext context, CmsBuildContext cmsBuildContext)
			throws CloneNotSupportedException {
		int pageNum = getPageNum(channelsContext);
		if (pageNum > 0) {
			StringBuffer pageHtml = new StringBuffer();
			cmsBuildContext.setPageHtml(new ArrayList<StringBuffer>());
			cmsBuildContext.setPageContentHtml(new ArrayList<String>());
			cmsBuildContext.setPageHtmlReplaceStr("<"
					+ UUID.randomUUID().toString() + ">");
			NodeList nlChildren = currentNode.getChildNodes();
			for (int i = 0; i < showCmsChannelIdList.size(); i++) {
				CmsHtmlGenerateContext cmsHtmlGenerateContext = context.clone();
				cmsHtmlGenerateContext.setCurrentChannelId(showCmsChannelIdList
						.get(i));
				cmsHtmlGenerateContext.setNum(i);
				cmsHtmlGenerateContext.setTotalNum(showCmsChannelIdList.size());
				cmsHtmlGenerateContext.setCurrentShowType("channel");
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
			if (showCmsChannelIdList.size() == 0) {
				cmsBuildContext.getPageHtml().add(new StringBuffer(""));
				cmsBuildContext.setIsPaging("true");
			}
			if ((showCmsChannelIdList.size()) % pageNum != 0) {
				cmsBuildContext.getPageHtml().add(pageHtml);
			}
			return content.append(cmsBuildContext.getPageHtmlReplaceStr())
					.toString();
		} else {
			for (int i = 0; i < showCmsChannelIdList.size(); i++) {
				CmsHtmlGenerateContext cmsHtmlGenerateContext = context.clone();
				cmsHtmlGenerateContext.setCurrentChannelId(showCmsChannelIdList
						.get(i));
				cmsHtmlGenerateContext.setCurrentShowType("channel");
				cmsHtmlGenerateContext.setNum(i);
				cmsHtmlGenerateContext.setTotalNum(showCmsChannelIdList.size());
				NodeList nlChildren = currentNode.getChildNodes();
				// 生成子节点html
				content.append(createChildNodeHtml(nlChildren,
						cmsHtmlGenerateContext, cmsBuildContext));
			}
			return content.toString();
		}
	}

	// 分页参数PageNum
	public int getPageNum(CmsChannelsContext channelsContext) {
		Integer pageNum = 0;
		if (!(channelsContext.getPageNum() == null)) {
			try {
				pageNum = Integer.parseInt(channelsContext.getPageNum());
			} catch (Exception e) {
				e.printStackTrace();
				pageNum = 0;
			}
		}
		return pageNum;
	}

}
