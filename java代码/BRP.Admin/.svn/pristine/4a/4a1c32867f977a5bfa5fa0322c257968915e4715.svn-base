package strosoft.cms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import strosoft.app.common.*;
import strosoft.app.model.ListInfo;
import strosoft.app.service.GetListServiceHandlerContext;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.*;
import strosoft.cms.model.CmsChannelManager;
import strosoft.cms.model.CmsLanguageManager;

import net.sf.json.JSONObject;

/*
 * 取得数据表
 */
public class GetViewCmsContentListWidthPermissionServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		//重写获取参数方法
		GetListServiceHandlerContext context = this.getContext(request);
		ListInfo listInfo = this.createListInfo();
		// 数据列表
		ArrayList<LinkedHashMap<String, Object>> lstData = this
				.getDataList(context);
		listInfo.setDataList(lstData);
		// 当前页
		listInfo.setPageIndex(context.getPageIndex());
		// 分页大小
		listInfo.setPageSize(context.getPageSize());
		// 记录数
		listInfo.setRecordCount(context.getRecordCount());
		// 页数
		listInfo.setPageCount(context.getPageCount());
		this.processListInfo(listInfo);
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

		// System.out.println(sql);
		// int count = DbHelper.executeInt(sql);
		int count = MyBatisManager.getInstance().executeInt(sql);
		return count;
	}

	/*
	 * 取得数据列表
	 */
	private ArrayList<LinkedHashMap<String, Object>> getDataList(
			GetListServiceHandlerContext context) throws Exception {
		String sqlFields = "*";
		if (context.getFields() != null && !context.getFields().equals("")) {
			sqlFields = context.getFields();
		}
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
		String sql = String.format("select %s from %s%s%s%s%s", sqlFields,
				context.getTableName(), sqlWhere, sqlGroupBy, sqlOrderBy,
				sqlLimit);
		// ArrayList<HashMap<String, Object>> lstData = DbHelper
		// .executeHashMapList(sql);
		ArrayList<LinkedHashMap<String, Object>> lstData = MyBatisManager
				.getInstance().executeHashMapList(sql);

		return lstData;
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
	 * 取得参数,重写获取参数方法
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
		condition +=CmsChannelManager.getInstance().getPermissionByRole(request,"content");
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
		//判断是否有多语言支持
		Integer cmsSiteId = jData.getInt("cmsSiteId");
		Boolean isOnCmslaunguage = CmsLanguageManager.getInstance().getIsOnCmslaunguage(cmsSiteId);
		if(jData.containsKey("cmsLanguageCode") && isOnCmslaunguage){
			String cmsLanguageCode = jData.getString("cmsLanguageCode");
			condition = context.getCondition()+" and cms_language_code = '"+cmsLanguageCode+"'";
			context.setCondition(condition);
			context.setTableName("view_cms_content_language");
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
				int parentId = JsonHelper.getInt(jData, parameterName);
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
