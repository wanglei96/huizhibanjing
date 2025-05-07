package strosoft.app.common;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.data.DataEntity;

import net.sf.json.JSONArray;

/*
 * Code数据管理类
 */
public class CodeDataManager extends TableDataMaster {

	public int delete(String code) throws Exception {
		// TODO Auto-generated method stub
		String typeName = this.getTableName();
		String sql = String.format("delete from %s where code='%s'", typeName,
				code);
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 取得数据实体
	 */
	public DataEntity getDataEntity(String code) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {

			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName;
			DataEntity dataEntity = (DataEntity) sqlSession.selectOne(
					sqlCommandID, code);
			return dataEntity;
		} finally {
			sqlSession.close();
		}
	}
	/*
	 * 取得数据实体
	 */
	public DataEntity getDataEntity(SqlSession sqlSession,String code) throws IOException {
			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName;
			DataEntity dataEntity = (DataEntity) sqlSession.selectOne(
					sqlCommandID, code);
			return dataEntity;
	}

	/*
	 * 批量删除
	 */
	public int batchDeleteByCodes(List<String> jarrCode) throws Exception {
		StringBuffer sbCode = new StringBuffer();
		for (String code : jarrCode) {
			if (sbCode.length() > 0) {
				sbCode.append("','");
			}
			sbCode.append(code);
		}
		String strCodes = "'" + sbCode.toString() + "'";
		String sql = String.format("delete from %s where code in (%s)",
				this.getTableName(), strCodes);
		int result = MyBatisManager.getInstance().executeUpdate(sql);
		return result;

	}

	/*
	 * 删除
	 */
	public int delete(String tableName, String code) throws Exception {
		String sql = String.format("delete from %s where code='%s'", tableName,
				code);
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/*
	 * 删除
	 */
	public int delete(SqlSession session, String tableName, String code) throws Exception {
		String sql = String.format("delete from %s where code='%s'", tableName,
				code);
		return MyBatisManager.getInstance().executeUpdate(session, sql);
	}

	/*
	 * Cms多项删除
	 */
	public int batchDelete(int cmsSiteId, String tableName, List<String> lstCode)
			throws Exception {
		StringBuffer sbCode = new StringBuffer();
		for (String code : lstCode) {
			if (sbCode.length() > 0) {
				sbCode.append("','");
			}
			sbCode.append(code);
		}
		String strCodes = "'" + sbCode.toString() + "'";
		String sql = String.format(
				"delete from %s where code in (%s) and cms_site_id=%s",
				tableName, strCodes, cmsSiteId);
		int result = MyBatisManager.getInstance().executeUpdate(sql);
		return result;
	}

	/*
	 * 多项删除
	 */
	public int batchDelete(String tableName, List<String> lstCode)
			throws Exception {
		StringBuffer sbCode = new StringBuffer();
		for (String code : lstCode) {
			if (sbCode.length() > 0) {
				sbCode.append("','");
			}
			sbCode.append(code);
		}
		String strCodes = "'" + sbCode.toString() + "'";
		String sql = String.format("delete from %s where code in (%s)",
				tableName, strCodes);
		int result = MyBatisManager.getInstance().executeUpdate(sql);
		return result;
	}

	/*
	 * 移动
	 */
	public void move(String tableName, String moveDirection, String code)
			throws Exception {
		move(tableName, moveDirection, code, null);
	}

	/*
	 * 移动
	 */
	public void move(String tableName, String moveDirection, String code,
			String condition) throws Exception {
		int exchangeDisplayOrder = this.getExchangeDisplayOrder(tableName,
				moveDirection, code, condition);
		if (exchangeDisplayOrder == -1) {
			return;
		}
		String exchangeCode = this.getCodeByDisplayOrder(tableName,
				exchangeDisplayOrder, condition);
		if (exchangeCode == null || exchangeCode.equals("")) {
			return;
		}
		int displayOrder = this.getDisplayOrderByCode(tableName, code,
				condition);
		String updateExchangeCommandText = String.format(
				"update %s set display_order=%s where code='%s'", tableName,
				displayOrder, exchangeCode);
		String updateCommandText = String.format(
				"update %s set display_order=%s where code='%s'", tableName,
				exchangeDisplayOrder, code);
		if (condition != null && !condition.equals("")) {
			updateExchangeCommandText += String.format(" and (%s)", condition);
			updateCommandText += String.format(" and (%s)", condition);
		}
		String[] sqls = new String[] { updateExchangeCommandText,
				updateCommandText };
		MyBatisManager.getInstance().executeUpdate(sqls);
	}

	/*
	 * 根据编码取得显示顺序
	 */
	private int getDisplayOrderByCode(String tableName, String code,
			String condition) throws Exception {
		String sql = String
				.format("select display_order from %s where code='%s'",
						tableName, code);
		if (condition != null && !condition.equals("")) {
			sql += String.format(" and (%s)", condition);
		}
		return MyBatisManager.getInstance().executeInt(sql);
	}

	/*
	 * 取得ID
	 */
	private String getCodeByDisplayOrder(String tableName, int displayOrder,
			String condition) throws Exception {
		String sql = String.format(
				"select code from %s where display_order=%s", tableName,
				displayOrder);
		if (condition != null && !condition.equals("")) {
			sql += String.format(" and (%s)", condition);
		}
		String code = MyBatisManager.getInstance().executeString(sql);
		return code;
	}

	/*
	 * 取得交换DisplayOrder
	 */
	private int getExchangeDisplayOrder(String tableName, String moveDirection,
			String code, String condition) throws Exception {
		String filerText = "";
		if (condition != null && !condition.equals("")) {
			filerText = String.format(" and (%s)", condition);
		}
		String sql = "";
		if (moveDirection.equals(MoveDirection.MoveUp)) {
			sql = String
					.format("select ifnull(max(display_order),-1) from %s where display_order<(select display_order from %s where code='%s'%s)%s",
							tableName, tableName, code, filerText, filerText);
		} else {
			sql = String
					.format("select ifnull(min(display_order), -1) from %s where display_order>(select display_order from %s where code='%s'%s)%s",
							tableName, tableName, code, filerText, filerText);
		}
		int exchangeDisplayOrder = MyBatisManager.getInstance().executeInt(sql);
		return exchangeDisplayOrder;
	}
}
