package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysRole;
import strosoft.sys.data.SysUserRole;
import strosoft.sys.data.SysUserRoleMaster;

public class SysUserRoleManager extends SysUserRoleMaster {
	private static SysUserRoleManager _instance;

	public static SysUserRoleManager getInstance() {
		if (_instance == null) {
			_instance = new SysUserRoleManager();
		}
		return _instance;
	}

	private SysUserRoleManager() {
		this.setTableName("sys_user_role");
		this.setTypeName("SysUserRole");
	}


	@SuppressWarnings("unchecked")
	public ArrayList<SysUserRole> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	/*
	 * 多项添加
	 */
	public int add(int sysUserId, List<Integer> lstValues) throws Exception {
		String[] sqls = new String[lstValues.size() + 1];
		sqls[0] = String.format("delete from sys_user_role where sys_user_id = %s", sysUserId);
		for (int i = 0; i < lstValues.size(); i++) {
			Integer value = lstValues.get(i);
			sqls[i + 1] = String.format("insert into sys_user_role (sys_user_id,sys_role_id) values (%s, %s)",
					sysUserId, value);
		}
		int result = MyBatisManager.getInstance().executeUpdate(sqls);
		return result;
	}

	/*
	 * 根据ID查询列表
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SysUserRole> getListBySysUserId(String sysRoleId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName + "ById";
			List<Object> lstDataEntity = sqlSession.selectList(sqlCommandID, Integer.parseInt(sysRoleId));
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	public JSONArray setJSONArrayUserRole(List<SysUserRole> lstSysUserRole, JSONArray jarrSysRole) {
		for (int i = 0; i < jarrSysRole.size(); i++) {
			Boolean flag = hasPermission(lstSysUserRole, jarrSysRole.getJSONObject(i).getString("id"));
			jarrSysRole.getJSONObject(i).put("checked", flag);
		}
		return jarrSysRole;
	}

	/**
	 * 该项角色是否在角色列表中
	 * 
	 * @param lstSysUserRole
	 * @return
	 */
	public Boolean hasPermission(List<SysUserRole> lstSysUserRole, String sysRoleId) {
		if (null == lstSysUserRole) {
			return false;
		}
		for (int i = 0; i < lstSysUserRole.size(); i++) {
			Integer currentPermission = lstSysUserRole.get(i).getSysRoleId();
			if (currentPermission.toString().equals(sysRoleId)) {
				return true;
			}
		}
		return false;
	}

	public JSONObject getJSONArrayBySysUserId(int sysUserId) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			// 获取操作人员的角色
			List<SysUserRole> lstSysUserRole = SysUserRoleManager.getInstance().getList("sys_user_id=" + sysUserId);
			// 获取全部角色
			List<SysRole> lstSysRoles = SysRoleManager.getInstance().getList();
			JSONArray jarrSysRole = JsonHelper.toJsonArray(lstSysRoles);
			// 设置该角色拥有的权限
			jarrSysRole = this.setJSONArrayUserRole(lstSysUserRole, jarrSysRole);
			JSONObject jResult = new JSONObject();
			jResult.put("dataList", jarrSysRole);
			return jResult;
		} catch (Exception e) {
			e.printStackTrace();
			if (null != sqlSession) {
				sqlSession.rollback();
			}
			throw e;
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
	}
}
