package strosoft.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.CompanyIdNameTree;
import strosoft.app.util.HashMapHelper;

/*
 * Id树型表管理类
 */
@SuppressWarnings("unchecked")
public class CompanyIdNameTreeManager extends IdDataManager {
	private static CompanyIdNameTreeManager _instance;

	public static CompanyIdNameTreeManager getInstance() {
		if (_instance == null) {
			_instance = new CompanyIdNameTreeManager();
		}
		return _instance;
	}

	private CompanyIdNameTreeManager() {
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
	public CompanyIdNameTree getEntity(String tableName, int id) throws Exception {
		String sql = String.format("select * from %s where id=%s", tableName, id);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		CompanyIdNameTree theCompanyIdNameTree = toEntity(hmData);
		return theCompanyIdNameTree;
	}

	/*
	 * HashMap转为数据实体
	 */
	public CompanyIdNameTree toEntity(HashMap<String, Object> hmData) {
		CompanyIdNameTree theCompanyIdNameTree = new CompanyIdNameTree();
		// id
		theCompanyIdNameTree.setId(HashMapHelper.getInt(hmData, "id"));
		// 名称
		theCompanyIdNameTree.setName(HashMapHelper.getString(hmData, "name", "Name"));
		// 父节点id
		theCompanyIdNameTree.setParentId(HashMapHelper.getInt(hmData, "parent_id"));
		// 描述
		theCompanyIdNameTree.setDescription(HashMapHelper.getString(hmData, "description"));
		// 显示顺序
		int displayOrder = HashMapHelper.getInt(hmData, "display_order");
		theCompanyIdNameTree.setDisplayOrder(displayOrder);
		// 站点id
		int companyId = HashMapHelper.getInt(hmData, "company_id");
		theCompanyIdNameTree.setCompanyId(companyId);
		// 创建时间
		theCompanyIdNameTree.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time"));
		// 修改时间
		theCompanyIdNameTree.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time"));
		return theCompanyIdNameTree;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<CompanyIdNameTree> getList(int companyId) throws Exception {
		String sql = String.format("select * from %s where company_id = %s order by display_order", this.getTableName(),
				companyId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		ArrayList<CompanyIdNameTree> lstIdNameTree = new ArrayList<CompanyIdNameTree>();
		for (HashMap<String, Object> hmData : lstData) {
			CompanyIdNameTree theCompanyIdNameTree = toEntity(hmData);
			lstIdNameTree.add(theCompanyIdNameTree);
		}
		return lstIdNameTree;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, CompanyIdNameTree newCompanyIdNameTree) throws Exception {
		String sql = String.format(
				"insert into %s(name, parent_id, description, display_order, company_id, create_time, update_time) values('%s', %s, '%s', %s, %s, now(), now())",
				tableName, newCompanyIdNameTree.getName(), newCompanyIdNameTree.getParentId(),
				newCompanyIdNameTree.getDescription(), newCompanyIdNameTree.getDisplayOrder(),
				newCompanyIdNameTree.getCompanyId());
		// System.out.println(sql);
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 更新
	 */
	public int update(String tableName, CompanyIdNameTree newCompanyIdNameTree) throws Exception {
		String sql = String.format(
				"update %s set name='%s', parent_id=%s, description='%s', display_order=%s, company_id=%s, update_time=now() where id=%s",
				tableName, newCompanyIdNameTree.getName(), newCompanyIdNameTree.getParentId(),
				newCompanyIdNameTree.getDescription(), newCompanyIdNameTree.getDisplayOrder(),
				newCompanyIdNameTree.getCompanyId(), newCompanyIdNameTree.getId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

}
