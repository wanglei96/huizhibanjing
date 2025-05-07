package strosoft.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import strosoft.app.common.CodeDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.CompanyCodeNameTable;
import strosoft.app.util.HashMapHelper;

/*
 * code基础表管理类
 */
public class CompanyCodeNameTableManager extends CodeDataManager {
	private static CompanyCodeNameTableManager _instance;

	public static CompanyCodeNameTableManager getInstance() {
		if (_instance == null) {
			_instance = new CompanyCodeNameTableManager();
		}
		return _instance;
	}

	private CompanyCodeNameTableManager() {
	}

	/*
	 * 检查登录名称是否存在
	 */
	public Boolean existsName(int companyId, String tableName, String name) throws Exception {
		return MyBatisManager.getInstance().exists(tableName, "name", name, "company_id=" + companyId);
	}

	/*
	 * 取得实体
	 */
	public CompanyCodeNameTable getEntity(int companyId, String tableName, String code) throws Exception {
		String sql = String.format("select * from %s where code='%s' and company_id = %s", tableName, code, companyId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		CompanyCodeNameTable theCodeNameTable = toEntity(hmData);
		return theCodeNameTable;
	}

	/*
	 * HashMap转为数据实体
	 */
	public CompanyCodeNameTable toEntity(HashMap<String, Object> hmData) {
		CompanyCodeNameTable theCodeNameTable = new CompanyCodeNameTable();
		// 编码
		theCodeNameTable.setCode(HashMapHelper.getString(hmData, "code"));
		// 名称
		theCodeNameTable.setName(HashMapHelper.getString(hmData, "name", "Name"));
		// 描述
		theCodeNameTable.setDescription(HashMapHelper.getString(hmData, "description"));
		// 显示顺序
		int displayOrder = HashMapHelper.getInt(hmData, "display_order");
		theCodeNameTable.setDisplayOrder(displayOrder);
		// 创建时间
		theCodeNameTable.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time"));
		// 修改时间
		theCodeNameTable.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time"));
		int companyId = HashMapHelper.getInt(hmData, "company_id");
		theCodeNameTable.setCompanyId(companyId);
		return theCodeNameTable;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<CompanyCodeNameTable> getList(int companyId) throws Exception {
		String sql = String.format("select * from %s where company_id=%s order by display_order", this.getTableName(),
				companyId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		ArrayList<CompanyCodeNameTable> lstIdNameTable = new ArrayList<CompanyCodeNameTable>();
		for (HashMap<String, Object> hmData : lstData) {
			CompanyCodeNameTable theCodeNameTable = toEntity(hmData);
			lstIdNameTable.add(theCodeNameTable);
		}
		return lstIdNameTable;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, CompanyCodeNameTable newCompanyCodeNameTable) throws Exception {
		String sql = String.format(
				"insert into %s(code, name, description, display_order, company_id, create_time, update_time) values('%s', '%s', '%s', %s, %s, now(), now())",
				tableName, newCompanyCodeNameTable.getCode(), newCompanyCodeNameTable.getName(),
				newCompanyCodeNameTable.getDescription(), newCompanyCodeNameTable.getDisplayOrder(),
				newCompanyCodeNameTable.getCompanyId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 更新
	 */
	public int update(String tableName, CompanyCodeNameTable newCompanyCodeNameTable) throws Exception {
		String sql = String.format(
				"update %s set name='%s', description='%s', display_order=%s, company_id=%s, update_time=now() where code='%s'",
				tableName, newCompanyCodeNameTable.getName(), newCompanyCodeNameTable.getDescription(),
				newCompanyCodeNameTable.getDisplayOrder(), newCompanyCodeNameTable.getCompanyId(),
				newCompanyCodeNameTable.getCode());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

}
