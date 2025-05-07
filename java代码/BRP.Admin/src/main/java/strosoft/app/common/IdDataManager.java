package strosoft.app.common;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.data.DataEntity;
import strosoft.app.util.ClassHelper;

/*
 * ID数据管理类
 */
public class IdDataManager extends TableDataMaster {

	public int delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String tableName = this.getTableName();
		return delete(tableName, id);
	}

	/*
	 * 取得实体
	 */
	public DataEntity getDataEntity(Integer id) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName;
			DataEntity dataEntity = (DataEntity) sqlSession.selectOne(sqlCommandID, id);
			return dataEntity;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 取得实体
	 */
	public DataEntity getDataEntity(SqlSession sqlSession, Integer id) throws IOException {
		String typeName = this.getTypeName();
		String sqlCommandID = typeName + ".select" + typeName;
		DataEntity dataEntity = (DataEntity) sqlSession.selectOne(sqlCommandID, id);
		return dataEntity;
	}

	/*
	 * 取得实体
	 */
	@Override
	public DataEntity getDataEntity(String id) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName;
			DataEntity dataEntity = (DataEntity) sqlSession.selectOne(sqlCommandID, id);
			return dataEntity;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 添加
	 */
	@Override
	public int add(DataEntity dataEntity) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			int newId = add(sqlSession, dataEntity);
			sqlSession.commit();
			return newId;
		} catch (Exception ex) {
			sqlSession.rollback();
			throw ex;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 添加
	 */
	@Override
	public int add(SqlSession sqlSession, DataEntity dataEntity)
			throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException,
			NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
		String typeName = this.getTypeName();
		String sqlCommandID = typeName + ".insert" + typeName;
		int result = sqlSession.insert(sqlCommandID, dataEntity);
		int newId = this.getNewId(dataEntity);
		if (newId > 0) {
			return newId;
		} else {
			return result;
		}
	}

	/*
	 * 取得新ID
	 */
	private int getNewId(DataEntity dataEntity) throws IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
		@SuppressWarnings("rawtypes")
		Class theClass = dataEntity.getClass();
		Boolean existsGetIdMethod = ClassHelper.existsMethod(theClass, "getId");
		if (existsGetIdMethod) {
			@SuppressWarnings("unchecked")
			Method theGetIdMethod = theClass.getMethod("getId");
			int newId = Integer.parseInt(theGetIdMethod.invoke(dataEntity).toString());
			return newId;
		} else {
			return 0;
		}
	}

	/*
	 * 删除
	 */
	public int delete(String tableName, Integer id) throws Exception {
		String sql = String.format("delete from %s where id=%s", tableName, id);
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 删除
	 */
	public int delete(SqlSession sqlSession, String tableName, Integer id) throws Exception {
		String sql = String.format("delete from %s where id=%s", tableName, id);
		return MyBatisManager.getInstance().executeUpdate(sqlSession, sql);

	}

	/*
	 * 批量删除
	 */
	public int batchDelete(String tableName, List<Integer> lstId) throws Exception {
		StringBuffer sbId = new StringBuffer();
		for (int id : lstId) {
			if (sbId.length() > 0) {
				sbId.append(",");
			}
			sbId.append(id);
		}
		String strIds = sbId.toString();
		String sql = String.format("delete from %s where id in (%s)", tableName, strIds);
		int result = MyBatisManager.getInstance().executeUpdate(sql);
		return result;
	}

	/*
	 * 批量删除
	 */
	public void move(String tableName, String moveDirection, Integer id) throws Exception {
		move(tableName, moveDirection, id, null);
	}

	/*
	 * 移动显示顺序
	 */
	public void move(String tableName, String moveDirection, Integer id, String condition) throws Exception {
		int exchangeDsiplayOrder = this.getExchangeDisplayOrder(tableName, moveDirection, id, condition);
		if (exchangeDsiplayOrder == -1) {
			return;
		}
		int exchangeID = this.getIDByDisplayOrder(tableName, exchangeDsiplayOrder, condition);
		if (exchangeID == -1) {
			return;
		}
		int orginalDisplaryOrder = this.getDisplayOrderById(tableName, id);
		String updateExchangeCommandText = String.format("update %s set display_order=%s where id=%s", tableName,
				orginalDisplaryOrder, exchangeID);
		String UpdateCommandText = String.format("update %s set display_order=%s where id=%s", tableName,
				exchangeDsiplayOrder, id);
		String[] sqls = new String[] { updateExchangeCommandText, UpdateCommandText };
		MyBatisManager.getInstance().executeUpdate(sqls);
	}

	/*
	 * 取得显示顺序
	 */
	private int getDisplayOrderById(String tableName, Integer id) throws Exception {
		String sql = String.format("select display_order from %s where id=%s", tableName, id);
		return MyBatisManager.getInstance().executeInt(sql);
	}

	/*
	 * 根据显示顺序取得ID
	 */
	private int getIDByDisplayOrder(String tableName, Integer displayOrder, String condition) throws Exception {
		String commandText = String.format("select id from %s where display_order=%s", tableName, displayOrder);
		if (condition != null && !condition.equals("")) {
			commandText += String.format(" and (%s)", condition);
		}
		int id = MyBatisManager.getInstance().executeInt(commandText);
		return id;
	}

	/*
	 * 取得交换的显示顺序
	 */
	private int getExchangeDisplayOrder(String tableName, String moveDirection, Integer id, String condition)
			throws Exception {
		String filerText = "";
		if (condition != null && !condition.equals("")) {
			filerText = String.format(" and (%s)", condition);
		}
		String commandText = "";
		if (moveDirection.equals(MoveDirection.MoveUp)) {
			commandText = String.format(
					"select ifnull(max(display_order),-1) from %s where display_order<(select display_order from %s where id=%s)%s",
					tableName, tableName, id, filerText);
		} else {
			commandText = String.format(
					"select ifnull(min(display_order), -1) from %s where display_order>(select display_order from %s where id=%s)%s",
					tableName, tableName, id, filerText);
		}
		int exchangeID = MyBatisManager.getInstance().executeInt(commandText);
		return exchangeID;
	}
}
