package strosoft.app.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.CodeDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.data.CodeNameTable;
import strosoft.app.util.HashMapHelper;
import strosoft.app.util.MySqlHelper;

import net.sf.json.JSONArray;

/*
 * code基础表管理类
 */
public class CodeNameTableManager extends CodeDataManager {
	private static CodeNameTableManager _instance;

	public static CodeNameTableManager getInstance() {
		if (_instance == null) {
			_instance = new CodeNameTableManager();
		}
		return _instance;
	}

	private CodeNameTableManager() {
	}

	/*
	 * 检查登录名称是否存在
	 */
	public Boolean existsName(String tableName, String name) throws Exception {
		return MyBatisManager.getInstance().exists(tableName, "name", name);
	}

	/*
	 * 取得实体
	 */
	public CodeNameTable getEntity(String tableName, String code) throws Exception {
		String sql = String.format("select * from %s where code='%s'", tableName, code);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		CodeNameTable theCodeNameTable = toEntity(hmData);
		return theCodeNameTable;
	}

	/*
	 * HashMap转为数据实体
	 */
	public CodeNameTable toEntity(HashMap<String, Object> hmData) {
		CodeNameTable theCodeNameTable = new CodeNameTable();
		theCodeNameTable.setCode(HashMapHelper.getString(hmData, "code"));
		theCodeNameTable.setName(HashMapHelper.getString(hmData, "name", "Name"));
		theCodeNameTable.setDescription(HashMapHelper.getString(hmData, "description"));
		theCodeNameTable.setEnabled(HashMapHelper.getBoolean(hmData, "enabled"));
		theCodeNameTable.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time"));
		theCodeNameTable.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time"));
		int displayOrder = HashMapHelper.getInt(hmData, "display_order");
		theCodeNameTable.setDisplayOrder(displayOrder);
		return theCodeNameTable;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<CodeNameTable> getList() throws Exception {
		String sql = String.format("select * from %s order by display_order", this.getTableName());
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		ArrayList<CodeNameTable> lstIdNameTable = new ArrayList<CodeNameTable>();
		for (HashMap<String, Object> hmData : lstData) {
			CodeNameTable theCodeNameTable = toEntity(hmData);
			lstIdNameTable.add(theCodeNameTable);
		}
		return lstIdNameTable;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, CodeNameTable newCodeNameTable) throws Exception {
		String sql = String.format(
				"insert into %s(code, name, description, display_order,enabled,create_time,update_time) values('%s', '%s', '%s', %s, %s, now(), now())",
				tableName, newCodeNameTable.getCode(), newCodeNameTable.getName(), newCodeNameTable.getDescription(),
				newCodeNameTable.getDisplayOrder(),
				newCodeNameTable.getEnabled() == null ? "null" : (newCodeNameTable.getEnabled() ? "1" : "0"));
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	public int update(String tableName, CodeNameTable newCodeNameTable) throws Exception {
		String sql = String.format(
				"update %s set name='%s', description='%s', display_order=%s, enabled=%s, update_time=now() where code='%s'",
				tableName, newCodeNameTable.getName(), newCodeNameTable.getDescription(),
				newCodeNameTable.getDisplayOrder(),
				newCodeNameTable.getEnabled() == null ? "null" : (newCodeNameTable.getEnabled() ? "1" : "0"),
				newCodeNameTable.getCode());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

}
