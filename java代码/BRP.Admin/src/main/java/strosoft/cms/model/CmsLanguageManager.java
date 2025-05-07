package strosoft.cms.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.CodeDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.CmsChannelLanguage;
import strosoft.cms.data.CmsContent;
import strosoft.cms.data.CmsContentLanguage;
import strosoft.cms.data.CmsLanguage;
import strosoft.cms.data.CmsSite;
import strosoft.sys.data.SysSetting;
import strosoft.sys.model.SysSettingManager;

public class CmsLanguageManager extends CodeDataManager {
	private static CmsLanguageManager _instance;
	private static HashMap<Integer, Boolean> isOnCmslaunguageLsit;

	private CmsLanguageManager() {
		this.setTableName("cms_language");
		this.setTypeName("CmsLanguage");
	}

	public static CmsLanguageManager getInstance() throws IOException {
		if (_instance == null) {
			_instance = new CmsLanguageManager();
			try {
				isOnCmslaunguageLsit = getIsCmslaunguageOnList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _instance;
	}

	/*
	 * 添加CMS语言时，新增对语言的cmsChannelLanguage和cmsContentLanguage
	 */
	/*
	 * 添加
	 */
	public int add(SqlSession sqlSession, DataEntity dataEntity)
			throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException,
			NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
		CmsLanguage cmsLanguage = (CmsLanguage) dataEntity;
		String typeName = this.getTypeName();
		String sqlCommandID = typeName + ".insert" + typeName;
		Integer cmsSiteId = cmsLanguage.getCmsSiteId();
		int result = sqlSession.insert(sqlCommandID, cmsLanguage);
		// 获取该语言编码下的栏目ID和内容ID
		String cmsLanguageCode = cmsLanguage.getCode();
		/*
		 * List<CmsChannelLanguage> cmsChannelLanguages =
		 * CmsChannelLanguageManager.getInstance().getList(sqlSession, cmsLanguageCode);
		 * List<CmsContentLanguage> cmsContentLanguages =
		 * CmsContentLanguageManager.getInstance().getList(sqlSession, cmsLanguageCode);
		 */
		String cmsChannelLanguageCondition = " id not in (select cms_channel_id from cms_channel_language where cms_language_code = '"
				+ cmsLanguageCode + "') and cms_site_id = " + cmsSiteId + " ";
		// String cmsContentLanguageCondition = " and id not in (select cms_content_id
		// from cms_content_language where cms_language_code = '"+cmsLanguageCode+"')";
		// 重新添加该语言编码的栏目和内容
		String cmsChannelIdStr = CmsChannelManager.getInstance().getChannelIdStrByCondition(sqlSession,
				cmsChannelLanguageCondition);
		String condition = "";
		if (null == cmsChannelIdStr) {
			condition = "1=2";
		} else {
			condition = " id in (" + cmsChannelIdStr + ")";
		}
		String insertSql = "INSERT INTO cms_channel_language(cms_channel_id,cms_language_code) SELECT id,'"
				+ cmsLanguageCode + "' FROM cms_channel where " + condition;
		sqlSession.insert("CmsChannelLanguage.insertCmsChannelLanguageBySql", insertSql);
		if (null != cmsChannelIdStr) {
			condition = " cms_channel_id in (" + cmsChannelIdStr + ")";
		}
		insertSql = "INSERT INTO cms_content_language(cms_content_id,cms_language_code) SELECT id,'" + cmsLanguageCode
				+ "' FROM cms_content where " + condition;
		sqlSession.insert("CmsContentLanguage.insertCmsContentLanguageBySql", insertSql);
		return result;
	}

	/**
	 * 查看CMS多语言是开启
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	private static HashMap<Integer, Boolean> getIsCmslaunguageOnList() throws Exception {
		HashMap<Integer, Boolean> onCmslaunguageLsit = new HashMap<Integer, Boolean>();
		String sql = "select id,is_cms_language from cms_site";
		ArrayList<LinkedHashMap<String, Object>> cmsSiteList = MyBatisManager.getInstance().executeHashMapList(sql);
		for (int i = 0; i < cmsSiteList.size(); i++) {
			Integer cmsSiteId = (Integer) cmsSiteList.get(i).get("id");
			Integer isCmsLanguage = (Integer) cmsSiteList.get(i).get("is_cms_language");
			if (isCmsLanguage != null && isCmsLanguage > 0) {
				onCmslaunguageLsit.put(cmsSiteId, true);
			} else {
				onCmslaunguageLsit.put(cmsSiteId, false);
			}
		}
		return onCmslaunguageLsit;
	}

	/**
	 * 获取CMS多语言开启状态
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public Boolean getIsOnCmslaunguage(Integer cmsSiteId) {
		return isOnCmslaunguageLsit.get(cmsSiteId);
	}

	public int add(CmsLanguage entity) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = add(sqlSession, entity);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int add(SqlSession sqlSession, CmsLanguage entity) {
		int rows = sqlSession.insert("CmsLanguage.insertCmsLanguage", entity);
		return rows;
	}

	public int addList(List<CmsLanguage> list) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = addList(sqlSession, list);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int addList(SqlSession sqlSession, List<CmsLanguage> list) {
		int rows = sqlSession.insert("CmsLanguage.insertCmsLanguages", list);
		return rows;
	}

	public int delete(String code) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = delete(sqlSession, code);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int delete(SqlSession sqlSession, String code) {
		int rows = delete(sqlSession, getEntity(sqlSession, code));
		return rows;
	}

	public int delete(CmsLanguage entity) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = delete(sqlSession, entity);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int delete(SqlSession sqlSession, CmsLanguage entity) {
		CmsChannelLanguageManager.getInstance().deleteByCmsLanguageCode(sqlSession, entity.getCode());
		CmsContentLanguageManager.getInstance().deleteByCmsLanguageCode(sqlSession, entity.getCode());
		int rows = sqlSession.delete("CmsLanguage.deleteCmsLanguage", entity);
		return rows;
	}

	public int batchDelete(List<String> list) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			for (int i = 0; i < list.size(); i++) {
				rows += delete(sqlSession, list.get(i));
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			rows = 0;
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int batchDelete(SqlSession sqlSession, List<String> list) {
		int rows = 0;
		for (int i = 0; i < list.size(); i++) {
			rows += delete(sqlSession, list.get(i));
		}
		return rows;
	}

	public int batchDeleteByEntity(SqlSession sqlSession, List<CmsLanguage> list) {
		int rows = 0;
		for (int i = 0; i < list.size(); i++) {
			rows += delete(sqlSession, list.get(i));
		}
		return rows;
	}

	public DataEntity getDataEntity(String code) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		DataEntity newDataEntity = null;
		try {
			newDataEntity = getDataEntity(sqlSession, code);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return newDataEntity;
	}

	public DataEntity getDataEntity(SqlSession sqlSession, String code) {
		DataEntity newDataEntity = getEntity(sqlSession, code);
		return newDataEntity;
	}

	public CmsLanguage getEntity(String code) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		CmsLanguage newEntity = null;
		try {
			newEntity = getEntity(sqlSession, code);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return newEntity;
	}

	public CmsLanguage getEntity(SqlSession sqlSession, String code) {
		CmsLanguage entity = createEntity();
		entity.setCode(code);
		CmsLanguage newEntity = sqlSession.selectOne("CmsLanguage.selectCmsLanguage", entity);
		return newEntity;
	}

	public List<CmsLanguage> getList() throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsLanguage> list = null;
		try {
			list = getList(sqlSession);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return list;
	}

	public List<CmsLanguage> getList(SqlSession sqlSession) {
		List<CmsLanguage> list = sqlSession.selectList("CmsLanguage.selectCmsLanguages");
		return list;
	}

	public List<CmsLanguage> getList(String condition) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsLanguage> list = null;
		try {
			list = getList(sqlSession, condition);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return list;
	}

	public List<CmsLanguage> getList(SqlSession sqlSession, String condition) {
		List<CmsLanguage> list = sqlSession.selectList("CmsLanguage.selectCmsLanguageByCondition", condition);
		return list;
	}

	public List<CmsLanguage> getList(String condition, String orderBy) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsLanguage> list = null;
		try {
			list = getList(sqlSession, condition, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return list;
	}

	public List<CmsLanguage> getList(SqlSession sqlSession, String condition, String orderBy) {
		String conditionAndOrderBy = condition + " order by " + orderBy;
		List<CmsLanguage> list = sqlSession.selectList("CmsLanguage.selectCmsLanguageByCondition", conditionAndOrderBy);
		return list;
	}

	public List<CmsLanguage> getList(int pagesize, int pageindex) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsLanguage> list = null;
		try {
			list = getList(sqlSession, pagesize, pageindex);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return list;
	}

	public List<CmsLanguage> getList(SqlSession sqlSession, int pagesize, int pageindex) {
		String limt = " limt " + pagesize * (pageindex - 1) + "," + pagesize * pageindex;
		List<CmsLanguage> list = sqlSession.selectList("CmsLanguage.selectCmsLanguageByCondition", limt);
		return list;
	}

	public List<CmsLanguage> getListPage(String condition, int pagesize, int pageindex) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsLanguage> list = null;
		try {
			list = getList(sqlSession, condition, pagesize, pageindex);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return list;
	}

	public List<CmsLanguage> getList(SqlSession sqlSession, String condition, int pagesize, int pageindex) {
		String sql = condition + " limt " + pagesize * (pageindex - 1) + "," + pagesize * pageindex;
		List<CmsLanguage> list = sqlSession.selectList("CmsLanguage.selectCmsLanguageByCondition", sql);
		return list;
	}

	public List<CmsLanguage> getList(String condition, String orderBy, int pagesize, int pageindex) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsLanguage> list = null;
		try {
			list = getList(sqlSession, condition, orderBy, pagesize, pageindex);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return list;
	}

	public List<CmsLanguage> getList(SqlSession sqlSession, String condition, String orderBy, int pagesize,
			int pageindex) {
		String sql = condition + "  order by  " + orderBy + " limt " + pagesize * (pageindex - 1) + ","
				+ pagesize * pageindex;
		List<CmsLanguage> list = sqlSession.selectList("CmsLanguage.selectCmsLanguageByCondition", sql);
		return list;
	}

	public Integer getCount(String condition) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		Integer count = null;
		try {
			count = getCount(sqlSession, condition);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return count;
	}

	public Integer getCount(SqlSession sqlSession, String condition) {
		Integer rows = sqlSession.selectOne("CmsLanguage.selectCountByCondition", condition);
		return rows;
	}

	public int update(CmsLanguage entity) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = update(sqlSession, entity);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int update(SqlSession sqlSession, CmsLanguage entity) throws IOException {
		int rows = sqlSession.update("CmsLanguage.updateCmsLanguage", entity);
		return rows;
	}

	public CmsLanguage createEntity() {
		return new CmsLanguage();
	}

	public DataEntity createDataEntity() {
		return new CmsLanguage();
	}

	public int clear() throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = clear(sqlSession);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int clear(SqlSession sqlSession) {
		int rows = 0;
		List<CmsLanguage> list = getList(sqlSession);
		for (int i = 0; i < list.size(); i++) {
			rows += delete(sqlSession, list.get(i));
		}
		return rows;
	}

	public CmsLanguage getEntityByCode(String code) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		CmsLanguage entity = null;
		try {
			entity = getEntityByCode(sqlSession, code);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return entity;
	}

	public CmsLanguage getEntityByCode(SqlSession sqlSession, String code) {
		CmsLanguage entity = sqlSession.selectOne("selectCmsLanguageByCondition", "code=" + code);
		return entity;
	}

	public boolean existsCode(String code) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		Boolean flag = null;
		try {
			flag = existsCode(sqlSession, code);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return flag;
	}

	public boolean existsCode(SqlSession sqlSession, String code) {
		Integer rows = sqlSession.selectOne("CmsLanguage.selectIntBySql",
				"select count(id) where code= '" + code + "'");
		return rows > 0 ? true : false;
	}
}