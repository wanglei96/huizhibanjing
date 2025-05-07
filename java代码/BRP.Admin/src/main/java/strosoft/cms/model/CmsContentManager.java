package strosoft.cms.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.app.util.StringHelper;
import strosoft.cms.data.CmsContent;
import strosoft.cms.data.CmsContentMaster;

public class CmsContentManager extends CmsContentMaster {
	private static CmsContentManager _instance;

	private CmsContentManager() {
		this.setTableName("cms_content");
		this.setTypeName("CmsContent");
	}

	public static CmsContentManager getInstance() {
		if (_instance == null) {
			_instance = new CmsContentManager();
		}
		return _instance;
	}

	/*
	 * 添加,传入SqlSession
	 */
	public int add(CmsContent dataEntity, ArrayList<CmsContent> lstCmsContent) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		String groupCode = "";
		String alSql = "";
		try {
			// 添加栏目后，返回栏目的id，添加所属栏目组信息
			int newCmsContentId = addCmsContent(sqlSession, dataEntity);
			sqlSession.commit();
			return newCmsContentId;
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
	 * 添加,传入SqlSession
	 */
	public int addCmsContent(SqlSession sqlSession, CmsContent dataEntity)
			throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException,
			NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
		dataEntity.setDisplayOrder(CmsChannelManager.getInstance().getDispalyOrderWhenAdd(sqlSession, "cms_content",
				" cms_channel_id =" + dataEntity.getCmsChannelId(), "Behind"));
		System.out.println(dataEntity.getDisplayOrder());
		int newCmsContentId = super.add(sqlSession, dataEntity);
		return newCmsContentId;
	}

	/*
	 * 修改,传入SqlSession
	 */
	public int update(DataEntity dataEntity, ArrayList<CmsContent> lstCmsContent) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		String groupCode = "";
		String alSql = "";
		try {
			// 添加栏目后，返回栏目的id，添加所属栏目组信息
			int newCmsContentId = super.update(sqlSession, dataEntity);
			sqlSession.commit();
			return newCmsContentId;
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
	 * 删除
	 */
	public int delete(int id, ArrayList<CmsContent> lstCmsContent) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			int deleteId = delete(sqlSession, id);
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

	/*
	 * 删除,传入SqlSession
	 */
	public int delete(SqlSession sqlSession, int id) throws Exception {
		String sql = "select cms_channel_id from cms_content where id = " + id;
		int cmsChannelId = sqlSession.selectOne("CmsChannel.selectIntBySql", sql);
		CmsChannelManager.getInstance().setDispalyOrderWhenDelete(sqlSession, id, "cms_content",
				"cms_channel_id=" + cmsChannelId);
		// 删除对应多语言内容
		deleteCmsContentLanguage(sqlSession, id);
		int deleteId = super.delete(sqlSession, "cms_content", id);
		return deleteId;
	}

	/*
	 * 根据channelId删除,传入SqlSession
	 */
	public int deleteByCmsChannelId(SqlSession sqlSession, int cmsChannelId) throws Exception {
		List<CmsContent> lstCmsChannelId = this.getListByCmsChannelId(sqlSession, cmsChannelId);
		int result = 0;
		for (int i = 0; i < lstCmsChannelId.size(); i++) {
			delete(sqlSession, lstCmsChannelId.get(i));
			result++;
		}
		return result;
	}

	/*
	 * 取得实体
	 */
	public CmsContent getEntity(int cmsContentId) throws Exception {
		return (CmsContent) super.getDataEntity(cmsContentId);
	}

	/*
	 * 取得实体,传入sqlSession
	 */
	public CmsContent getEntity(SqlSession sqlSession, int cmsContentId) throws SQLException, IOException {
		String typeName = this.getTypeName();
		String sqlCommandID = typeName + ".select" + typeName;
		CmsContent dataEntity = (CmsContent) sqlSession.selectOne(sqlCommandID, cmsContentId);
		return dataEntity;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<CmsContent> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	public int update(String tableName, int id) throws Exception {

		int i = MyBatisManager.getInstance()
				.executeUpdate("update cms_content set cms_content_status_code='Approving' where id=" + id + " ");

		return i;
	}

	/*
	 * 根据ChannelID获取循环内容
	 */
	public List<CmsContent> getContentList(int cmsChannelId) {
		SqlSession sqlSession;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			return sqlSession.selectList("CmsContent.selectEntityByCmsChannelId", cmsChannelId);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 根据ChannelID获取循环内容,传入sqlsession
	 */
	public List<CmsContent> getContentList(SqlSession sqlSession, int cmsChannelId) {
		return sqlSession.selectList("CmsContent.selectEntityByCmsChannelId", cmsChannelId);
	}

	/*
	 * 根据ChannelID获取循环内容ID,传入sqlsession
	 */
	public List<Integer> getContentIdList(int cmsChannelId) throws Exception {
		List<CmsContent> lstCmsContent = this.getListByCmsChannelId(cmsChannelId);
		List<Integer> lstCmsContentId = new ArrayList<>();
		for (CmsContent currentCmsContent : lstCmsContent) {
			lstCmsContentId.add(currentCmsContent.getId());
		}
		return lstCmsContentId;
	}

	/*
	 * 根据ChannelID获取循环内容ID
	 */
	public List<Integer> getContentIdListByCmsChannelIdList(List<Integer> cmsChannelIdList) throws Exception {
		String cmsChannelIds = StringHelper.Join(cmsChannelIdList);
		String condition = "cms_channel_id in (" + cmsChannelIds + ")";
		List<CmsContent> lstCmsContent = this.getList(condition);
		List<Integer> lstCmsContentId = new ArrayList<>();
		for (CmsContent currentCmsContent : lstCmsContent) {
			lstCmsContentId.add(currentCmsContent.getId());
		}
		return lstCmsContentId;
	}

	// 删除内容时删除对应多语言内容（cmsContentLanguage）
	public Integer deleteCmsContentLanguage(SqlSession sqlSession, Integer cmsContentId) {
		Integer result = CmsContentLanguageManager.getInstance().deleteByCmsContentId(sqlSession, cmsContentId);
		if (result > -1) {
			return 1;
		}
		return 0;
	}

	// 删除内容时删除对应多语言内容（cmsContentLanguage）
	public Integer deleteCmsContentLanguage(SqlSession sqlSession, List<Integer> cmsContentIds) {
		Integer result = 0;
		for (int i = 0; i < cmsContentIds.size(); i++) {
			result += deleteCmsContentLanguage(sqlSession, cmsContentIds.get(i));
		}
		return result;
	}

	// 新增内容时，新增对应多语言内容（cmsContentLanguage）
	public Integer addCmsContentLanguageWhenAdd(SqlSession sqlSession, Integer cmsContentId) {

		return null;
	}

	@Override
	public int deleteByCmsContentStatusCode(SqlSession sqlSession, String code) {
		try {
			String condition = String.format("cms_content_status_code='%s'", code);
			return deleteByCondition(sqlSession, condition);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}

	}
}