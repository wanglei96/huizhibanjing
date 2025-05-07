package strosoft.app.common;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.data.DataEntity;
import strosoft.app.util.StringHelper;

/*
 * 数据管理类
 */
public abstract class TableDataMaster<T> extends DataMaster {

	private String tableName;

	public String getTableName() {
		// TODO Auto-generated method stub
		return tableName;
	}

	public void setTableName(String tableName) {
		// TODO Auto-generated method stub
		this.tableName = tableName;
	}

	/*
	 * 添加
	 */
	public int add(DataEntity dataEntity) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			int result = add(sqlSession, dataEntity);
			sqlSession.commit();
			return result;
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
	public int add(SqlSession sqlSession, DataEntity dataEntity)
			throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException,
			NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
		String typeName = this.getTypeName();
		String sqlCommandID = typeName + ".insert" + typeName;
		int result = sqlSession.insert(sqlCommandID, dataEntity);
		return result;
	}

	/*
	 * 修改
	 */
	public int update(DataEntity dataEntity) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".update" + typeName;
			// System.out.println(sqlCommandID);
			int result = sqlSession.update(sqlCommandID, dataEntity);
			sqlSession.commit();
			return result;

		} catch (Exception ex) {
			sqlSession.rollback();
			throw ex;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 修改
	 */
	public int update(SqlSession sqlSession, DataEntity dataEntity) throws Exception {
		String typeName = this.getTypeName();
		String sqlCommandID = typeName + ".update" + typeName;
		// System.out.println(sqlCommandID);
		int result = sqlSession.update(sqlCommandID, dataEntity);
		return result;
	}

	/*
	 * 删除
	 */

	public int delete(DataEntity dataEntity) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".delete" + typeName;
			int result = sqlSession.delete(sqlCommandID, dataEntity);
			sqlSession.commit();
			return result;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 删除
	 */

	public int delete(SqlSession sqlSession, DataEntity dataEntity) throws Exception {
		String typeName = this.getTypeName();
		String sqlCommandID = typeName + ".delete" + typeName;
		int result = sqlSession.delete(sqlCommandID, dataEntity);
		return result;
	}

	private void alert() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public ArrayList<T> toList(List<Object> lstDataEntity) {
		ArrayList<T> lstT = new ArrayList<T>();
		for (Object currentDataEntity : lstDataEntity) {
			T newT = (T) currentDataEntity;
			lstT.add(newT);
		}
		return lstT;
	}

	public ArrayList<HashMap<String, Object>> GetHashMapList() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 取得列表
	 */
	public List<Object> getObjectList() throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {

			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName + "s";
			List<Object> lstDataEntity = sqlSession.selectList(sqlCommandID);
			return lstDataEntity;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 取得数据实体
	 */
	public DataEntity getDataEntity(String primaryKey) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName;
			DataEntity theDataEntity = (DataEntity) sqlSession.selectOne(sqlCommandID, primaryKey);
			return theDataEntity;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 取得最大显示顺序
	 */
	public int getMaxDisplayOrder(String tableName) throws Exception {
		String sql = String.format("select ifnull(max(display_order), 0) from %s", tableName);
		int maxDisplayOrder = MyBatisManager.getInstance().executeInt(sql);
		return maxDisplayOrder;
	}

	/*
	 * 取得最大显示顺序
	 */
	public int getMaxDisplayOrder(String tableName, String condition) throws Exception {
		String whereSql = "";
		if (condition != null && !condition.equals("")) {
			whereSql = " where " + condition;
		}
		String sql = String.format("select ifnull(max(display_order), 0) from %s%s", tableName, whereSql);
		int maxDisplayOrder = MyBatisManager.getInstance().executeInt(sql);
		return maxDisplayOrder;
	}

	public void Clear() {
		// TODO Auto-generated method stub

	}

	/*
	 * 多项删除
	 */
	public int batchDeleteByIds(List<Integer> lstId) throws Exception {
		StringBuffer sbId = new StringBuffer();
		for (int id : lstId) {
			if (sbId.length() > 0) {
				sbId.append(",");
			}
			sbId.append(id);
		}
		String strIds = sbId.toString();
		String sql = String.format("delete from %s where id in (%s)", this.getTableName(), strIds);
		int result = MyBatisManager.getInstance().executeUpdate(sql);
		return result;
	}

	public int batchDeleteByEntity(List<DataEntity> list) throws Exception {
		for (DataEntity entity : list) {
			delete(entity);
		}
		return list.size();
	}

	/*
	 * 多项删除
	 */
	public int batchDeleteByCodes(String tableName, List<String> lstPrimaryKey) throws Exception {
		StringBuffer sbPrimaryKey = new StringBuffer();
		for (String primaryKey : lstPrimaryKey) {
			if (sbPrimaryKey.length() > 0) {
				sbPrimaryKey.append("','");
			}
			sbPrimaryKey.append(primaryKey);
		}
		String strCodes = "'" + sbPrimaryKey.toString() + "'";
		String sql = String.format("delete from %s where code in (%s)", tableName, strCodes);
		int result = MyBatisManager.getInstance().executeUpdate(sql);
		return result;
	}

	public List<DataEntity> getList(String condition) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			List<DataEntity> dataEntityList = getList(sqlSession, condition);
			return dataEntityList;
		} finally {
			sqlSession.close();
		}
	}

	public List<DataEntity> getList(SqlSession sqlSession, String condition) throws IOException {
		String typeName = this.getTypeName();
		String sqlCommandID = typeName + ".select" + typeName + "ByCondition";
		List<DataEntity> dataEntityList = sqlSession.selectList(sqlCommandID, condition);
		return dataEntityList;
	}

	public String getUUID32() {
		return StringHelper.getUUID32();
	}

	/**
	 * 批量添加
	 * 
	 * @param lstDataEntity
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Integer> add(ArrayList<DataEntity> lstDataEntity) throws Exception {
		ArrayList<Integer> lstNewID = new ArrayList<Integer>();
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			for (DataEntity currentDataEntity : lstDataEntity) {
				int newID = add(sqlSession, currentDataEntity);
				lstNewID.add(newID);
			}
			sqlSession.commit();
			return lstNewID;
		} catch (Exception ex) {
			sqlSession.rollback();
			throw ex;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 批量更新
	 * 
	 * @param lstDataEntity
	 * @return
	 * @throws Exception
	 */
	public int update(List<DataEntity> lstDataEntity) throws Exception {
		int affectedCount = 0;
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			for (DataEntity currentDataEntity : lstDataEntity) {
				affectedCount += update(sqlSession, currentDataEntity);
			}
			sqlSession.commit();
			return affectedCount;
		} catch (Exception ex) {
			sqlSession.rollback();
			throw ex;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 根据条件删除
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public int deleteByCondition(String condition) throws IOException, Exception {
		String sql = String.format("delete from %s where %s", this.getTableName(), condition.replace("'", "''"));
		int result = MyBatisManager.getInstance().executeDelete(sql);
		return result;
	}

	/**
	 * 根据条件删除
	 * 
	 * @param sqlSession
	 * @param condition
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public int deleteByCondition(SqlSession sqlSession, String condition) throws IOException, Exception {
		String sql = String.format("delete from %s where %s", this.getTableName(), condition.replace("'", "''"));
		int result = MyBatisManager.getInstance().executeDelete(sqlSession, sql);
		return result;
	}
}
