package strosoft.cms.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.ClassHelper;
import strosoft.cms.data.ViewCmsChannel;
import strosoft.cms.data.ViewCmsChannelInfo;
import strosoft.cms.data.ViewCmsChannelLanguage;

public class ViewCmsChannelInfoManager extends IdDataManager {

	private static ViewCmsChannelInfoManager _instance;

	public static ViewCmsChannelInfoManager getInstance() {
		if (_instance == null) {
			_instance = new ViewCmsChannelInfoManager();
		}
		return _instance;
	}

	// 获取多语言栏目列表；
	public HashMap<String, ViewCmsChannelInfo> getViewCmsChannelInfoListByCmsChannelId(SqlSession sqlSession,
			Integer cmsChannelId, Integer cmsSiteId)
			throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			InstantiationException, SecurityException, NoSuchMethodException {
		HashMap<String, ViewCmsChannelInfo> viewCmsChannelInfoMap = new HashMap<String, ViewCmsChannelInfo>();
		String condition = "id = " + cmsChannelId;
		// 判断是否有多语言支持
		Boolean isOnCmslaunguage = CmsLanguageManager.getInstance().getIsOnCmslaunguage(cmsSiteId);
		if (isOnCmslaunguage) {
			List<ViewCmsChannelLanguage> lstViewCmsChannelLanguage = ViewCmsChannelLanguageManager.getInstance()
					.getList(sqlSession, condition);
			for (int i = 0; i < lstViewCmsChannelLanguage.size(); i++) {
				ViewCmsChannelInfo viewCmsChannelInfo = (ViewCmsChannelInfo) ClassHelper
						.copyObject(ViewCmsChannelInfo.class, lstViewCmsChannelLanguage.get(i));
				String cmsLanguageCode = viewCmsChannelInfo.getCmsLanguageCode();
				viewCmsChannelInfoMap.put(cmsLanguageCode, viewCmsChannelInfo);
			}
		} else {
			List<ViewCmsChannel> lstViewCmsChannel = ViewCmsChannelManager.getInstance().getList(sqlSession, condition);
			for (int i = 0; i < lstViewCmsChannel.size(); i++) {
				ViewCmsChannelInfo viewCmsChannelInfo = (ViewCmsChannelInfo) ClassHelper
						.copyObject(ViewCmsChannelInfo.class, lstViewCmsChannel.get(i));
				viewCmsChannelInfoMap.put("Default", viewCmsChannelInfo);
			}
		}
		return viewCmsChannelInfoMap;
	}

	// 根据条件获取多语言栏目列表
	public List<ViewCmsChannelInfo> getViewCmsChannelInfoListByCondition(SqlSession sqlSession, String condition,
			Integer cmsSiteId) throws IOException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException {
		List<ViewCmsChannelInfo> lstViewCmsChannelInfo = new ArrayList<ViewCmsChannelInfo>();
		// 判断是否有多语言支持
		Boolean isOnCmslaunguage = CmsLanguageManager.getInstance().getIsOnCmslaunguage(cmsSiteId);
		if (isOnCmslaunguage) {
			List<ViewCmsChannelLanguage> lstViewCmsChannelLanguage = ViewCmsChannelLanguageManager.getInstance()
					.getList(sqlSession, condition);
			for (int i = 0; i < lstViewCmsChannelLanguage.size(); i++) {
				ViewCmsChannelInfo viewCmsChannelInfo = (ViewCmsChannelInfo) ClassHelper
						.copyObject(ViewCmsChannelInfo.class, lstViewCmsChannelLanguage.get(i));
				lstViewCmsChannelInfo.add(viewCmsChannelInfo);
			}
		} else {
			List<ViewCmsChannel> lstViewCmsChannel = ViewCmsChannelManager.getInstance().getList(sqlSession, condition);
			for (int i = 0; i < lstViewCmsChannel.size(); i++) {
				ViewCmsChannelInfo viewCmsChannelInfo = (ViewCmsChannelInfo) ClassHelper
						.copyObject(ViewCmsChannelInfo.class, lstViewCmsChannel.get(i));
				lstViewCmsChannelInfo.add(viewCmsChannelInfo);
			}
		}
		return lstViewCmsChannelInfo;
	}

	// 根据条件获取多语言栏目列表
	public List<ViewCmsChannelInfo> getViewCmsChannelInfosByConditiond(String condition, Integer cmsSiteId)
			throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			InstantiationException, SecurityException, NoSuchMethodException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		return getViewCmsChannelInfoListByCondition(sqlSession, condition, cmsSiteId);
	}

	// 获取多语言栏目列表
	public List<ViewCmsChannelInfo> getViewCmsChannelInfoList(Integer cmsSiteId) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		String condition = " 1=1 ";
		List<ViewCmsChannelInfo> viewCmsChannelInfos = getViewCmsChannelInfoListByCondition(sqlSession, condition,
				cmsSiteId);
		/*
		 * //获取对应栏目组 String sql = "SELECT\n" + "	cccg.cms_channel_id,\n" +
		 * "	cccg.cms_channel_group_code,\n" + "	ccg.`name`\n" + "FROM\n" +
		 * "	cms_channel_channel_group AS cccg,\n" + "	cms_channel_group AS ccg\n"
		 * + "WHERE\n" + "	cccg.cms_channel_group_code = ccg.`code`";
		 * ArrayList<LinkedHashMap<String, Object>> cmsChannelGroups =
		 * MyBatisManager.getInstance().executeHashMapList(sql); for(int
		 * j=0;j<viewCmsChannelInfos.size();j++){ ViewCmsChannelInfo
		 * currentViewCmsChannelInfo = viewCmsChannelInfos.get(j); Integer
		 * currentCmsChannelId = currentViewCmsChannelInfo.getId(); List<String>
		 * cmsChannelGroupCodeList = new ArrayList<String>(); List<String>
		 * cmsChannelGroupNameList = new ArrayList<String>(); for(int
		 * i=0;i<cmsChannelGroups.size();i++){ LinkedHashMap<String, Object>
		 * currentCmsChannelGroup = cmsChannelGroups.get(i); Integer cmsChannelId =
		 * (Integer) currentCmsChannelGroup.get("cms_channel_id");
		 * if(currentCmsChannelId.equals(cmsChannelId)){ String cmsChannelCode =
		 * (String) currentCmsChannelGroup.get("cms_channel_group_code"); String
		 * cmsChannelName = (String) currentCmsChannelGroup.get("name");
		 * cmsChannelGroupCodeList.add(cmsChannelCode);
		 * cmsChannelGroupNameList.add(cmsChannelName); } }
		 * currentViewCmsChannelInfo.setCmsChannelGroupCodeList(cmsChannelGroupCodeList)
		 * ;
		 * currentViewCmsChannelInfo.setCmsChannelGroupNameList(cmsChannelGroupNameList)
		 * ; }
		 */
		return viewCmsChannelInfos;
	}

}
