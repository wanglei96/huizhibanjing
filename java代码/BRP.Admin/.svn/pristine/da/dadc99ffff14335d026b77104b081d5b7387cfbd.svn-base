package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONArray;
import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.sys.data.SysRolePermission;
import strosoft.sys.data.SysRolePermissionMaster;

public class SysRolePermissionManager extends SysRolePermissionMaster {
	private static SysRolePermissionManager _instance;

	public static SysRolePermissionManager getInstance() {
		if (_instance == null) {
			_instance = new SysRolePermissionManager();
		}
		return _instance;
	}

	private SysRolePermissionManager() {
		this.setTableName("sys_role_permission");
		this.setTypeName("SysRolePermission");
	}

	/*
	 * 多项添加
	 */
	public int add(int sysRoleId, List<String> lstValues) throws Exception {
		String[] sqls = new String[lstValues.size() + 1];
		sqls[0] = String.format("delete from sys_role_permission where sys_role_id = %s", sysRoleId);
		for (int i = 0; i < lstValues.size(); i++) {
			String value = lstValues.get(i);
			sqls[i + 1] = String.format("insert into sys_role_permission (sys_role_id,permission) values (%s, '%s')",
					sysRoleId, value);
		}
		int result = MyBatisManager.getInstance().executeUpdate(sqls);
		return result;
	}

	/*
	 * 根据ID查询列表
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SysRolePermission> getListBySysRoleId(String sysRoleId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName + "BySysRoleId";
			List<Object> lstDataEntity = sqlSession.selectList(sqlCommandID, Integer.parseInt(sysRoleId));
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 根据ID查询列表
	 */
	public List<SysRolePermission> getListBySysUserId(SqlSession sqlSession, int sysUserId) throws IOException {
		return sqlSession.selectList("SysRolePermission.selectSysRolePermissionBySysUserId", sysUserId);
	}

	/**
	 * 取得角色权限列表
	 * 
	 * @param sqlSession
	 * @param sysRoleId
	 * @return
	 */
	public List<SysRolePermission> getListBySysRoleId(SqlSession sqlSession, int sysRoleId) {
		List<SysRolePermission> lstSysRolePermission = sqlSession
				.selectList("SysRolePermission.selectSysRolePermissionBySysRoleId", sysRoleId);
		return lstSysRolePermission;
	}

	public int deleteSysRolePermissionsByPermissions(SqlSession sqlSession, List<String> permissions) {
		int result = 0;
		if (null == permissions || permissions.size() < 1) {
			return 0;
		}
		String strPermissions = stringListToString(permissions);
		String sql = String.format("delete from sys_role_permission where permission in (%s)", strPermissions);
		result = sqlSession.delete("SysRolePermission.deleteSysRolePermissionBySql", sql);
		return result;
	}

	public String stringListToString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		if (null == list || list.size() < 1) {
			return "";
		}
		sb.append("'");
		sb.append(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			sb.append("','");
			sb.append(list.get(i));
		}
		sb.append("'");
		return sb.toString();
	}

	/**
	 * 设置权限
	 * 
	 * @param lstSysRolePermission
	 * @param jarrMenuFunction
	 * @return
	 */
	public JSONArray setJSONArrayPermission(List<SysRolePermission> lstSysRolePermission, JSONArray jarrMenuFunction) {
		for (int i = 0; i < jarrMenuFunction.size(); i++) {
			Boolean flag = hasPermission(lstSysRolePermission, jarrMenuFunction.getJSONObject(i).getString("value"));
			jarrMenuFunction.getJSONObject(i).put("checked", flag);
		}
		return jarrMenuFunction;
	}

	/**
	 * 检查是否有权限
	 * 
	 * @param list
	 * @param permission
	 * @return
	 */
	public Boolean hasPermission(List<SysRolePermission> list, String permission) {
		if (null == list) {
			return false;
		}
		for (int i = 0; i < list.size(); i++) {
			String currentPermission = list.get(i).getPermission();
			if (currentPermission.equals(permission)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 批量添加角色权限
	 * @param sqlSession
	 * @param lstSysRolePermission
	 * @return
	 */
	public int addList(SqlSession sqlSession, List<SysRolePermission> lstSysRolePermission) {
		if (null == lstSysRolePermission || lstSysRolePermission.size() == 0) {
			return 0;
		}
		int rows = sqlSession.insert("SysRolePermission.insertSysRolePermission", lstSysRolePermission);
		return rows;

	}
}
