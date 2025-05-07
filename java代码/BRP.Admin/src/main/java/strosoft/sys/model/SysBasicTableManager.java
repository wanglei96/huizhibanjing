package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.sys.data.SysBasicTable;
import strosoft.sys.data.SysBasicTableMaster;
import strosoft.sys.data.SysBasicTableRelation;

/*
 * 数据层
 */

public class SysBasicTableManager extends SysBasicTableMaster {
	private static SysBasicTableManager _instance;

	public static SysBasicTableManager getInstance() {
		if (_instance == null) {
			_instance = new SysBasicTableManager();
		}
		return _instance;
	}

	private SysBasicTableManager() {
		this.setTableName("sys_basic_table");
		this.setTypeName("SysBasicTable");
	}

	/*
	 * 取得实体
	 */
	public SysBasicTable getEntity(int sysBasicTableID) throws Exception {
		return (SysBasicTable) super.getDataEntity(sysBasicTableID);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysBasicTable> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	/*
	 * 添加
	 */
	public int add(String tableName, SysBasicTable newSysBasicTable) throws Exception {
		String sql = String.format(
				"insert into %s(table_name, title,sys_basic_table_type_code ,descriptions) values('%s', '%s', '%s', '%s')",
				tableName, newSysBasicTable.getTableName(), newSysBasicTable.getTitle(),
				newSysBasicTable.getSysBasicTableTypeCode(), newSysBasicTable.getDescription());

		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 删除 数据库中的表
	 */
	@Override
	public int delete(Integer id) throws Exception {
		ArrayList<String> lstSql = getDeleteSqlList(id);
		return MyBatisManager.getInstance().executeUpdate(lstSql);
	}

	/*
	 * 取得删除脚本列表
	 */
	private ArrayList<String> getDeleteSqlList(int id) throws Exception {
		ArrayList<String> lstSql = new ArrayList<String>();
		SysBasicTable theSysBasicTable = SysBasicTableManager.getInstance().getEntity(id);
		String tableTypeCode = theSysBasicTable.getSysBasicTableTypeCode();
		String tableName = theSysBasicTable.getTableName();
		// 删除外键
		ArrayList<String> lstDropForeignKeySql = this.getDropForeignKeySql(theSysBasicTable);
		lstSql.addAll(lstDropForeignKeySql);
		// 删除基础表记录
		String sqlDeleteSysBasicTable = String.format("delete from sys_basic_table where id=%s", id);
		// 自定义表
		if (tableTypeCode.equals("CustomTable")) {
			// 删除自定义表字段(sys_basic_table_field)
			lstSql.add(String.format("delete from sys_basic_table_field where table_name='%s'", tableName));
			lstSql.add(sqlDeleteSysBasicTable);
			return lstSql;
		}
		lstSql.add(sqlDeleteSysBasicTable);
		// 删除表
		String sqlDropTable = "DROP TABLE IF EXISTS " + tableName;
		lstSql.add(sqlDropTable);
		return lstSql;
	}

	/*
	 * 取得删除外键脚本
	 */
	private ArrayList<String> getDropForeignKeySql(SysBasicTable theSysBasicTable) throws Exception {
		ArrayList<String> lstSql = new ArrayList<String>();
		List<SysBasicTableRelation> lstSysBasicTableRelation = SysBasicTableRelationManager.getInstance()
				.getListByBasicTableName(theSysBasicTable.getTableName());
		for (SysBasicTableRelation currentSysBasicTableRelation : lstSysBasicTableRelation) {
			ArrayList<String> lstDeleteSql = SysBasicTableRelationManager.getInstance()
					.getDeleteSql(currentSysBasicTableRelation);
			lstSql.addAll(lstDeleteSql);
		}
		return lstSql;
	}

	/*
	 * 批量删除
	 */
	public void batchDeleteTable(List<String> tableNames) throws Exception {

		for (String tableName : tableNames) {
			MyBatisManager.getInstance().executeUpdate("DROP TABLE " + tableName);
		}

	}

	/*
	 * 创建脚本
	 */
	public String createSql(int id) throws Exception {
		SysBasicTable theSysBasicTable = this.getEntity(id);
		// 创建表脚本
		String sqlCreateTable = this.getCreateTableSql(theSysBasicTable);
		// 外键脚本
		String sqlAddForiegnKey = this.getAddForiegnKeySql(theSysBasicTable);
		// 插入基础表记录
		String sqlAddSysBasicTable = this.getAddSysBasicTableSql(theSysBasicTable);
		String sql = sqlCreateTable + "\r\n\r\n" + sqlAddForiegnKey + "\r\n\r\n" + sqlAddSysBasicTable;
		return sql;
	}

	/*
	 * 取得插入基础表记录
	 */
	private String getAddSysBasicTableSql(SysBasicTable theSysBasicTable) {
		String sql = String.format(
				"INSERT INTO sys_basic_table(table_name,title,sys_basic_table_type_code,description) VALUES('%s','%s','%s','%s');",
				theSysBasicTable.getTableName(), theSysBasicTable.getTitle(),
				theSysBasicTable.getSysBasicTableTypeCode(), theSysBasicTable.getDescription());
		return sql;
	}

	/*
	 * 取得添加外键脚本
	 */
	private String getAddForiegnKeySql(SysBasicTable theSysBasicTable) throws Exception {
		StringBuffer sbSql = new StringBuffer();
		List<SysBasicTableRelation> lstSysBasicTableRelation = SysBasicTableRelationManager.getInstance()
				.getListByBasicTableName(theSysBasicTable.getTableName());
		for (SysBasicTableRelation currentSysBasicTableRelation : lstSysBasicTableRelation) {

			String[] sqlAddForeignKey = SysBasicTableRelationManager.getInstance()
					.getCreateRelationSql(currentSysBasicTableRelation);
			for (String sqlForeignKey : sqlAddForeignKey) {
				sbSql.append(sqlForeignKey);
				sbSql.append("\r\n\r\n");
			}
		}
		String sql = sbSql.toString();
		return sql;
	}

	/*
	 * 取得基础表主键字段名
	 */
	public String getBasicTablePrimaryKeyName(SysBasicTable theSysBasicTable) throws Exception {
		if (theSysBasicTable.getSysBasicTableTypeCode().equals("IdNameTable")) {
			return "id";
		} else if (theSysBasicTable.getSysBasicTableTypeCode().equals("CodeNameTable")) {
			return "code";
		} else if (theSysBasicTable.getSysBasicTableTypeCode().equals("IdNameTree")) {
			return "id";
		} else if (theSysBasicTable.getSysBasicTableTypeCode().equals("CodeNameTree")) {
			return "code";
		} else if (theSysBasicTable.getSysBasicTableTypeCode().equals("CmsIdNameTable")) {
			return "id";
		} else if (theSysBasicTable.getSysBasicTableTypeCode().equals("CmsCodeNameTable")) {
			return "code";
		} else if (theSysBasicTable.getSysBasicTableTypeCode().equals("CmsIdNameTree")) {
			return "id";
		} else if (theSysBasicTable.getSysBasicTableTypeCode().equals("CmsCodeNameTree")) {
			return "code";
		} else {
			throw new Exception(String.format("未找到编码为%的基础表类型！", theSysBasicTable.getSysBasicTableTypeCode()));
		}
	}

	/*
	 * 取得创建表脚本
	 */
	public String getCreateTableSql(SysBasicTable theSysBasicTable) throws Exception {
		// TODO Auto-generated method stub
		String tableName = theSysBasicTable.getTableName();
		String sysBasicTableTypeCode = theSysBasicTable.getSysBasicTableTypeCode();
		String sqlCreateTable = "";
		if (sysBasicTableTypeCode.equals("CodeNameTable")) {
			sqlCreateTable = "CREATE TABLE IF NOT EXISTS `" + tableName
					+ "` (`code` varchar(50) NOT NULL,`name` VARCHAR(50) NOT NULL,`description` VARCHAR(4000) DEFAULT NULL,`display_order` INT(4) DEFAULT NULL, enabled bit DEFAULT 1, create_time datetime DEFAULT now(), update_time datetime DEFAULT now(), PRIMARY KEY (`code`)) ENGINE=INNODB DEFAULT CHARSET=utf8;";
		} else if (sysBasicTableTypeCode.equals("IdNameTable")) {
			sqlCreateTable = "CREATE TABLE IF NOT EXISTS `" + tableName
					+ "` (`id` INT(4) NOT NULL AUTO_INCREMENT,`name` VARCHAR(50) NOT NULL,`description` VARCHAR(4000) DEFAULT NULL,`display_order` INT(4) DEFAULT NULL, enabled bit DEFAULT 1, create_time datetime DEFAULT now(), update_time datetime DEFAULT now(),PRIMARY KEY (`id`)) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
		} else if (sysBasicTableTypeCode.equals("IdNameTree")) {
			sqlCreateTable = "CREATE TABLE IF NOT EXISTS `" + tableName
					+ "` (`id` INT(4) NOT NULL AUTO_INCREMENT,`name` VARCHAR(50) NOT NULL,`description` VARCHAR(4000) DEFAULT NULL,`display_order` INT(4) DEFAULT NULL,parent_id INT(4) DEFAULT NULL, enabled bit DEFAULT 1, create_time datetime DEFAULT now(), update_time datetime DEFAULT now(),PRIMARY KEY (`id`))ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
		} else if (sysBasicTableTypeCode.equals("CodeNameTree")) {
			sqlCreateTable = "CREATE TABLE IF NOT EXISTS `" + tableName
					+ "` (`code` varchar(50) NOT NULL,`name` VARCHAR(50) NOT NULL,`description` VARCHAR(4000) DEFAULT NULL,`display_order` INT(4) DEFAULT NULL,parent_code VARCHAR(50) DEFAULT NULL, enabled bit DEFAULT 1, create_time datetime DEFAULT now(), update_time datetime DEFAULT now(),PRIMARY KEY (`code`),KEY `parent_code` (`parent_code`)) ENGINE=INNODB DEFAULT CHARSET=utf8;";

		} else if (sysBasicTableTypeCode.equals("CmsIdNameTable")) {
			sqlCreateTable = "CREATE TABLE IF NOT EXISTS `" + tableName
					+ "` (`id` INT(4) NOT NULL AUTO_INCREMENT,`name` VARCHAR(50) NOT NULL,`description` VARCHAR(4000) DEFAULT NULL,`display_order` INT(4) DEFAULT NULL,`cms_site_id` INT(4) NOT NULL, enabled bit DEFAULT 1, create_time datetime DEFAULT now(), update_time datetime DEFAULT now(),PRIMARY KEY (`id`)) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
		} else if (sysBasicTableTypeCode.equals("CmsCodeNameTable")) {
			sqlCreateTable = "CREATE TABLE IF NOT EXISTS `" + tableName
					+ "` (`code` varchar(50) NOT NULL,`name` VARCHAR(50) NOT NULL,`description` VARCHAR(4000) DEFAULT NULL,`display_order` INT(4) DEFAULT NULL,`cms_site_id` INT(4) NOT NULL, enabled bit DEFAULT 1, create_time datetime DEFAULT now(), update_time datetime DEFAULT now(),PRIMARY KEY (`code`,`cms_site_id`)) ENGINE=INNODB DEFAULT CHARSET=utf8;";
		} else if (sysBasicTableTypeCode.equals("CmsIdNameTree")) {
			sqlCreateTable = "CREATE TABLE IF NOT EXISTS `" + tableName
					+ "` (`id` INT(4) NOT NULL AUTO_INCREMENT,`name` VARCHAR(50) NOT NULL,`description` VARCHAR(4000) DEFAULT NULL,`display_order` INT(4) DEFAULT NULL,`parent_id` INT(4) DEFAULT NULL,`cms_site_id` INT(4) NOT NULL, enabled bit DEFAULT 1, create_time datetime DEFAULT now(), update_time datetime DEFAULT now(),PRIMARY KEY (`id`)) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
		} else if (sysBasicTableTypeCode.equals("CmsCodeNameTree")) {
			sqlCreateTable = "CREATE TABLE IF NOT EXISTS `" + tableName
					+ "` (`code` varchar(50) NOT NULL,`name` VARCHAR(50) NOT NULL,`description` VARCHAR(4000) DEFAULT NULL,`display_order` INT(4) DEFAULT NULL,parent_code VARCHAR(50),`cms_site_id` INT(4) NOT NULL, enabled bit DEFAULT 1, create_time datetime DEFAULT now(), update_time datetime DEFAULT now(),PRIMARY KEY (`code`,`cms_site_id`)) ENGINE=INNODB DEFAULT CHARSET=utf8;";
		} else if (sysBasicTableTypeCode.equals("CustomTable")) {
			sqlCreateTable = "SELECT * FROM " + tableName + " limit 1";
		} else {
			throw new Exception(String.format("未找到编码为%s的基础表类型", sysBasicTableTypeCode));
		}
		return sqlCreateTable;
	}

	public SysBasicTable getEntityByTableName(String basicTableName) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName + "ByTableName";
			SysBasicTable theSysBasicTable = (SysBasicTable) sqlSession.selectOne(sqlCommandID, basicTableName);
			return theSysBasicTable;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 生成脚本
	 */
	public String createSql(List<Integer> lstId) throws Exception {
		StringBuffer sbSql = new StringBuffer();
		for (int id : lstId) {
			String currentSql = this.createSql(id);
			sbSql.append(currentSql);
			sbSql.append("\r\n\r\n");
		}
		String sql = sbSql.toString();
		return sql;
	}

	/*
	 * 批量删除
	 */
	public int batchDelete(List<Integer> lstId) throws IOException, Exception {
		ArrayList<String> lstSql = new ArrayList<String>();
		for (int id : lstId) {
			ArrayList<String> lstDeleteSql = getDeleteSqlList(id);
			lstSql.addAll(lstDeleteSql);
		}
		return MyBatisManager.getInstance().executeUpdate(lstSql);
	}

	/**
	 * 设置启动状态
	 * 
	 * @param tableName
	 * @param primaryKeyName
	 * @param primaryKey
	 * @param enabled
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public int setEnabled(String tableName, String primaryKeyName, String primaryKey, Boolean enabled)
			throws IOException, Exception {
		String sql = String.format("update %s set enabled=%s where %s='%s'", tableName,
				enabled == null ? "null" : (enabled ? "1" : "0"), primaryKeyName, primaryKey);
		int result = MyBatisManager.getInstance().executeUpdate(sql);
		return result;
	}

}
