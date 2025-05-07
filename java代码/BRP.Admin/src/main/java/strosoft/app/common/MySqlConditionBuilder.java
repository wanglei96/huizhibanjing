package strosoft.app.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import strosoft.app.util.DateHelper;

public class MySqlConditionBuilder {

	private final ArrayList<String> lstCondition = new ArrayList<String>();

	/*
	 * 添加条件
	 */
	public void add(String condition) {
		if (condition != null && !condition.equals("")) {
			lstCondition.add(condition);
		}
	}

	/*
	 * 添加条件
	 */
	public void addFormat(String condition, Object... args) {
		if (condition != null && !condition.equals("")) {
			lstCondition.add(String.format(condition, args));
		}
	}

	/*
	 * 添加字段条件
	 */
	public void addFieldCondition(String fieldName, String keyword) {
		if (keyword != null && !keyword.equals("")) {
			String condition = String.format("%s like '%%%s%%'", fieldName.replace("'", "''"),
					keyword.replace("'", "''"));
			this.add(condition);
		}
	}

	/*
	 * 添加时间范围条件
	 */
	public void addTimeSpanCondition(String strBeginDate, String strEndDate, String timeFieldName) {
		if (strBeginDate != null && !strBeginDate.equals("")) {
			Date beginDate = DateHelper.toDateTime(strBeginDate);
			String condition = String.format("%s >='%s' ", timeFieldName,
					DateHelper.format(beginDate, "yyyy-MM-dd HH:mm:ss"));
			this.add(condition);
		}
		if (strEndDate != null && !strEndDate.equals("")) {
			Date endDate = DateHelper.toDateTime(strEndDate);
			String condition = String.format("%s <'%s' ", timeFieldName,
					DateHelper.format(DateHelper.addDays(endDate, 1), "yyyy-MM-dd HH:mm:ss"));
			this.add(condition);
		}
	}

	/*
	 * 添加时间范围条件
	 */
	public void addTimeSpanSecondCondition(Date beginDate, Date endDate, String timeFieldName) {
		if (beginDate != null) {
			String condition = String.format("%s >='%s' ", timeFieldName,
					DateHelper.format(beginDate, "yyyy-MM-dd HH:mm:ss"));
			this.add(condition);
		}
		if (endDate != null) {
			Date dtEndTime = new Date(endDate.getYear(), endDate.getMonth(), endDate.getDate(), endDate.getHours(),
					endDate.getMinutes(), endDate.getSeconds());
			String condition = String.format("%s <'%s' ", timeFieldName,
					DateHelper.format(DateHelper.addSeconds(dtEndTime, 1), "yyyy-MM-dd HH:mm:ss"));
			this.add(condition);
		}
	}

	/*
	 * 添加时间范围条件
	 */
	public void addTimeSpanMinuteCondition(Date beginDate, Date endDate, String timeFieldName) {
		if (beginDate != null) {
			Date dtBeginTime = new Date(beginDate.getYear(), beginDate.getMonth(), beginDate.getDate(),
					beginDate.getHours(), beginDate.getMinutes(), 0);
			String condition = String.format("%s >='%s' ", timeFieldName,
					DateHelper.format(dtBeginTime, "yyyy-MM-dd HH:mm:ss"));
			this.add(condition);
		}
		if (endDate != null) {
			Date dtEndTime = new Date(endDate.getYear(), endDate.getMonth(), endDate.getDate(), endDate.getHours(),
					endDate.getMinutes(), 0);
			String condition = String.format("%s <'%s' ", timeFieldName,
					DateHelper.format(DateHelper.addMinutes(dtEndTime, 1), "yyyy-MM-dd HH:mm:ss"));
			this.add(condition);
		}
	}

	/*
	 * 添加时间范围条件
	 */
	public void addTimeSpanHourCondition(Date beginDate, Date endDate, String timeFieldName) {
		if (beginDate != null) {
			Date dtBeginTime = new Date(beginDate.getYear(), beginDate.getMonth(), beginDate.getDate(),
					beginDate.getHours(), 0, 0);
			String condition = String.format("%s >='%s' ", timeFieldName,
					DateHelper.format(dtBeginTime, "yyyy-MM-dd HH:mm:ss"));
			this.add(condition);
		}
		if (endDate != null) {
			Date dtEndTime = new Date(endDate.getYear(), endDate.getMonth(), endDate.getDate(), endDate.getHours(), 0,
					0);
			String condition = String.format("%s <'%s' ", timeFieldName,
					DateHelper.format(DateHelper.addHours(dtEndTime, 1), "yyyy-MM-dd HH:mm:ss"));
			this.add(condition);
		}
	}

	/*
	 * 添加时间范围条件
	 */
	public void addTimeSpanMonthCondition(Date beginDate, Date endDate, String timeFieldName) {
		if (beginDate != null) {
			Date dtBeginTime = new Date(beginDate.getYear(), beginDate.getMonth(), 1, 0, 0, 0);
			String condition = String.format("%s >='%s' ", timeFieldName,
					DateHelper.format(dtBeginTime, "yyyy-MM-dd HH:mm:ss"));
			this.add(condition);
		}
		if (endDate != null) {
			Date dtEndTime = new Date(endDate.getYear(), endDate.getMonth(), 1, 0, 0, 0);
			String condition = String.format("%s <'%s' ", timeFieldName,
					DateHelper.format(DateHelper.addMonths(dtEndTime, 1), "yyyy-MM-dd HH:mm:ss"));
			this.add(condition);
		}
	}

	/*
	 * 添加Decimal范围条件
	 */
	public void addDecimalSpanCondition(BigDecimal beginValue, BigDecimal endValue, String fieldName) {
		if (beginValue != null) {
			String condition = String.format("ifnull(%s, 0) >=%s ", fieldName, beginValue);
			this.add(condition);
		}
		if (endValue != null) {
			String condition = String.format("ifnull(%s, 0) <=%s ", fieldName, endValue);
			this.add(condition);
		}
	}

	/*
	 * 添加int范围条件
	 */
	public void addIntSpanCondition(Integer beginValue, Integer endValue, String fieldName) {
		if (beginValue != null) {
			String condition = String.format("ifnull(%s, 0) >=%s ", fieldName, beginValue);
			this.add(condition);
		}
		if (endValue != null) {
			String condition = String.format("ifnull(%s, 0) <=%s ", fieldName, endValue);
			this.add(condition);
		}
	}

	/*
	 * 添加时间范围条件
	 */
	public void addTimeSpanCondition(String strBeginDate, String strEndDate) {
		this.addTimeSpanCondition(strBeginDate, strEndDate, "create_time");
	}

	/*
	 * 添加时间范围条件
	 */
	public void addTimeSpanCondition(Date beginDate, Date endDate) {
		this.addTimeSpanCondition(beginDate, endDate, "create_time");
	}

	/*
	 * 添加时间范围条件
	 */
	public void addTimeSpanCondition(Date beginDate, Date endDate, String timeFieldName) {
		String strBeginDate = DateHelper.format(beginDate, "yyyy-MM-dd");
		String strEndDate = DateHelper.format(endDate, "yyyy-MM-dd");
		this.addTimeSpanCondition(strBeginDate, strEndDate, timeFieldName);
	}

	/*
	 * 转换成字符串
	 */
	@Override
	public String toString() {
		StringBuffer sbCondition = new StringBuffer();
		for (String condition : lstCondition) {
			if (sbCondition.length() > 0) {
				sbCondition.append(" and ");
			}
			sbCondition.append("(" + condition + ")");
		}
		return sbCondition.toString();
	}

	/*
	 * 添加相等条件
	 */
	public void addEqualCondition(String fieldName, Integer value) {
		if (value != null) {
			this.addFormat("%s = %s", fieldName, value);
		}
	}

	/*
	 * 添加相等条件
	 */
	public void addEqualCondition(String fieldName, Boolean value) {
		if (value != null) {
			this.addFormat("ifnull(%s, 0) = %s", fieldName, value ? 1 : 0);
		}
	}

	/*
	 * 添加相等条件
	 */
	public void addEqualCondition(String fieldName, String value) {
		if (value != null && !value.equals("")) {
			this.addFormat("%s = '%s'", fieldName, value.replaceAll("'", "''"));
		}
	}

	/**
	 * 添加相等条件
	 * 
	 * @param fieldName
	 * @param value
	 * @param isBoolean
	 */
	public void addEqualCondition(String fieldName, String value, Boolean isBoolean) {
		if (value != null && !value.equals("")) {
			if (isBoolean) {
				this.addFormat("ifnull(%s, 0) = %s", fieldName, value.replace("'", "''"));
			} else {
				this.addFormat("%s = '%s'", fieldName, value.replaceAll("'", "''"));
			}
		}
	}

	/*
	 * 添加不相等条件
	 */
	public void addNotEqualCondition(String fieldName, Integer value) {
		if (value != null) {
			this.addFormat("%s <> %s", fieldName, value);
		}
	}

	/*
	 * 添加不相等条件
	 */
	public void addNotEqualCondition(String fieldName, Boolean value) {
		if (value != null) {
			this.addFormat("ifnull(%s, 0) <> %s", fieldName, value ? 1 : 0);
		}
	}

	/*
	 * 添加不相等条件
	 */
	public void addNotEqualCondition(String fieldName, String value) {
		if (value != null) {
			this.addFormat("%s <> '%s'", fieldName, value.replace("'", "''"));
		}
	}

	/*
	 * 添加不相等条件
	 */
	public void addNotEqualCondition(String fieldName, String value, Boolean isBoolean) {
		if (value != null) {
			if (isBoolean) {
				this.addFormat("ifnull(%s, 0) <> %s", fieldName, value.replace("'", "''"));
			}
			else {
				this.addFormat("%s <> '%s'", fieldName, value.replace("'", "''"));
			}
		}
	}

	/*
	 * 添加Like条件
	 */
	public void addLikeCondition(String fieldName, String value) {
		if (value != null && !value.equals("")) {
			this.addFormat("%s like '%%%s%%'", fieldName, value.replace("'", "''"));
		}
	}

	/*
	 * 添加In条件
	 */
	public void addInCondition(String fieldName, String values) {
		if (values == null || values.equals("")) {
			return;
		}
		String[] arrValue = values.split(",");
		ArrayList<String> lstValue = new ArrayList<String>();
		for (String value : arrValue) {
			lstValue.add(value);
		}
		addInCondition(fieldName, lstValue);
	}

	/*
	 * 添加In条件
	 */
	public void addInCondition(String fieldName, ArrayList<String> lstValue) {
		if (lstValue == null || lstValue.size() == 0) {
			return;
		}
		String ids = getValuesString(lstValue);
		this.addFormat("%s in (%s)", fieldName, ids);
	}

	/*
	 * 添加In条件
	 */
	public void addInCondition(String fieldName, List<Integer> lstValue) {
		if (lstValue == null || lstValue.size() == 0) {
			return;
		}
		String ids = getValuesString(lstValue);
		this.addFormat("%s in (%s)", fieldName, ids);
	}

	/*
	 * 添加not in条件
	 */
	public void addNotInCondition(String fieldName, ArrayList<String> lstValue) {
		if (lstValue == null || lstValue.size() == 0) {
			return;
		}
		String ids = getValuesString(lstValue);
		this.addFormat("%s not in (%s)", fieldName, ids);
	}

	/*
	 * 添加not in条件
	 */
	public void addNotInCondition(String fieldName, String values) {
		if (values == null || values.equals("")) {
			return;
		}
		String[] arrValue = values.split(",");
		ArrayList<String> lstValue = new ArrayList<String>();
		for (String value : arrValue) {
			lstValue.add(value);
		}
		addNotInCondition(fieldName, lstValue);
	}

	/*
	 * 添加not in条件
	 */
	public void addNotInCondition(String fieldName, List<Integer> lstValue) {
		if (lstValue == null || lstValue.size() == 0) {
			return;
		}
		String ids = getValuesString(lstValue);
		this.addFormat("%s not in (%s)", fieldName, ids);
	}

	/*
	 * 取得逗号分隔的值
	 */
	public String getValuesString(ArrayList<String> lstValue) {
		StringBuffer sbValues = new StringBuffer();
		for (int i = 0; i < lstValue.size(); i++) {
			if (i > 0) {
				sbValues.append(",");
			}
			sbValues.append(String.format("'%s'", lstValue.get(i).replace("'", "''")));
		}
		return sbValues.toString();
	}

	/*
	 * 取得逗号分隔的值
	 */
	public String getValuesString(List<Integer> lstValue) {
		StringBuffer sbValues = new StringBuffer();
		for (int i = 0; i < lstValue.size(); i++) {
			if (i > 0) {
				sbValues.append(",");
			}
			sbValues.append(String.format("'%s'", lstValue.get(i)));
		}
		return sbValues.toString();
	}

	/*
	 * 添加Code类型树条件
	 */
	public void addCodeTreeCondition(String fieldName, String treeTableName, String parentCode) throws Exception {
		this.addTreeCondition(fieldName, treeTableName, "code", "parent_code", parentCode);
	}

	/*
	 * 添加ID类型树条件
	 */
	public void addIDTreeCondition(String fieldName, String treeTableName, Integer parentID) throws Exception {
		if (parentID == null) {
			return;
		}
		this.addTreeCondition(fieldName, treeTableName, "id", "parent_id", parentID.toString(), "0");
	}

	/*
	 * 添加Code类型树条件
	 */
	public void addCodeTreeCondition(String fieldName, String treeTableName, ArrayList<String> lstParentCode)
			throws Exception {
		this.addTreeCondition(fieldName, treeTableName, "code", "parent_code", lstParentCode);
	}

	/*
	 * 添加ID类型树条件
	 */
	public void addIDTreeCondition(String fieldName, String treeTableName, ArrayList<String> lstParentID)
			throws Exception {
		if (lstParentID == null || lstParentID.size() == 0) {
			return;
		}
		ArrayList<String> lstParentValue = new ArrayList<String>();
		for (String parentID : lstParentID) {
			lstParentValue.add(parentID);
		}
		this.addTreeCondition(fieldName, treeTableName, "id", "parent_id", lstParentValue, "0");
	}

	/*
	 * 添加树条件
	 */
	public void addTreeCondition(String fieldName, String treeTableName, String keyFieldName, String parentKeyFieldName,
			ArrayList<String> lstParentValue) throws Exception {
		this.addTreeCondition(fieldName, treeTableName, keyFieldName, parentKeyFieldName, lstParentValue, "");
	}

	/*
	 * 添加树条件
	 */
	public void addTreeCondition(String fieldName, String treeTableName, String keyFieldName, String parentKeyFieldName,
			ArrayList<String> lstParentValue, String nullValue) throws Exception {
		if (lstParentValue == null || lstParentValue.size() == 0) {
			return;
		}
		String treeCondition = this.getTreeCondition(fieldName, treeTableName, keyFieldName, parentKeyFieldName,
				lstParentValue, nullValue);
		this.add(treeCondition);
	}

	/*
	 * 取得ID树条件
	 */
	public String getIDTreeCondition(String fieldName, String treeTableName, ArrayList<String> lstParentID)
			throws Exception {
		if (lstParentID == null || lstParentID.size() == 0) {
			return null;
		}
		ArrayList<String> lstParentValue = new ArrayList<String>();
		for (String parentID : lstParentID) {
			lstParentValue.add(parentID);
		}
		return getTreeCondition(fieldName, treeTableName, "id", "parent_id", lstParentValue, "0");
	}

	/*
	 * 取得树查询条件
	 */
	public String getTreeCondition(String fieldName, String treeTableName, String keyFieldName,
			String parentKeyFieldName, ArrayList<String> lstParentValue, String nullValue) throws Exception {
		StringBuffer sbCondition = new StringBuffer();
		for (String parentValue : lstParentValue) {
			if (sbCondition.length() > 0) {
				sbCondition.append(",");
			}
			String parentValuesConditionString = this.getParentValuesConditionString(treeTableName, keyFieldName,
					parentKeyFieldName, parentValue);
			sbCondition.append(parentValuesConditionString);
		}
		String parentValuesCondition = sbCondition.toString();
		String treeCondition = String.format("ifnull(%s,'%s') in (%s)", fieldName, nullValue, parentValuesCondition);
		return treeCondition;
	}

	/*
	 * 添加树条件
	 */
	public void addTreeCondition(String fieldName, String treeTableName, String keyFieldName, String parentKeyFieldName,
			String parentValue) throws Exception {
		this.addTreeCondition(fieldName, treeTableName, keyFieldName, parentKeyFieldName, parentValue, "");
	}

	/*
	 * 添加父节点条件
	 */
	public void addTreeCondition(String fieldName, String treeTableName, String keyFieldName, String parentKeyFieldName,
			String parentValue, String nullValue) throws Exception {
		if (parentValue == null || parentValue.equals("")) {
			return;
		}
		String parentValuesConditionString = this.getParentValuesConditionString(treeTableName, keyFieldName,
				parentKeyFieldName, parentValue);
		this.addFormat("ifnull(%s,'%s') in (%s)", fieldName, nullValue, parentValuesConditionString);
	}

	/*
	 * 取得子节点ID
	 */
	private String getChildParentValuesConditionString(ArrayList<LinkedHashMap<String, Object>> dtAll,
			String keyFieldName, String parentKeyFieldName, String parentValue) {
		StringBuffer sbCodeConditionString = new StringBuffer();
		for (HashMap<String, Object> hmCurrent : dtAll) {
			Object objParentValue = hmCurrent.get(parentKeyFieldName);

			String strParentValue = "";
			if (objParentValue != null) {
				strParentValue = objParentValue.toString();
			}
			if (!strParentValue.equals(parentValue)) {
				continue;
			}
			String currentKeyValue = hmCurrent.get(keyFieldName).toString();
			if (currentKeyValue == null) {
				currentKeyValue = "";
			}
			if (sbCodeConditionString.length() > 0) {
				sbCodeConditionString.append(",");
			}
			sbCodeConditionString.append("'" + currentKeyValue + "'");
			// 递归添加子节点
			String childCodeConditionString = this.getChildParentValuesConditionString(dtAll, keyFieldName,
					parentKeyFieldName, currentKeyValue);
			if (childCodeConditionString != null && !childCodeConditionString.equals("")) {
				sbCodeConditionString.append(",");
				sbCodeConditionString.append(childCodeConditionString);
			}
		}
		return sbCodeConditionString.toString();
	}

	/*
	 * 取得逗号分隔的Code字符串
	 */
	private String getParentValuesConditionString(String tableName, String keyFieldName, String parentKeyFieldName,
			String parentValue) throws Exception {
		String parentValuesConditionString = String.format("'%s'", parentValue);
		ArrayList<LinkedHashMap<String, Object>> dtAll = MyBatisManager.getInstance().getHashMapList(tableName);
		String childParentValuesConditionString = this.getChildParentValuesConditionString(dtAll, keyFieldName,
				parentKeyFieldName, parentValue);
		if (childParentValuesConditionString != null && !childParentValuesConditionString.equals("")) {
			parentValuesConditionString += "," + childParentValuesConditionString;
		}
		return parentValuesConditionString;
	}
}
