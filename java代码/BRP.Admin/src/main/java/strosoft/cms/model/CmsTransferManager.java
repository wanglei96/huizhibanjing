package strosoft.cms.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.CmsContent;


@SuppressWarnings("unchecked")
public class CmsTransferManager extends IdDataManager {
	private static CmsTransferManager _instance;

	public static CmsTransferManager getInstance() {
		if (_instance == null) {
			_instance = new CmsTransferManager();
		}
		return _instance;
	}

	private CmsTransferManager() {
		
	}

	/*
	 * 根据ID获取内容
	 */
	
	public CmsContent getCmsContentById(String id) throws IOException {
		
		SqlSession sqlSession=MyBatisManager.getInstance().openSession();
		CmsContent cmsContent = sqlSession.selectOne("selectCmsContent", id);
		sqlSession.close();
		return cmsContent;
	}
	/*
	 * 根据ID获取栏目
	 */
	
	public CmsChannel getCmsChannelById(int id) throws  Exception {
		return CmsChannelManager.getInstance().getEntity(id);
	}
	
	/*
	 * 查询所有 所属栏目 的内容
	 */
	
	public List<CmsContent> getContentByChannelList(String cmsChannelId) throws ClassNotFoundException, SQLException, IOException {
		
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		List<CmsContent> list = sqlSession.selectList("selectCmsContentByCmsChannelId", cmsChannelId);
		sqlSession.close();
		return list;
		
	}

	/*
	 * 添加一个栏目
	 */
	public void addChannel(CmsChannel cmsChanel) throws IOException {
		
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		
		sqlSession.insert("insertCmsChannel", cmsChanel);
		sqlSession.commit();
		
		sqlSession.close();
		
	}
	/*
	 * 添加一个栏目
	 */
	public void addContent(CmsContent cmsContent) throws IOException {
		
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		
		sqlSession.insert("insertCmsContent", cmsContent);
		sqlSession.commit();
		
		sqlSession.close();
		
	}
	
	/*
	 * 删除一个栏目
	 */
	public void deleteChannel(String id) throws IOException {
		
		
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		
		sqlSession.delete("deleteCmsChannel", id);
		sqlSession.commit();
		
		sqlSession.close();
	}
	
	/*
	 * 删除一个栏目
	 */
	public void deleteContent(String id) throws IOException {
		
		
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		
		sqlSession.delete("deleteCmsContent", id);
		sqlSession.commit();
		
		sqlSession.close();
	}
	
	
}
