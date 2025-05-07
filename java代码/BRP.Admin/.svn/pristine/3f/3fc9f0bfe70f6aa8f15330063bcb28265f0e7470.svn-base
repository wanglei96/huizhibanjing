package strosoft.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.IdNameTable;
import strosoft.app.util.HashMapHelper;

/*
 * Id基础表管理类
 */
public class IdNameTableManager extends IdDataManager {
	private static IdNameTableManager _instance;

	public static IdNameTableManager getInstance() {
		if (_instance == null) {
			_instance = new IdNameTableManager();
		}
		return _instance;
	}

	private IdNameTableManager() {
	}

	/*
	 * 检查登录账号是否存在
	 */
	public Boolean existsName(String tableName, String name) throws Exception {
		return MyBatisManager.getInstance().exists(tableName, "name", name);
	}

	/*
	 * 取得实体
	 */
	public IdNameTable getEntity(String tableName, int id) throws Exception {
		String sql = String.format("select * from %s where id=%s", tableName, id);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		IdNameTable theIdNameTable = toEntity(hmData);
		return theIdNameTable;
	}

	/*
	 * HashMap转为数据实体
	 */
	public IdNameTable toEntity(HashMap<String, Object> hmData) {
		IdNameTable theIdNameTable = new IdNameTable();
		//ID
		theIdNameTable.setId(HashMapHelper.getInt(hmData, "id"));
		//名称
		theIdNameTable.setName(HashMapHelper.getString(hmData, "name", "Name"));
		//描述
		theIdNameTable.setDescription(HashMapHelper.getString(hmData, "description"));
		//显示顺序
		int displayOrder = HashMapHelper.getInt(hmData, "display_order");
		theIdNameTable.setDisplayOrder(displayOrder);
		//是否启用
		theIdNameTable.setEnabled(HashMapHelper.getBoolean(hmData, "enabled"));
		//创建时间
		theIdNameTable.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time"));
		//修改时间
		theIdNameTable.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time"));
		return theIdNameTable;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<IdNameTable> getList() throws Exception {
		String sql = String.format("select * from %s order by display_order", this.getTableName());
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		ArrayList<IdNameTable> lstIdNameTable = new ArrayList<IdNameTable>();
		for (HashMap<String, Object> hmData : lstData) {
			IdNameTable theIdNameTable = toEntity(hmData);
			lstIdNameTable.add(theIdNameTable);
		}
		return lstIdNameTable;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, IdNameTable newIdNameTable) throws Exception {
		String sql = String.format(
				"insert into %s(name, description, display_order, enabled, create_time, update_time) values('%s', '%s', %s, %s, now(), now())",
				tableName, newIdNameTable.getName(), newIdNameTable.getDescription(), newIdNameTable.getDisplayOrder(),
				newIdNameTable.getEnabled() == null ? "null" : (newIdNameTable.getEnabled() ? "1" : "0"));
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 更新
	 */
	public int update(String tableName, IdNameTable newIdNameTable) throws Exception {
		String sql = String.format(
				"update %s set name='%s', description='%s', display_order=%s, enabled=%s, update_time=now() where id=%s", tableName,
				newIdNameTable.getName(), newIdNameTable.getDescription(), newIdNameTable.getDisplayOrder(),
				newIdNameTable.getEnabled() == null ? "null" : (newIdNameTable.getEnabled() ? "1" : "0"),
				newIdNameTable.getId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

}
