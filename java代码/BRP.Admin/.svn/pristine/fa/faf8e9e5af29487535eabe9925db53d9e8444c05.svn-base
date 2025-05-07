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
import strosoft.app.data.CmsIdNameTree;
import strosoft.app.util.HashMapHelper;
import strosoft.app.util.MySqlHelper;

import net.sf.json.JSONArray;

/*
 * Id树型表管理类
 */
@SuppressWarnings("unchecked")
public class CmsIdNameTreeManager extends IdDataManager {
	private static CmsIdNameTreeManager _instance;

	public static CmsIdNameTreeManager getInstance() {
		if (_instance == null) {
			_instance = new CmsIdNameTreeManager();
		}
		return _instance;
	}

	private CmsIdNameTreeManager() {
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
	public CmsIdNameTree getEntity(String tableName, int id) throws Exception {
		String sql = String.format("select * from %s where id=%s", tableName,
				id);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager
				.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		CmsIdNameTree theCmsIdNameTree = toEntity(hmData);
		return theCmsIdNameTree;
	}

	/*
	 * HashMap转为数据实体
	 */
	public CmsIdNameTree toEntity(HashMap<String, Object> hmData) {
		CmsIdNameTree theCmsIdNameTree = new CmsIdNameTree();
		// id
		theCmsIdNameTree.setId(HashMapHelper.getInt(hmData, "id"));
		// 名称
		theCmsIdNameTree.setName(HashMapHelper
				.getString(hmData, "name", "Name"));
		// 父节点id
		theCmsIdNameTree.setParentId(HashMapHelper.getInt(hmData, "parent_id"));
		// 描述
		theCmsIdNameTree.setDescription(HashMapHelper.getString(hmData,
				"description"));
		// 显示顺序
		int displayOrder = HashMapHelper.getInt(hmData, "display_order");
		theCmsIdNameTree.setDisplayOrder(displayOrder);
		// 站点id
		int cmsSiteId = HashMapHelper.getInt(hmData, "cms_site_id");
		theCmsIdNameTree.setCmsSiteId(cmsSiteId);
		//创建时间
		theCmsIdNameTree.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time"));
		//修改时间
		theCmsIdNameTree.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time"));
		return theCmsIdNameTree;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<CmsIdNameTree> getList(int cmsSiteId) throws Exception {
		String sql = String
				.format("select * from %s where cms_site_id = %s order by display_order",
						this.getTableName(), cmsSiteId);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager
				.getInstance().executeHashMapList(sql);
		ArrayList<CmsIdNameTree> lstIdNameTree = new ArrayList<CmsIdNameTree>();
		for (HashMap<String, Object> hmData : lstData) {
			CmsIdNameTree theCmsIdNameTree = toEntity(hmData);
			lstIdNameTree.add(theCmsIdNameTree);
		}
		return lstIdNameTree;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, CmsIdNameTree newCmsIdNameTree)
			throws Exception {
		String sql = String
				.format("insert into %s(name, parent_id, description, display_order, cms_site_id, create_time, update_time) values('%s', %s, '%s', %s, %s, now(), now())",
						tableName, newCmsIdNameTree.getName(),
						newCmsIdNameTree.getParentId(),
						newCmsIdNameTree.getDescription(),
						newCmsIdNameTree.getDisplayOrder(),
						newCmsIdNameTree.getCmsSiteId());
		// System.out.println(sql);
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 更新
	 */
	public int update(String tableName, CmsIdNameTree newCmsIdNameTree)
			throws Exception {
		String sql = String
				.format("update %s set name='%s', parent_id=%s, description='%s', display_order=%s, cms_site_id=%s, update_time=now() where id=%s",
						tableName, newCmsIdNameTree.getName(),
						newCmsIdNameTree.getParentId(),
						newCmsIdNameTree.getDescription(),
						newCmsIdNameTree.getDisplayOrder(),
						newCmsIdNameTree.getCmsSiteId(),
						newCmsIdNameTree.getId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

}
