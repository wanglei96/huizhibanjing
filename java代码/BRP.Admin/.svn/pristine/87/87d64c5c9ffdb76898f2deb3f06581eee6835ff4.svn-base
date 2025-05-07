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

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.data.CmsIdNameTable;
import strosoft.app.util.HashMapHelper;
import strosoft.app.util.MySqlHelper;

import net.sf.json.JSONArray;

/*
 * Id基础表管理类
 */
@SuppressWarnings("unchecked")
public class CmsIdNameTableManager extends IdDataManager {
	private static CmsIdNameTableManager _instance;

	public static CmsIdNameTableManager getInstance() {
		if (_instance == null) {
			_instance = new CmsIdNameTableManager();
		}
		return _instance;
	}

	private CmsIdNameTableManager() {
	}

	/*
	 * 检查登录账号是否存在
	 */
	public Boolean existsName(int cmsSiteId, String tableName, String name)
			throws Exception {
		return MyBatisManager.getInstance().exists(tableName, "name", name,
				"cms_site_id=" + cmsSiteId);
	}

	/*
	 * 取得实体
	 */
	public CmsIdNameTable getEntity(String tableName, int id) throws Exception {
		String sql = String.format("select * from %s where id=%s", tableName,
				id);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager
				.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		CmsIdNameTable theIdNameTable = toEntity(hmData);
		return theIdNameTable;
	}

	/*
	 * HashMap转为数据实体
	 */
	public CmsIdNameTable toEntity(HashMap<String, Object> hmData) {
		CmsIdNameTable theCmsIdNameTable = new CmsIdNameTable();
		theCmsIdNameTable.setId(HashMapHelper.getInt(hmData, "id"));
		//名称
		theCmsIdNameTable.setName(HashMapHelper.getString(hmData, "name", "Name"));
		//描述
		theCmsIdNameTable.setDescription(HashMapHelper.getString(hmData,
				"description", "Description"));
		//显示顺序
		int displayOrder = HashMapHelper.getInt(hmData, "display_order",
				"display_order", "displayOrder");
		theCmsIdNameTable.setDisplayOrder(displayOrder);
		//站点ID
		int cmsSiteId = HashMapHelper.getInt(hmData, "cms_site_id");
		theCmsIdNameTable.setCmsSiteId(cmsSiteId);
		//创建时间
		theCmsIdNameTable.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time"));
		//修改时间
		theCmsIdNameTable.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time"));
		return theCmsIdNameTable;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<CmsIdNameTable> getList(int cmsSiteId) throws Exception {
		String sql = String.format(
				"select * from %s where cms_site_id=%s order by display_order",
				this.getTableName(), cmsSiteId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager
				.getInstance().executeHashMapList(sql);
		ArrayList<CmsIdNameTable> lstIdNameTable = new ArrayList<CmsIdNameTable>();
		for (HashMap<String, Object> hmData : lstData) {
			CmsIdNameTable theIdNameTable = toEntity(hmData);
			lstIdNameTable.add(theIdNameTable);
		}
		return lstIdNameTable;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, CmsIdNameTable newIdNameTable)
			throws Exception {
		String sql = String
				.format("insert into %s(name, description, display_order, cms_site_id,create_time,update_time) values('%s', '%s', %s, %s, now(), now())",
						tableName, newIdNameTable.getName(),
						newIdNameTable.getDescription(),
						newIdNameTable.getDisplayOrder(),
						newIdNameTable.getCmsSiteId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 更新
	 */
	public int update(String tableName, CmsIdNameTable newIdNameTable)
			throws Exception {
		String sql = String
				.format("update %s set name='%s', description='%s', display_order=%s, cms_site_id=%s update_time=now() where id=%s",
						tableName, newIdNameTable.getName(),
						newIdNameTable.getDescription(),
						newIdNameTable.getDisplayOrder(),
						newIdNameTable.getCmsSiteId(), newIdNameTable.getId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

}
