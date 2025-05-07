package strosoft.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import strosoft.app.common.CodeDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.CmsCodeNameTree;
import strosoft.app.util.HashMapHelper;

/*
 * code树型表管理类
 */
public class CmsCodeNameTreeManager extends CodeDataManager {
	private static CmsCodeNameTreeManager _instance;

	public static CmsCodeNameTreeManager getInstance() {
		if (_instance == null) {
			_instance = new CmsCodeNameTreeManager();
		}
		return _instance;
	}

	private CmsCodeNameTreeManager() {
	}

	/*
	 * 检查登录名称是否存在
	 */
	public Boolean existsName(int cmsSiteId, String tableName, String name) throws Exception {
		return MyBatisManager.getInstance().exists(tableName, "name", name, "cms_site_id=" + cmsSiteId);
	}

	/*
	 * 取得实体
	 */
	public CmsCodeNameTree getEntity(int cmsSiteId, String tableName, String code) throws Exception {
		String sql = String.format("select * from %s where code='%s' and cms_site_id=%s", tableName, code, cmsSiteId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		CmsCodeNameTree theCmsCodeNameTree = toEntity(hmData);
		return theCmsCodeNameTree;
	}

	/*
	 * HashMap转为数据实体
	 */
	public CmsCodeNameTree toEntity(HashMap<String, Object> hmData) {
		CmsCodeNameTree theCmsCodeNameTree = new CmsCodeNameTree();
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
		int cmsSiteId = HashMapHelper.getInt(hmData, "cms_site_id");
		theCmsCodeNameTree.setCmsSiteId(cmsSiteId);
		return theCmsCodeNameTree;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<CmsCodeNameTree> getList(int cmsSiteId) throws Exception {
		String sql = String.format("select * from %s where cms_site_id=%s order by display_order", this.getTableName(),
				cmsSiteId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		ArrayList<CmsCodeNameTree> lstCmsCodeNameTable = new ArrayList<CmsCodeNameTree>();
		for (HashMap<String, Object> hmData : lstData) {
			CmsCodeNameTree theCodeNameTree = toEntity(hmData);
			lstCmsCodeNameTable.add(theCodeNameTree);
		}
		return lstCmsCodeNameTable;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, CmsCodeNameTree newCmsCodeNameTree) throws Exception {
		String sql = String.format(
				"insert into %s(code, name, parent_code, description, display_order, cms_site_id, create_time, update_time) values('%s', '%s', '%s', '%s', %s, %s,now(), now())",
				tableName, newCmsCodeNameTree.getCode(), newCmsCodeNameTree.getName(),
				newCmsCodeNameTree.getParentCode(), newCmsCodeNameTree.getDescription(),
				newCmsCodeNameTree.getDisplayOrder(), newCmsCodeNameTree.getCmsSiteId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	public int update(String tableName, CmsCodeNameTree newCmsCodeNameTree) throws Exception {
		String sql = String.format(
				"update %s set name='%s', parent_code='%s',description='%s', display_order=%s, cms_site_id=%s, update_time=now() where code='%s' and cms_site_id=%s",
				tableName, newCmsCodeNameTree.getName(), newCmsCodeNameTree.getParentCode(),
				newCmsCodeNameTree.getDescription(), newCmsCodeNameTree.getDisplayOrder(),
				newCmsCodeNameTree.getCmsSiteId(), newCmsCodeNameTree.getCode(), newCmsCodeNameTree.getCmsSiteId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}
}
