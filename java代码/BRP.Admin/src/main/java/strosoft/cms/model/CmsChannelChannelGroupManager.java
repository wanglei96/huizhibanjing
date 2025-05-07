package strosoft.cms.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.cms.data.CmsChannelChannelGroup;
import strosoft.cms.data.CmsChannelChannelGroupMaster;

public class CmsChannelChannelGroupManager extends CmsChannelChannelGroupMaster {
	private static CmsChannelChannelGroupManager _instance;

	private CmsChannelChannelGroupManager() {
		this.setTableName("cms_channel_channel_group");
		this.setTypeName("CmsChannelChannelGroup");
	}

	public static CmsChannelChannelGroupManager getInstance() {
		if (_instance == null) {
			_instance = new CmsChannelChannelGroupManager();
		}
		return _instance;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<CmsChannelChannelGroup> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	/*
	 * 根据cmsChannelId删除所属栏目组信息
	 */
	public int deleteByCmsChannelId(SqlSession sqlSession, int cmsChannelId) throws IOException {
		int result = sqlSession.delete("CmsChannelChannelGroup.deleteCmsChannelChannelGroupByCmsChannelId",
				cmsChannelId);
		return result;
	}

	/*
	 * 根据cmsChannelId获取所属栏目组信息
	 */
	public List<CmsChannelChannelGroup> getByCmsChannelId(SqlSession sqlSession, int cmsChannelId) throws IOException {
		List<CmsChannelChannelGroup> result = sqlSession
				.selectList("CmsChannelChannelGroup.selectCmsChannelChannelGroupByCmsChannelId", cmsChannelId);
		return result;
	}
}