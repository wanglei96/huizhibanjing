package strosoft.app.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import net.sf.json.JSONObject;
import strosoft.app.util.DateHelper;
import strosoft.app.util.StringHelper;

public class MyBatisManager {

	private SqlSessionFactory factory;
	private static MyBatisManager _instance;
	private static final String mapperNamespace = "strosoft.app.common";

	public static MyBatisManager getInstance() throws IOException {
		if (_instance == null) {
			_instance = new MyBatisManager();
		}
		return _instance;
	}

	private MyBatisManager() throws IOException {
		this.loadConfig();
	}

	private void loadConfig() throws IOException {
		// 通过配置文件获取数据库连接信息
		try (BufferedReader bReader = new BufferedReader(new FileReader(SysConfig.getMyBatisConfigFile()))) {
			// 通过配置信息构建SqlSessionFactory
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			factory = builder.build(bReader);
		}
	}

	/**
	 * 开启事务
	 * 
	 * @return
	 * @throws IOException
	 */
	public SqlSession openSession() throws IOException {

		// 通过SqlSessionFactory打开数据库会话
		SqlSession sqlSession = factory.openSession();

		return sqlSession;
	}

	/**
	 * 提交事务
	 * 
	 * @param sqlSession
	 * @throws IOException
	 */
	public void commitSession(SqlSession sqlSession) throws IOException {
		if (sqlSession == null) {
			return;
		}
		sqlSession.commit();
	}

	/**
	 * 回滚事务
	 * 
	 * @param sqlSession
	 * @throws IOException
	 */
	public void rollbackSession(SqlSession sqlSession) throws IOException {
		if (sqlSession == null) {
			return;
		}
		sqlSession.rollback();
	}

	/**
	 * 关闭事务
	 * 
	 * @param sqlSession
	 * @throws IOException
	 */
	public void closeSession(SqlSession sqlSession) throws IOException {
		if (sqlSession == null) {
			return;
		}
		sqlSession.close();
	}

	/* 查询列表 */
	public ArrayList<LinkedHashMap<String, Object>> executeHashMapList(String sql) throws Exception {

		SqlSession sqlSession = this.openSession();
		try {
			JSONObject jSql = new JSONObject();
			jSql.put("sql", sql);
			List<Object> lstDataEntity = sqlSession.selectList(mapperNamespace + ".executeList", jSql);
			ArrayList<LinkedHashMap<String, Object>> lstData = new ArrayList<LinkedHashMap<String, Object>>();
			for (int i = 0; i < lstDataEntity.size(); i++) {
				LinkedHashMap<String, Object> hmData = new LinkedHashMap<String, Object>();
				LinkedHashMap jData = (LinkedHashMap) lstDataEntity.get(i);
				for (Object key : jData.keySet()) {
					Object value = jData.get(key);
					hmData.put(key.toString(), value);
				}
				lstData.add(hmData);
			}
			sqlSession.commit();
			return lstData;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/* 查询列表 */
	public ArrayList<LinkedHashMap<String, Object>> executeHashMapList(SqlSession sqlSession, String sql)
			throws Exception {
		JSONObject jSql = new JSONObject();
		jSql.put("sql", sql);
		List<Object> lstDataEntity = sqlSession.selectList(mapperNamespace + ".executeList", jSql);
		ArrayList<LinkedHashMap<String, Object>> lstData = new ArrayList<LinkedHashMap<String, Object>>();
		for (int i = 0; i < lstDataEntity.size(); i++) {
			LinkedHashMap<String, Object> hmData = new LinkedHashMap<String, Object>();
			LinkedHashMap jData = (LinkedHashMap) lstDataEntity.get(i);
			for (Object key : jData.keySet()) {
				Object value = jData.get(key);
				hmData.put(key.toString(), value);
			}
			lstData.add(hmData);
		}
		return lstData;
	}

	/**
	 * 取得整数值
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int executeInt(String sql) throws Exception {
		SqlSession sqlSession = this.openSession();
		try {
			int result = executeInt(sqlSession, sql);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 取得int值
	 * 
	 * @param sqlSession
	 * @param sql
	 * @return
	 */
	public int executeInt(SqlSession sqlSession, String sql) {
		JSONObject jSql = new JSONObject();
		jSql.put("sql", sql);
		List<Object> lstResult = sqlSession.selectList(mapperNamespace + ".executeInt", jSql);
		if (lstResult == null || lstResult.size() == 0) {
			return 0;
		}
		Object objValue = lstResult.get(0);
		if (objValue == null) {
			return 0;
		}
		return (Integer) objValue;
	}

	/*
	 * 执行查询返回字符串
	 */
	public String executeString(String sql) throws Exception {
		SqlSession sqlSession = this.openSession();
		try {
			String result = executeString(sqlSession, sql);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 执行查询返回字符串
	 */
	public String executeString(SqlSession sqlSession, String sql) {
		JSONObject jSql = new JSONObject();
		jSql.put("sql", sql);
		List<Object> lstData = sqlSession.selectList(mapperNamespace + ".executeString", jSql);
		if (lstData == null || lstData.size() == 0) {
			return null;
		}
		Object value = lstData.get(0);
		if (value == null) {
			return null;
		}
		return lstData.get(0).toString();
	}

	/**
	 * 取得Decimal值
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public BigDecimal executeDecimal(String sql) throws Exception {
		SqlSession sqlSession = this.openSession();
		try {
			BigDecimal result = executeDecimal(sqlSession, sql);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 取得BigDecimal值
	 * 
	 * @param sqlSession
	 * @param sql
	 * @return
	 */
	public BigDecimal executeDecimal(SqlSession sqlSession, String sql) {
		JSONObject jSql = new JSONObject();
		jSql.put("sql", sql);
		List<Object> lstValue = sqlSession.selectList(mapperNamespace + ".executeString", jSql);
		if (lstValue == null || lstValue.size() == 0) {
			return null;
		}
		Object objValue = lstValue.get(0);
		if (objValue == null) {
			return null;
		}
		String strValue = objValue.toString();
		BigDecimal decValue = new BigDecimal(strValue);
		return decValue;
	}

	/**
	 * 取得整数值
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Date executeDate(String sql) throws Exception {
		SqlSession sqlSession = this.openSession();
		try {
			Date result = executeDate(sqlSession, sql);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 取得Date类型值
	 * 
	 * @param sqlSession
	 * @param sql
	 * @return
	 */
	public Date executeDate(SqlSession sqlSession, String sql) {
		JSONObject jSql = new JSONObject();
		jSql.put("sql", sql);
		List<Object> lstResult = sqlSession.selectList(mapperNamespace + ".executeInt", jSql);
		if (lstResult == null || lstResult.size() == 0) {
			return null;
		}
		Object objValue = lstResult.get(0);
		if (objValue == null) {
			return null;
		}
		Date dateValue = DateHelper.toDate(objValue.toString());
		return dateValue;
	}

	public List<String> getStringListBysql(SqlSession sqlSession, String sql) throws IOException {
		List<String> result = new ArrayList<String>();
		result = sqlSession.selectList(mapperNamespace + ".selectStringBysql", sql);
		return result;
	}

	/*
	 * 检查指定字段值是否存在
	 */
	public Boolean exists(String tableName, String fieldName, String value) throws Exception {
		String condition = String.format(" %s = '%s'", fieldName, value);
		int count = getCount(tableName, condition);
		return count > 0;
	}

	/*
	 * 检查指定字段值是否存在
	 */
	public Boolean exists(String tableName, String fieldName, String value, String condition) throws Exception {
		String equalCondition = String.format(" %s = '%s'", fieldName, value);
		if (condition == null || condition.equals("")) {
			condition = equalCondition;
		} else {
			condition = String.format("(%s) and %s", condition, equalCondition);
		}
		int count = getCount(tableName, condition);
		return count > 0;
	}

	/*
	 * 取得记录数
	 */
	public int getCount(String tableName) throws Exception {
		return getCount(tableName, null);
	}

	/*
	 * 取得记录数
	 */
	public int getCount(String tableName, String condition) throws Exception {
		String sqlWhere = "";
		if (condition != null && condition != "") {
			sqlWhere = " where " + condition;
		}
		String sql = String.format("select count(*) from %s%s", tableName, sqlWhere);
		int count = executeInt(sql);
		return count;
	}

	/*
	 * 更新
	 */
	public int executeUpdate(String sql) throws Exception {
		SqlSession sqlSession = openSession();
		try {
			int result = executeUpdate(sqlSession, sql);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 更新
	 */
	public int executeUpdate(SqlSession sqlSession, String sql) throws IOException {
		JSONObject jSql = new JSONObject();
		jSql.put("sql", sql);
		int update = sqlSession.update(mapperNamespace + ".executeUpdate", jSql);
		return update;
	}

	/*
	 * 多项更新
	 */
	public int executeUpdate(String[] sqls) throws Exception {
		SqlSession sqlSession = openSession();
		try {
			int result = executeUpdate(sqlSession, sqls);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 执行多条语句
	 * 
	 * @param sqlSession
	 * @param sqls
	 * @return
	 * @throws IOException
	 */
	public int executeUpdate(SqlSession sqlSession, String[] sqls) throws IOException {
		int result = 0;
		for (int i = 0; i < sqls.length; i++) {
			String sql = sqls[i];
			result += executeUpdate(sqlSession, sql);
		}
		return result;
	}

	/**
	 * 执行多条语句
	 * 
	 * @param sqlSession
	 * @param alSql
	 * @return
	 * @throws IOException
	 */

	public int executeUpdate(SqlSession sqlSession, ArrayList<String> alSql) throws IOException {
		int result = 0;
		for (int i = 0; i < alSql.size(); i++) {
			String sql = alSql.get(i);
			result += executeUpdate(sqlSession, sql);
		}
		return result;
	}

	/**
	 * 执行多条语句
	 * 
	 * @param alSql
	 * @return
	 * @throws Exception
	 */
	public int executeUpdate(ArrayList<String> alSql) throws Exception {
		SqlSession sqlSession = openSession();
		try {
			int result = 0;
			for (int i = 0; i < alSql.size(); i++) {
				String sql = alSql.get(i);
				result += executeUpdate(sqlSession, sql);
			}
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 取得指定表ArrayList
	 */
	public ArrayList<LinkedHashMap<String, Object>> getHashMapList(String tableName) throws Exception {
		String sql = String.format("select * from %s", tableName);
		return executeHashMapList(sql);
	}

	/*
	 * 取得指定数据库中所有表
	 */
	public ArrayList<LinkedHashMap<String, Object>> getAllTables() throws Exception {
		String databaseName = this.getDatabaseName();
		return getAllTables(databaseName);
	}

	/*
	 * 取得指定数据库中所有表
	 */
	public ArrayList<LinkedHashMap<String, Object>> getAllTables(String databaseName) throws Exception {
		String sql = String.format(
				"SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '%s' and Table_type = 'BASE TABLE'",
				databaseName);
		return executeHashMapList(sql);
	}

	/*
	 * 取得指定表中所有字段
	 */
	public ArrayList<LinkedHashMap<String, Object>> getAllColumns(String databaseName, String tableName)
			throws Exception {
		String sql = String.format(
				"select * from INFORMATION_SCHEMA.Columns where table_name='%s' and table_schema='%s'", tableName,
				databaseName);
		return executeHashMapList(sql);
	}

	/*
	 * 取得指定表中所有字段
	 */
	public ArrayList<LinkedHashMap<String, Object>> getAllColumns(String tableName) throws Exception {
		String databaseName = getDatabaseName();
		return getAllColumns(databaseName, tableName);
	}

	/*
	 * 取得数据库名
	 */
	public String getDatabaseName() {
		String url = (String) factory.getConfiguration().getVariables().get("databaseUrl");
		String databaseString = url.substring(url.lastIndexOf('/') + 1);
		String databaseName = databaseString.split("\\?")[0];
		return databaseName;
	}

	/*
	 * 约束是否存在
	 */
	public Boolean existsConstraint(String constraintName) throws Exception {
		String databaseName = getDatabaseName();
		return existsConstraint(databaseName, constraintName);
	}

	/*
	 * 约束是否存在
	 */
	public Boolean existsConstraint(String databaseName, String constraintName) throws Exception {
		String sql = String.format(
				"select count(*) from information_schema.KEY_COLUMN_USAGE where TABLE_SCHEMA='%s' and constraint_name = '%s'",
				databaseName, constraintName);
		int count = executeInt(sql);
		return count > 0;

	}

	/*
	 * 索引是否存在
	 */
	public Boolean existsIndex(String tableName, String indexName) throws Exception {
		String databaseName = getDatabaseName();
		return existsIndex(databaseName, tableName, indexName);
	}

	/*
	 * 索引是否存在
	 */
	public Boolean existsIndex(String databaseName, String tableName, String indexName) throws Exception {
		String sql = String.format(
				"SELECT count(*) FROM information_schema.statistics WHERE TABLE_SCHEMA='%s' AND table_name = '%s' AND index_name = '%s'",
				databaseName, tableName, indexName);
		int count = executeInt(sql);
		return count > 0;
	}

	/*
	 * 执行命令
	 */
	public int executeCommand(String commandId, String commandType, JSONObject jParameters) throws Exception {
		SqlSession sqlSession = this.openSession();
		try {
			if (commandType == null || commandType.equals("")) {
				commandType = "update";
			}
			if (commandType.equalsIgnoreCase("update")) {
				int result = sqlSession.update(commandId, jParameters);
				sqlSession.commit();
				return result;
			}
			if (commandType.equalsIgnoreCase("delete")) {
				int result = sqlSession.delete(commandId, jParameters);
				sqlSession.commit();
				return result;
			}
			if (commandType.equalsIgnoreCase("insert")) {
				int result = sqlSession.insert(commandId, jParameters);
				sqlSession.commit();
				return result;
			}
			throw new Exception("未找到CommandType：" + commandType);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 取得指定表中的外键
	 */
	public ArrayList<LinkedHashMap<String, Object>> getReferencedColumns(String databaseName, String tableName)
			throws Exception {
		String sql = String.format(
				"select TABLE_NAME,COLUMN_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME,REFERENCED_TABLE_SCHEMA from INFORMATION_SCHEMA.KEY_COLUMN_USAGE  where TABLE_NAME='%s'and table_schema='%s'",
				tableName, databaseName);
		return executeHashMapList(sql);
	}

	/*
	 * 取得指定表中的被其它表引用的外键
	 */
	public ArrayList<LinkedHashMap<String, Object>> getBeReferencedColumns(String databaseName, String tableName)
			throws Exception {
		String sql = String.format(
				"select TABLE_NAME,COLUMN_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE  where REFERENCED_TABLE_NAME='%s'and table_schema='%s'",
				tableName, databaseName);
		return executeHashMapList(sql);
	}

	/*
	 * 取得指定表中的唯一约束字段
	 */
	public ArrayList<LinkedHashMap<String, Object>> getUniqueColumns(String databaseName, String tableName)
			throws Exception {
		String sql = String.format("SHOW INDEX FROM %s.%s WHERE Non_unique=0", databaseName, tableName);
		return executeHashMapList(sql);
	}

	/**
	 * 获取字符串List列表
	 */
	public ArrayList<String> getStringList(SqlSession sqlSession, String sql) throws IOException {
		ArrayList<String> databases = new ArrayList<String>();
		databases = (ArrayList<String>) MyBatisManager.getInstance().getStringListBysql(sqlSession, sql);
		return databases;
	}

	/*
	 * 新增
	 */
	public int executeInsert(String sql) throws Exception {
		SqlSession sqlSession = openSession();
		try {
			int result = executeInsert(sqlSession, sql);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 新增
	 */
	public int executeInsert(SqlSession sqlSession, String sql) throws IOException {
		JSONObject jSql = new JSONObject();
		jSql.put("sql", sql);
		int insert = sqlSession.insert(mapperNamespace + ".executeAdd", jSql);
		return insert;
	}

	/*
	 * 删除
	 */
	public int executeDelete(String sql) throws Exception {
		SqlSession sqlSession = openSession();
		try {
			int result = executeDelete(sqlSession, sql);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 删除
	 */
	public int executeDelete(SqlSession sqlSession, String sql) throws IOException {
		JSONObject jSql = new JSONObject();
		jSql.put("sql", sql);
		int insert = sqlSession.delete(mapperNamespace + ".executeDelete", jSql);
		return insert;
	}

	/**
	 * 取得新编号
	 * 
	 * @param tableName
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public String newNo(String tableName, String fieldName) throws Exception {
		return newNo(tableName, fieldName, null);
	}

	/**
	 * 取得新编号
	 * 
	 * @param tableName
	 * @param fieldName
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public String newNo(String tableName, String fieldName, String condition) throws Exception {
		String sql = String.format("select max(%s) from %s", fieldName, tableName);
		if (!StringHelper.isNullOrEmpty(condition)) {
			sql += " where " + condition;
		}
		String maxNo = executeString(sql);
		String newNo = StringHelper.addOne(maxNo);
		return newNo;
	}

	/**
	 * 取得新编号
	 * 
	 * @param tableName
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public String newNo(SqlSession sqlSession, String tableName, String fieldName) throws Exception {
		return newNo(tableName, fieldName, null);
	}

	/**
	 * 取得新编号
	 * 
	 * @param tableName
	 * @param fieldName
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public String newNo(SqlSession sqlSession, String tableName, String fieldName, String condition) throws Exception {
		String sql = String.format("select max(%s) from %s", fieldName, tableName);
		if (!StringHelper.isNullOrEmpty(condition)) {
			sql += " where " + condition;
		}
		String maxNo = executeString(sqlSession, sql);
		String newNo = StringHelper.addOne(maxNo);
		return newNo;
	}

	/**
	 * 生成编号
	 * 
	 * @param sqlSession
	 * @param tableName
	 * @param fieldName
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public String createRandomNo(SqlSession sqlSession, String tableName, String fieldName, Integer length)
			throws Exception {
		for (int i = 0; i < 1000; i++) {
			String newNo = StringHelper.getRandomText("0123456789", length);
			String sql = String.format("select count(*) from %s where %s='%s'", tableName, fieldName, newNo);
			Integer count = this.executeInt(sqlSession, sql);
			if (count == 0) {
				return newNo;
			}
		}
		throw new Exception("生成编号失败！");
	}

	/**
	 * 设置默认记录
	 * 
	 * @param tableName
	 * @param primaryKeyFieldName
	 * @param isDefaultFieldName
	 * @param primaryKey
	 * @throws Exception
	 */
	public void setDefault(String tableName, String primaryKeyFieldName, String isDefaultFieldName, Object primaryKey)
			throws Exception {
		if (StringHelper.isNullOrEmpty(primaryKeyFieldName)) {
			primaryKeyFieldName = "id";
		}
		if (StringHelper.isNullOrEmpty(isDefaultFieldName)) {
			isDefaultFieldName = "is_default";
		}
		String sql = String.format("update %s set %s = (case when %s = '%s' then 1 else 0 end)", tableName,
				isDefaultFieldName, primaryKeyFieldName, primaryKey);
		this.executeUpdate(sql);
	}
}
