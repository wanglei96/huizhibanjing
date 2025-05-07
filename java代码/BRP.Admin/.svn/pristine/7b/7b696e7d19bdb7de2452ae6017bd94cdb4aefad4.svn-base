package strosoft.sys.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.sys.data.SysRole;

/*
 * 角色
 */
public class SysRoleManager extends IdDataManager {
	private static SysRoleManager _instance;

	public static SysRoleManager getInstance() {
		if (_instance == null) {
			_instance = new SysRoleManager();
		}
		return _instance;
	}

	private SysRoleManager() {
		this.setTableName("sys_role");
		this.setTypeName("SysRole");
	}

	/*
	 * 检查登录账号是否存在
	 */
	public Boolean existsLoginName(String roleName) throws Exception {
		return MyBatisManager.getInstance().exists(this.getTableName(), "name", roleName);
	}

	/*
	 * 取得实体
	 */
	public SysRole getEntity(int sysRoleID) throws Exception {
		return (SysRole) super.getDataEntity(sysRoleID);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysRole> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	/*
	 * 删除
	 */
	@Override
	public int delete(Integer id) throws Exception {
		String sqlDeleteSysUserRole = String.format("delete from sys_user_role where sys_role_id=%s", id);
		String sqlDeleteSysRole = String.format("delete from %s where id=%s", this.getTableName(), id);
		String[] arrSql = new String[] { sqlDeleteSysUserRole, sqlDeleteSysRole };
		return MyBatisManager.getInstance().executeUpdate(arrSql);
	}

}
