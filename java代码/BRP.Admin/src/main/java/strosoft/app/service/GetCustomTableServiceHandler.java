package strosoft.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.MyBatisManager;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.data.SysBasicTableField;
import strosoft.sys.model.SysBasicTableFieldManager;

import net.sf.json.JSONObject;

public class GetCustomTableServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取request参数字符串
		String data = request.getParameter("data");
		System.out.println(data);
		//将参数字符串转为JSONObject类型
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "table_name",
				"tableName", "TableName");
		//查找主键
		String primaryKeyCondition = "table_name = '"+tableName+"' AND is_primary_key = 1";
		List<SysBasicTableField> sysBasicTableFields = SysBasicTableFieldManager.getInstance().getList(primaryKeyCondition);
		if(sysBasicTableFields.size()==0){
			this.writeErrorResponse(response, "基础表没有设置主键");
			return;
		}
		//获取主键名
		String primaryKeyName = sysBasicTableFields.get(0).getFieldName();
		//获取查询条件
		String primaryValue = JsonHelper.getString(jData,primaryKeyName);
		String sql = "select * from "+tableName+" where "+primaryKeyName+" = '"+primaryValue+"'";
		//按照主键查询数据列表
		ArrayList<LinkedHashMap<String, Object>> list = MyBatisManager.getInstance().executeHashMapList(sql);
		if(list.size()==0){
			this.writeErrorResponse(response, "没有找到该条数据");
			return;
		}
		//把字段名转为属性名
		HashMap<String, Object> result = new HashMap<String, Object>();
		Iterator<Entry<String, Object>> iter = list.get(0).entrySet().iterator(); 
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			String key = StringHelper.getParameterName((String) entry.getKey()); 
			Object val = entry.getValue();
			result.put(key, val);
		}
		this.writeSuccessResponse(response,result);
	}

}
