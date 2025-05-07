package strosoft.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.CompanyIdNameTable;
import strosoft.app.util.HashMapHelper;

/*
 * Id基础表管理类
 */
@SuppressWarnings("unchecked")
public class CompanyIdNameTableManager extends IdDataManager {
	private static CompanyIdNameTableManager _instance;

	public static CompanyIdNameTableManager getInstance() {
		if (_instance == null) {
			_instance = new CompanyIdNameTableManager();
		}
		return _instance;
	}

	private CompanyIdNameTableManager() {
	}

	/*
	 * 检查登录账号是否存在
	 */
	public Boolean existsName(int companyId, String tableName, String name) throws Exception {
		return MyBatisManager.getInstance().exists(tableName, "name", name, "company_id=" + companyId);
	}

	/*
	 * 取得实体
	 */
	public CompanyIdNameTable getEntity(String tableName, int id) throws Exception {
		String sql = String.format("select * from %s where id=%s", tableName, id);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		CompanyIdNameTable theIdNameTable = toEntity(hmData);
		return theIdNameTable;
	}

	/*
	 * HashMap转为数据实体
	 */
	public CompanyIdNameTable toEntity(HashMap<String, Object> hmData) {
		CompanyIdNameTable theCompanyIdNameTable = new CompanyIdNameTable();
		theCompanyIdNameTable.setId(HashMapHelper.getInt(hmData, "id"));
		// 名称
		theCompanyIdNameTable.setName(HashMapHelper.getString(hmData, "name", "Name"));
		// 描述
		theCompanyIdNameTable.setDescription(HashMapHelper.getString(hmData, "description", "Description"));
		// 显示顺序
		int displayOrder = HashMapHelper.getInt(hmData, "display_order", "display_order", "displayOrder");
		theCompanyIdNameTable.setDisplayOrder(displayOrder);
		// 站点ID
		int companyId = HashMapHelper.getInt(hmData, "company_id");
		theCompanyIdNameTable.setCompanyId(companyId);
		// 创建时间
		theCompanyIdNameTable.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time"));
		// 修改时间
		theCompanyIdNameTable.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time"));
		return theCompanyIdNameTable;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<CompanyIdNameTable> getList(int companyId) throws Exception {
		String sql = String.format("select * from %s where company_id=%s order by display_order", this.getTableName(),
				companyId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		ArrayList<CompanyIdNameTable> lstIdNameTable = new ArrayList<CompanyIdNameTable>();
		for (HashMap<String, Object> hmData : lstData) {
			CompanyIdNameTable theIdNameTable = toEntity(hmData);
			lstIdNameTable.add(theIdNameTable);
		}
		return lstIdNameTable;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, CompanyIdNameTable newCompanyIdNameTable) throws Exception {
		String sql = String.format(
				"insert into %s(name, description, display_order, company_id,create_time,update_time) values('%s', '%s', %s, %s, now(), now())",
				tableName, newCompanyIdNameTable.getName(), newCompanyIdNameTable.getDescription(),
				newCompanyIdNameTable.getDisplayOrder(), newCompanyIdNameTable.getCompanyId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 更新
	 */
	public int update(String tableName, CompanyIdNameTable newCompanyIdNameTable) throws Exception {
		String sql = String.format(
				"update %s set name='%s', description='%s', display_order=%s, company_id=%s update_time=now() where id=%s",
				tableName, newCompanyIdNameTable.getName(), newCompanyIdNameTable.getDescription(),
				newCompanyIdNameTable.getDisplayOrder(), newCompanyIdNameTable.getCompanyId(),
				newCompanyIdNameTable.getId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

}
