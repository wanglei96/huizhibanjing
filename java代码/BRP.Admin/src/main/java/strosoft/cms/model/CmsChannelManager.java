package strosoft.cms.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.CmsChannelChannelGroup;
import strosoft.cms.data.CmsChannelMaster;
import strosoft.cms.data.CmsContent;
import strosoft.cms.data.ViewCmsChannel;
import strosoft.sys.data.SysUserRole;
import strosoft.sys.model.SysRoleCmsChannelPermissionManager;

public class CmsChannelManager extends CmsChannelMaster {
	private static CmsChannelManager _instance;

	private CmsChannelManager() {
		this.setTableName("cms_channel");
		this.setTypeName("CmsChannel");
	}

	public static CmsChannelManager getInstance() {
		if (_instance == null) {
			_instance = new CmsChannelManager();
		}
		return _instance;
	}

	/*
	 * 取得实体
	 */
	public CmsChannel getEntity(int cmsChannelId) throws Exception {
		return (CmsChannel) super.getDataEntity(cmsChannelId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<CmsChannel> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	/*
	 * 根据名称获得栏目
	 */
	public CmsChannel getDataEntityByName(String attributeValue) {

		SqlSession sqlSession;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			return sqlSession.selectOne("CmsChannel.selectCmsChannelByName", attributeValue);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * 根据code获取栏目
	 */
	public CmsChannel getEntityByCode(String attributeValue) {
		SqlSession sqlSession;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			return sqlSession.selectOne("CmsChannel.selectCmsChannelByCode", attributeValue);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 新增栏目
	 * 
	 * @see strosoft.app.common.IdDataManager#add(strosoft.app.data.DataEntity)
	 */
	public int add(CmsChannel dataEntity, ArrayList<CmsChannelChannelGroup> lstCmsChannelChannelGroup)
			throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			// 添加栏目后，返回栏目的id，添加所属栏目组信息
			int newCmsChannelId = add(dataEntity, lstCmsChannelChannelGroup);
			return newCmsChannelId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 新增栏目
	 * 
	 * @see strosoft.app.common.IdDataManager#add(strosoft.app.data.DataEntity)
	 */
	public int add(SqlSession sqlSession, CmsChannel dataEntity,
			ArrayList<CmsChannelChannelGroup> lstCmsChannelChannelGroup) throws Exception {
		// 添加栏目后，返回栏目的id，添加所属栏目组信息
		int newCmsChannelId = addCmsChannel(sqlSession, dataEntity);
		if (lstCmsChannelChannelGroup != null) {
			for (CmsChannelChannelGroup currentCmsChannelChannelGroup : lstCmsChannelChannelGroup) {
				currentCmsChannelChannelGroup.setCmsChannelId(newCmsChannelId);
				CmsChannelChannelGroupManager.getInstance().add(sqlSession, currentCmsChannelChannelGroup);
			}
		}
		return newCmsChannelId;
	}

	/*
	 * 添加设置display_order,传入SqlSession
	 */
	public int addCmsChannel(SqlSession sqlSession, CmsChannel dataEntity)
			throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException,
			NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
		dataEntity.setDisplayOrder(
				getDispalyOrderWhenAdd(sqlSession, "cms_channel", " parent_id =" + dataEntity.getParentId(), "Behind"));
		int newCmsChannelId = super.add(sqlSession, dataEntity);
		// 设置权限，拥有此栏目的父栏目权限的角色都获取此栏目权限
		List<Integer> sysRoleList = SysRoleCmsChannelPermissionManager.getInstance()
				.getSysRoleIdByCmsChannelId(sqlSession, dataEntity.getParentId());
		SysRoleCmsChannelPermissionManager.getInstance().AddSysRoleIdListAndCmsChannelId(sqlSession, sysRoleList,
				dataEntity);
		return newCmsChannelId;
	}

	/*
	 * 修改栏目
	 * 
	 * @see strosoft.app.common.IdDataManager#add(strosoft.app.data.DataEntity)
	 */
	public int update(CmsChannel dataEntity, ArrayList<CmsChannelChannelGroup> lstCmsChannelChannelGroup)
			throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		Integer result = -1;
		try {
			result = update(sqlSession, dataEntity, lstCmsChannelChannelGroup);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	public int update(SqlSession sqlSession, CmsChannel dataEntity,
			ArrayList<CmsChannelChannelGroup> lstCmsChannelChannelGroup) throws Exception {
		int cmsChannelId = dataEntity.getId();
		// 修改栏目后，删除原所属栏目组信息，添加新所属栏目组信息
		CmsChannel oldChannel = (CmsChannel) getDataEntity(cmsChannelId);
		dataEntity.setCreateTime(oldChannel.getCreateTime());
		int result = super.update(sqlSession, dataEntity);
		CmsChannelChannelGroupManager.getInstance().deleteByCmsChannelId(sqlSession, cmsChannelId);
		if (lstCmsChannelChannelGroup != null) {
			for (CmsChannelChannelGroup currentCmsChannelChannelGroup : lstCmsChannelChannelGroup) {
				currentCmsChannelChannelGroup.setCmsChannelId(cmsChannelId);
				CmsChannelChannelGroupManager.getInstance().add(sqlSession, currentCmsChannelChannelGroup);
			}
		}
		sqlSession.commit();
		return result;
	}

	/*
	 * 根据cmsChannelGroupCode获取循环内容
	 */
	public List<ViewCmsChannel> getList(HashMap<String, String> conditions) {
		SqlSession sqlSession;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			return sqlSession.selectList("CmsChannel.selectCmsChannelByConditions", conditions);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 取得所属栏目组关系
	 */
	public ArrayList<CmsChannelChannelGroup> getCmsChannelChannelGroupList(JSONObject jData) {
		if (!jData.containsKey("cmsChannelGroupCodes")) {
			return null;
		}
		JSONArray groupCodes = jData.getJSONArray("cmsChannelGroupCodes");
		ArrayList<CmsChannelChannelGroup> lstCmsChannelChannelGroup = new ArrayList<CmsChannelChannelGroup>();
		for (int i = 0; i < groupCodes.size(); i++) {
			CmsChannelChannelGroup newCmsChannelChannelGroup = new CmsChannelChannelGroup();
			newCmsChannelChannelGroup.setCmsChannelGroupCode(groupCodes.getString(i));
			lstCmsChannelChannelGroup.add(newCmsChannelChannelGroup);
		}
		return lstCmsChannelChannelGroup;
	}

	/*
	 * 删除,传入SqlSession
	 */
	public int delete(int id, ArrayList<CmsContent> lstCmsContent) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		String groupCode = "";
		String alSql = "";
		try {
			int deleteId = super.delete(sqlSession, "cms_content", id);
			sqlSession.commit();
			return deleteId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	// 删除单个栏目,传入Sqlsession
	public int deletOne(SqlSession sqlSession, int cmsChannelIds) throws Exception {
		List<Integer> list = new ArrayList<Integer>();
		list.add(cmsChannelIds);
		int result = deleteCmsChannels(sqlSession, list);
		return result;
	}

	// 删除单个栏目
	public int delet(int cmsChannelIds) throws Exception {
		List<Integer> list = new ArrayList<Integer>();
		list.add(cmsChannelIds);
		int result = batchDeleteCmsChannels(list);
		return result;
	}

	// 批量删除栏目
	public int batchDeleteCmsChannels(List<Integer> cmsChannelIds) throws Exception {
		System.out.println(cmsChannelIds);
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			int result = deleteCmsChannels(sqlSession, cmsChannelIds);
			sqlSession.commit();
			System.out.println("一个删除：" + result + "个栏目");
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	// 获取当前栏目上level级id
	public Integer getUpLevelByIdAndLevel(SqlSession sqlSession, int cmsChannelIds, int level) {
		Integer cmsChannelId = cmsChannelIds;
		for (int i = 0; i < level; i++) {
			String sql = "select parent_id from cms_channel where id=" + cmsChannelId;
			cmsChannelId = sqlSession.selectOne("CmsChannel.selectIntBySql", sql);
			if (cmsChannelId == null) {
				return null;
			}
		}
		return cmsChannelId;
	}

	// 获取当前栏目下level级id
	public List<Integer> getSubLevelByIdAndLevel(SqlSession sqlSession, int cmsChannelIds, int level) {
		int cmsChannelId = cmsChannelIds;
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(cmsChannelId);
		for (int i = 0; i < level; i++) {
			ids = getSubCmsChannelsIds(sqlSession, ids);
		}
		return ids;
	}

	// 获取对应站点的首页id
	public int getIndexId(SqlSession sqlSession, int cmsSiteId) {
		String sql = "select id from cms_channel where (parent_id=0 or parent_id is null) and cms_site_id=" + cmsSiteId;
		int rootCmsChannelId = sqlSession.selectOne("CmsChannel.selectIntBySql", sql);
		return rootCmsChannelId;
	}

	public int deleteCmsChannel(Integer cmsChannelId) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			int result = CmsChannelManager.getInstance().deleteCmsChannel(sqlSession, cmsChannelId);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 删除栏目
	 * 
	 * @param sqlSession
	 * @param cmsChannelId
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsChannel(SqlSession sqlSession, Integer cmsChannelId) throws Exception {
		int result = 0;
		// 获取子栏目
		List<Integer> lstSubCmsChannelId = getSubCmsChannelIdList(sqlSession, cmsChannelId);
		if (lstSubCmsChannelId.size() > 0) {
			result += deleteCmsChannels(sqlSession, lstSubCmsChannelId);
		}
		// 删除栏目下内容
		deleteCmsContents(sqlSession, cmsChannelId);
		// 删除栏目组关系
		deleteCmsChannelGroupById(sqlSession, cmsChannelId);
		// 删除栏目本身
		CmsChannel cmsChannel = this.getEntity(sqlSession, cmsChannelId);
		if (null == cmsChannel) {
			return 0;
		}
		// 删除对应对应多语言栏目
		deleteCmsChannelLanguageWhenDelete(sqlSession, cmsChannelId);
		setDispalyOrderWhenDelete(sqlSession, cmsChannelId, "cms_channel", "parent_id =" + cmsChannel.getParentId());
		SysRoleCmsChannelPermissionManager.getInstance().deleteByCmsChannelId(sqlSession, cmsChannelId);
		int deletNum = this.deleteCmsChannelById(sqlSession, cmsChannelId);
		if (deletNum > 0) {
			result++;
		}
		return result;
	}

	/**
	 * 批量删除栏目
	 * 
	 * @param lstCmsChannelId
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsChannels(List<Integer> lstCmsChannelId) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			int result = CmsChannelManager.getInstance().deleteCmsChannels(sqlSession, lstCmsChannelId);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 批量删除栏目
	 * 
	 * @param sqlSession
	 * @param lstCmsChannelId
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsChannels(SqlSession sqlSession, List<Integer> lstCmsChannelId) throws Exception {
		int result = 0;
		int result2 = 0;
		for (int i = 0; i < lstCmsChannelId.size(); i++) {
			// 获取子栏目
			List<Integer> lstSubCmsChannelId = getSubCmsChannelIdList(sqlSession, lstCmsChannelId.get(i));
			if (lstSubCmsChannelId.size() > 0) {
				result2 += deleteCmsChannels(sqlSession, lstSubCmsChannelId);
			}
			// 删除栏目下内容
			deleteCmsContents(sqlSession, lstCmsChannelId.get(i));
			// 删除栏目组关系
			deleteCmsChannelGroupById(sqlSession, lstCmsChannelId.get(i));
			// 删除栏目本身
			CmsChannel cmsChannel = sqlSession.selectOne("CmsChannel.selectCmsChannel", lstCmsChannelId.get(i));
			if (null == cmsChannel) {
				continue;
			}
			// 删除对应对应多语言栏目
			deleteCmsChannelLanguageWhenDelete(sqlSession, lstCmsChannelId.get(i));
			setDispalyOrderWhenDelete(sqlSession, lstCmsChannelId.get(i), "cms_channel",
					"parent_id =" + cmsChannel.getParentId());
			int deletNum = deleteCmsChannelById(sqlSession, lstCmsChannelId.get(i));
			if (deletNum > 0) {
				result++;
			}
		}
		return result + result2;
	}

	/**
	 * 取得子栏目ID列表
	 * 
	 * @param sqlSession
	 * @param parentId
	 * @return
	 */
	public List<Integer> getSubCmsChannelIdList(SqlSession sqlSession, Integer parentId) {
		List<CmsChannel> lstSubCmsChannel = this.getListByParentId(sqlSession, parentId);
		List<Integer> lstSubCmsChannelId = new ArrayList<>();
		for (CmsChannel currentCmsChannel : lstSubCmsChannel) {
			lstSubCmsChannelId.add(currentCmsChannel.getId());
		}
		return lstSubCmsChannelId;
	}

	/**
	 * 根据父节点ID取得列表
	 * 
	 * @param sqlSession
	 * @param parentId
	 * @return
	 */
	public List<CmsChannel> getListByParentId(SqlSession sqlSession, Integer parentId) {
		String condition = "parent_id=" + parentId;
		return getList(sqlSession, condition);
	}

	// 根据channelid删除栏目下的内容
	public int deleteCmsContents(SqlSession sqlSession, int cmsChannelId) throws Exception {
		System.out.println("开始删除栏目下内容");
		int result = CmsContentManager.getInstance().deleteByCmsChannelId(sqlSession, cmsChannelId);
		return result;
	}

	// 根据channelid删除栏目
	public int deleteCmsChannelById(SqlSession sqlSession, int cmsChannelId) {
		System.out.println("开始删除栏目");

		int result = sqlSession.delete("CmsChannel.deleteCmsChannel", cmsChannelId);
		return result;
	}

	// 根据channelid删除所属栏目组关系
	public int deleteCmsChannelGroupById(SqlSession sqlSession, int cmsChannelId) throws Exception {
		System.out.println("开始删除栏目组关联关系");
		int result = CmsChannelChannelGroupManager.getInstance().deleteByCmsChannelId(sqlSession, cmsChannelId);
		return result;
	}

	// 根据sql修改匹配栏目模板
	public int updateCmsChannelBySql(SqlSession sqlSession, String sql) throws Exception {
		System.out.println("开始修改匹配栏目模板");
		int result = sqlSession.update("CmsChannel.updateCmsChannelBysql", sql);
		return result;
	}

	// 根据channelidList获取子栏目ids
	public List<Integer> getSubCmsChannelsIds(SqlSession sqlSession, List<Integer> ids) {
		if (ids.size() < 1) {
			return new ArrayList<Integer>();
		}
		System.out.println("开始获取子栏目");
		String sql = "select id from cms_channel where parent_id in (" + getIdsString(ids) + ")";
		List<Integer> result = sqlSession.selectList("CmsChannel.selectIntBySql", sql);
		return result;
	}

	// 根据IdList获取idString
	public String getIdsString(List<Integer> ids) {
		StringBuffer idsString = new StringBuffer("");
		for (int i = 0; i < ids.size() - 1; i++) {
			idsString.append(ids.get(i) + ",");
		}
		idsString.append(ids.get(ids.size() - 1));
		return idsString.toString();
	}

	// 根据operatorId获取id数组
	public List<Integer> getPermissionChannelIdList(SqlSession sqlSession, JSONObject jData) {
		Integer operatorId = jData.getInt("operatorId");
		Integer cmsSiteId = jData.getInt("cmsSiteId");
		SysUserRole userRole = sqlSession.selectOne("SysUserRole.selectSysUserRoleBySysUserId", operatorId);
		List<Integer> result = new ArrayList<Integer>();
		String sql = "";
		if ((userRole.getSysRoleId() - 1) == 0) {
			sql = "SELECT id FROM cms_channel where cms_site_id=" + cmsSiteId;
			result = sqlSession.selectList("CmsChannel.selectIntBySql", sql);
			return result;
		}
		sql = "SELECT cms_channel_id FROM sys_role_cms_channel_permission WHERE sys_role_id in (";
		sql += "SELECT sys_role_id FROM sys_user_role WHERE sys_user_id = '" + operatorId + "')";
		result = sqlSession.selectList("CmsChannel.selectIntBySql", sql);
		return result;
	}

	// 根据operatorId获取idString
	public String getPermissionChannelIdStr(SqlSession sqlSession, Integer operatorId, String type) {
		SysUserRole userRole = sqlSession.selectOne("SysUserRole.selectSysUserRoleBySysUserId", operatorId);
		if ((userRole.getSysRoleId() - 1) == 0) {
			return "";
		}
		String sql = "SELECT cms_channel_id FROM sys_role_cms_channel_permission WHERE sys_role_id in (";
		sql += "SELECT sys_role_id FROM sys_user_role WHERE sys_user_id = '" + operatorId + "')";
		List<Integer> result = sqlSession.selectList("CmsChannel.selectIntBySql", sql);
		if (result.size() < 1) {
			return " and 1=2";
		}
		if (type.equals("channel")) {
			return " and id in (" + getIdsString(result) + ")";
		}
		return " and cms_channel_id in (" + getIdsString(result) + ")";
	}

	// 根据条件获取idString
	public String getChannelIdStrByCondition(SqlSession sqlSession, String condition) {
		String sql = "SELECT id FROM cms_channel WHERE " + condition;
		List<Integer> result = sqlSession.selectList("CmsChannel.selectIntBySql", sql);
		if (result.size() < 1) {
			return null;
		}
		return getIdsString(result);
	}

	/*
	 * 根据管理员角色取得栏目权限
	 */
	public String getPermissionByRole(HttpServletRequest request, String type) throws IOException {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		Integer operatorId = null;
		SqlSession sqlSession = null;
		try {
			operatorId = jData.getInt("operatorId");
		} catch (Exception e) {
			// e.printStackTrace();
			return "";
		}
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			return CmsChannelManager.getInstance().getPermissionChannelIdStr(sqlSession, operatorId, type);
		} catch (Exception e) {
			e.printStackTrace();
			return "1=2";
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * 新增时设置序号
	 */
	public int getDispalyOrderWhenAdd(SqlSession sqlSession, String tableName, String condition, String addPosition) {
		int displayOrder = 0;
		// 序号从最后添加
		if (addPosition.equalsIgnoreCase("Behind")) {
			String sql = "select max(display_order) from " + tableName + " where " + condition;
			Integer maxDisplayOrder = sqlSession.selectOne("CmsChannel.selectIntBySql", sql);
			if (null == maxDisplayOrder) {
				displayOrder = 1;
			} else {
				displayOrder = maxDisplayOrder + 1;
			}
		}
		// 序号从最前面添加
		if (addPosition.equalsIgnoreCase("Front")) {
			String sql = "update " + tableName + " Set display_order=(display_order+1) where " + condition;
			sqlSession.update("CmsChannel.updateBysql", sql);
			displayOrder = 1;
		}
		return displayOrder;
	}

	/*
	 * 删除时设置序号
	 */
	public void setDispalyOrderWhenDelete(SqlSession sqlSession, Integer id, String tableName, String condition)
			throws IOException {
		String sql = "select id from " + tableName + " where " + condition + " and display_order>"
				+ "(select display_order from " + tableName + " where id = " + id + ")";
		List<Integer> idList = sqlSession.selectList("CmsChannel.selectIntBySql", sql);
		if (null == idList || idList.size() < 1) {
			return;
		}
		sql = "update " + tableName + " set display_order=(display_order-1) where id in (" + getIdsString(idList)
				+ ") and " + condition;
		MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
	}

	// 新增栏目时，添加栏目对应多语言栏目（cmsChannelLanguage）
	/*
	 * public Integer addCmsChannelLanguagesWhenAdd(SqlSession sqlSession,Integer
	 * cmsChannelId){ Integer result = 0 ; List<CmsLanguage> cmsLanguages =
	 * CmsLanguageManager.getInstance().getList(sqlSession);
	 * List<CmsChannelLanguage> cmsChannelLanguages = new
	 * ArrayList<CmsChannelLanguage>(); for(int i=0;i<cmsLanguages.size();i++){
	 * CmsChannelLanguage cmsChannelLanguage = new CmsChannelLanguage();
	 * cmsChannelLanguage.setCmsChannelId(cmsChannelId);
	 * cmsChannelLanguage.setCmsLanguageCode(cmsLanguages.get(i).getCode());
	 * cmsChannelLanguages.add(cmsChannelLanguage); }
	 * if(cmsChannelLanguages.size()>0){ result =
	 * CmsChannelLanguageManager.getInstance().addList(sqlSession,
	 * cmsChannelLanguages); } return result; }
	 */
	// 删除时，删除栏目对应多语言栏目（cmsChannelLanguage）
	public Integer deleteCmsChannelLanguageWhenDelete(SqlSession sqlSession, Integer cmsChannelId) {
		CmsChannelLanguageManager.getInstance().deleteByCmsChannelId(sqlSession, cmsChannelId);
		return 1;
	}

	// 删除时，删除栏目对应多语言栏目（cmsChannelLanguage）
	public Integer deleteCmsChannelLanguageWhenDelete(SqlSession sqlSession, List<Integer> cmsChannelIds) {
		Integer result = 0;
		for (int i = 0; i < cmsChannelIds.size(); i++) {
			result += deleteCmsChannelLanguageWhenDelete(sqlSession, cmsChannelIds.get(i));
		}
		return result;
	}
}