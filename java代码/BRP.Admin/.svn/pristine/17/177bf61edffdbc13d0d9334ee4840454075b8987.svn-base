package strosoft.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import strosoft.app.common.CodeDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.CompanyCodeNameTree;
import strosoft.app.util.HashMapHelper;

/*
 * CompanyCode树型表管理类
 */
public class CompanyCodeNameTreeManager extends CodeDataManager {
	private static CompanyCodeNameTreeManager _instance;

	public static CompanyCodeNameTreeManager getInstance() {
		if (_instance == null) {
			_instance = new CompanyCodeNameTreeManager();
		}
		return _instance;
	}

	private CompanyCodeNameTreeManager() {
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
	public CompanyCodeNameTree getEntity(int companyId, String tableName, String code) throws Exception {
		String sql = String.format("select * from %s where code='%s' and company_id=%s", tableName, code, companyId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		CompanyCodeNameTree theCmsCodeNameTree = toEntity(hmData);
		return theCmsCodeNameTree;
	}

	/*
	 * HashMap转为数据实体
	 */
	public CompanyCodeNameTree toEntity(HashMap<String, Object> hmData) {
		CompanyCodeNameTree theCmsCodeNameTree = new CompanyCodeNameTree();
		// 编码
		theCmsCodeNameTree.setCode(HashMapHelper.getString(hmData, "code"));
		// 名称
		theCmsCodeNameTree.setName(HashMapHelper.getString(hmData, "name", "Name"));
		// 父节点编码
		theCmsCodeNameTree.setParentCode(HashMapHelper.getString(hmData, "parent_code"));
		// 描述
		theCmsCodeNameTree.setDescription(HashMapHelper.getString(hmData, "description"));
		// 显示顺序
		int displayOrder = HashMapHelper.getInt(hmData, "display_order");
		theCmsCodeNameTree.setDisplayOrder(displayOrder);
		// 创建时间
		theCmsCodeNameTree.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time"));
		// 修改时间
		theCmsCodeNameTree.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time"));
		// 站点id
		int companyId = HashMapHelper.getInt(hmData, "company_id");
		theCmsCodeNameTree.setCompanyId(companyId);
		return theCmsCodeNameTree;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<CompanyCodeNameTree> getList(int companyId) throws Exception {
		String sql = String.format("select * from %s where company_id=%s order by display_order", this.getTableName(),
				companyId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		ArrayList<CompanyCodeNameTree> lstCmsCodeNameTable = new ArrayList<CompanyCodeNameTree>();
		for (HashMap<String, Object> hmData : lstData) {
			CompanyCodeNameTree theCodeNameTree = toEntity(hmData);
			lstCmsCodeNameTable.add(theCodeNameTree);
		}
		return lstCmsCodeNameTable;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, CompanyCodeNameTree newCompanyCodeNameTree) throws Exception {
		String sql = String.format(
				"insert into %s(code, name, parent_code, description, display_order, company_id, create_time, update_time) values('%s', '%s', '%s', '%s', %s, %s,now(), now())",
				tableName, newCompanyCodeNameTree.getCode(), newCompanyCodeNameTree.getName(),
				newCompanyCodeNameTree.getParentCode(), newCompanyCodeNameTree.getDescription(),
				newCompanyCodeNameTree.getDisplayOrder(), newCompanyCodeNameTree.getCompanyId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	public int update(String tableName, CompanyCodeNameTree newCompanyCodeNameTree) throws Exception {
		String sql = String.format(
				"update %s set name='%s', parent_code='%s',description='%s', display_order=%s, company_id=%s, update_time=now() where code='%s' and company_id=%s",
				tableName, newCompanyCodeNameTree.getName(), newCompanyCodeNameTree.getParentCode(),
				newCompanyCodeNameTree.getDescription(), newCompanyCodeNameTree.getDisplayOrder(),
				newCompanyCodeNameTree.getCompanyId(), newCompanyCodeNameTree.getCode(),
				newCompanyCodeNameTree.getCompanyId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}
}
