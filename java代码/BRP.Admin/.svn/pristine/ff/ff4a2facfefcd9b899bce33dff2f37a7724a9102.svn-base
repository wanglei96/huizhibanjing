package strosoft.sys.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONObject;
import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.JsonHelper;
import strosoft.cms.data.CmsChannel;
import strosoft.sys.data.SysRoleCmsChannelPermission;
import strosoft.sys.data.SysRoleCmsChannelPermissionMaster;
import strosoft.sys.data.SysRolePermission;

public class SysRoleCmsChannelPermissionManager extends SysRoleCmsChannelPermissionMaster {
	private static SysRoleCmsChannelPermissionManager _instance;

	public static SysRoleCmsChannelPermissionManager getInstance() {
		if (_instance == null) {
			_instance = new SysRoleCmsChannelPermissionManager();
		}
		return _instance;
	}

	private SysRoleCmsChannelPermissionManager() {
		this.setTableName("sys_role_cms_channel_permission");
		this.setTypeName("SysRoleCmsChannelPermission");
	}

	/*
	 * 取得实体
	 */
	public SysRolePermission getEntity(int SysRolePermissionID) throws Exception {
		return (SysRolePermission) super.getDataEntity(SysRolePermissionID);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysRoleCmsChannelPermission> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	/*
	 * 多项添加
	 */
	public int add(int sysRoleId, int cmsSiteId, List<Integer> lstValues) throws Exception {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			String[] sqls = new String[lstValues.size() + 1];
			sqls[0] = "delete from sys_role_cms_channel_permission where sys_role_id = " + sysRoleId;
			sqlSession.delete("SysRoleCmsChannelPermission.deleteSysRoleCmsChannelPermissionBySysRoleSql", sqls[0]);
			for (int i = 0; i < lstValues.size(); i++) {
				Integer value = lstValues.get(i);
				sqls[i + 1] = String.format(
						"insert into sys_role_cms_channel_permission (sys_role_id,cms_channel_id,cms_site_id) values (%s, '%s','%s')",
						sysRoleId, value, cmsSiteId);
			}
			for (int i = 1; i < sqls.length; i++) {
				sqlSession.insert("SysRoleCmsChannelPermission.addSysRoleCmsChannelPermissionBySysRoleSql", sqls[i]);
			}
			sqlSession.commit();
			result = lstValues.size();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/*
	 * 多项添加
	 */
	public int add(int sysRoleId, JSONObject values) throws Exception {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			String sql = "delete from sys_role_cms_channel_permission where sys_role_id = " + sysRoleId;
			sqlSession.delete("SysRoleCmsChannelPermission.deleteSysRoleCmsChannelPermissionBySysRoleSql", sql);
			Iterator<String> iterator = values.keys();
			while (iterator.hasNext()) {
				String key = iterator.next();
				List<Integer> permissions = JsonHelper.getIntArray(values, key);
				int cmsSiteId = Integer.parseInt(key);
				for (int i = 0; i < permissions.size(); i++) {
					Integer value = permissions.get(i);
					sql = String.format(
							"insert into sys_role_cms_channel_permission (sys_role_id,cms_channel_id,cms_site_id) values (%s, '%s','%s')",
							sysRoleId, value, cmsSiteId);
					sqlSession.insert("SysRoleCmsChannelPermission.addSysRoleCmsChannelPermissionBySysRoleSql", sql);
					result++;
				}
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/*
	 * 根据ID查询列表
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SysRoleCmsChannelPermission> getListBySysRoleIdAndCmsSiteId(String sysRoleId, int cmsSiteId)
			throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String sqlCommandID = "SysRoleCmsChannelPermission.selectSysRoleCmsChannelBysSql";
			String sql = "select * from sys_role_cms_channel_permission where sys_role_id = " + sysRoleId
					+ " and cms_site_id=" + cmsSiteId;
			List<SysRoleCmsChannelPermission> lstDataEntity = sqlSession.selectList(sqlCommandID, sql);
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 根据ID查询列表
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SysRoleCmsChannelPermission> getListBySysRoleId(String sysRoleId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String sqlCommandID = "SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissionBySysRoleId";
			List<SysRoleCmsChannelPermission> lstDataEntity = sqlSession.selectList(sqlCommandID,
					Integer.parseInt(sysRoleId));
			return super.toList(lstDataEntity);
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 根据栏目id查找拥有此栏目权限的角色id
	 */
	public List<Integer> getSysRoleIdByCmsChannelId(SqlSession sqlSession, Integer cmsChannelId) {
		String sql = "select sys_role_id from sys_role_cms_channel_permission where cms_channel_id =" + cmsChannelId;
		List<Integer> sysRoleList = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleBysCmsChannelId",
				sql);
		return sysRoleList;
	}

	/*
	 * 栏目id对多角色id添加
	 */
	public void AddSysRoleIdListAndCmsChannelId(SqlSession sqlSession, List<Integer> sysRoleList,
			CmsChannel cmsChannel) {
		String sql = "";
		SysRoleCmsChannelPermission sysRoleCmsChannelPermission = new SysRoleCmsChannelPermission();
		for (int i = 0; i < sysRoleList.size(); i++) {
			sysRoleCmsChannelPermission.setCmsChannelId(cmsChannel.getId());
			sysRoleCmsChannelPermission.setSysRoleId(sysRoleList.get(i));
			sysRoleCmsChannelPermission.setCmsSiteId(cmsChannel.getCmsSiteId());
			sqlSession.selectOne("SysRoleCmsChannelPermission.insertSysRoleCmsChannelPermission",
					sysRoleCmsChannelPermission);
		}
	}

	/**
	 * 根据栏目ID删除
	 * 
	 * @param sqlSession
	 * @param cmsChannelId
	 * @return
	 */
	public int deleteByCmsChannelId(SqlSession sqlSession, Integer cmsChannelId) {
		String condition = String.format("cms_channel_id=%s", cmsChannelId);
		int rows = 0;
		try {
			rows = this.deleteByCondition(sqlSession, condition);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rows;

	}

	/**
	 * 根据网站Id删除
	 * 
	 * @param sqlSession
	 * @param cmsSiteId
	 * @return
	 */
	public int deleteByCmsSiteId(SqlSession sqlSession, Integer cmsSiteId) {
		String condition = String.format("cms_site_id=%s", cmsSiteId);
		int rows = 0;
		try {
			rows = this.deleteByCondition(sqlSession, condition);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rows;
	}
}
