package strosoft.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.CodeDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.data.CodeNameTree;
import strosoft.app.util.HashMapHelper;

/*
 * code树型表管理类
 */
public class CodeNameTreeManager extends CodeDataManager {
	private static CodeNameTreeManager _instance;

	public static CodeNameTreeManager getInstance() {
		if (_instance == null) {
			_instance = new CodeNameTreeManager();
		}
		return _instance;
	}

	private CodeNameTreeManager() {
	}

	/*
	 * 检查登录名称是否存在
	 */
	public Boolean existsName(String tableName, String name) throws Exception {
		return MyBatisManager.getInstance().exists(tableName, "name", name);
	}

	/*
	 * 取得实体
	 */
	public CodeNameTree getEntity(String tableName, String code) throws Exception {
		String sql = String.format("select * from %s where code='%s'", tableName, code);
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		if (lstData.size() == 0) {
			return null;
		}
		HashMap<String, Object> hmData = lstData.get(0);
		CodeNameTree theCodeNameTree = toEntity(hmData);
		return theCodeNameTree;
	}

	/*
	 * HashMap转为数据实体
	 */
	public CodeNameTree toEntity(HashMap<String, Object> hmData) {
		CodeNameTree theCodeNameTree = new CodeNameTree();
		// 编码
		theCodeNameTree.setCode(HashMapHelper.getString(hmData, "code"));
		theCodeNameTree.setName(HashMapHelper.getString(hmData, "name", "Name"));
		theCodeNameTree.setParentCode(HashMapHelper.getString(hmData, "parent_code", "parentCode", "ParentCode"));
		theCodeNameTree.setDescription(HashMapHelper.getString(hmData, "description", "Description"));
		theCodeNameTree.setCreateTime(HashMapHelper.getDatetime(hmData, "create_time", "CreateTime"));
		theCodeNameTree.setUpdateTime(HashMapHelper.getDatetime(hmData, "update_time", "UpdateTime"));
		int displayOrder = HashMapHelper.getInt(hmData, "display_order", "displayOrder", "DisplayOrder");
		theCodeNameTree.setDisplayOrder(displayOrder);
		return theCodeNameTree;
	}

	/*
	 * 取得列表
	 */
	public ArrayList<CodeNameTree> getList() throws Exception {
		String sql = String.format("select * from %s order by display_order", this.getTableName());
		List<LinkedHashMap<String, Object>> lstData = MyBatisManager.getInstance().executeHashMapList(sql);
		ArrayList<CodeNameTree> lstCodeNameTable = new ArrayList<CodeNameTree>();
		for (HashMap<String, Object> hmData : lstData) {
			CodeNameTree theCodeNameTree = toEntity(hmData);
			lstCodeNameTable.add(theCodeNameTree);
		}
		return lstCodeNameTable;
	}

	/*
	 * 添加
	 */
	public int add(String tableName, CodeNameTree newCodeNameTable) throws Exception {
		String sql = String.format(
				"insert into %s(code, name, parent_code, description, display_order, create_time, update_time) values('%s', '%s', '%s', '%s', %s, now(),now())",
				tableName, newCodeNameTable.getCode(), newCodeNameTable.getName(), newCodeNameTable.getParentCode(),
				newCodeNameTable.getDescription(), newCodeNameTable.getDisplayOrder());
		// System.out.println(sql);
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	public int update(String tableName, CodeNameTree newCodeNameTable) throws Exception {
		String sql = String.format(
				"update %s set name='%s', parent_code='%s',description='%s', display_order=%s, update_time=now() where code='%s'",
				tableName, newCodeNameTable.getName(), newCodeNameTable.getParentCode(),
				newCodeNameTable.getDescription(), newCodeNameTable.getDisplayOrder(), newCodeNameTable.getCode());
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 删除
	 */
	@Override
	public int delete(String tableName, String code) throws Exception {
		MySqlConditionBuilder conditionBuilder = new MySqlConditionBuilder();
		conditionBuilder.addCodeTreeCondition("code", tableName, code);
		String condition = conditionBuilder.toString();
		String sql = String.format("delete from %s where %s", tableName, condition);
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 删除
	 */
	public int delete(SqlSession sqlSession, String tableName, String code) throws Exception {
		MySqlConditionBuilder conditionBuilder = new MySqlConditionBuilder();
		conditionBuilder.addCodeTreeCondition("code", tableName, code);
		String condition = conditionBuilder.toString();
		String sql = String.format("delete from %s where %s", tableName, condition);
		return MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
	}
}
