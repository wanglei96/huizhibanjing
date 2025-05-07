package strosoft.cms.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.cms.data.CmsContent;
import strosoft.cms.data.CmsContentLanguage;
import strosoft.cms.data.CmsLanguage;

public class CmsContentLanguageManager extends IdDataManager {
	private static CmsContentLanguageManager _instance;

	private CmsContentLanguageManager() {
		this.setTableName("cms_content_language");
		this.setTypeName("CmsContentLanguage");
	}

	public static CmsContentLanguageManager getInstance() {
		if (_instance == null) {
			_instance = new CmsContentLanguageManager();
		}
		return _instance;
	}

	// 通过cmsContent新增
	public int addByCmsContent(SqlSession sqlSession, CmsContent cmsContent, String cmsLanguageCode, Integer cmsSiteId)
			throws IOException {
		// 新增语言内容
		Integer result = 0;
		String condition = " cms_site_id = " + cmsSiteId;
		List<CmsLanguage> cmsLanguages = CmsLanguageManager.getInstance().getList(sqlSession, condition);
		List<CmsContentLanguage> cmsContentLanguages = new ArrayList<CmsContentLanguage>();
		for (int i = 0; i < cmsLanguages.size(); i++) {
			CmsContentLanguage cmsContentLanguage = null;
			CmsLanguage cmsLanguage = cmsLanguages.get(i);
			if (cmsLanguage.getCode().equals(cmsLanguageCode)) {
				cmsContentLanguage = createEntityByCmsContent(cmsContent, cmsLanguageCode, null);
			} else {
				cmsContentLanguage = new CmsContentLanguage();
				cmsContentLanguage.setCmsContentId(cmsContent.getId());
				cmsContentLanguage.setCmsLanguageCode(cmsLanguage.getCode());
			}
			cmsContentLanguages.add(cmsContentLanguage);
		}
		if (cmsContentLanguages.size() > 0) {
			result = CmsContentLanguageManager.getInstance().addList(sqlSession, cmsContentLanguages);
		}
		return result;
	}

	// 通过cmsContent修改
	public int updateByCmsContent(SqlSession sqlSession, CmsContent cmsContent, String cmsLanguageCode,
			Integer cmsContentLanguageId) throws IOException {
		CmsContentLanguage cmsContentLanguage = createEntityByCmsContent(cmsContent, cmsLanguageCode,
				cmsContentLanguageId);
		return update(sqlSession, cmsContentLanguage);
	}

	// 传入cmsContent、语言编码、多语言内容ID创建多语言内容实体
	public CmsContentLanguage createEntityByCmsContent(CmsContent cmsContent, String cmsLanguageCode,
			Integer cmsContentLanguageId) {
		CmsContentLanguage cmsContentLanguage = new CmsContentLanguage();
		cmsContentLanguage.setId(cmsContentLanguageId);
		cmsContentLanguage.setTitle(cmsContent.getTitle());
		cmsContentLanguage.setSubTitle(cmsContent.getSubTitle());
		cmsContentLanguage.setUrl(cmsContent.getUrl());
		cmsContentLanguage.setText(cmsContent.getText());
		cmsContentLanguage.setSummary(cmsContent.getSummary());
		cmsContentLanguage.setAuthor(cmsContent.getAuthor());
		cmsContentLanguage.setSource(cmsContent.getSource());
		cmsContentLanguage.setRemark(cmsContent.getRemark());
		cmsContentLanguage.setDescription(cmsContent.getDescription());
		cmsContentLanguage.setKeywords(cmsContent.getKeywords());
		cmsContentLanguage.setThumbFileId(cmsContent.getThumbFileId());
		cmsContentLanguage.setAttachmentFileId(cmsContent.getAttachmentFileId());
		cmsContentLanguage.setVideoFileId(cmsContent.getVideoFileId());
		cmsContentLanguage.setCmsContentId(cmsContent.getId());
		cmsContentLanguage.setCmsLanguageCode(cmsLanguageCode);
		return cmsContentLanguage;
	}

	public int add(CmsContentLanguage entity) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int result = 0;
		try {
			result = add(sqlSession, entity);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return result;
	}

	public int add(SqlSession sqlSession, CmsContentLanguage entity) {
		int rows = sqlSession.insert("CmsContentLanguage.insertCmsContentLanguage", entity);
		int newId = entity.getId();
		if (newId > 0) {
			return newId;
		} else {
			return rows;
		}
	}

	public int addList(List<CmsContentLanguage> list) throws IOException {
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

	public int addList(SqlSession sqlSession, List<CmsContentLanguage> list) {
		int rows = sqlSession.insert("CmsContentLanguage.insertCmsContentLanguages", list);
		return rows;
	}

	@Override
	public int delete(Integer id) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = delete(sqlSession, id);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int delete(SqlSession sqlSession, Integer id) {
		int rows = delete(sqlSession, getEntity(sqlSession, id));
		return rows;
	}

	public int delete(CmsContentLanguage entity) throws IOException {
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

	public int delete(SqlSession sqlSession, CmsContentLanguage entity) {
		int rows = sqlSession.delete("CmsContentLanguage.deleteCmsContentLanguage", entity);
		return rows;
	}

	public int batchDelete(List<Integer> list) throws IOException {
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

	public int batchDelete(SqlSession sqlSession, List<Integer> list) {
		int rows = 0;
		for (int i = 0; i < list.size(); i++) {
			rows += delete(sqlSession, list.get(i));
		}
		return rows;
	}

	public int batchDeleteByEntity(SqlSession sqlSession, List<CmsContentLanguage> list) {
		int rows = 0;
		for (int i = 0; i < list.size(); i++) {
			rows += delete(sqlSession, list.get(i));
		}
		return rows;
	}

	@Override
	public DataEntity getDataEntity(Integer id) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		DataEntity newDataEntity = null;
		try {
			newDataEntity = getDataEntity(sqlSession, id);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return newDataEntity;
	}

	@Override
	public DataEntity getDataEntity(SqlSession sqlSession, Integer id) {
		DataEntity newDataEntity = getEntity(sqlSession, id);
		return newDataEntity;
	}

	public CmsContentLanguage getEntity(Integer id) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		CmsContentLanguage newEntity = null;
		try {
			newEntity = getEntity(sqlSession, id);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return newEntity;
	}

	public CmsContentLanguage getEntity(SqlSession sqlSession, Integer id) {
		CmsContentLanguage entity = createEntity();
		entity.setId(id);
		CmsContentLanguage newEntity = sqlSession.selectOne("CmsContentLanguage.selectCmsContentLanguage", entity);
		return newEntity;
	}

	public List<CmsContentLanguage> getList() throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> list = null;
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

	public List<CmsContentLanguage> getList(SqlSession sqlSession) {
		List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguages");
		return list;
	}

	@Override
	public List<CmsContentLanguage> getList(String condition) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> list = null;
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

	@Override
	public List<CmsContentLanguage> getList(SqlSession sqlSession, String condition) {
		List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCondition",
				condition);
		return list;
	}

	public List<CmsContentLanguage> getList(String condition, String orderBy) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> list = null;
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

	public List<CmsContentLanguage> getList(SqlSession sqlSession, String condition, String orderBy) {
		String conditionAndOrderBy = condition + " orderBy " + orderBy;
		List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCondition",
				conditionAndOrderBy);
		return list;
	}

	public List<CmsContentLanguage> getList(int pagesize, int pageindex) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> list = null;
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

	public List<CmsContentLanguage> getList(SqlSession sqlSession, int pagesize, int pageindex) {
		String limt = " limt " + pagesize * (pageindex - 1) + "," + pagesize * pageindex;
		List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCondition",
				limt);
		return list;
	}

	public List<CmsContentLanguage> getListPage(String condition, int pagesize, int pageindex) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> list = null;
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

	public List<CmsContentLanguage> getList(SqlSession sqlSession, String condition, int pagesize, int pageindex) {
		String sql = condition + " limt " + pagesize * (pageindex - 1) + "," + pagesize * pageindex;
		List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCondition",
				sql);
		return list;
	}

	public List<CmsContentLanguage> getList(String condition, String orderBy, int pagesize, int pageindex)
			throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> list = null;
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

	public List<CmsContentLanguage> getList(SqlSession sqlSession, String condition, String orderBy, int pagesize,
			int pageindex) {
		String sql = condition + " orderBy " + orderBy + " limt " + pagesize * (pageindex - 1) + ","
				+ pagesize * pageindex;
		List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCondition",
				sql);
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
		Integer rows = sqlSession.selectOne("CmsContentLanguage.selectCountByCondition", condition);
		return rows;
	}

	public int update(CmsContentLanguage entity) throws IOException {
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

	public int update(SqlSession sqlSession, CmsContentLanguage entity) throws IOException {
		int rows = sqlSession.update("CmsContentLanguage.updateCmsContentLanguage", entity);
		return rows;
	}

	public CmsContentLanguage createEntity() {
		return new CmsContentLanguage();
	}

	public DataEntity createDataEntity() {
		return new CmsContentLanguage();
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
		List<CmsContentLanguage> list = getList(sqlSession);
		for (int i = 0; i < list.size(); i++) {
			rows += delete(sqlSession, list.get(i));
		}
		return rows;
	}

	public int deleteByThumbFileId(Integer thumbFileId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = deleteByThumbFileId(sqlSession, thumbFileId);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int deleteByAttachmentFileId(Integer attachmentFileId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = deleteByAttachmentFileId(sqlSession, attachmentFileId);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int deleteByVideoFileId(Integer videoFileId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = deleteByVideoFileId(sqlSession, videoFileId);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int deleteByCmsContentId(Integer cmsContentId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = deleteByCmsContentId(sqlSession, cmsContentId);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int deleteByCmsLanguageCode(String cmsLanguageCode) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = deleteByCmsLanguageCode(sqlSession, cmsLanguageCode);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}

	public int deleteByThumbFileId(SqlSession sqlSession, Integer thumbFileId) {
		List<CmsContentLanguage> list = getListByThumbFileId(sqlSession, thumbFileId);
		int rows = batchDeleteByEntity(sqlSession, list);
		return rows;
	}

	public int deleteByAttachmentFileId(SqlSession sqlSession, Integer attachmentFileId) {
		List<CmsContentLanguage> list = getListByAttachmentFileId(sqlSession, attachmentFileId);
		int rows = batchDeleteByEntity(sqlSession, list);
		return rows;
	}

	public int deleteByVideoFileId(SqlSession sqlSession, Integer videoFileId) {
		List<CmsContentLanguage> list = getListByVideoFileId(sqlSession, videoFileId);
		int rows = batchDeleteByEntity(sqlSession, list);
		return rows;
	}

	public int deleteByCmsContentId(SqlSession sqlSession, Integer cmsContentId) {
		List<CmsContentLanguage> list = getListByCmsContentId(sqlSession, cmsContentId);
		int rows = batchDeleteByEntity(sqlSession, list);
		return rows;
	}

	public int deleteByCmsLanguageCode(SqlSession sqlSession, String cmsLanguageCode) {
		List<CmsContentLanguage> list = getListByCmsLanguageCode(sqlSession, cmsLanguageCode);
		int rows = batchDeleteByEntity(sqlSession, list);
		return rows;
	}

	public List<CmsContentLanguage> getListByThumbFileId(Integer thumbFileId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> entitys = null;
		try {
			entitys = getListByThumbFileId(sqlSession, thumbFileId);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return entitys;
	}

	public List<CmsContentLanguage> getListByAttachmentFileId(Integer attachmentFileId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> entitys = null;
		try {
			entitys = getListByAttachmentFileId(sqlSession, attachmentFileId);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return entitys;
	}

	public List<CmsContentLanguage> getListByVideoFileId(Integer videoFileId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> entitys = null;
		try {
			entitys = getListByVideoFileId(sqlSession, videoFileId);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return entitys;
	}

	public List<CmsContentLanguage> getListByCmsContentId(Integer cmsContentId) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> entitys = null;
		try {
			entitys = getListByCmsContentId(sqlSession, cmsContentId);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return entitys;
	}

	public List<CmsContentLanguage> getListByCmsLanguageCode(String cmsLanguageCode) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContentLanguage> entitys = null;
		try {
			entitys = getListByCmsLanguageCode(sqlSession, cmsLanguageCode);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return entitys;
	}

	public List<CmsContentLanguage> getListByThumbFileId(SqlSession sqlSession, Integer thumbFileId) {
		List<CmsContentLanguage> entitys = sqlSession
				.selectList("CmsContentLanguage.selectCmsContentLanguageByThumbFileId", thumbFileId);
		return entitys;
	}

	public List<CmsContentLanguage> getListByAttachmentFileId(SqlSession sqlSession, Integer attachmentFileId) {
		List<CmsContentLanguage> entitys = sqlSession
				.selectList("CmsContentLanguage.selectCmsContentLanguageByAttachmentFileId", attachmentFileId);
		return entitys;
	}

	public List<CmsContentLanguage> getListByVideoFileId(SqlSession sqlSession, Integer videoFileId) {
		List<CmsContentLanguage> entitys = sqlSession
				.selectList("CmsContentLanguage.selectCmsContentLanguageByVideoFileId", videoFileId);
		return entitys;
	}

	public List<CmsContentLanguage> getListByCmsContentId(SqlSession sqlSession, Integer cmsContentId) {
		List<CmsContentLanguage> entitys = sqlSession
				.selectList("CmsContentLanguage.selectCmsContentLanguageByCmsContentId", cmsContentId);
		return entitys;
	}

	public List<CmsContentLanguage> getListByCmsLanguageCode(SqlSession sqlSession, String cmsLanguageCode) {
		List<CmsContentLanguage> entitys = sqlSession
				.selectList("CmsContentLanguage.selectCmsContentLanguageByCmsLanguageCode", cmsLanguageCode);
		return entitys;
	}

	public CmsContentLanguage getEntityById(Integer id) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		CmsContentLanguage entity = null;
		try {
			entity = getEntityById(sqlSession, id);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return entity;
	}

	public CmsContentLanguage getEntityById(SqlSession sqlSession, Integer id) {
		CmsContentLanguage entity = sqlSession.selectOne("selectCmsContentLanguageByCondition", "id=" + id);
		return entity;
	}

	public boolean existsId(Integer id) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		Boolean flag = null;
		try {
			flag = existsId(sqlSession, id);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return flag;
	}

	public boolean existsId(SqlSession sqlSession, Integer id) {
		Integer rows = sqlSession.selectOne("CmsContentLanguage.selectIntBySql",
				"select count(id) where id= '" + id + "'");
		return rows > 0 ? true : false;
	}
}