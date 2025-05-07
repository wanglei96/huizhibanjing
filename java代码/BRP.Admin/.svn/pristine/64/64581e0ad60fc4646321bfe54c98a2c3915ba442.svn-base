package strosoft.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.data.IdNameTree;
import strosoft.app.util.HashMapHelper;

/*
 * Id树型表管理类
 */
@SuppressWarnings("unchecked")
public class IdNameTreeManager extends IdDataManager {
	private static IdNameTreeManager _instance;

	public static IdNameTreeManager getInstance() {
		if (_instance == null) {
			_instance = new IdNameTreeManager();
		}
		return _instance;
	}

	private IdNameTreeManager() {
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
	public IdNameTree getEntity(String tableName, int id) throws Exception {
		String sql = String.format("select * from %s where id=%s", tableName, id);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		IdNameTree theIdNameTree = toEntity(hmData);
		return theIdNameTree;
	}

	/*
	 * HashMap转为数据实体
	 */
	public IdNameTree toEntity(HashMap<String, Object> hmData) {
		IdNameTree theIdNameTree = new IdNameTree();
		theIdNameTree.setId(HashMapHelper.getInt(hmData, "id", "ID"));
		theIdNameTree.setName(HashMapHelper.getString(hmData, "name", "Name"));
		theIdNameTree
				.setParentId(HashMapHelper.getInt(hmData, "parent_id", "parentId", "parentID", "ParentId", "ParentID"));
		theIdNameTree.setDescription(HashMapHelper.getString(hmData, "description", "Description"));
		theIdNameTree.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time", "CreateTime"));
		theIdNameTree.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time", "UpdateTime"));
		int displayOrder = HashMapHelper.getInt(hmData, "display_order", "displayOrder", "DisplayOrder");
		theIdNameTree.setDisplayOrder(displayOrder);
		return theIdNameTree;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<IdNameTree> getList() throws Exception {
		String sql = String.format("select * from %s order by display_order", this.getTableName());
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		ArrayList<IdNameTree> lstIdNameTree = new ArrayList<IdNameTree>();
		for (HashMap<String, Object> hmData : lstData) {
			IdNameTree theIdNameTree = toEntity(hmData);
			lstIdNameTree.add(theIdNameTree);
		}
		return lstIdNameTree;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, IdNameTree newIdNameTree) throws Exception {
		String sql = String.format(
				"insert into %s(name, parent_id, description, display_order, create_time, update_time) values('%s', %s, '%s', %s, now(),now())",
				tableName, newIdNameTree.getName(), newIdNameTree.getParentId(), newIdNameTree.getDescription(),
				newIdNameTree.getDisplayOrder());
		// System.out.println(sql);
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 更新
	 */
	public int update(String tableName, IdNameTree newIdNameTree) throws Exception {
		String sql = String.format(
				"update %s set name='%s', parent_id=%s, description='%s', display_order=%s, update_time=now() where id=%s",
				tableName, newIdNameTree.getName(), newIdNameTree.getParentId(), newIdNameTree.getDescription(),
				newIdNameTree.getDisplayOrder(), newIdNameTree.getId());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 删除
	 */
	@Override
	public int delete(String tableName, Integer id) throws Exception {
		MySqlConditionBuilder conditionBuilder = new MySqlConditionBuilder();
		conditionBuilder.addIDTreeCondition("id", tableName, id);
		String condition = conditionBuilder.toString();
		String sql = String.format("delete from %s where %s", tableName, condition);
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 删除
	 */
	@Override
	public int delete(SqlSession sqlSession, String tableName, Integer id) throws Exception {
		MySqlConditionBuilder conditionBuilder = new MySqlConditionBuilder();
		conditionBuilder.addIDTreeCondition("id", tableName, id);
		String condition = conditionBuilder.toString();
		String sql = String.format("delete from %s where %s", tableName, condition);
		return MyBatisManager.getInstance().executeUpdate(sqlSession, sql);

	}

}
