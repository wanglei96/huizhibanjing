package strosoft.app.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import strosoft.app.common.*;
import strosoft.app.util.*;
import strosoft.sys.data.SysBasicTableField;
import strosoft.sys.model.SysBasicTableFieldManager;

import net.sf.json.JSONObject;

/*
 * 取得自定义基础表
 */
public class GetCustomTableListServiceHandler extends GetListServiceHandler {
	/*
	 * 取得查询条件
	 */
	protected String getCondition(HttpServletRequest request) throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "table_name",
				"tableName", "TableName");
		String condition = JsonHelper
				.getString(jData, "condition", "Condition");
		MySqlConditionBuilder conditionBuilder = new MySqlConditionBuilder();
		if (condition != null && !condition.equals("")) {
			conditionBuilder.add(condition);
		}
		//查找查询字段
		String conditionStr = "table_name = '"+tableName+"' AND show_in_query = 1 AND `enable`=1";
		List<SysBasicTableField> sysBasicTableFields = SysBasicTableFieldManager.getInstance().getList(conditionStr);
		for(int i=0;i<sysBasicTableFields.size();i++){
			SysBasicTableField currentField = sysBasicTableFields.get(i);
			//查询匹配都为like
			String fieldName = currentField.getFieldName();
			String parameterName = StringHelper.getParameterName(fieldName);
			String value = JsonHelper.getString(jData, parameterName);
			conditionBuilder.addLikeCondition(fieldName, value);
		}
		condition = conditionBuilder.toString();
		return condition;
	}

}
