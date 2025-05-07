package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.sys.data.*;


/*
 * 基础表关联表管理类
 */
public class SysBasicTableRelationManager extends SysBasicTableRelationMaster {
	private static SysBasicTableRelationManager _instance;

	public static SysBasicTableRelationManager getInstance() {
		if (_instance == null) {
			_instance = new SysBasicTableRelationManager();
		}
		return _instance;
	}

	private SysBasicTableRelationManager() {
		this.setTableName("sys_basic_table_relation");
		this.setTypeName("SysBasicTableRelation");
	}

	// 获取列表
	@SuppressWarnings("unchecked")
	public ArrayList<SysBasicTableRelation> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	/*
	 * 根据基础表名取得关联表列表
	 */
	public List<SysBasicTableRelation> getListByBasicTableName(
			String basicTableName) throws IOException {

		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {

			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName
					+ "sByBasicTableName";
			List<Object> lstDataEntity = sqlSession.selectList(sqlCommandID,
					basicTableName);
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	public String[] getCreateRelationSql(
			SysBasicTableRelation newSysBasicTableRelation) throws Exception {
		SysBasicTable theSysBasicTable = SysBasicTableManager.getInstance()
				.getEntityByTableName(
						newSysBasicTableRelation.getBasicTableName());

		String basicTablePrimaryKeyName = SysBasicTableManager.getInstance()
				.getBasicTablePrimaryKeyName(theSysBasicTable);
		String[] sqls = new String[] {
				String.format(
						"ALTER TABLE `%s` ADD INDEX `fk_%s_%s_idx` (`%s` ASC);",
						newSysBasicTableRelation.getRelatedTableName(),
						newSysBasicTableRelation.getRelatedTableName(),
						newSysBasicTableRelation.getBasicTableName(),
						newSysBasicTableRelation.getRelatedColumnName()),
				String.format("ALTER TABLE `%s`"
						+ " ADD CONSTRAINT `fk_%s_%s`"
						+ "	FOREIGN KEY (`%s`) REFERENCES `%s` (`%s`)"
						+ " ON DELETE CASCADE ON UPDATE CASCADE;",
						newSysBasicTableRelation.getRelatedTableName(),
						newSysBasicTableRelation.getRelatedTableName(),
						newSysBasicTableRelation.getBasicTableName(),
						newSysBasicTableRelation.getRelatedColumnName(),
						newSysBasicTableRelation.getBasicTableName(),
						basicTablePrimaryKeyName) };
		return sqls;
	}

	/*
	 * 删除基础表关联
	 */
	public int delete(SysBasicTableRelation theSysBasicTableRelation)
			throws Exception {
		ArrayList<String> lstSql = this.getDeleteSql(theSysBasicTableRelation);
		return MyBatisManager.getInstance().executeUpdate(lstSql);
	}

	/*
	 * 取得删除脚本
	 */
	public ArrayList<String> getDeleteSql(
			SysBasicTableRelation theSysBasicTableRelation) throws IOException, Exception {
		ArrayList<String> lstSql = new ArrayList<String>();
		lstSql.add("DELETE FROM sys_basic_table_relation WHERE related_table_name='"
				+ theSysBasicTableRelation.getRelatedTableName()
				+ "' and related_column_name='"
				+ theSysBasicTableRelation.getRelatedColumnName() + "';");
		String foreignKeyName = String.format("fk_%s_%s",
				theSysBasicTableRelation.getRelatedTableName(),
				theSysBasicTableRelation.getBasicTableName());
		Boolean existsForeignKey = MyBatisManager.getInstance()
				.existsConstraint(foreignKeyName);
		if (existsForeignKey) {
			lstSql.add(String.format(
					"ALTER TABLE `%s` DROP FOREIGN KEY `fk_%s_%s`;",
					theSysBasicTableRelation.getRelatedTableName(),
					theSysBasicTableRelation.getRelatedTableName(),
					theSysBasicTableRelation.getBasicTableName()));
		}
		String indexName = String.format("fk_%s_%s_idx",
				theSysBasicTableRelation.getRelatedTableName(),
				theSysBasicTableRelation.getBasicTableName());
		Boolean existsIndex = MyBatisManager.getInstance().existsIndex(
				theSysBasicTableRelation.getRelatedTableName(), indexName);
		if (existsIndex) {
			lstSql.add(String.format(
					"ALTER TABLE `%s` DROP INDEX `fk_%s_%s_idx` ;",
					theSysBasicTableRelation.getRelatedTableName(),
					theSysBasicTableRelation.getRelatedTableName(),
					theSysBasicTableRelation.getBasicTableName()));
		}
		return lstSql;
	}
}
