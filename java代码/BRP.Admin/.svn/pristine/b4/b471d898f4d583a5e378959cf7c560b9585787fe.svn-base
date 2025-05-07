package strosoft.cms.service;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import strosoft.app.common.*;
import strosoft.app.model.ListInfo;
import strosoft.app.service.GetListServiceHandlerContext;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.*;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.ViewCmsChannel;
import strosoft.cms.data.ViewCmsChannelLanguage;
import strosoft.cms.model.CmsChannelManager;
import strosoft.cms.model.CmsLanguageManager;
import strosoft.cms.model.ViewCmsChannelLanguageManager;
import strosoft.cms.model.ViewCmsChannelManager;
import strosoft.sys.data.SysMenu;
import strosoft.sys.model.SysMenuManager;

import net.sf.json.JSONObject;

/*
 * 取得数据表
 */
public class GetViewCmsChannelListServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		GetListServiceHandlerContext context = this.getContext(request);
		ListInfo listInfo = this.createListInfo();
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		Integer cmsSiteId = jData.getInt("cmsSiteId");
		//判断是否开启CMS多语言支持
		Boolean isOnCmslaunguage = CmsLanguageManager.getInstance().getIsOnCmslaunguage(cmsSiteId);
		if(jData.containsKey("cmsLanguageCode") && isOnCmslaunguage){
			String cmsLanguageCode = jData.getString("cmsLanguageCode");
			String condition = context.getCondition()+" and cms_language_code = '"+cmsLanguageCode+"'";
			context.setCondition(condition);
			ArrayList<ViewCmsChannelLanguage> lstData = this.getViewCmsChannelLanguageList(context,cmsLanguageCode);
			listInfo.setDataList(lstData);
		}else{
			List<ViewCmsChannel> lstData = this.getViewCmsChannelList(context);
			listInfo.setDataList(lstData);
		}
		// 当前页
		listInfo.setPageIndex(context.getPageIndex());
		// 分页大小
		listInfo.setPageSize(context.getPageSize());
		// 记录数
		listInfo.setRecordCount(context.getRecordCount());
		// 页数
		listInfo.setPageCount(context.getPageCount());
		this.processListInfo(listInfo);
		System.out.println(JSONObject.fromObject(listInfo));
		this.writeSuccessResponse(response, listInfo);

	}

	/*
	 * 取得记录数
	 */
	private int getRecordCount(GetListServiceHandlerContext context)
			throws Exception {
		String sqlWhere = "";
		if (context.getCondition() != null
				&& !context.getCondition().equals("")) {
			sqlWhere = " where " + context.getCondition();
		}
		String sqlGroupBy = "";

		if (context.getGroupBy() != null && !context.getGroupBy().equals("")) {
			sqlWhere = " group by " + context.getGroupBy();
		}
		String sql = String.format("select count(*) from %s%s%s",
				context.getTableName(), sqlWhere, sqlGroupBy);

		 System.out.println(sql);
		int count = MyBatisManager.getInstance().executeInt(sql);
		return count;
	}

	/*
	 * 取得数据列表
	 */
	private List<ViewCmsChannel> getViewCmsChannelList(
			GetListServiceHandlerContext context) throws Exception {
		String sqlWhere = "";
		if (context.getCondition() != null
				&& !context.getCondition().equals("")) {
			sqlWhere = " where " + context.getCondition();
		}

		String sqlGroupBy = "";
		if (context.getGroupBy() != null && !context.getGroupBy().equals("")) {
			sqlGroupBy = " group by  " + context.getGroupBy();
		}
		String sqlOrderBy = "";
		if (context.getOrderBy() != null && !context.getOrderBy().equals("")) {
			sqlOrderBy = " order by  " + context.getOrderBy();
		}
		String sqlLimit = "";
		if (context.getPageSize() > 0) {
			sqlLimit = " limit "
					+ String.valueOf(context.getPageIndex()
							* context.getPageSize()) + ","
					+ context.getPageSize();
		}
		String sql = "select * from view_cms_channel " + sqlWhere + sqlOrderBy + sqlLimit;
		System.out.println(sql);
		//获取所有栏目
		List<ViewCmsChannel> allViewChannels = ViewCmsChannelManager.getInstance().getList();
		//key为栏目Id的hashTable
		Hashtable<Integer, ViewCmsChannel> viewChannelHashtable = ViewCmsChannelManager.getInstance().getViewChannelHashtable(allViewChannels);
		//获取查询的栏目
		List<ViewCmsChannel> lstData = ViewCmsChannelManager.getInstance().getList(sqlWhere, sqlOrderBy);
		//添加父菜单
		for(int i = 0; i <= lstData.size() - 1; i++){
			ViewCmsChannel curentCmsChannel = lstData.get(i);
			List<ViewCmsChannel> lstParentsChannel = ViewCmsChannelManager.getInstance().getParentsList(curentCmsChannel.getId(),viewChannelHashtable);
			for (ViewCmsChannel currentParentChannel : lstParentsChannel) {
				Boolean exists = existsCmsChannel(lstData,currentParentChannel.getId());
				if(!exists){
					lstData.add(currentParentChannel);
					}
				}
			}
		//按显示顺序排序
		for(int i = 0; i < lstData.size() - 1; i++){
			for(int j = i + 1; j < lstData.size();j++){
				if(lstData.get(i).getDisplayOrder() > lstData.get(j).getDisplayOrder() ){
					ViewCmsChannel cmsChannel = lstData.get(i);
					lstData.set(i, lstData.get(j));
					lstData.set(j, cmsChannel);
				}
			}
		}
		return lstData;
	}
	private Boolean existsCmsChannel(List<ViewCmsChannel> lstCmsChannel, int id) {
		for (ViewCmsChannel currentCmsChannel : lstCmsChannel) {
			if (currentCmsChannel.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 取得数据列表
	 */
	private ArrayList<ViewCmsChannelLanguage> getViewCmsChannelLanguageList(
			GetListServiceHandlerContext context,String cmsLanguageCode) throws Exception {
		String sqlWhere = "";
		if (context.getCondition() != null
				&& !context.getCondition().equals("")) {
			sqlWhere = " where " + context.getCondition();
		}

		String sqlGroupBy = "";
		if (context.getGroupBy() != null && !context.getGroupBy().equals("")) {
			sqlGroupBy = " group by  " + context.getGroupBy();
		}
		String sqlOrderBy = "";
		if (context.getOrderBy() != null && !context.getOrderBy().equals("")) {
			sqlOrderBy = " order by  " + context.getOrderBy();
		}
		String sqlLimit = "";
		if (context.getPageSize() > 0) {
			sqlLimit = " limit "
					+ String.valueOf(context.getPageIndex()
							* context.getPageSize()) + ","
					+ context.getPageSize();
		}
		String sql = sqlWhere + sqlOrderBy + sqlLimit;
		System.out.println(sql);
		//获取所有语言栏目
 		ArrayList<ViewCmsChannelLanguage> allViewChannels = ViewCmsChannelLanguageManager.getInstance().getListByCmsLanguageCdoe(cmsLanguageCode);
		//key为栏目Id的hashTable
		Hashtable<Integer, ViewCmsChannelLanguage> viewChannelHashtable = ViewCmsChannelLanguageManager.getInstance().getViewChannelHashtable(allViewChannels);
		//获取查询的栏目
		ArrayList<ViewCmsChannelLanguage> lstData = ViewCmsChannelLanguageManager.getInstance().getListBySql(sql);
		//添加父菜单
		for(int i = 0; i <= lstData.size() - 1; i++){
			ViewCmsChannelLanguage curentCmsChannel = lstData.get(i);
			List<ViewCmsChannelLanguage> lstParentsChannel = ViewCmsChannelLanguageManager.getInstance().getParentsList(curentCmsChannel.getId(),viewChannelHashtable);
			for (ViewCmsChannelLanguage currentParentChannel : lstParentsChannel) {
				Boolean exists = existsCmsChannelLanguage(lstData,currentParentChannel.getId());
				if(!exists){
					lstData.add(currentParentChannel);
					}
				}
			}
		//按显示顺序排序
		for(int i = 0; i < lstData.size() - 1; i++){
			for(int j = i + 1; j < lstData.size();j++){
				if(lstData.get(i).getDisplayOrder() > lstData.get(j).getDisplayOrder() ){
					ViewCmsChannelLanguage cmsChannel = lstData.get(i);
					lstData.set(i, lstData.get(j));
					lstData.set(j, cmsChannel);
				}
			}
		}
		return lstData;
	}
	private Boolean existsCmsChannelLanguage(ArrayList<ViewCmsChannelLanguage> lstCmsChannel, int id) {
		for (ViewCmsChannelLanguage currentCmsChannel : lstCmsChannel) {
			if (currentCmsChannel.getId() == id) {
				return true;
			}
		}
		return false;
	}
	/*
	 * 检查输入
	 */
	protected Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GetListServiceHandlerContext context = this.getContext(request);
		String tableName = context.getTableName();
		if (tableName == null || tableName.equals("")) {
			this.writeErrorResponse(response, "表名不能为空！");
			return false;
		}
		return true;
	}

	/*
	 * 取得列表信息
	 */
	protected ListInfo createListInfo() {
		return new ListInfo();
	}

	/*
	 * 处理数据
	 */
	protected void processListInfo(ListInfo listInfo) {
	}

	/*
	 * 取得参数
	 */
	protected GetListServiceHandlerContext getContext(HttpServletRequest request)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);

		Node configNode = this.getConfigNode();
		GetListServiceHandlerContext context = new GetListServiceHandlerContext();
		// 表名
		String tableName = JsonHelper.getString(jData, "table_name",
				"tableName", "TableName");
		if (tableName == null || tableName.equals("")) {
			tableName = XmlDocumentHelper.getAttribute(configNode, "TableName");
		}
		context.setTableName(tableName);
		// 条件
		String condition = this.getCondition(request);
		//栏目权限
		condition +=CmsChannelManager.getInstance().getPermissionByRole(request,"channel");
		context.setCondition(condition);
		// 排序
		String orderBy = JsonHelper.getString(jData, "order_by", "orderBy",
				"OrderBy");
		if (orderBy == null || orderBy.equals("")) {
			orderBy = XmlDocumentHelper.getAttribute(configNode, "OrderBy");
		}
		context.setOrderBy(orderBy);
		// 查询字段
		String fields = JsonHelper.getString(jData, "fields", "Fields");
		if (fields == null || fields.equals("")) {
			fields = XmlDocumentHelper.getAttribute(configNode, "Fields");
		}
		context.setFields(fields);
		// 分组
		String groupBy = JsonHelper.getString(jData, "group_by", "groupBy",
				"GroupBy");
		if (groupBy == null || groupBy.equals("")) {
			groupBy = XmlDocumentHelper.getAttribute(configNode, "GroupBy");
		}
		context.setGroupBy(groupBy);
		// 当前页
		String strPageIndex = JsonHelper.getString(jData, "page_index",
				"pageIndex", "PageIndex");
		if (strPageIndex == null || strPageIndex.equals("")) {
			strPageIndex = XmlDocumentHelper.getAttribute(configNode,
					"PageIndex");
		}
		if (strPageIndex == null || strPageIndex.equals("")) {
			context.setPageIndex(0);
		} else {
			context.setPageIndex(Integer.parseInt(strPageIndex));
		}
		// 分页大小
		String strPageSize = JsonHelper.getString(jData, "page_size",
				"pageSize", "PageSize");
		if (strPageSize == null || strPageSize.equals("")) {
			strPageSize = XmlDocumentHelper
					.getAttribute(configNode, "PageSize");
		}
		if (strPageSize == null || strPageSize.equals("")) {
			context.setPageSize(SysConstants.PAGE_SIZE);
		} else {
			context.setPageSize(Integer.parseInt(strPageSize));
		}
		// 记录数
		int recordCount = this.getRecordCount(context);
		context.setRecordCount(recordCount);
		// 页数
		int pageCount = PagerHelper.getPageCount(context.getRecordCount(),
				context.getPageSize());
		context.setPageCount(pageCount);
		return context;
	}

	/*
	 * 取得查询条件
	 */
	protected String getCondition(HttpServletRequest request) throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		Node configNode = this.getConfigNode();

		String condition = JsonHelper
				.getString(jData, "condition", "Condition");
		if (condition == null || condition.equals("")) {
			condition = XmlDocumentHelper.getAttribute(configNode, "Condition");
		}
		MySqlConditionBuilder conditionBuilder = new MySqlConditionBuilder();
		if (condition != null && !condition.equals("")) {
			conditionBuilder.add(condition);
		}
		// 从请求参数构造查询条件
		ArrayList<Node> lstSearchItem = XmlDocumentHelper
				.selectNodesByNodeName(configNode, "SearchItem");
		for (Node nodeSearchItem : lstSearchItem) {
			String fieldName = XmlDocumentHelper.getAttribute(nodeSearchItem,
					"FieldName");
			String operation = XmlDocumentHelper.getAttribute(nodeSearchItem,
					"Operation");
			String parameterName = XmlDocumentHelper.getAttribute(
					nodeSearchItem, "ParameterName");
			String value = JsonHelper.getString(jData, parameterName);
			if (operation.toLowerCase().equals("like")) {
				conditionBuilder.addLikeCondition(fieldName, value);
			} else if (operation.toLowerCase().equals("equal")) {
				conditionBuilder.addEqualCondition(fieldName, value);
			} else if (operation.toLowerCase().equals("notequal")) {
				conditionBuilder.addNotEqualCondition(fieldName, value);
			} else if (operation.toLowerCase().equals("in")) {
				conditionBuilder.addInCondition(fieldName, value);
			} else if (operation.toLowerCase().equals("notin")) {
				conditionBuilder.addNotInCondition(fieldName, value);
			} else if (operation.toLowerCase().equals("idtree")) {
				String treeTableName = XmlDocumentHelper.getAttribute(
						nodeSearchItem, "TreeTableName");
				Integer parentId = JsonHelper.getInt(jData, parameterName);
				conditionBuilder.addIDTreeCondition(fieldName, treeTableName,
						parentId);
			} else if (operation.toLowerCase().equals("codetree")) {
				String treeTableName = XmlDocumentHelper.getAttribute(
						nodeSearchItem, "TreeTableName");
				String parentCode = JsonHelper.getString(jData, parameterName);
				conditionBuilder.addCodeTreeCondition(fieldName, treeTableName,
						parentCode);
			} else if (operation.toLowerCase().equals("timespan")) {
				String beginDateParameterName = XmlDocumentHelper.getAttribute(
						nodeSearchItem, "BeginDateParameterName");
				if (beginDateParameterName == null
						|| beginDateParameterName.equals("")) {
					beginDateParameterName = "beginDate";
				}
				String endDateParameterName = XmlDocumentHelper.getAttribute(
						nodeSearchItem, "EndDateParameterName");
				if (endDateParameterName == null
						|| endDateParameterName.equals("")) {
					endDateParameterName = "endDate";
				}
				String strBeginDate = JsonHelper.getString(jData,
						beginDateParameterName);
				String strEndDate = JsonHelper.getString(jData,
						endDateParameterName);
				conditionBuilder.addTimeSpanCondition(strBeginDate, strEndDate,
						fieldName);
			}
		}
		condition = conditionBuilder.toString();
		return condition;
	}
}
