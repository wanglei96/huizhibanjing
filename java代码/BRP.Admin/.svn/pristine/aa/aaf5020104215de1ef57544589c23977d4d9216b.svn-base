package strosoft.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import strosoft.app.common.CodeDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.CmsCodeNameTable;
import strosoft.app.util.HashMapHelper;

/*
 * code基础表管理类
 */
public class CmsCodeNameTableManager extends CodeDataManager {
	private static CmsCodeNameTableManager _instance;

	public static CmsCodeNameTableManager getInstance() {
		if (_instance == null) {
			_instance = new CmsCodeNameTableManager();
		}
		return _instance;
	}

	private CmsCodeNameTableManager() {
	}

	/*
	 * 检查登录名称是否存在
	 */
	public Boolean existsName(int cmsSiteId, String tableName, String name)
			throws Exception {
		return MyBatisManager.getInstance().exists(tableName, "name", name,
				"cms_site_id=" + cmsSiteId);
	}

	/*
	 * 取得实体
	 */
	public CmsCodeNameTable getEntity(int cmsSiteId, String tableName,
			String code) throws Exception {
		String sql = String.format(
				"select * from %s where code='%s' and cms_site_id = %s",
				tableName, code, cmsSiteId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager
				.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		CmsCodeNameTable theCodeNameTable = toEntity(hmData);
		return theCodeNameTable;
	}

	/*
	 * HashMap转为数据实体
	 */
	public CmsCodeNameTable toEntity(HashMap<String, Object> hmData) {
		CmsCodeNameTable theCodeNameTable = new CmsCodeNameTable();
		//编码
		theCodeNameTable.setCode(HashMapHelper.getString(hmData, "code"));
		//名称
		theCodeNameTable.setName(HashMapHelper
				.getString(hmData, "name", "Name"));
		//描述
		theCodeNameTable.setDescription(HashMapHelper.getString(hmData,
				"description"));
		//显示顺序
		int displayOrder = HashMapHelper.getInt(hmData, "display_order");
		theCodeNameTable.setDisplayOrder(displayOrder);
		//创建时间
		theCodeNameTable.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time"));
		//修改时间
		theCodeNameTable.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time"));
		int cmsSiteId = HashMapHelper.getInt(hmData, "cms_site_id");
		theCodeNameTable.setCmsSiteId(cmsSiteId);
		return theCodeNameTable;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<CmsCodeNameTable> getList(int cmsSiteId) throws Exception {
		String sql = String.format(
				"select * from %s where cms_site_id=%s order by display_order",
				this.getTableName(), cmsSiteId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager
				.getInstance().executeHashMapList(sql);
		ArrayList<CmsCodeNameTable> lstIdNameTable = new ArrayList<CmsCodeNameTable>();
		for (HashMap<String, Object> hmData : lstData) {
			CmsCodeNameTable theCodeNameTable = toEntity(hmData);
			lstIdNameTable.add(theCodeNameTable);
		}
		return lstIdNameTable;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, CmsCodeNameTable newCodeNameTable)
			throws Exception {
		String sql = String
				.format("insert into %s(code, name, description, display_order, cms_site_id, create_time, update_time) values('%s', '%s', '%s', %s, %s, now(), now())",
						tableName, newCodeNameTable.getCode(),
						newCodeNameTable.getName(),
						newCodeNameTable.getDescription(),
						newCodeNameTable.getDisplayOrder(),
						newCodeNameTable.getCmsSiteId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 更新
	 */
	public int update(String tableName, CmsCodeNameTable newCodeNameTable)
			throws Exception {
		String sql = String
				.format("update %s set name='%s', description='%s', display_order=%s, cms_site_id=%s, update_time=now() where code='%s'",
						tableName, newCodeNameTable.getName(),
						newCodeNameTable.getDescription(),
						newCodeNameTable.getDisplayOrder(),
						newCodeNameTable.getCmsSiteId(),
						newCodeNameTable.getCode());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

}
