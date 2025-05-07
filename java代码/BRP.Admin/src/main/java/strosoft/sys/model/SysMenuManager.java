package strosoft.sys.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.data.SysMenu;
import strosoft.sys.data.SysMenuMaster;
import strosoft.sys.data.SysRolePermission;
import strosoft.sys.data.ViewSysMenuSysFunction;

public class SysMenuManager extends SysMenuMaster {
	private static SysMenuManager _instance;

	public static SysMenuManager getInstance() {
		if (_instance == null) {
			_instance = new SysMenuManager();
		}
		return _instance;
	}

	private SysMenuManager() {
		this.setTableName("sys_menu");
		this.setTypeName("SysMenu");
	}

	/*
	 * 检查登录账号是否存在
	 */
	public Boolean existsLoginName(String loginName) throws Exception {
		return MyBatisManager.getInstance().exists(this.getTableName(), "name", loginName);
	}

	/*
	 * 取得实体
	 */
	public SysMenu getEntity(int sysMenuID) throws Exception {
		return (SysMenu) super.getDataEntity(sysMenuID);
	}

	/*
	 * 获取列表
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SysMenu> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysMenu> getListBySysUserId(int sysUserId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = typeName + ".select" + typeName + "BySysUserId";
			System.out.println(" sqlCommandID " + sqlCommandID);
			List<Object> lstDataEntity = sqlSession.selectList(sqlCommandID, sysUserId);
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysMenu> getAdminListBySysUserId(int sysUserId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = "SysMenuCustom.selectAdminSysMenuBySysUserId";
			List<Object> lstDataEntity = sqlSession.selectList(sqlCommandID, sysUserId);
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysMenu> getFrontListBySysUserId(int sysUserId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String typeName = this.getTypeName();
			String sqlCommandID = "SysMenuCustom.selectFrontSysMenuBySysUserId";
			List<Object> lstDataEntity = sqlSession.selectList(sqlCommandID, sysUserId);
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysMenu> getListBySysUserIdAndMenuTypeCode(Integer sysUserId, String menuTypeCode)
			throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (sysUserId == null) {
				sysUserId = 0;
			}
			map.put("sysUserId", sysUserId);
			map.put("menuTypeCode", menuTypeCode);
			String typeName = this.getTypeName();
			String sqlCommandID = "SysMenuCustom.selectSysMenuBySysUserIdAndSysMenuTypeCode";
			List<Object> lstDataEntity = sqlSession.selectList(sqlCommandID, map);
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 从sysMenuHashtable中获取指定id菜单的所有父菜单，直到菜单树的根节点
	 * 
	 * @param id               菜单Id
	 * @param sysMenuHashtable 所有系统菜单组成的Hashtable
	 * @return
	 */
	public ArrayList<SysMenu> getParentsList(int id, Hashtable<Integer, SysMenu> sysMenuHashtable) {
		ArrayList<SysMenu> lstParentMenu = new ArrayList<SysMenu>();
		SysMenu theSysMenu = sysMenuHashtable.get(id);
		if (theSysMenu.getParentId() == null || theSysMenu.getParentId() == 0) {
			return lstParentMenu;
		}
		SysMenu theParentMenu = sysMenuHashtable.get(theSysMenu.getParentId());
		if (theParentMenu == null) {
			return lstParentMenu;
		}
		lstParentMenu.add(theParentMenu);
		ArrayList<SysMenu> lstParentParentMenu = this.getParentsList(theSysMenu.getParentId(), sysMenuHashtable);
		if (lstParentParentMenu.size() > 0) {
			lstParentMenu.addAll(lstParentParentMenu);
		}
		return lstParentMenu;
	}

	/**
	 * 把系统菜单ArrayList转换为Key为id的Hashtable
	 * 
	 * @param allSysMenu
	 * @return
	 */
	public Hashtable<Integer, SysMenu> getSysMenuHashtable(ArrayList<SysMenu> allSysMenu) {
		Hashtable<Integer, SysMenu> sysMenuHashtable = new Hashtable<Integer, SysMenu>();
		for (int i = 0; i < allSysMenu.size(); i++) {
			SysMenu sysMenu = allSysMenu.get(i);
			sysMenuHashtable.put(sysMenu.getId(), sysMenu);
		}
		return sysMenuHashtable;
	}

	/**
	 * 根据ID删除
	 */
	@Override
	@SuppressWarnings("null")
	public int delete(Integer id) throws Exception {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			List<Integer> lstID = new ArrayList<Integer>();
			lstID.add(id);
			@SuppressWarnings("unchecked")
			List<Integer> lstSubSysMenuID = getSubSysMenuIDList(sqlSession, lstID);
			lstID.addAll(lstSubSysMenuID);
			result = batchDeleteSysMenu(sqlSession, lstID);
			sqlSession.commit();
		} catch (IOException e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getSubSysMenuIDList(SqlSession sqlSession, List<Integer> lstID) {
		if (null == lstID || lstID.size() == 0) {
			return new ArrayList<Integer>();
		}
		String idString = StringHelper.Join(lstID);
		String sql = "select id from sys_menu where parent_id in(" + idString + ")";
		List<Integer> lstSysMenuID = sqlSession.selectList("SysMenu.selectIntBySql", sql);
		List<Integer> lstSubSysMenuID = getSubSysMenuIDList(sqlSession, lstSysMenuID);
		lstSysMenuID.addAll(lstSubSysMenuID);
		return lstSysMenuID;
	}

	/**
	 * 
	 * @param sqlSession
	 * @param ids
	 * @return
	 */
	public int batchDeleteSysMenu(SqlSession sqlSession, List<Integer> lstSysMenuID) {
		String tableName = this.getTableName();
		int result = 0;
		String sql = String.format("select value from %s where id in (%s)", tableName, StringHelper.Join(lstSysMenuID));
		List<String> values = sqlSession.selectList("SysMenu.selectStringBySql", sql);
		SysFunctionManager.getInstance().batchDeleteByResource(sqlSession, values);
		SysRolePermissionManager.getInstance().deleteSysRolePermissionsByPermissions(sqlSession, values);
		sql = String.format("delete from %s where id in (%s)", tableName, StringHelper.Join(lstSysMenuID));
		result = sqlSession.delete("SysMenu.deleteSysMenuBySql", sql);
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
	 * 根据商户用户ID和菜单类型取得菜单列表
	 * 
	 * @param companyUserId
	 * @param menuTypeCode
	 * @return
	 * @throws IOException
	 */
	public ArrayList<SysMenu> getListByComapnyUserIdAndMenuTypeCode(int companyUserId, String menuTypeCode)
			throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("companyUserId", companyUserId);
			map.put("menuTypeCode", menuTypeCode);
			String typeName = this.getTypeName();
			String sqlCommandID = "SysMenuCustom.selectSysMenuByCompanyUserIdAndSysMenuTypeCode";
			List<Object> lstDataEntity = sqlSession.selectList(sqlCommandID, map);
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 取得商户用户的菜单列表
	 * 
	 * @param companyUserId
	 * @return
	 * @throws IOException
	 */
	public ArrayList<SysMenu> getListByComapnyUserId(int companyUserId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("companyUserId", companyUserId);
			String typeName = this.getTypeName();
			String sqlCommandID = "SysMenuCustom.selectSysMenuByCompanyUserId";
			List<Object> lstDataEntity = sqlSession.selectList(sqlCommandID, map);
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 根据
	 * 
	 * @param sysMenuTypeCode
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> getListBySysMenuTypeCode(String sysMenuTypeCode) throws Exception {
		String condition = String.format("sys_menu_type_code='%s'", sysMenuTypeCode);
		return getList(condition);
	}

	/**
	 * 根据角色和菜单权限取得权限列表
	 * 
	 * @param sysRoleId
	 * @param sysMenuTypeCode
	 * @return
	 * @throws Exception
	 */
	public JSONObject getListBySysRoleIdAndSysMenuTypeCode(Integer sysRoleId, String sysMenuTypeCode) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			JSONObject jResult = new JSONObject();
			List<SysRolePermission> rolePermissions = SysRolePermissionManager.getInstance()
					.getListBySysRoleId(sqlSession, sysRoleId);

			String condition = String.format("sys_menu_type_code = '%s' and value != '%s'", sysMenuTypeCode,
					sysMenuTypeCode);
			String orderBy = "display_order";
			List<ViewSysMenuSysFunction> lstViewSysMenuSysFunction = ViewSysMenuSysFunctionManager.getInstance()
					.getList(sqlSession, condition, orderBy);
			JSONArray jarrMenuFunction = JsonHelper.toJsonArray(lstViewSysMenuSysFunction);
			// 设置选中状态
			jarrMenuFunction = SysRolePermissionManager.getInstance().setJSONArrayPermission(rolePermissions,
					jarrMenuFunction);
			jResult.put("dataList", jarrMenuFunction);
			return jResult;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
	}

	/**
	 * 菜单是否存在
	 * 
	 * @param lstSysMenu
	 * @param sysMenuId
	 * @return
	 */
	public Boolean existsSysMenu(ArrayList<SysMenu> lstSysMenu, int sysMenuId) {
		for (SysMenu currentSysMenu : lstSysMenu) {
			if (currentSysMenu.getId().equals(sysMenuId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 添加父级菜单
	 * 
	 * @param lstSysMenu
	 * @param htSysMenu
	 */
	public void addParents(ArrayList<SysMenu> lstSysMenu, Hashtable<Integer, SysMenu> htSysMenu) {
		for (int i = 0; i <= lstSysMenu.size() - 1; i++) {
			SysMenu curentSysMenu = lstSysMenu.get(i);
			List<SysMenu> lstParentsMenu = SysMenuManager.getInstance().getParentsList(curentSysMenu.getId(),
					htSysMenu);
			for (SysMenu currentParentMenu : lstParentsMenu) {
				Boolean exists = SysMenuManager.getInstance().existsSysMenu(lstSysMenu, currentParentMenu.getId());
				if (!exists) {
					lstSysMenu.add(currentParentMenu);
				}
			}
		}
	}

	/**
	 * 排序
	 * 
	 * @param lstSysMenu
	 */
	public void sort(ArrayList<SysMenu> lstSysMenu) {
		for (int i = 0; i < lstSysMenu.size() - 1; i++) {
			for (int j = i + 1; j < lstSysMenu.size(); j++) {
				if (lstSysMenu.get(i).getDisplayOrder() > lstSysMenu.get(j).getDisplayOrder()) {
					SysMenu tmpSysMenu = lstSysMenu.get(i);
					lstSysMenu.set(i, lstSysMenu.get(j));
					lstSysMenu.set(j, tmpSysMenu);
				}
			}
		}
	}

	/**
	 * 取得商户菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> getCompanyMenuList() throws Exception {
		String condition = "sys_menu_type_code='Company'";
		List<SysMenu> lstSysMenu = this.getList(condition);
		return lstSysMenu;
	}
}
